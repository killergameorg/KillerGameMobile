package clients.asteroids.messages;

import java.io.Serializable;

public class AccelerateShipMessage implements Serializable {

	private static final long serialVersionUID = 6788857672671689167L;

	public int shipId;
	public boolean accelerate;
	
}
