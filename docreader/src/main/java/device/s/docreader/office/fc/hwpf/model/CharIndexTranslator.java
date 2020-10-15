package device.s.docreader.office.fc.hwpf.model;

import device.s.docreader.office.fc.util.Internal;

@ Internal
public interface CharIndexTranslator
{
    /**
     * Calculates the byte index of the given char index.
     * 
     * @param charPos
     *            The char position
     * @return The byte index
     */
    int getByteIndex(int charPos);

    /**
     * Calculates the char index of the given byte index.
     * Look forward if index is not in table
     *
     * @param bytePos The character offset to check 
     * @return the char index
     */
    int getCharIndex(int bytePos);

    /**
     * Calculates the char index of the given byte index.
     * Look forward if index is not in table
     *
     * @param bytePos The character offset to check
     * @param startCP look from this characted position 
     * @return the char index
     */
    int getCharIndex(int bytePos, int startCP);

    /**
     * Check if index is in table
     *
     * @param bytePos
     * @return true if index in table, false if not
     */

    boolean isIndexInTable(int bytePos);

    /**
     * Return first index >= bytePos that is in table
     *
     * @param bytePos
     * @return  first index greater or equal to bytePos that is in table
     */
    public int lookIndexForward(int bytePos);

    /**
     * Return last index <= bytePos that is in table
     *
     * @param bytePos
     * @return last index less of equal to bytePos that is in table
     */
    public int lookIndexBackward(int bytePos);

}
