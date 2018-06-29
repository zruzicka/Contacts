package cz.zr.contacts.model;

/**
 * Contact parameters enumeration.
 */
public enum ContactField {

    /** */
    NAME("firstName"),

    /** */
    LAST_NAME("lastName"),

    /** */
    EMAIL("email");

    private final String value;

    /**
     * @param value
     */
    private ContactField(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}