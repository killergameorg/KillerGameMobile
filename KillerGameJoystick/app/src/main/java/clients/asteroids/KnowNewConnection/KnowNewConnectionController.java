package clients.asteroids.KnowNewConnection;

import DTO.AppState;
import clients.asteroids.messages.packagesToKnow.OptionsPackageAsk;
import clients.asteroids.messages.packagesToKnow.PackageAsk;
import clients.asteroids.messages.packagesToKnow.PackageSendType;
import clients.asteroids.messages.packagesToKnow.TypeNewConnection;
import communications.ConnectionController;

/**
 * KnowNewConnectionController
 *
 * This class is used to manage the connection with the new device
 */
public class KnowNewConnectionController {

    public KnowNewConnectionController(){
    }

    /**
     * @param question
     * @param conn
     *
     * This method is used to manage the connection with the new device
     */
    public void manageKnowConnection(PackageAsk question, ConnectionController conn){
        if(question.getOptionsPackageAsk() == OptionsPackageAsk.TYPE_DEVICE){
            conn.sendPrivate(AppState.getAppState().getIp(), new PackageSendType(TypeNewConnection.MOBILE));
        }
    }

}
