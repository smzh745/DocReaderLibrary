// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import android.graphics.Point;

import device.s.docreader.office.java.awt.Color;
import device.s.docreader.office.thirdpart.emf.EMFConstants;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class ExtFloodFill extends EMFTag implements EMFConstants
{

    private Point start;

    private Color color;

    private int mode;

    public ExtFloodFill()
    {
        super(53, 1);
    }

    public ExtFloodFill(Point start, Color color, int mode)
    {
        this();
        this.start = start;
        this.color = color;
        this.mode = mode;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new ExtFloodFill(emf.readPOINTL(), emf.readCOLORREF(), emf.readDWORD());
    }

    public String toString()
    {
        return super.toString() + "\n  start: " + start + "\n  color: " + color + "\n  mode: "
            + mode;
    }
}
