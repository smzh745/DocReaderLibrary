// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class SelectObject extends EMFTag
{

    private int index;

    public SelectObject()
    {
        super(37, 1);
    }

    public SelectObject(int index)
    {
        this();
        this.index = index;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SelectObject(emf.readDWORD());
    }

    public String toString()
    {
        return super.toString() + "\n  index: 0x" + Integer.toHexString(index);
    }


    public void render(EMFRenderer renderer)
    {
        GDIObject gdiObject;

        if (index < 0)
        {
            gdiObject = StockObjects.getStockObject(index);
        }
        else
        {
            gdiObject = renderer.getGDIObject(index);
        }

        if (gdiObject != null)
        {
            // render that object
            gdiObject.render(renderer);
        }
        else
        {
        }
    }
}
