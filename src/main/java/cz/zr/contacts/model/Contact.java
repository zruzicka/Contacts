package cz.zr.contacts.model;

/**
 * Contact details model.
 *
 * @author zruzicka
 */
public class Contact {

    /** First name. */
    private final String name;

    private final String lastName;

    private final String email;

    /**
     * Factory method: create Contact.
     *
     * @param name
     * @param lastName
     * @param email
     * @return
     */
    public static Contact create(String name, String lastName, String email) {
        return new Contact(name, lastName, email);
    }

    /**
     * @param name
     * @param lastName
     * @param email
     */
    public Contact(String name, String lastName, String email) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", lastName=" + lastName + ", email=" + email + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
