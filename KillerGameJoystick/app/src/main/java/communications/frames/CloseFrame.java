package communications.frames;

public class CloseFrame extends Frame {

    private static final long serialVersionUID = 1916770907617445458L;

    public CloseFrame() {
        super(FrameType.CLOSE);
        super.setPayload("");
    }

    /**
     * There is no payload for a ping acknowledgement frame, so this method does nothing.
     * @param payload ignored payload
     */
    public void setPayload(String payload) {
        // Theres no need of payload in a Ping
    }

}