/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.java.awt.Shape;
import device.s.docreader.office.java.awt.Stroke;


public class BasicStroke implements Stroke {


    public final static int JOIN_MITER = 0;


    public final static int JOIN_ROUND = 1;


    public final static int JOIN_BEVEL = 2;


    public final static int CAP_BUTT = 0;


    public final static int CAP_ROUND = 1;


    public final static int CAP_SQUARE = 2;

    float width;

    int join;
    int cap;
    float miterlimit;

    float dash[];
    float dash_phase;


    public BasicStroke(float width, int cap, int join, float miterlimit,
		       float dash[], float dash_phase) {
	if (width < 0.0f) {
	    throw new IllegalArgumentException("negative width");
	}
	if (cap != CAP_BUTT && cap != CAP_ROUND && cap != CAP_SQUARE) {
	    throw new IllegalArgumentException("illegal end cap value");
	}
	if (join == JOIN_MITER) {
	    if (miterlimit < 1.0f) {
		throw new IllegalArgumentException("miter limit < 1");
	    }
	} else if (join != JOIN_ROUND && join != JOIN_BEVEL) {
	    throw new IllegalArgumentException("illegal line join value");
	}
	if (dash != null) {
	    if (dash_phase < 0.0f) {
		throw new IllegalArgumentException("negative dash phase");
	    }
	    boolean allzero = true;
	    for (int i = 0; i < dash.length; i++) {
		float d = dash[i];
		if (d > 0.0) {
		    allzero = false;
		} else if (d < 0.0) {
		    throw new IllegalArgumentException("negative dash length");
		}
	    }
	    if (allzero) {
		throw new IllegalArgumentException("dash lengths all zero");
	    }
	}
	this.width	= width;
	this.cap	= cap;
	this.join	= join;
	this.miterlimit	= miterlimit;
        if (dash != null) {
            this.dash = (float []) dash.clone();
        }
	this.dash_phase	= dash_phase;
    }


    public BasicStroke(float width, int cap, int join, float miterlimit) {
	this(width, cap, join, miterlimit, null, 0.0f);
    }


    public BasicStroke(float width, int cap, int join) {
	this(width, cap, join, 10.0f, null, 0.0f);
    }

    public BasicStroke(float width) {
	this(width, CAP_SQUARE, JOIN_MITER, 10.0f, null, 0.0f);
    }


    public BasicStroke() {
	this(1.0f, CAP_SQUARE, JOIN_MITER, 10.0f, null, 0.0f);
    }



    public Shape createStrokedShape(Shape s) {
//	FillAdapter filler = new FillAdapter();
//	PathStroker stroker = new PathStroker(filler);
//	PathDasher dasher = null;
//
//	try {
//	    PathConsumer consumer;
//
//	    stroker.setPenDiameter(width);
//	    stroker.setPenT4(null);
//	    stroker.setCaps(RasterizerCaps[cap]);
//	    stroker.setCorners(RasterizerCorners[join], miterlimit);
//	    if (dash != null) {
//		dasher = new PathDasher(stroker);
//		dasher.setDash(dash, dash_phase);
//		dasher.setDashT4(null);
//		consumer = dasher;
//	    } else {
//		consumer = stroker;
//	    }
//
//	    feedConsumer(consumer, s.getPathIterator(null));
//	} finally {
//	    stroker.dispose();
//	    if (dasher != null) {
//		dasher.dispose();
//	    }
//	}
//
//	return filler.getShape();
    	return null;
    }

//    private void feedConsumer(PathConsumer consumer, PathIterator pi) {
//	try {
//	    consumer.beginPath();
//	    boolean pathClosed = false;
//	    float mx = 0.0f;
//	    float my = 0.0f;
//	    float point[]  = new float[6];
//
//	    while (!pi.isDone()) {
//		int type = pi.currentSegment(point);
//		if (pathClosed == true) {
//		    pathClosed = false;
//		    if (type != PathIterator.SEG_MOVETO) {
//			// Force current point back to last moveto point
//			consumer.beginSubpath(mx, my);
//		    }
//		}
//		switch (type) {
//		case PathIterator.SEG_MOVETO:
//		    mx = point[0];
//		    my = point[1];
//		    consumer.beginSubpath(point[0], point[1]);
//		    break;
//		case PathIterator.SEG_LINETO:
//		    consumer.appendLine(point[0], point[1]);
//		    break;
//		case PathIterator.SEG_QUADTO:
//		    // Quadratic curves take two points
//		    consumer.appendQuadratic(point[0], point[1],
//					     point[2], point[3]);
//		    break;
//		case PathIterator.SEG_CUBICTO:
//		    // Cubic curves take three points
//		    consumer.appendCubic(point[0], point[1],
//					 point[2], point[3],
//					 point[4], point[5]);
//		    break;
//		case PathIterator.SEG_CLOSE:
//		    consumer.closedSubpath();
//		    pathClosed = true;
//		    break;
//		}
//		pi.next();
//	    }
//
//	    consumer.endPath();
//	} catch (PathException e) {
//	    throw new InternalError("Unable to Stroke shape ("+
//				    e.getMessage()+")");
//	}
//    }


