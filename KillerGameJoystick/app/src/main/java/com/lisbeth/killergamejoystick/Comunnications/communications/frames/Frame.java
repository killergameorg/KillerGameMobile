package com.lisbeth.killergamejoystick.Comunnications.communications.frames;

import java.io.Serializable;

/**
 * The abstract base class for communication frames.
 * A frame represents a unit of data that is exchanged between two communication endpoints.
 * A frame consists of a header and a payload. The header contains metadata such as the unique
 * identifier of the frame, the time-to-live (TTL) value, and the source and target IP addresses.
 * The payload contains the actual data being exchanged.
 * This class provides methods to set and get the header and payload fields, as well as to decrement
 * the TTL value and determine whether the TTL has expired.
 * Concrete subclasses of this class implement specific types of frames, such as request frames,
 * response frames, or error frames, each with their own set of fields and behavior.
 * @author Miquel A. Fuster
 */
public abstract class Frame implements Serializable {

	private static final long serialVersionUID = 8929688478778125237L;
	
	private FrameType frameType;
	private Long id;
	private String sourceIp;
	private String targetIp;
	
	private Object payload;
	
	/**
	 * Constructs a new Frame object with the given frame type.
	 * @param frameType the type of the communication frame
	 */
	protected Frame(FrameType frameType) {
		this.frameType = frameType;
	}
	
	/**
	 * Sets the header fields of a packet with the provided values.
	 * @param id the unique identifier of the packet
	 * @param sourceIp the source IP address of the packet
	 * @param targetIp the target IP address of the packet
	 */
	public void setHeader(Long id, String sourceIp, String targetIp) {
		this.id = id;
		this.sourceIp = sourceIp;
		this.targetIp = targetIp;
	}
	
	/**
	 * Returns the ID of this packet.
	 * @return the ID of this packet
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Returns the type of the frame.
	 * @return the type of the frame
	 */
	public FrameType getFrameType() {
		return this.frameType;
	}
	
	/**
	 * Returns the payload of this frame.
	 * @return the payload of this frame
	 */
	public Object getPayload() {
		return payload;
	}
	
	/**
	 * Devuelve la IP de destino del communications.frame
	 * @return String con la ip de destion
	 */
	public String getSourceIp() {
		return sourceIp;
	}
	
	/**
	 * Returns the source IP address of this packet.
	 * @return the source IP address of this packet
	 */
	public String getTargetIp() {
		return targetIp;
	}
	
	/**
	 * Sets the payload of the current frame to the specified value.
	 * @param message the payload to set
	 */
	public void setPayload(Object message) {
		this.payload = message;
	}
}
