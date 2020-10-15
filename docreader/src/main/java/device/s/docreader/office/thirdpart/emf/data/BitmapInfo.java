// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.thirdpart.emf.EMFInputStream;

import java.io.IOException;


public class BitmapInfo
{

    private BitmapInfoHeader header;

    public BitmapInfo(BitmapInfoHeader header)
    {
        this.header = header;
    }

    public BitmapInfo(EMFInputStream emf) throws IOException
    {
        header = new BitmapInfoHeader(emf);
        // colormap not necessary for true color image
    }

    public String toString()
    {
        return "  BitmapInfo\n" + header.toString();
    }

    public BitmapInfoHeader getHeader()
    {
        return header;
    }
}
