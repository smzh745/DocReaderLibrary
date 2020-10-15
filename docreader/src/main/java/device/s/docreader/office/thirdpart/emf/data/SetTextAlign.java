// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.thirdpart.emf.EMFConstants;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class SetTextAlign extends EMFTag implements EMFConstants
{

    private int mode;

    public SetTextAlign()
    {
        super(22, 1);
    }

    public SetTextAlign(int mode)
    {
        this();
        this.mode = mode;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SetTextAlign(emf.readDWORD());
    }

    public String toString()
    {
        return super.toString() + "\n  mode: " + mode;
    }


    public void render(EMFRenderer renderer)
    {
        renderer.setTextAlignMode(mode);
    }
}
