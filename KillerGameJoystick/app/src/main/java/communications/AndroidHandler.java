package communications;



import android.annotation.SuppressLint;
import android.content.Intent;

import com.lisbeth.killergamejoystick.ActiveGameActivity;
import com.lisbeth.killergamejoystick.GameConfig;
import com.lisbeth.killergamejoystick.MainActivity;

import clients.asteroids.AsteroidsController;


public class AndroidHandler {
    public static ConnectionController conn;
    public static AsteroidsController asteroids;
    public static int shipId;

    public static MainActivity ConnectActivity;
    public static GameConfig ControllerConfigActivity;
    public static ActiveGameActivity ControllerActivity;


}