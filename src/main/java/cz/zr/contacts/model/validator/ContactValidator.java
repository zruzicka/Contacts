package cz.zr.contacts.model.validator;

import org.apache.commons.lang3.StringUtils;

import cz.zr.contacts.model.Contact;

/**
 * {@link Validator} for {@link Contact}.
 *
 * @author zruzicka
 */
public class ContactValidator implements Validator<Contact> {

    @Override
    public boolean isValid(Contact model) {
        if (model == null) {
            return false;
        }
        if (StringUtils.isBlank(model.getName()) && StringUtils.isBlank(model.getLastName())
                && StringUtils.isBlank(model.getEmail())) {
            return false; // All fields should not be blank in the same time.
        }
        // TODO Check business rules - which fields cannot be blank for a valid record?
        return true; // Valid otherwise.
    }

}
