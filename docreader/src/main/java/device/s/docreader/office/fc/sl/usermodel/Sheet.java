package device.s.docreader.office.fc.sl.usermodel;

/**
 * Common parent of Slides, Notes and Masters
 */
public interface Sheet extends ShapeContainer {
	public SlideShow getSlideShow();

	public MasterSheet getMasterSheet();

	public Background getBackground();
}
