package device.s.docreader.office.fc.hwpf.usermodel;

import device.s.docreader.office.common.pictureefftect.PictureEffectInfo;
import device.s.docreader.office.system.IControl;

/**
 * User-friendly interface to office drawing objects.
 * <p>
 * Some properties and enumeration constants description are quotes from the
 * following sources:
 * <ul>
 * <li>[MS-ODRAW] -- v20110608; Office Drawing Binary File Format; Copyright (c)
 * 2011 Microsoft Corporation.
 * </ul>
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {dot} com)
 */
public interface OfficeDrawing
{

    public enum HorizontalPositioning {

        /**
         * The shape is horizontally offset by an absolute distance from the
         * page element.
         */
        ABSOLUTE,

        /**
         * The shape is horizontally positioned at the center of the page
         * element.
         */
        CENTER,

        /**
         * The shape is horizontally positioned like {@link #LEFT} on
         * odd-numbered pages and like {@link #RIGHT} on even-numbered pages.
         */
        INSIDE,

        /**
         * The shape is horizontally positioned at the left side of the page
         * element.
         */
        LEFT,

        /**
         * The shape is horizontally positioned like {@link #RIGHT} on
         * odd-numbered pages and like {@link #LEFT} on even-numbered pages.
         */
        OUTSIDE,

        /**
         * The shape is horizontally positioned at the right side of the page
         * element.
         */
        RIGHT;
    }

    public enum HorizontalRelativeElement {
        CHAR, MARGIN, PAGE, TEXT;
    }

    public enum VerticalPositioning {

        /**
         * The shape is vertically offset by an absolute distance from the page
         * element
         */
        ABSOLUTE,

        /**
         * The shape is vertically positioned at the bottom of the page element
         */
        BOTTOM,

        /**
         * The shape is vertically positioned in the center of the page element
         */
        CENTER,

        /**
         * The shape is vertically positioned like msopvTop on odd-numbered
         * pages and like msopvBottom on even-numbered pages
         */
        INSIDE,

        /**
         * The shape is vertically positioned like {@link #BOTTOM} on
         * odd-numbered pages and like {@link #TOP} on even-numbered pages
         */
        OUTSIDE,

        /**
         * The shape is vertically positioned at the top of the page element
         */
        TOP;
    }

    public enum VerticalRelativeElement {
        LINE, MARGIN, PAGE, TEXT;
    }

    /**
     * 
     * @return
     */
    public PictureEffectInfo getPictureEffectInfor();
    
    /**
     * Returns the type of horizontal positioning to use for a shape
     * 
     * @return the type of horizontal positioning to use for a shape
     */
    public byte getHorizontalPositioning();

    /**
     * Specifies a page element relative to which a shape is horizontally
     * positioned
     * 
     * @return a page element relative to which a shape is horizontally
     *         positioned
     */
    public byte getHorizontalRelative();

    /**
     * Returns picture data if this shape has (single?) associated picture data
     */
    byte[] getPictureData(IControl control);
    
    public byte[] getPictureData(IControl control, int index);
    
    public HWPFShape getAutoShape();

    /**
     * Bottom of the rectangle enclosing shape relative to the origin of the
     * shape
     */
    int getRectangleBottom();

    /**
     * Left of rectangle enclosing shape relative to the origin of the shape
     */
    int getRectangleLeft();

    /**
     * Right of rectangle enclosing shape relative to the origin of the shape
     */
    int getRectangleRight();

    /**
     * Top of rectangle enclosing shape relative to the origin of the shape
     */
    int getRectangleTop();

    /**
     * Shape Identifier
     */
    int getShapeId();
    /**
     * 
     * @return
     */
    public int getWrap();
    
    /**
     * is behind docs
     * @return
     */
    public boolean isBelowText();
    
    /**
     * 
     * @return
     */
    public boolean isAnchorLock();
    /**
     * 
     */
    public String getTempFilePath(IControl control);

    /**
     * Specifies the type of vertical positioning to use for a shape
     * 
     * @return return the type of vertical positioning to use for a shape
     */
    public byte getVerticalPositioning();

    /**
     * Specifies a page element relative to which a shape is vertically
     * positioned
     * 
     * @return a page element relative to which a shape is vertically positioned
     */
    public byte getVerticalRelativeElement();

}
