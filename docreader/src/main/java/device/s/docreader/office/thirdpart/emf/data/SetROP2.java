// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.thirdpart.emf.EMFConstants;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class SetROP2 extends EMFTag implements EMFConstants
{

    private int mode;

    public SetROP2()
    {
        super(20, 1);
    }

    public SetROP2(int mode)
    {
        this();
        this.mode = mode;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SetROP2(emf.readDWORD());
    }


    public String toString()
    {
        return super.toString() + "\n  mode: " + mode;
    }


    public void render(EMFRenderer renderer)
    {
        // The SetROP2 function sets the current foreground mix mode.
        // GDI uses the foreground mix mode to combine pens and interiors
        // of filled objects with the colors already on the screen. The
        // foreground mix mode defines how colors from the brush or pen
        // and the colors in the existing image are to be combined.
        renderer.setRop2(mode);
    }
}
