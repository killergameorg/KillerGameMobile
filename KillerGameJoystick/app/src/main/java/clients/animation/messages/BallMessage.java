package clients.animation.messages;

import java.io.Serializable;

import clients.animation.Ball;
import clients.animation.Edge;

public class BallMessage implements Serializable {

	private static final long serialVersionUID = 4941127940403274015L;

	public Edge from;
	public Ball ball;

}
