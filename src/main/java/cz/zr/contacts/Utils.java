package cz.zr.contacts;

import org.apache.commons.lang3.StringUtils;

/**
 * Utils
 *
 * @author zruzicka
 */
public class Utils {

    /**
     * Returns array's value at given index or default value if index is exceeded. </br>
     *
     * Given array cannot be <code>null</code>.
     *
     * @param array
     * @param index
     * @return Array's value at given index.
     */
    public static String valueAt(String[] array, int index) {
        return index < array.length ? array[index] : "";
    }

    /**
     * Returns array's value at given index or default value if index is exceeded. </br>
     *
     * Given array may be <code>null</code> - in such case default value is provided.
     *
     * @param array
     * @param index
     * @return Array's value at given index.
     */
    public static String notNullValueAt(String[] array, int index) {
        String result;
        if (array == null) {
            result = "";
        } else {
            result = StringUtils.defaultString(valueAt(array, index));
        }
        return result;
    }

    private Utils() {
        // Not intended for instantiation.
    }
}
