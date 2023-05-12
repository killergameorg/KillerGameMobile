package clients.animation;

import android.util.Log;

import communications.ConnectionController;
import communications.P2PCommListener;

public class AnimationController implements P2PCommListener {

	String TAG = this.getClass().getName();

	private ConnectionController comm;

	public AnimationController() {
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
	}

	@Override
	public void onNewConnection(String ip) {
		Log.d(TAG, "onNewConnection() called with: ip = [" + ip + "]");
	}

	private void removeNeighbour(String ip) {
	}

	public void sendFlood(Object message) {
		comm.sendFlood(message);
	}

	public void sendPrivate(String ip, Object message) {
		comm.sendPrivate(ip, message);
	}

	public void setComm(ConnectionController comm) {
		this.comm = comm;
	}

}
