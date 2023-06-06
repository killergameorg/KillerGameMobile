package DTO;

import clients.asteroids.messages.GameState;
import communications.AndroidHandler;

public class AccountInfo {

    private int shipId;
    private boolean isMobilMaster;
    private int lifeScore;
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
        }
        this.gameState = gameState;
    }

    public static synchronized AccountInfo getAccount() {
        if(AccountInfo.account == null){
            AccountInfo.account = new AccountInfo();
        }
        return AccountInfo.account;
    }
}
