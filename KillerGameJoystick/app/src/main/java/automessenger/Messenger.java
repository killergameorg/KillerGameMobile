package automessenger;

import android.os.Build;
import android.util.Log;

import java.util.Hashtable;

import communications.ConnectionController;
import communications.P2PCommListener;

public class Messenger implements P2PCommListener {
	static final String TAG = "CCMM";

	private ConnectionController comm;
	private Hashtable<String, Integer> connectedPeers;
	
	public Messenger() {
		connectedPeers = new Hashtable<>();
	}
	
	private void senderBucle() {
		while(true) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				connectedPeers.forEach((ip, n) -> {
					Log.d(TAG, "senderBucle() called [" + ip + "," + n + "]");
					comm.sendPrivate(ip, String.valueOf(n));
					connectedPeers.put(ip, ++n);
				});
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void onIncomingMessage(String ip, Object object) {
		Log.i(TAG, "IncomingMessage() From [" + ip + "] " + (String)object);
	}

	@Override
	public void onConnectionClosed(String ip) {
		Log.i(TAG, "onConnectionClosed: [" + ip + "] " + "cerró su conexión");
		connectedPeers.remove(ip);
	}

	@Override
	public void onConnectionLost(String ip) {
		Log.i(TAG, "onConnectionLost: [" + ip + "] " + "conexión cerrada de forma inesperada");
		connectedPeers.remove(ip);
	}

	@Override
	public void onNewConnection(String ip) {
		Log.i(TAG, "onNewConnection: [" + ip + "] " + "conectado");
		connectedPeers.put(ip, 0);
	}
	
	public void setComm(ConnectionController comm) {
		Log.d(TAG, "setComm() called with: comm = [" + comm + "]");
		this.comm = comm;
		new Thread(this::senderBucle).start();
	}

}
