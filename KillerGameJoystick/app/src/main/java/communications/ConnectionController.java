package communications;

import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import communications.frames.CloseFrame;
import communications.frames.Frame;
import communications.frames.MessageFrame;
import communications.frames.PingAckFrame;
import communications.frames.Frame;

public class ConnectionController {

    String TAG = this.getClass().getName();

    private class ConnectionData {

        Socket socket;
        ObjectInputStream inStream;
        ObjectOutputStream outStream;

        public ConnectionData(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream) {
            this.socket = socket;
            this.inStream = inStream;
            this.outStream = outStream;
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) {
                return true;
            }

            if(!(other instanceof ConnectionData)) {
                return false;
            }

            return this.socket.getInetAddress().getHostAddress()
                    .equals(((ConnectionData) other).socket.getInetAddress().getHostAddress());
        }
    }

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(ConnectionController.class.getClass().getName());

    private String serverIp;
    private int serverPort;

    private P2PCommListener commListener;
    private Map<String, ConnectionData> connectedPeers;
    private Map<String, Long> peerMessageControl;
    private List<String> reconnectionPeers;
    private String localIp;

    private long messageId;

    public ConnectionController(String ip, int port) {

        serverIp = ip;
        serverPort = port;

        reconnectionPeers = new ArrayList<>();
        reconnectionPeers.add(serverIp);

        connectedPeers = new Hashtable<>();
        peerMessageControl = new Hashtable<>();

    }

    void addConnection(Socket socket) throws IOException {
        Log.d(TAG, "addConnection() called with: socket = [" + socket.getLocalAddress() + "]");
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        ConnectionData connection = new ConnectionData(socket, in, out);
        String remoteIp = socket.getInetAddress().getHostAddress();
        if(!connectedPeers.containsKey(remoteIp)) {
            setLocalIp(socket.getLocalAddress().getHostAddress());
            connectedPeers.put(remoteIp, connection);
            // Inicia un hilo para recibir objetos entrantes del cliente destino
            Thread hilo = new Thread(new ConnectionManager(this, getLocalIp(), remoteIp, in));
            hilo.start();
            if(commListener != null) commListener.onNewConnection(socket.getInetAddress().getHostAddress());
        } else {
            try {
                socket.close();
            } catch (IOException e) {}
        }
    }

    void closePeer(String ip) {
        killConnection(ip, true);
    }

    private void connect(String ip) throws IOException {
        Log.d(TAG, "connect() called with: ip = [" + ip + "]");
        addConnection(new Socket(ip, serverPort));
    }

    boolean controlPeerMessageId(String ip, long messageId) {
        if(!peerMessageControl.containsKey(ip) || peerMessageControl.get(ip) < messageId) {
            peerMessageControl.put(ip, messageId);
            return true;
        }
        return false;
    }

    private Frame generateMessageFrame(String ip) {
        Frame frame = new MessageFrame();
        frame.setHeader(getMessageId(), getLocalIp(), ip);
        return frame;
    }

    private String getLocalIp() {
        return localIp;
    }

    private synchronized long getMessageId() {
        return messageId++;
    }

    public void initialize() {
		/*Thread hiloServidor;
		try {
			hiloServidor = new Thread(new ServerManager(this, serverPort));
			hiloServidor.start();
		} catch (IOException e) {
			LOGGER.warning("[FATAL] No se puede crear el socket de servidor. La aplicación se va a cerrar.");
			e.printStackTrace();
			System.exit(-1);
		}*/

        Thread hiloReconector = new Thread(() -> {
            while(true) {
                if(reconnectionPeers.size() > connectedPeers.size()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        reconnectionPeers.forEach(ip -> {
                            try {
                                if(!connectedPeers.containsKey(ip)) {
                                    connect(ip);
                                }
                            } catch (IOException e) {
                                Log.e(TAG, e.getMessage());
                            }
                        });
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        });
        hiloReconector.start();
    }

    void killConnection(String ip, boolean intentional) {
        if(connectedPeers.containsKey(ip)) {
            ConnectionData peer = connectedPeers.get(ip);
            try {
                peer.socket.close();
            } catch (IOException e) {}
            connectedPeers.remove(ip);
            resetPeerMessageId(ip);
        }
        if(commListener != null) {
            if(intentional) {
                reconnectionPeers.remove(ip);
                commListener.onConnectionClosed(ip);
            } else {
                commListener.onConnectionLost(ip);
            }
        }
    }

    void pushMessage(String sourceIp, Object payload) {
        if(commListener != null) commListener.onIncomingMessage(sourceIp, payload);
    }

    void resetPeerMessageId(String ip) {
        if(peerMessageControl.containsKey(ip)) {
            peerMessageControl.put(ip, -1L);
        }
    }

    void responsePing(String peerIp) {
        Frame response = new PingAckFrame();
        response.setHeader(getMessageId(), getLocalIp(), peerIp);
        send(response);
    }

    void send(Frame frame) {
        String ip = frame.getTargetIp();
        if(connectedPeers.containsKey(ip)) {
            sendMessage(connectedPeers.get(ip), frame);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectedPeers.forEach((ipPeer, conn) -> {
                    sendMessage(conn, frame);
                });
            }
        }
    }

    public void sendDisconnectionAdvise() {
        Frame frame = new CloseFrame();
        frame.setHeader(getMessageId(), getLocalIp(), "*");
        send(frame);
    }

    public void sendFlood(Object message) {
        Frame frame = generateMessageFrame("*");
        frame.setPayload(message);
        send(frame);
    }

    private void sendMessage(ConnectionData connection, Frame frame) {
        new Thread(() -> {
            try {
                ObjectOutputStream out = connection.outStream;
                out.writeObject(frame);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                killConnection(frame.getTargetIp(), false);
            }
        }).start();
    }

    public void sendPrivate(String ip, Object message) {
        Log.d(TAG, "sendPrivate() called with: ip = [" + ip + "], message = [" + message + "]");
        Frame frame = generateMessageFrame(ip);
        frame.setPayload(message);
        send(frame);
    }

    public void setCommListener(P2PCommListener commListener) {
        this.commListener = commListener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectedPeers.forEach((peerIp, connection) -> {
                Log.d(TAG, "setCommListener() called with: commListener = [" + commListener + "]");
                commListener.onNewConnection(peerIp);
            });
        }
    }

    private void setLocalIp(String ip) {
        if(localIp == null || !localIp.equals(ip)) {
            localIp = ip;
        }
    }

    public String getServerIp() {
        return serverIp;
    }
}