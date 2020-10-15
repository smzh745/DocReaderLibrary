package device.s.docreader.office.fc.dom4j.io;

import device.s.docreader.office.fc.dom4j.Document;
import device.s.docreader.office.fc.dom4j.DocumentException;
import device.s.docreader.office.fc.dom4j.DocumentFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SAXModifier
{
    private XMLWriter xmlWriter;

    private XMLReader xmlReader;

    private boolean pruneElements;

    private SAXModifyReader modifyReader;

    private HashMap modifiers = new HashMap();


    public SAXModifier()
    {
    }

    /**
     * Creates a new modifier. <br>
     * The XMLReader to parse the source will be created via the
     * org.xml.sax.driver system property or JAXP if the system property is not
     * set.
     * 
     * @param pruneElements
     *            Set to true when the modified document must NOT be kept in
     *            memory.
     */
    public SAXModifier(boolean pruneElements)
    {
        this.pruneElements = pruneElements;
    }

    /**
     * Creates a new modifier that will the specified {@link
     * XMLReader} to parse the source.
     * 
     * @param xmlReader
     *            The XMLReader to use
     */
    public SAXModifier(XMLReader xmlReader)
    {
        this.xmlReader = xmlReader;
    }

    /**
     * Creates a new modifier that will the specified {@link
     * XMLReader} to parse the source.
     * 
     * @param xmlReader
     *            The XMLReader to use
     * @param pruneElements
     *            Set to true when the modified document must NOT be kept in
     *            memory.
     */
    public SAXModifier(XMLReader xmlReader, boolean pruneElements)
    {
        this.xmlReader = xmlReader;
    }

    /**
     * Reads a Document from the given {@link File}and writes it to the
     * specified {@link XMLWriter}using SAX. Registered {@linkElementModifier}
     * objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>File</code> to read from.
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(File source) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given {@link InputSource}and
     * writes it to the specified {@link XMLWriter}using SAX. Registered
     * {@link ElementModifier}objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>org.xml.sax.InputSource</code> to read from.
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(InputSource source) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given {@link InputStream}and writes it
     * to the specified {@link XMLWriter}using SAX. Registered {@link
     * ElementModifier} objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>java.io.InputStream</code> to read from.
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(InputStream source) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given {@link InputStream}and writes it
     * to the specified {@link XMLWriter}using SAX. Registered {@link
     * ElementModifier} objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>java.io.InputStream</code> to read from.
     * @param systemId
     *            DOCUMENT ME!
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(InputStream source, String systemId) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given {@link Reader}and writes it to
     * the specified {@link XMLWriter}using SAX. Registered {@link
     * ElementModifier} objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>java.io.Reader</code> to read from.
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(Reader source) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given {@link Reader}and writes it to
     * the specified {@link XMLWriter}using SAX. Registered {@link
     * ElementModifier} objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>java.io.Reader</code> to read from.
     * @param systemId
     *            DOCUMENT ME!
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(Reader source, String systemId) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given {@link URL}and writes it to the
     * specified {@link XMLWriter}using SAX. Registered {@linkElementModifier}
     * objects are invoked on the fly.
     * 
     * @param source
     *            is the <code>java.net.URL</code> to read from.
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(URL source) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Reads a Document from the given URL or filename and writes it to the
     * specified {@link XMLWriter}using SAX. Registered {@linkElementModifier}
     * objects are invoked on the fly.
     * 
     * @param source
     *            is the URL or filename to read from.
     * 
     * @return the newly created Document instance
     * 
     * @throws DocumentException
     *             DocumentException org.dom4j.DocumentException} if an error
     *             occurs during parsing.
     */
    public Document modify(String source) throws DocumentException
    {
        try
        {
            return installModifyReader().read(source);
        }
        catch(SAXModifyException ex)
        {
            Throwable cause = ex.getCause();
            throw new DocumentException(cause.getMessage(), cause);
        }
    }

    /**
     * Adds the {@link ElementModifier}to be called when the specified element
     * path is encounted while parsing the source.
     * 
     * @param path
     *            The element path to be handled
     * @param modifier
     *            The {@link ElementModifier}to be called by the event based
     *            processor.
     */
    public void addModifier(String path, ElementModifier modifier)
    {
        this.modifiers.put(path, modifier);
    }

    /**
     * Removes all registered {@link ElementModifier}instances from the event
     * based processor.
     */
    public void resetModifiers()
    {
        this.modifiers.clear();
        getSAXModifyReader().resetHandlers();
    }

    /**
     * Removes the {@link ElementModifier}from the event based processor, for
     * the specified element path.
     * 
     * @param path
     *            The path to remove the {@link ElementModifier}for.
     */
    public void removeModifier(String path)
    {
        this.modifiers.remove(path);
        getSAXModifyReader().removeHandler(path);
    }


    public DocumentFactory getDocumentFactory()
    {
        return getSAXModifyReader().getDocumentFactory();
    }

    public void setDocumentFactory(DocumentFactory factory)
    {
        getSAXModifyReader().setDocumentFactory(factory);
    }

    /**
     * Returns the current {@link XMLWriter}.
     * 
     * @return XMLWriter
     */
    public XMLWriter getXMLWriter()
    {
        return this.xmlWriter;
    }

    /**
     * Sets the {@link XMLWriter}used to write the modified document.
     * 
     * @param writer
     *            The writer to use.
     */
    public void setXMLWriter(XMLWriter writer)
    {
        this.xmlWriter = writer;
    }


    public boolean isPruneElements()
    {
        return pruneElements;
    }

    private SAXReader installModifyReader() throws DocumentException
    {
        try
        {
            SAXModifyReader reader = getSAXModifyReader();

            if (isPruneElements())
            {
                modifyReader.setDispatchHandler(new PruningDispatchHandler());
            }

            reader.resetHandlers();

            Iterator modifierIt = this.modifiers.entrySet().iterator();

            while (modifierIt.hasNext())
            {
                Map.Entry entry = (Map.Entry)modifierIt.next();

                SAXModifyElementHandler handler = new SAXModifyElementHandler(
                    (ElementModifier)entry.getValue());
                reader.addHandler((String)entry.getKey(), handler);
            }

            reader.setXMLWriter(getXMLWriter());
            reader.setXMLReader(getXMLReader());

            return reader;
        }
        catch(SAXException ex)
        {
            throw new DocumentException(ex.getMessage(), ex);
        }
    }

    private XMLReader getXMLReader() throws SAXException
    {
        if (this.xmlReader == null)
        {
            xmlReader = SAXHelper.createXMLReader(false);
        }

        return this.xmlReader;
    }

    private SAXModifyReader getSAXModifyReader()
    {
        if (modifyReader == null)
        {
            modifyReader = new SAXModifyReader();
        }

        return modifyReader;
    }
}


