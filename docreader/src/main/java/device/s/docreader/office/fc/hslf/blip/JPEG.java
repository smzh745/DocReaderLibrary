package device.s.docreader.office.fc.hslf.blip;

import device.s.docreader.office.fc.hslf.model.Picture;

/**
 * Represents a JPEG picture data in a PPT file
 *
 * @author Yegor Kozlov
 */
public final class JPEG extends Bitmap
{

    /**
     * @return type of  this picture
     * @see  Picture#JPEG
     */
    public int getType()
    {
        return Picture.JPEG;
    }

    /**
     * JPEG signature is <code>0x46A0</code>
     *
     * @return JPEG signature (<code>0x46A0</code>)
     */
    public int getSignature()
    {
        return 0x46A0;
    }
}
