package clients.asteroids;

import DTO.AccountInfo;
import clients.asteroids.messages.GameState;

public class ClusterCommunicationController {


    public void setGameState(GameState gameState) {
        AccountInfo.getAccount().setGameState(gameState);
    }
}
