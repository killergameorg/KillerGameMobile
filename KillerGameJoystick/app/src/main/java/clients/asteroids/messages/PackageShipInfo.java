package clients.asteroids.messages;

import java.io.Serializable;

public class PackageShipInfo implements Serializable {
    private static final long serialVersionUID = 2167L;

    private int life;
    private int teamScore;


    public PackageShipInfo(int life, int teamScore) {
        this.life = life;
        this.teamScore = teamScore;
    }

    public int getLife() {
        return life;
    }

    public int getScore() {
        return teamScore;
    }
}
