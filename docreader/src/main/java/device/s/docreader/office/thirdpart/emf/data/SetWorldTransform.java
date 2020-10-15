// Copyright 2002, FreeHEP.

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.java.awt.geom.AffineTransform;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;

/**
 * SetWorldTransform TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: SetWorldTransform.java 10367 2007-01-22 19:26:48Z duns $
 */
public class SetWorldTransform extends EMFTag
{

    private AffineTransform transform;

    public SetWorldTransform()
    {
        super(35, 1);
    }

    public SetWorldTransform(AffineTransform transform)
    {
        this();
        this.transform = transform;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len) throws IOException
    {

        return new SetWorldTransform(emf.readXFORM());
    }

    public String toString()
    {
        return super.toString() + "\n  transform: " + transform;
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer)
    {
        if (renderer.getPath() != null)
        {
            renderer.setPathTransform(transform);
        }
        else
        {
            renderer.resetTransformation();
            renderer.transform(transform);
        }
    }
}
