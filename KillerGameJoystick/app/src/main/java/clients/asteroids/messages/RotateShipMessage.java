package clients.asteroids.messages;

import java.io.Serializable;

public class RotateShipMessage implements Serializable {

	private static final long serialVersionUID = -8767567833009851847L;
	
	public int shipId;
	public int rotation;

}
