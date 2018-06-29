package cz.zr.contacts.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import cz.zr.contacts.ExceptionHandler;
import cz.zr.contacts.model.Contact;
import cz.zr.contacts.model.validator.ContactValidator;
import cz.zr.contacts.model.validator.Validator;
import cz.zr.contacts.repo.ContactRepository;

/**
 * ContactService
 *
 * @author zruzicka
 */
public class ContactService {

    /** @see {@link ContactRepository} */
    private final ContactRepository repository = new ContactRepository();

    /** Collection of unique {@link Contact}s */
    private final Collection<Contact> contacts = new HashSet<>();

    private final Validator<Contact> validator = new ContactValidator();

    /** */
    public ContactService() {
        load();
    }

    /**
     * Persisted contacts are loaded from a storage into the collection.
     */
    private void load() {
        try {
            contacts.addAll(repository.load());
        } catch (IOException e) {
            ExceptionHandler.handleExc(e);
        }
    }

    /**
     * Given contact is validated and in case of uniqueness is added into {@link Contact}s collection and saved into a storage.
     *
     * @param contact
     */
    public void add(Contact contact) {
        if (!validator.isValid(contact)) {
            return; // No further processing for invalid input.
        }
        boolean isUniqueContact;
        synchronized (this) {
            // Be prepared for concurrency and race condition.
            // TODO Search for a better race condition solution.
            isUniqueContact = contacts.add(contact);
        }
        if (isUniqueContact) {
            try {
                repository.save(contact);
            } catch (IOException e) {
                ExceptionHandler.handleExc(e);
            }
        }
    }

}
