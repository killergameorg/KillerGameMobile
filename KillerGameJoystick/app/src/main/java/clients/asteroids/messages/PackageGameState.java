package clients.asteroids.messages;

import java.io.Serializable;

public class PackageGameState implements Serializable {
    private static final long serialVersionUID = 91345667L;

    private GameState gameState;

    public PackageGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

}
