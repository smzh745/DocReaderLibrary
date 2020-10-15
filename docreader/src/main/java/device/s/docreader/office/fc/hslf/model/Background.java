package device.s.docreader.office.fc.hslf.model;

import device.s.docreader.office.fc.ddf.EscherContainerRecord;

/**
 * Background shape
 *
 * @author Yegor Kozlov
 */
public final class Background extends Shape
{

    protected Background(EscherContainerRecord escherRecord, Shape parent)
    {
        super(escherRecord, parent);
    }

    protected EscherContainerRecord createSpContainer(boolean isChild)
    {
        return null;
    }

    /*public void draw(Graphics2D graphics)
    {
        Fill f = getFill();
        Dimension pg = getSheet().getSlideShow().getPageSize();
        Rectangle anchor = new Rectangle(0, 0, pg.width, pg.height);
        switch (f.getFillType())
        {
            case Fill.FILL_SOLID:
                Color color = f.getForegroundColor();
                graphics.setPaint(color);
                graphics.fill(anchor);
                break;
            case Fill.FILL_PICTURE:
                PictureData data = f.getPictureData();
                if (data instanceof Bitmap)
                {
                    BufferedImage img = null;
                    try
                    {
                        img = ImageIO.read(new ByteArrayInputStream(data.getData()));
                    }
                    catch(Exception e)
                    {
                        logger.log(POILogger.WARN, "ImageIO failed to create image. image.type: "
                            + data.getType());
                        return;
                    }
                    Image scaledImg = img.getScaledInstance(anchor.width, anchor.height,
                        Image.SCALE_SMOOTH);
                    graphics.drawImage(scaledImg, anchor.x, anchor.y, null);

                }
                break;
            default:
                logger.log(POILogger.WARN, "unsuported fill type: " + f.getFillType());
                break;
        }
    }*/
}
