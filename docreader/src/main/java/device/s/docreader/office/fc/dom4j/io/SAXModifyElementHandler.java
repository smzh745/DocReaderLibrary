

package device.s.docreader.office.fc.dom4j.io;

import device.s.docreader.office.fc.dom4j.Document;
import device.s.docreader.office.fc.dom4j.Element;
import device.s.docreader.office.fc.dom4j.ElementHandler;
import device.s.docreader.office.fc.dom4j.ElementPath;


class SAXModifyElementHandler implements ElementHandler
{
    private ElementModifier elemModifier;

    private Element modifiedElement;

    public SAXModifyElementHandler(ElementModifier elemModifier)
    {
        this.elemModifier = elemModifier;
    }

    public void onStart(ElementPath elementPath)
    {
        this.modifiedElement = elementPath.getCurrent();
    }

    public void onEnd(ElementPath elementPath)
    {
        try
        {
            Element origElement = elementPath.getCurrent();
            Element currentParent = origElement.getParent();

            if (currentParent != null)
            {
                // Clone sets parent + document to null
                Element clonedElem = (Element)origElement.clone();

                // Ask for modified element
                modifiedElement = elemModifier.modifyElement(clonedElem);

                if (modifiedElement != null)
                {
                    // Restore parent + document
                    modifiedElement.setParent(origElement.getParent());
                    modifiedElement.setDocument(origElement.getDocument());

                    // Replace old with new element in parent
                    int contentIndex = currentParent.indexOf(origElement);
                    currentParent.content().set(contentIndex, modifiedElement);
                }

                // Remove the old element
                origElement.detach();
            }
            else
            {
                if (origElement.isRootElement())
                {
                    // Clone sets parent + document to null
                    Element clonedElem = (Element)origElement.clone();

                    // Ask for modified element
                    modifiedElement = elemModifier.modifyElement(clonedElem);

                    if (modifiedElement != null)
                    {
                        // Restore parent + document
                        modifiedElement.setDocument(origElement.getDocument());

                        // Replace old with new element in parent
                        Document doc = origElement.getDocument();
                        doc.setRootElement(modifiedElement);
                    }

                    // Remove the old element
                    origElement.detach();
                }
            }

            // Put the new element on the ElementStack, it might get pruned by
            // the PruningDispatchHandler
            if (elementPath instanceof ElementStack)
            {
                ElementStack elementStack = ((ElementStack)elementPath);
                elementStack.popElement();
                elementStack.pushElement(modifiedElement);
            }
        }
        catch(Exception ex)
        {
            throw new SAXModifyException(ex);
        }
    }

    /**
     * DOCUMENT ME!
     * 
     * @return Returns the modified Element.
     */
    protected Element getModifiedElement()
    {
        return modifiedElement;
    }
}

