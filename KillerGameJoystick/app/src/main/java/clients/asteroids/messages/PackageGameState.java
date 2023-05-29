package clients.asteroids.messages;

import java.io.Serializable;

public class PackageGameState implements Serializable {
    private static final long serialVersionUID = 9167L;

    private GameStateTypes gameState;

    public PackageGameState(GameStateTypes gameState) {
        this.gameState = gameState;
    }

    public GameStateTypes getGameState() {
        return gameState;
    }

}
