package clients.asteroids;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import clients.asteroids.messages.IdMessage;
import clients.asteroids.messages.newShip;
import communications.AndroidHandler;
import communications.ConnectionController;
import communications.P2PCommListener;

public class AsteroidsController implements P2PCommListener{

	final String TAG = this.getClass().getName();

	private int id;

	private ConnectionController comm;

	public AsteroidsController() {
		id = 500;
	}

	@Override
	public void onConnectionClosed(String ip) {
		Log.d(TAG, "onConnectionClosed() called with: ip = [" + ip + "]");
	}

	@Override
	public void onConnectionLost(String ip) {
		Log.d(TAG, "onConnectionLost() called with: ip = [" + ip + "]");
	}

	@Override
	public void onIncomingMessage(String ip, Object message) {
		Log.d(TAG, "onIncomingMessage() called with: ip = [" + ip + "], message = [" + message + "]");
		if (message instanceof newShip) {
			AndroidHandler.shipId = ((newShip) message).shipId;
		}
	}

	@Override
	public void onNewConnection(String ip) {
		Log.d(TAG, "onNewConnection() called with: ip = [" + ip + "]");
		IdMessage idMessage = new IdMessage();
		idMessage.id = id;
		comm.sendPrivate(ip, idMessage);
	}

	public void sendShipControlMessage(Object message) {
		comm.sendFlood(message);
	}

	public void setComm(ConnectionController comm) {
		Log.d(TAG, "setComm() called with: comm = [" + comm + "]");
		this.comm = comm;
	}




}
