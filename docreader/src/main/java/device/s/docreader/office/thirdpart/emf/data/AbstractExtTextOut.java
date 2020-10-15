// Copyright 2007, FreeHEP

package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.thirdpart.emf.EMFConstants;
import device.s.docreader.office.thirdpart.emf.EMFRenderer;
import device.s.docreader.office.thirdpart.emf.EMFTag;

/**
 * Abstraction of commonality between the {@link ExtTextOutA} and {@link ExtTextOutW} tags.
 *
 * @author Daniel Noll (daniel@nuix.com)
 * @version $Id: ExtTextOutW.java 10140 2006-12-07 07:50:41Z duns $
 */
public abstract class AbstractExtTextOut extends EMFTag implements EMFConstants
{

    private Rectangle bounds;

    private int mode;

    private float xScale, yScale;

    /**
     * Constructs the tag.
     *
     * @param id id of the element
     * @param version emf version in which this element was first supported
     * @param bounds text boundary
     * @param mode text mode
     * @param xScale horizontal scale factor
     * @param yScale vertical scale factor
     */
    protected AbstractExtTextOut(int id, int version, Rectangle bounds, int mode, float xScale,
        float yScale)
    {

        super(id, version);
        this.bounds = bounds;
        this.mode = mode;
        this.xScale = xScale;
        this.yScale = yScale;
    }

    public abstract Text getText();

    public String toString()
    {
        return super.toString() + "\n  bounds: " + bounds + "\n  mode: " + mode + "\n  xScale: "
            + xScale + "\n  yScale: " + yScale + "\n" + getText().toString();
    }

    /**
     * displays the tag using the renderer
     *
     * @param renderer EMFRenderer storing the drawing session data
     */
    public void render(EMFRenderer renderer)
    {
        Text text = getText();
        renderer.drawOrAppendText(text.getString(), text.getPos().x, text.getPos().y);
    }
}
