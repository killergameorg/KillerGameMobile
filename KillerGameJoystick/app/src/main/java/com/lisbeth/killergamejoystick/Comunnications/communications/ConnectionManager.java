package com.lisbeth.killergamejoystick.Comunnications.communications;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.lisbeth.killergamejoystick.Comunnications.communications.frames.Frame;

class ConnectionManager implements Runnable {

	private boolean active;
	private ConnectionController controller;
	private ObjectInputStream in;
	private String localIp;
	private String remoteIp;

	public ConnectionManager(ConnectionController controller, String localIp, String remoteIp, ObjectInputStream in) {
		this.localIp = localIp;
		this.remoteIp = remoteIp;
		this.in = in;
		this.controller = controller;
		active = true;
	}

	private void handleFrame(Frame frame) {
		if(controller.controlPeerMessageId(frame.getSourceIp(), frame.getId())) {
			switch(frame.getFrameType()) {
			case MESSAGE:
				// El paquete es nuestro. Lo matamos
				if(frame.getSourceIp().equals(localIp)) return;
				// El paquete va para nosotros o es un flood. Enviar el payload y la remoteIp de origen al controlador para tratarlo.
				if(frame.getTargetIp().equals(localIp) || frame.getTargetIp().equals("*")) {
					controller.pushMessage(frame.getSourceIp(), frame.getPayload());
				}
				// Reenviar si no es para nosotros
				if(!frame.getTargetIp().equals(localIp)) {
					controller.send(frame);
				}
				break;
			case PING:
				controller.responsePing(frame.getSourceIp());
				break;
			case PING_ACK:
				break;
			case CLOSE:
				controller.closePeer(frame.getSourceIp());
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void run() {
		try {
			while (active) {
				Object objetoRecibido = in.readObject();
				if (objetoRecibido instanceof Frame) {
					Frame frame = (Frame) objetoRecibido;
					handleFrame(frame);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			controller.killConnection(remoteIp, false);
			active = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void stop() {
		active = false;
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
