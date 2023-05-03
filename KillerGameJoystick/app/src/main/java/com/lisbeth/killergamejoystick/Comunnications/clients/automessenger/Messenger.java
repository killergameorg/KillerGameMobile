package com.lisbeth.killergamejoystick.Comunnications.clients.automessenger;

import android.os.Build;

import com.lisbeth.killergamejoystick.Comunnications.communications.ConnectionController;

import java.util.Hashtable;

import com.lisbeth.killergamejoystick.Comunnications.communications.ConnectionController;
import com.lisbeth.killergamejoystick.Comunnications.communications.P2PCommListener;

public class Messenger implements P2PCommListener {

	private ConnectionController comm;
	private Hashtable<String, Integer> connectedPeers;
	
	public Messenger() {
		connectedPeers = new Hashtable<>();
	}
	
	private void senderBucle() {
		while(true) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				connectedPeers.forEach((ip, n) -> {
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
		System.out.println("[" + ip + "] " + (String)object);
	}

	@Override
	public void onConnectionClosed(String ip) {
		System.err.println("[" + ip + "] " + "cerró su conexión");
		connectedPeers.remove(ip);
	}

	@Override
	public void onConnectionLost(String ip) {
		System.err.println("[" + ip + "] " + "conexión cerrada de forma inesperada");
		connectedPeers.remove(ip);
	}

	@Override
	public void onNewConnection(String ip) {
		System.err.println("[" + ip + "] " + "conectado");
		connectedPeers.put(ip, 0);
	}
	
	public void setComm(ConnectionController comm) {
		this.comm = comm;
		new Thread(this::senderBucle).start();
	}

}
