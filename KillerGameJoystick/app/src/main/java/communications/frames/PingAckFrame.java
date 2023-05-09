package communications.frames;

/**
 * Represents a ping acknowledgement frame that extends the {@link Frame} class.
 * The {@code PingAckFrame} class represents a specific type of frame that is used to
 * acknowledge the receipt of a ping request. It inherits from the {@code Frame} class
 * and provides a specialized implementation for the {@link FrameType#PING_ACK} type.
 * Instances of this class are created using the default constructor, which sets the frame type
 * to {@link FrameType#PING_ACK}.
 * @see Frame
 * @author Miquel A. Fuster
 */
public class PingAckFrame extends Frame {
	
	private static final long serialVersionUID = -5514130143056617042L;
	
	/**
	 * Constructs a new {@code PingAckFrame} instance with the frame type set to {@link FrameType#PING_ACK}.
	 */
	public PingAckFrame() {
		super(FrameType.PING_ACK);
		super.setPayload("");
	}
	
	/**
	 * There is no payload for a ping acknowledgement frame, so this method does nothing.
	 * @param payload ignored payload
	 */
	public void setPayload(String payload) {
		// Theres no need of payload in a Ping
	}
	
}
