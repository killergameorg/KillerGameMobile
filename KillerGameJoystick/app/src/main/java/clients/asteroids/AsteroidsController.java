package clients.asteroids;

import android.util.Log;

import clients.asteroids.KnowNewConnection.KnowNewConnectionController;
import clients.asteroids.messages.PackageGameState;
import clients.asteroids.messages.PackageShipMobile;
import clients.asteroids.messages.packagesToKnow.OptionsPackageAsk;
import clients.asteroids.messages.packagesToKnow.PackageAsk;
import communications.AndroidHandler;
import communications.ConnectionController;
import communications.P2PCommListener;

public class AsteroidsController implements P2PCommListener {

    final String TAG = this.getClass().getName();
    private ConnectionController comm;
    private ClusterCommunicationController cluster;
    private KnowNewConnectionController knowNewConnectionController;

    public AsteroidsController() {
        Log.d(TAG, "AsteroidsController() called");
        this.knowNewConnectionController = new KnowNewConnectionController();
    }
    @Override
    public void onConnectionClosed(String ip) {
        Log.d(TAG, "onConnectionClosed() called with: ip = [" + ip + "]");
        AndroidHandler.ControllerConfigActivity.finish();
    }

    @Override
    public void onConnectionLost(String ip) {
        Log.d(TAG, "onConnectionLost() called with: ip = [" + ip + "]");
        AndroidHandler.ControllerConfigActivity.finish();
    }

    @Override
    public void onIncomingMessage(String ip, Object message) {

        if(message instanceof PackageGameState){
            this.getCluster().setGameState(((PackageGameState) message).getGameState());
        }else if(message instanceof PackageAsk){
           this.getKnowNewConnectionController().manageKnowConnection((PackageAsk) message, this.getComm());
        }
        /*Log.d(TAG, "onIncomingMessage() called with: ip = [" + ip + "], message = [" + message + "]");


        if (message instanceof PackageShipMobile) {
            AndroidHandler.shipId = ((newShip) message).shipId;
            AndroidHandler.ConnectActivity.launchActivity(ActiveGameActivity.class);
        }*/
    }



    private boolean isPackageShipInfo(PackageShipMobile message){
        return message.getMessage() instanceof  PackageGameState;
    }

    @Override
    public void onNewConnection(String ip) {
        Log.d(TAG, "onNewConnection() called with: ip = [" + ip + "]");
  }

    // TODO
    public void sendShipControlMessage(Object message) {
        comm.sendFlood(message);
    }

    public void setComm(ConnectionController comm) {
        Log.d(TAG, "setComm() called with: comm = [" + comm + "]");
        this.comm = comm;
    }

    public void setCluster(ClusterCommunicationController cluster) {
        Log.d(TAG, "setCluster() called with: cluster = [" + cluster + "]");
        this.cluster = cluster;
    }

    public ClusterCommunicationController getCluster() {
        return cluster;
    }

    public void setKnowNewConnectionController(KnowNewConnectionController knowNewConnectionController) {
        this.knowNewConnectionController = knowNewConnectionController;
    }

    public KnowNewConnectionController getKnowNewConnectionController() {
        return knowNewConnectionController;
    }

    public ConnectionController getComm() {
        return comm;
    }
}
