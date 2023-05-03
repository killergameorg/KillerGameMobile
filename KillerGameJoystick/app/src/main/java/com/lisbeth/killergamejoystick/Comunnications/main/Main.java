package com.lisbeth.killergamejoystick.Comunnications.main;

import com.lisbeth.killergamejoystick.Comunnications.clients.automessenger.Messenger;
import com.lisbeth.killergamejoystick.Comunnications.communications.ConnectionController;

public class Main {
	
	public static void main(String[] args) {
		startAutoMessenger();
	}

	@SuppressWarnings("unused")
	static private void startAutoMessenger() {
		ConnectionController conn = new ConnectionController();
		Messenger messenger = new Messenger();
		messenger.setComm(conn);
		conn.setCommListener(messenger);
		conn.initialize();
	}

}
