package clients.asteroids;

import android.util.Log;

import clients.asteroids.messages.PackageGameState;
import clients.asteroids.messages.PackageShipInfo;
import clients.asteroids.messages.PackageShipMobile;
import communications.AndroidHandler;
import communications.ConnectionController;
import communications.P2PCommListener;

public class AsteroidsController implements P2PCommListener {

    final String TAG = this.getClass().getName();
    private ConnectionController comm;

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

        if(message instanceof PackageShipMobile){
            if(isPackageShipInfo((PackageShipMobile) message)){
                // TODO: por hacer el seteo de dto
            }
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
        /*
        IdMessage idMessage = new IdMessage();
        idMessage.id = id;
        comm.sendPrivate(ip, idMessage);
    */}

    // TODO
    public void sendShipControlMessage(Object message) {
        comm.sendFlood(message);
    }

    public void setComm(ConnectionController comm) {
        Log.d(TAG, "setComm() called with: comm = [" + comm + "]");
        this.comm = comm;
    }

}
