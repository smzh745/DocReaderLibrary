
package device.s.docreader.office.fc.ddf;

public abstract class EscherProperty {
    private short  _id;

    /**
     * The id is distinct from the actual property number.  The id includes the property number the blip id
     * flag and an indicator whether the property is complex or not.
     */
    public EscherProperty(short id) {
        _id   = id;
    }

    /**
     * Constructs a new escher property.  The three parameters are combined to form a property
     * id.
     */
    public EscherProperty(short propertyNumber, boolean isComplex, boolean isBlipId) {
        _id   = (short)(propertyNumber +
                (isComplex ? 0x8000 : 0x0) +
                (isBlipId ? 0x4000 : 0x0));
    }

    public short getId() {
        return _id;
    }

    public short getPropertyNumber() {
        return (short) (_id & (short) 0x3FFF);
    }

    public boolean isComplex() {
        return (_id & (short) 0x8000) != 0;
    }

    public boolean isBlipId() {
        return (_id & (short) 0x4000) != 0;
    }

    public String getName() {
        return EscherProperties.getPropertyName(getPropertyNumber());
    }

    /**
     * Most properties are just 6 bytes in length.  Override this if we're
     * dealing with complex properties.
     */
    public int getPropertySize() {
        return 6;
    }

    /**
     * Escher properties consist of a simple fixed length part and a complex variable length part.
     * The fixed length part is serialized first.
     */
    abstract public int serializeSimplePart( byte[] data, int pos );
    /**
     * Escher properties consist of a simple fixed length part and a complex variable length part.
     * The fixed length part is serialized first.
     */
    abstract public int serializeComplexPart( byte[] data, int pos );
}
