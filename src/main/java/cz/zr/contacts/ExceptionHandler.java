package cz.zr.contacts;

/**
 * ExceptionHandler
 *
 * @author zruzicka
 */
public class ExceptionHandler {

    // TODO Add real (log4j) Logger.

    /**
     * Default exception handling for given exception.
     *
     * @param e
     */
    public static void handleExc(Exception e) {
        // TODO provide proper exception handling and logging.
        e.printStackTrace();
    }

    private ExceptionHandler() {
        // Not intended for instantiation.
    }

}
