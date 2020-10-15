package device.s.docreader.office.fc.hslf.record;

import device.s.docreader.office.fc.hslf.model.textproperties.AutoNumberTextProp;

import java.util.LinkedList;

/**
 * Abstract class which all atom records will extend.
 *
 * @author Nick Burch
 */

public abstract class RecordAtom extends Record
{
    /**
     * We are an atom
     */
    public boolean isAnAtom()
    {
        return true;
    }

    /**
     * We're an atom, returns null
     */
    public Record[] getChildRecords()
    {
        return null;
    }
    
    //
    public LinkedList<AutoNumberTextProp> getExtendedParagraphPropList()
    {
        return null;
    }
    
    
}
