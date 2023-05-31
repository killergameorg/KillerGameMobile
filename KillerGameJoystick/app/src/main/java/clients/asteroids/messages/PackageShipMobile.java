package clients.asteroids.messages;

import java.io.Serializable;

public class PackageShipMobile implements Serializable {
    private static final long serialVersionUID = 9137L;

    private int accountId;
    private boolean isMaster;
    private Object message;

    public PackageShipMobile(int accountID, boolean isMaster, Object message ){
        this.accountId = accountID;
        this.isMaster = isMaster;
        this.message = message;
    }

    public Object getMessage(){
        return this.message;
    }

    public boolean getIsMaster(){
        return this.isMaster;
    }

    public int getAccountId(){
        return this.accountId;
    }
}
