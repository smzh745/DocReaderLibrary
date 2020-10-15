// Copyright 2002, FreeHEP.
package device.s.docreader.office.thirdpart.emf.data;

import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.thirdpart.emf.EMFConstants;
import device.s.docreader.office.thirdpart.emf.EMFInputStream;
import device.s.docreader.office.thirdpart.emf.EMFTag;

import java.io.IOException;

public class ExtTextOutA extends AbstractExtTextOut implements EMFConstants {

    private TextA text;

    public ExtTextOutA() {
        super(83, 1, null, 0, 1, 1);
    }

    public ExtTextOutA(
        Rectangle bounds,
        int mode,
        float xScale,
        float yScale,
        TextA text) {

        super(83, 1, bounds, mode, xScale, yScale);
        this.text = text;
    }

    public EMFTag read(int tagID, EMFInputStream emf, int len)
            throws IOException {

        return new ExtTextOutA(
            emf.readRECTL(),
            emf.readDWORD(),
            emf.readFLOAT(),
            emf.readFLOAT(),
            TextA.read(emf));
    }

    public Text getText() {
        return text;
    }
}
