package device.s.docreader.office.fc.util;

import device.s.docreader.office.fc.poifs.filesystem.POIFSFileSystem;

import java.io.FilterInputStream;
import java.io.InputStream;


/**
 * A wrapper around an {@link InputStream}, which
 *  ignores close requests made to it.
 *
 * Useful with {@link POIFSFileSystem}, where you want
 *  to control the close yourself.
 */
public class CloseIgnoringInputStream extends FilterInputStream {
   public CloseIgnoringInputStream(InputStream in) {
      super(in);
   }

   public void close() {
      // Does nothing and ignores you
      return;
   }
}
