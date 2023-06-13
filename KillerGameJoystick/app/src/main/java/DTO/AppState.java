package DTO;

public class AppState {
    private boolean isSoundMusic;
    private static AppState appState;
    private boolean isConnected;
    private String ip;


    private AppState(){
        this.isSoundMusic = true;
        this.isConnected = false;
        this.ip = "";
    }

    public synchronized boolean getIsSoundMusic(){
        return isSoundMusic;
    }

    public synchronized void setIsSoundMusic(boolean state){
        this.isSoundMusic = state;
    }

    public static AppState getAppState(){
        if(AppState.appState == null){
            AppState.appState = new AppState();
        }

        return  AppState.appState;
    }

    public synchronized boolean getIsConnected(){
        return isConnected;
    }

    public synchronized void setIsConnected(boolean state){
        this.isConnected = state;
    }

    public synchronized String getIp(){
        return ip;
    }

    public synchronized void setIp(String ip){
        this.ip = ip;
    }
}
