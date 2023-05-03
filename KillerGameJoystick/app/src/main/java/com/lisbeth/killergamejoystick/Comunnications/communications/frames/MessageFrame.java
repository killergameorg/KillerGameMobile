package com.lisbeth.killergamejoystick.Comunnications.communications.frames;

/**
 * Represents a message frame that extends the {@link Frame} class.
 * The {@code MessageFrame} class represents a specific type of frame that can be used to
 * send and receive messages between network devices. It inherits from the {@code Frame} class
 * and provides a specialized implementation for the {@link FrameType#MESSAGE} type.
 * Instances of this class are created using the default constructor, which sets the frame type
 * to {@link FrameType#MESSAGE}.
 * @see Frame
 * @author Miquel A. Fuster
 */
public class MessageFrame extends Frame {

	private static final long serialVersionUID = -2062082288195104049L;

	/**
	 * Constructs a new {@code MessageFrame} instance with the frame type set to {@link FrameType#MESSAGE}.
	 */
	public MessageFrame() {
		super(FrameType.MESSAGE);
	}
}
