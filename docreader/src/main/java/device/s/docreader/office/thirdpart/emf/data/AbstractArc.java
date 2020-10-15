// Copyright 2007, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;


import android.graphics.Point;

import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.java.awt.Shape;
import device.s.docreader.office.java.awt.geom.Arc2D;
import device.s.docreader.office.thirdpart.emf.EMFConstants;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;


public abstract class AbstractArc extends EMFTag
{

    private Rectangle bounds;

    private Point start, end;

    protected AbstractArc(int id, int version, Rectangle bounds, Point start, Point end)
    {
        super(id, version);
        this.bounds = bounds;
        this.start = start;
        this.end = end;
    }

    public String toString()
    {
        return super.toString() + "\n  bounds: " + bounds + "\n  start: " + start + "\n  end: "
            + end;
    }
    
    protected double getAngle(Point point)
    {
        double circleX = bounds.getX() + bounds.getWidth() / 2;
        double circleY = bounds.getY() + bounds.getHeight() / 2;
        
        double x = point.x;
        double y = point.y;
        
        double alpha = 0;
        if (x > circleX)
        {
        	double nx = Math.abs(y - circleY) / (x - circleX);
        	alpha = Math.atan(nx) / Math.PI * 180;
        	
        	if (y > circleY)
        	{
        		alpha = 360 - alpha;
        	}
        }
        else if (x == circleX)
        {
        	alpha = (y < circleY) ? 90 : 270;
        }
        else
        {
        	double nx = Math.abs(y - circleY) / (circleX - x);
        	alpha = Math.atan(nx) / Math.PI * 180;
        	
        	if (y < circleY)
        	{
        		alpha = 180 - alpha;
        	}
        	else
        	{
        		alpha += 180;
        	}
        }
        
        return alpha;
    }


    protected Shape getShape(EMFRenderer renderer, int arcType)
    {
        // normalize start and end point to a circle
        //double nx0 = start.x / bounds.getWidth();

        // double ny0 = arc.getStart().y / arc.getBounds().height;
        //double nx1 = end.x / bounds.getWidth();

        // double ny1 = arc.getEnd().y / arc.getBounds().height;
        // calculate angle of start point
        double alpha0, alpha1;
        if (renderer.getArcDirection() == EMFConstants.AD_CLOCKWISE)
        {
//            alpha0 = Math.acos(nx0);
//            alpha1 = Math.acos(nx1);
            alpha0 = getAngle(end);
            alpha1 = getAngle(start);
        }
        else
        {
//            alpha0 = Math.acos(nx1);
//            alpha1 = Math.acos(nx0);
            alpha0 = getAngle(start);
            alpha1 = getAngle(end);
        }
        
        double extent = 0;
        
        if (alpha1 > alpha0)
        {
        	extent = alpha1 - alpha0;
        }
        else
        {
        	extent = 360 - (alpha0 - alpha1);
        }

        return new Arc2D.Double(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight(),
            alpha0, extent, arcType);
    }
}
