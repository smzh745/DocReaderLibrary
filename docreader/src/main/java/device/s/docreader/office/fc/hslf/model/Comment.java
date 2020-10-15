package device.s.docreader.office.fc.hslf.model;

import device.s.docreader.office.fc.hslf.record.Comment2000;

/**
 *
 * @author Nick Burch
 */
public final class Comment
{
    private Comment2000 _comment2000;

    public Comment(Comment2000 comment2000)
    {
        _comment2000 = comment2000;
    }

    protected Comment2000 getComment2000()
    {
        return _comment2000;
    }

    /**
     * Get the Author of this comment
     */
    public String getAuthor()
    {
        return _comment2000.getAuthor();
    }

    /**
     * Set the Author of this comment
     */
    public void setAuthor(String author)
    {
        _comment2000.setAuthor(author);
    }

    /**
     * Get the Author's Initials of this comment
     */
    public String getAuthorInitials()
    {
        return _comment2000.getAuthorInitials();
    }

    /**
     * Set the Author's Initials of this comment
     */
    public void setAuthorInitials(String initials)
    {
        _comment2000.setAuthorInitials(initials);
    }

    /**
     * Get the text of this comment
     */
    public String getText()
    {
        return _comment2000.getText();
    }

    /**
     * Set the text of this comment
     */
    public void setText(String text)
    {
        _comment2000.setText(text);
    }
}
