// Copyright 2002, FreeHEP.
package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.java.awt.geom.AffineTransform;
import device.s.docreader.office.java.awt.geom.GeneralPath;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;


public class BeginPath extends EMFTag {

    public BeginPath() {
        super(59, 1);
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        return this;
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer) {
        // The BeginPath function opens a path bracket in the specified
        // device context.
        renderer.setPath(new GeneralPath(
            renderer.getWindingRule()));
        renderer.setPathTransform(new AffineTransform());
    }
}
