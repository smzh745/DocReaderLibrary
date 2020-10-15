package device.s.docreader.office.fc.hwpf.sprm;

import device.s.docreader.office.fc.util.Internal;

@ Internal
public abstract class SprmUncompressor
{
    protected SprmUncompressor()
    {
    }

    /**
     * Converts an int into a boolean. If the int is non-zero, it returns true.
     * Otherwise it returns false.
     *
     * @param x The int to convert.
     *
     * @return A boolean whose value depends on x.
     */
    public static boolean getFlag(int x)
    {
        if (x != 0)
        {
            return true;
        }
        return false;
    }

}
