package device.s.docreader.office.fc.hwpf.usermodel;

/**
 * User-friendly interface to access document notes information
 * 
 * @author Sergey Vladimirov (vlsergey {at} gmail {doc} com)
 */
public interface Notes
{
    /**
     * Returns the location of note anchor in main textspace
     */
    int getNoteAnchorPosition(int index);

    /**
     * Returns count of notes in document
     */
    int getNotesCount();

    /**
     * Returns index of note (if exists, otherwise -1) with specified anchor
     * position
     */
    int getNoteIndexByAnchorPosition(int anchorPosition);

    /**
     * Returns the end offset of the text corresponding to the reference within
     * the footnote text address space
     */
    int getNoteTextEndOffset(int index);

    /**
     * Returns the start offset of the text corresponding to the reference
     * within the footnote text address space
     */
    int getNoteTextStartOffset(int index);
}
