package clients.asteroids.messages;

import java.io.Serializable;

public class PackageJoystick implements Serializable {
    private static final long serialVersionUID = 9167L;

    public boolean rotateLeft;
    public boolean rotateRight;
    public boolean accelerate;
    public boolean shoot;

    public PackageJoystick(boolean rotateLeft, boolean rotateRight, boolean accelerate, boolean shoot) {
        this.rotateLeft = rotateLeft;
        this.rotateRight = rotateRight;
        this.accelerate = accelerate;
        this.shoot = shoot;
    }


}
