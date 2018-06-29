package cz.zr.contacts.model.validator;

/**
 * Validator for model classes.
 *
 * @author zruzicka
 * @param <T>
 *            Validated model.
 */
public interface Validator<T> {

    /**
     * Validates whether given model is valid.
     *
     * @param model
     * @return Flag whether given model is valid.
     */
    boolean isValid(T model);

}
