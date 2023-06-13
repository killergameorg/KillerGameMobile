package DTO;

import clients.asteroids.AsteroidsController;
import clients.asteroids.messages.GameState;
import clients.asteroids.messages.Team;
import communications.AndroidHandler;

public class AccountInfo {

    private int shipId;
    private boolean isMobilMaster;
    private int lifeScore;
    private Team team;
    private static AccountInfo account ;
    private GameState gameState;


    private AccountInfo() {
            this.shipId = -1;
            this.isMobilMaster = false;
            this.lifeScore = -1;
            this.gameState = GameState.UNDEFINED;
    }

    public synchronized int getShipId() {
        return shipId;
    }

    public synchronized boolean isMobilMaster() {
        return isMobilMaster;
    }

    public synchronized void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public synchronized void setMobilMaster(boolean mobilMaster) {
        if(isMobilMaster && gameState == GameState.LOBBY){
            AndroidHandler.ConnectActivity.goToConfigActivity();
        }if(!isMobilMaster && gameState == GameState.LOBBY){
            AndroidHandler.ConnectActivity.goToLoadingActivity();
        }
        isMobilMaster = mobilMaster;
    }

    public synchronized int getLifeScore() {
        return lifeScore;
    }

    public synchronized void setLifeScore(int lifeScore) {

        this.lifeScore = lifeScore;
        if(this.lifeScore == 0){
            AndroidHandler.JoystickActivity.goToGameOver();
        }
    }

    public synchronized GameState getGameState() {
        return gameState;
    }

    public synchronized void setGameState(GameState gameState) {
        if(gameState == GameState.GAME){
            if(isMobilMaster) {
                AndroidHandler.ControllerConfigActivity.goToJoystick();
            } else{
                AndroidHandler.ControllerLoadingActivity.goToJoystick();
            }
        }else if(gameState == GameState.GAME_END){
            if(isMobilMaster) {
                AndroidHandler.JoystickActivity.goToConnectActivity();
            } else{
                AndroidHandler.JoystickActivity.goToConnectActivity();
            }
        }
        this.gameState = gameState;
    }

    public static synchronized AccountInfo getAccount() {
        if(AccountInfo.account == null){
            AccountInfo.account = new AccountInfo();
        }
        return AccountInfo.account;
    }

    public synchronized Team getTeam() {
        return team;
    }

    public synchronized void setTeam(Team team) {
        this.team = team;
    }
}
