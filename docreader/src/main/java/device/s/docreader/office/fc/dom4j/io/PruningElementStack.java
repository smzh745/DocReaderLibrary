package device.s.docreader.office.fc.dom4j.io;

import device.s.docreader.office.fc.dom4j.Element;
import device.s.docreader.office.fc.dom4j.ElementHandler;


class PruningElementStack extends ElementStack
{
    /** ElementHandler to call when pruning occurs */
    private ElementHandler elementHandler;

    /**
     * the element name path which denotes the node to remove from its parent
     * when it is complete (i.e. when it is popped from the stack). The first
     * entry in the path will be a child of the root node
     */
    private String[] path;

    /**
     * The level at which a path match can occur. We match when we have popped
     * the selected node so the and the lastElementIndex points to its parent so
     * this value should be path.length - 2
     */
    private int matchingElementIndex;

    public PruningElementStack(String[] path, ElementHandler elementHandler)
    {
        this.path = path;
        this.elementHandler = elementHandler;
        checkPath();
    }

    public PruningElementStack(String[] path, ElementHandler elementHandler, int defaultCapacity)
    {
        super(defaultCapacity);
        this.path = path;
        this.elementHandler = elementHandler;
        checkPath();
    }

    public Element popElement()
    {
        Element answer = super.popElement();

        if ((lastElementIndex == matchingElementIndex) && (lastElementIndex >= 0))
        {
            // we are popping the correct level in the tree
            // lets check if the path fits
            //
            // NOTE: this is an inefficient way of doing it - we could
            // maintain a history of which parts matched?
            if (validElement(answer, lastElementIndex + 1))
            {
                Element parent = null;

                for (int i = 0; i <= lastElementIndex; i++)
                {
                    parent = stack[i];

                    if (!validElement(parent, i))
                    {
                        parent = null;

                        break;
                    }
                }

                if (parent != null)
                {
                    pathMatches(parent, answer);
                }
            }
        }

        return answer;
    }

    protected void pathMatches(Element parent, Element selectedNode)
    {
        elementHandler.onEnd(this);
        parent.remove(selectedNode);
    }

    protected boolean validElement(Element element, int index)
    {
        String requiredName = path[index];
        String name = element.getName();

        if (requiredName == name)
        {
            return true;
        }

        if ((requiredName != null) && (name != null))
        {
            return requiredName.equals(name);
        }

        return false;
    }

    private void checkPath()
    {
        if (path.length < 2)
        {
            throw new RuntimeException("Invalid path of length: " + path.length
                + " it must be greater than 2");
        }

        matchingElementIndex = path.length - 2;
    }
}

