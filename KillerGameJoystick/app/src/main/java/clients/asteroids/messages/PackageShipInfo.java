package clients.asteroids.messages;

import java.io.Serializable;


public class PackageShipInfo implements Serializable {
    private static final long serialVersionUID = 2753167L;

    private int life;
    private Team team;

    public PackageShipInfo(int life, Team team) {
        this.life = life;
        this.team = team;
    }

    public int getLife() {
        return life;
    }

    /**
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

}
