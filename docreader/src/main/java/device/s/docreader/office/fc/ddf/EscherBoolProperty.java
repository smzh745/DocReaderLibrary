

package device.s.docreader.office.fc.ddf;


public class EscherBoolProperty
        extends EscherSimpleProperty
{

    public EscherBoolProperty( short propertyNumber, int value )
    {
        super(propertyNumber, value);
    }


    public boolean isTrue()
    {
        return propertyValue != 0;
    }

    /**
     * Whether this boolean property is false
     */
    public boolean isFalse()
    {
        return propertyValue == 0;
    }

//    public String toString()
//    {
//        return "propNum: " + getPropertyNumber()
//                + ", complex: " + isComplex()
//                + ", blipId: " + isBlipId()
//                + ", value: " + (getValue() != 0);
//    }

}
