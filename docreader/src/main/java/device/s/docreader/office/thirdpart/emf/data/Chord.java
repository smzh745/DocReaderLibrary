// Copyright 2002-2007, FreeHEP.
package device.s.docreader.office.thirdpart.emf.data;

import android.graphics.Point;

import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.java.awt.geom.Arc2D;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;

/**
 * Chord TAG.
 *
 * @author Mark Donszelmann
 * @version $Id: Chord.java 10377 2007-01-23 15:44:34Z duns $
 */
public class Chord extends AbstractArc {

    private Rectangle bounds;

    private Point start, end;

    public Chord() {
        super(46, 1, null, null, null);
    }

    public Chord(Rectangle bounds, Point start, Point end) {
        super(46, 1, bounds, start, end);
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        return new Chord(
            emf.readRECTL(),
            emf.readPOINTL(),
            emf.readPOINTL());
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer) {
        renderer.fillAndDrawOrAppend(
            getShape(renderer, Arc2D.CHORD));
    }
}
