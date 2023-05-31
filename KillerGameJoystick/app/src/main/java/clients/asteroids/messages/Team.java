package clients.asteroids.messages;


import java.io.Serializable;

public class Team implements Serializable {

    private static final long serialVersionUID = 934535656137L;

    private TeamName teamName;
    private int score;

    public Team(TeamName teamName) {
        this.setTeamName(teamName);
        this.setScore(0);
    }


    public TeamName getTeamName() {
        return teamName;
    }


    public void setTeamName(TeamName teamName) {
        this.teamName = teamName;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}