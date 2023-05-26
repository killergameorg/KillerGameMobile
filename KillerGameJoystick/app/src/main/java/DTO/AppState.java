package DTO;

public class AppState {
    private boolean isSoundMusic;
    private static AppState appState;


    private AppState(){
        this.isSoundMusic = true;
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
}
