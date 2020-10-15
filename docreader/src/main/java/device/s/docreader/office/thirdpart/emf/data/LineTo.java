// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import android.graphics.Point;

import device.s.docreader.office.java.awt.geom.GeneralPath;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;

/**
 * LineTo TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: LineTo.java 10367 2007-01-22 19:26:48Z duns $
 */
public class LineTo extends EMFTag
{

    private Point point;

    public LineTo()
    {
        super(54, 1);
    }

    public LineTo(Point point)
    {
        this();
        this.point = point;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new LineTo(emf.readPOINTL());
    }

    public String toString()
    {
        return super.toString() + "\n  point: " + point;
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer)
    {
        // The LineTo function draws a line from the current position up to,
        // but not including, the specified point.
        // The line is drawn by using the current pen and, if the pen is a
        // geometric pen, the current brush.
    	GeneralPath currentFigure = renderer.getFigure();
    	if (currentFigure != null)
    	{
    		currentFigure.lineTo((float)point.x, (float)point.y);
	        renderer.drawShape(currentFigure);
    	}
    	else
    	{
            currentFigure = new GeneralPath(renderer.getWindingRule());
            currentFigure.moveTo((float)point.x, (float)point.y);
            renderer.setFigure(currentFigure);
    	}
    }
}