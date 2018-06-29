package cz.zr.contacts;

import java.nio.charset.StandardCharsets;

import cz.zr.contacts.model.Contact;

/**
 * Constants.
 *
 * @author zruzicka
 */
public class Consts {

    /** Charset for URL query decoding. */
    public static final String URL_CHARSET = StandardCharsets.UTF_8.name();

    /** Charset applied for file read/write operations. */
    public static final String FILE_CHARSET = "windows-1250";

    /** System property which allows to load a storage directory. */
    public static final String STORAGE_DIRECTORY_SYSTEM_PROPERTY = "java.io.tmpdir";

    /** File name for {@link Contact}s storage. */
    public static final String CSV_FILE_NAME = "contacts.csv";

    /** Separator applied between fields in CSV file. */
    public static final String CSV_FIELDS_SEPARATOR = ",";

    /** New line separator applied in files. */
    public static final String FILE_NEW_LINE_SEPARATOR = "\n";

    private Consts() {
        // Not intended for instantiation.
    }
}
