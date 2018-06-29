package cz.zr.contacts.repo;

/**
 * Common serialization and deserialization interface.
 *
 * @author zruzicka
 *
 * @param <M>
 *            Model class.
 * @param <F>
 *            Input/output format.
 */
/*
 * In case of more complex (de)serialization logic could be both serialization and deserialization split into separate interfaces.
 */
public interface Serialization<M, F> {

    /**
     * @param model
     * @return Serialized given model.
     */
    public F serialize(M model);

    /**
     * @param input
     * @return Model deserialized from given input.
     */
    public M deserialize(F input);

}
