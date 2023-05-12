package clients.animation;

import java.io.Serializable;
import java.util.logging.Logger;

public class Ball implements Serializable {

	private static final long serialVersionUID = -4238822915986025242L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Ball.class.getClass().getName());

	private int x;
	private int y;
	private int dx;
	private int dy;
	private int diameter;
	private int red;
	private int green;
	private int blue;
	
	public Ball(int x, int y, int dx, int dy, int msRefresh) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;

		diameter = 30;
	}

	@Override
	public String toString() {
		return "p(" + x + "," + y + ") | v(" + dx + "," + dy + ")";
	}

}
