// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import android.graphics.Point;

import device.s.docreader.office.java.awt.Color;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class SetPixelV extends EMFTag
{

    private Point point;

    private Color color;

    public SetPixelV()
    {
        super(15, 1);
    }

    public SetPixelV(Point point, Color color)
    {
        this();
        this.point = point;
        this.color = color;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SetPixelV(emf.readPOINTL(), emf.readCOLORREF());
    }

    public String toString()
    {
        return super.toString() + "\n  point: " + point + "\n  color: " + color;
    }
}
