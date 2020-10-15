// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import android.graphics.Point;

import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class PolyPolygon extends AbstractPolyPolygon
{

    private int start, end;

    public PolyPolygon()
    {
        super(8, 1, null, null, null);
    }

    public PolyPolygon(Rectangle bounds, int start, int end, int[] numberOfPoints, Point[][] points)
    {

        super(8, 1, bounds, numberOfPoints, points);
        this.start = start;
        this.end = end;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        Rectangle bounds = emf.readRECTL();
        int np = emf.readDWORD();
        /* int totalNumberOfPoints = */emf.readDWORD();
        int[] pc = new int[np];
        Point[][] points = new Point[np][];
        for (int i = 0; i < np; i++)
        {
            pc[i] = emf.readDWORD();
            points[i] = new Point[pc[i]];
        }
        for (int i = 0; i < np; i++)
        {
            points[i] = emf.readPOINTL(pc[i]);
        }
        return new PolyPolygon(bounds, 0, np - 1, pc, points);
    }

}
