package device.s.docreader.office.fc.hslf.model;

import device.s.docreader.office.fc.hslf.model.textproperties.TextProp;
import device.s.docreader.office.fc.hslf.record.SheetContainer;


/**
 * The superclass of all master sheets - Slide masters, Notes masters, etc.
 *
 * For now it's empty. When we understand more about masters in ppt we will add the common functionality here.
 *
 * @author Yegor Kozlov
 */
public abstract class MasterSheet extends Sheet
{
    public MasterSheet(SheetContainer container, int sheetNo)
    {
        super(container, sheetNo);
    }

    /**
     * Pickup a style attribute from the master.
     * This is the "workhorse" which returns the default style attrubutes.
     */
    public abstract TextProp getStyleAttribute(int txtype, int level, String name,
        boolean isCharacter);

    /**
     * Checks if the shape is a placeholder.
     * (placeholders aren't normal shapes, they are visible only in the Edit Master mode)
     *
     *
     * @return true if the shape is a placeholder
     */
    public static boolean isPlaceholder(Shape shape)
    {
        if (!(shape instanceof TextShape))
            return false;

        TextShape tx = (TextShape)shape;
        return tx.getPlaceholderAtom() != null;
    }
}
