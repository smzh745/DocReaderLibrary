// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.java.awt.Color;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;

import java.io.IOException;


public class TriVertex
{

    private int x, y;

    private Color color;

    public TriVertex(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public TriVertex(EMFInputStream emf) throws IOException
    {
        x = emf.readLONG();
        y = emf.readLONG();
        color = emf.readCOLOR16();
    }

    public String toString()
    {
        return "[" + x + ", " + y + "] " + color;
    }
}
