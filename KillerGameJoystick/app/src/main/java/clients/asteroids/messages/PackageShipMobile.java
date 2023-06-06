package clients.asteroids.messages;

import java.io.Serializable;

public class PackageShipMobile implements PackageMobileCommunications {
    private static final long serialVersionUID = 9133567L;

    private int accountId;
    private boolean isMaster;
    private Object message;

    public PackageShipMobile(int accountId, boolean isMaster, Object message) {
        this.accountId = accountId;
        this.isMaster = isMaster;
        this.message = message;
    }

    /**
     * @return the accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * @return the isMaster
     */
    public boolean isMaster() {
        return isMaster;
    }

    /**
     * @return the message
     */
    public Object getMessage() {
        return message;
    }

}
