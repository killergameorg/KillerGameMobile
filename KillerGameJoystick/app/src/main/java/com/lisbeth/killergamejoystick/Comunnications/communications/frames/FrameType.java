package com.lisbeth.killergamejoystick.Comunnications.communications.frames;

/**
 * The FrameType enum represents the type of frame that can be sent over the network.
 * The available types are PING, PING_ACK, and MESSAGE.
 * @author Miquel A. Fuster
 */
public enum FrameType {
	PING,
	PING_ACK,
	CLOSE,
	MESSAGE
}
