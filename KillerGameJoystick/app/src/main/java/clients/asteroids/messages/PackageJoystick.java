package clients.asteroids.messages;

import java.io.Serializable;


public class PackageJoystick implements Serializable {
    private static final long serialVersionUID = 91357267L;

    public boolean rotateLeft;
    public boolean rotateRight;
    public boolean accelerate;
    public boolean shoot;

    public PackageJoystick(
            boolean rotateLeft, boolean rotateRight,
            boolean accelerate, boolean shoot

    ) {
        this.rotateLeft = rotateLeft;
        this.rotateRight = rotateRight;
        this.accelerate = accelerate;
        this.shoot =  shoot;
    }

    /**
     * @return the rotateLeft
     */
    public boolean isRotateLeft() {
        return rotateLeft;
    }

    /**
     * @return the rotateRight
     */
    public boolean isRotateRight() {
        return rotateRight;
    }

    /**
     * @return the accelerate
     */
    public boolean isAccelerate() {
        return accelerate;
    }

    /**
     * @return the shoot
     */
    public boolean isShoot() {
        return shoot;
    }

}
