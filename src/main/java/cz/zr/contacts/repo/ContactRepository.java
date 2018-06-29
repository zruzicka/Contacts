package cz.zr.contacts.repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.HashSet;

import cz.zr.contacts.Consts;
import cz.zr.contacts.model.Contact;
import cz.zr.contacts.model.validator.ContactValidator;
import cz.zr.contacts.model.validator.Validator;

/**
 * Repository for persistence operations.
 *
 * @author zruzicka
 */
public class ContactRepository {

    /** A directory where contacts are persisted. */
    private final File contactsDirectory;

    private final Serialization<Contact, String> serialization;

    private final Validator<Contact> validator;

    /** */
    public ContactRepository() {
        String directoryName = System.getProperty(Consts.STORAGE_DIRECTORY_SYSTEM_PROPERTY);
        contactsDirectory = new File(directoryName); // FIXME directoryName null check!
        serialization = new ContactSerializationCSV();
        validator = new ContactValidator();
    }

    /**
     * @return
     * @throws IOException
     */
    public Collection<Contact> load() throws IOException {
        Collection<Contact> contacts = new HashSet<>();
        File input = getFile();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), Consts.FILE_CHARSET))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                Contact loadedContact = serialization.deserialize(line);
                if (validator.isValid(loadedContact)) {
                    contacts.add(loadedContact);
                }
            }
        }
        return contacts;
    }

    /**
     * Given contact is persisted.
     *
     * @param contact
     * @throws IOException
     */
    public void save(Contact contact) throws IOException {
        File output = getFile();
        synchronized (this) {
            // Be prepared for concurrency and race condition.
            // TODO Search for a better race condition solution.
            try (OutputStream os = new FileOutputStream(output, true);
                    OutputStreamWriter writer = new OutputStreamWriter(os, Consts.FILE_CHARSET)) {
                writer.write(serialization.serialize(contact));
                writer.write(Consts.FILE_NEW_LINE_SEPARATOR);
            }
        }
    }

    /**
     * @return File for for persistence operations.
     * @throws IOException
     */
    private File getFile() throws IOException {
        File temp = null;
        if (contactsDirectory.exists()) {
            temp = new File(contactsDirectory, Consts.CSV_FILE_NAME);
            if (!temp.exists()) {
                temp.createNewFile();
            }
        }
        return temp;
    }
}