    public float getLineWidth() {
	return width;
    }


    public int getEndCap() {
	return cap;
    }


    public int getLineJoin() {
	return join;
    }


    public float getMiterLimit() {
	return miterlimit;
    }


    public float[] getDashArray() {
        if (dash == null) {
            return null;
        }

        return (float[]) dash.clone();
    }

    public float getDashPhase() {
	return dash_phase;
    }

    public int hashCode() {
	int hash = Float.floatToIntBits(width);
	hash = hash * 31 + join;
	hash = hash * 31 + cap;
	hash = hash * 31 + Float.floatToIntBits(miterlimit);
	if (dash != null) {
	    hash = hash * 31 + Float.floatToIntBits(dash_phase);
	    for (int i = 0; i < dash.length; i++) {
		hash = hash * 31 + Float.floatToIntBits(dash[i]);
	    }
	}
	return hash;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof BasicStroke)) {
            return false;
        }

        BasicStroke bs = (BasicStroke) obj;
        if (width != bs.width) {
            return false;
        }

        if (join != bs.join) {
            return false;
        }

        if (cap != bs.cap) {
            return false;
        }

        if (miterlimit != bs.miterlimit) {
            return false;
        }

        if (dash != null) {
	    if (dash_phase != bs.dash_phase) {
		return false;
	    }

	    if (!java.util.Arrays.equals(dash, bs.dash)) {
		return false;
	    }
        }
        else if (bs.dash != null) {
            return false;
        }

        return true;
    }

//    private static final int RasterizerCaps[] = {
//	Rasterizer.BUTT, Rasterizer.ROUND, Rasterizer.SQUARE
//    };
//
//    private static final int RasterizerCorners[] = {
//	Rasterizer.MITER, Rasterizer.ROUND, Rasterizer.BEVEL
//    };

//    private class FillAdapter implements PathConsumer {
//	boolean closed;
//	Path2D.Float path;
//
//	public FillAdapter() {
//            // Ductus only supplies float coordinates so
//            // Path2D.Double is not necessary here.
//	    path = new Path2D.Float(Path2D.WIND_NON_ZERO);
//	}
//
//	public Shape getShape() {
//	    return path;
//	}
//
//	public void dispose() {
//	}
//
//	public PathConsumer getConsumer() {
//	    return null;
//	}
//
//	public void beginPath() {}
//
//	public void beginSubpath(float x0, float y0) {
//	    if (closed) {
//		path.closePath();
//		closed = false;
//	    }
//	    path.moveTo(x0, y0);
//	}
//
//	public void appendLine(float x1, float y1) {
//	    path.lineTo(x1, y1);
//	}
//
//	public void appendQuadratic(float xm, float ym, float x1, float y1) {
//	    path.quadTo(xm, ym, x1, y1);
//	}
//
//	public void appendCubic(float xm, float ym,
//				float xn, float yn,
//				float x1, float y1) {
//	    path.curveTo(xm, ym, xn, yn, x1, y1);
//	}
//
//	public void closedSubpath() {
//	    closed = true;
//	}
//
//	public void endPath() {
//	    if (closed) {
//		path.closePath();
//		closed = false;
//	    }
//	}
//
//	public void useProxy(FastPathProducer proxy)
//	    throws PathException
//	{
//	    proxy.sendTo(this);
//	}
//
//	public long getCPathConsumer() {
//	    return 0;
//	}
//    }
}
