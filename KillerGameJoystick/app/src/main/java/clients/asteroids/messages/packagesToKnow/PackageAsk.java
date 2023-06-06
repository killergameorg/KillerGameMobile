package clients.asteroids.messages.packagesToKnow;


public class PackageAsk implements PackageProccessKnowNewConnection {

    // ! Attributes
    private OptionsPackageAsk optionsPackageAsk;

    // ! Constructor
    public PackageAsk(OptionsPackageAsk optionsPackageAsk) {
        this.setOptionsPackageAsk(optionsPackageAsk);
    }

    // ! Getters and Setters

    /**
     * @return the optionsPackageAsk
     */
    public OptionsPackageAsk getOptionsPackageAsk() {
        return optionsPackageAsk;
    }

    /**
     * @param optionsPackageAsk the optionsPackageAsk to set
     */
    public void setOptionsPackageAsk(OptionsPackageAsk optionsPackageAsk) {
        this.optionsPackageAsk = optionsPackageAsk;
    }

}