// Copyright 2002, FreeHEP.
package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;

public class EndPath extends EMFTag {

    public EndPath() {
        super(60, 1);
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
        // TODO: fix EMFGraphics2D?
        // this happens only when EMF is created by EMFGraphics2D
        // there could be an open figure (created with LineTo, PolylineTo etc.)
        // that is not closed and therefore not written to the currentPath
        //renderer.closeFigure();
    	renderer.appendFigure();

        // The EndPath function closes a path bracket and selects the path
        // defined by the bracket into the specified device context.
        //renderer.closePath();
    }
}
