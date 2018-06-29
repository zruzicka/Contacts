package cz.zr.contacts.repo;

import org.apache.commons.lang3.StringUtils;

import cz.zr.contacts.Consts;
import cz.zr.contacts.Utils;
import cz.zr.contacts.model.Contact;

/**
 * Contact serialization and deserialization for CSV files.
 *
 * @author zruzicka
 */
public class ContactSerializationCSV implements Serialization<Contact, String> {

    @Override
    public String serialize(Contact contact) {
        return serializeFields(contact.getName(), contact.getLastName(), contact.getEmail());
    }

    @Override
    public Contact deserialize(String input) {
        String[] contact = input.split(Consts.CSV_FIELDS_SEPARATOR);
        return Contact.create(Utils.valueAt(contact, 0), Utils.valueAt(contact, 1), Utils.valueAt(contact, 2));
    }

    /**
     * @param fields
     * @return Serialized given fields.
     */
    private String serializeFields(String... fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            sb.append(StringUtils.defaultString(fields[i]));
            if (i + 1 < fields.length) {
                sb.append(Consts.CSV_FIELDS_SEPARATOR);
            }
        }
        return sb.toString();
    }

}
