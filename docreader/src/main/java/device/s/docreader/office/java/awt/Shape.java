/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package device.s.docreader.office.java.awt;

import device.s.docreader.office.java.awt.geom.AffineTransform;
import device.s.docreader.office.java.awt.geom.PathIterator;
import device.s.docreader.office.java.awt.geom.Point2D;
import device.s.docreader.office.java.awt.geom.Rectangle2D;


public interface Shape {

    public Rectangle getBounds();


    public Rectangle2D getBounds2D();


    public boolean contains(double x, double y);


    public boolean contains(Point2D p);


    public boolean intersects(double x, double y, double w, double h);


    public boolean intersects(Rectangle2D r);


    public boolean contains(double x, double y, double w, double h);


    public boolean contains(Rectangle2D r);


    public PathIterator getPathIterator(AffineTransform at);


    public PathIterator getPathIterator(AffineTransform at, double flatness);
}
