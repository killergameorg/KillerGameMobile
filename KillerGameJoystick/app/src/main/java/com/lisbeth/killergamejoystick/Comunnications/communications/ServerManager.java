package com.lisbeth.killergamejoystick.Comunnications.communications;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ServerManager implements Runnable {

	private boolean alive;
	private ConnectionController controller;
	private ServerSocket serverSocket;
	
	
	public ServerManager(ConnectionController controller, int serverPort) throws IOException {
		serverSocket = new ServerSocket(serverPort);
		this.controller = controller;
		alive = true;
	}
	
	public void run() {
		try {
			while (alive) {
				Socket socket = serverSocket.accept();
				controller.addConnection(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void stop() {
		alive = false;
		try {
			serverSocket.close();
		} catch (IOException e) {}
	}
	
}
