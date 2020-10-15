package device.s.docreader.office.fc.hslf.record;

import device.s.docreader.office.fc.util.LittleEndian;


/**
 * A container record that specifies information about animation information for a shape.
 *
 * @author Yegor Kozlov
 */
public final class AnimationInfo extends RecordContainer
{
    private byte[] _header;

    // Links to our more interesting children
    private AnimationInfoAtom animationAtom;

    /**
     * Set things up, and find our more interesting children
     */
    protected AnimationInfo(byte[] source, int start, int len)
    {
        // Grab the header
        _header = new byte[8];
        System.arraycopy(source, start, _header, 0, 8);

        // Find our children
        _children = Record.findChildRecords(source, start + 8, len - 8);
        findInterestingChildren();
    }

    /**
     * Go through our child records, picking out the ones that are
     *  interesting, and saving those for use by the easy helper
     *  methods.
     */
    private void findInterestingChildren()
    {

        // First child should be the ExMediaAtom
        if (_children[0] instanceof AnimationInfoAtom)
        {
            animationAtom = (AnimationInfoAtom)_children[0];
        }
        /*else
        {
            logger.log(
                POILogger.ERROR,
                "First child record wasn't a AnimationInfoAtom, was of type "
                    + _children[0].getRecordType());
        }*/
    }

    /**
     * Create a new AnimationInfo, with blank fields
     */
    public AnimationInfo()
    {
        // Setup our header block
        _header = new byte[8];
        _header[0] = 0x0f; // We are a container record
        LittleEndian.putShort(_header, 2, (short)getRecordType());

        _children = new Record[1];
        _children[0] = animationAtom = new AnimationInfoAtom();
    }

    /**
     * We are of type 4103
     */
    public long getRecordType()
    {
        return RecordTypes.AnimationInfo.typeID;
    }


    /**
     * Returns the AnimationInfo
     */
    public AnimationInfoAtom getAnimationInfoAtom()
    {
        return animationAtom;
    }
    
    /**
     * 
     *
     */
    public void dispose()
    {
        super.dispose();
        _header = null;
        if (animationAtom != null)
        {
            animationAtom.dispose();
            animationAtom = null;
        }
    }

}
