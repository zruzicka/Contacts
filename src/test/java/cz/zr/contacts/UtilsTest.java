package cz.zr.contacts;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Utils} focused test cases.
 *
 * @author zruzicka
 */
public class UtilsTest {

    private static final String defaultValue = "";
    private static final String atIndex1 = "b";
    private static final String[] exampleInput = new String[] { "a", atIndex1, null, "d" };

    /** */
    @Test
    public void testValueAt__atIndex1() {
        Assert.assertEquals(atIndex1, Utils.valueAt(exampleInput, 1));
    }

    /** */
    @Test
    public void testValueAt__atIndex2() {
        Assert.assertEquals(null, Utils.valueAt(exampleInput, 2));
    }

    /** */
    @Test
    public void testValueAt__atIndex4() {
        Assert.assertEquals(defaultValue, Utils.valueAt(exampleInput, 4));
    }

    /** */
    @Test
    public void testValueAt__defaultValue() {
        String[] input = new String[] {};
        Assert.assertEquals(defaultValue, Utils.valueAt(input, 0));
    }

    /** */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testValueAt__atIndexMinus1() {
        Utils.valueAt(exampleInput, -1);
    }

    /** */
    @Test
    public void testNotNullValueAt__atIndex2() {
        Assert.assertEquals(defaultValue, Utils.notNullValueAt(exampleInput, 2));
    }

    /** */
    @Test
    public void testNotNullValueAt__null() {
        Assert.assertEquals(defaultValue, Utils.notNullValueAt(null, 2));
    }

}
