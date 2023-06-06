package clients.asteroids.messages;

public class PackageMobileSetAttributes implements PackageMobileCommunications {

    private String ip;
    private int id;
    private boolean isMaster;
    private Team team;

    public PackageMobileSetAttributes(String ip, int id, boolean isMaster, Team team) {
        this.setIp(ip);
        this.setId(id);
        this.setMaster(isMaster);
        this.setTeam(team);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
