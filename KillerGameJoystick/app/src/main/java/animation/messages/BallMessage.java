package animation.messages;

import java.io.Serializable;

import animation.Ball;
import animation.Edge;

public class BallMessage implements Serializable {

	private static final long serialVersionUID = 4941127940403274015L;

	public Edge from;
	public Ball ball;

}
