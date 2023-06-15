package communications;

/**
 * This interface defines the methods that an object must implement to handle P2P communication events.
 * @author Miquel A. Fuster
 */
public interface P2PCommListener {

    /**
     * Called when a message is received from the specified IP address.
     * @param ip The IP address of the sender.
     * @param message The message received.
     */
    public void onIncomingMessage(String ip, Object message);

    /**
     * Called when a connection is properly closed with the specified IP address.
     * @param ip The IP address of the new connection.
     */
    public void onConnectionClosed(String ip);

    /**
     * Called when a connection is lost with the specified IP address. Possibly ocurred an error in the communication.
     * @param ip The IP address of the new connection.
     */
    public void onConnectionLost(String ip);

    /**
     * Called when a new connection is established with the specified IP address.
     * @param ip The IP address of the new connection.
     */
    public void onNewConnection(String ip);

}