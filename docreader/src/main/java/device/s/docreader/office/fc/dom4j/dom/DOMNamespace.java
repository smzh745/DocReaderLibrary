

package device.s.docreader.office.fc.dom4j.dom;

import device.s.docreader.office.fc.dom4j.Element;
import device.s.docreader.office.fc.dom4j.tree.DefaultNamespace;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;


public class DOMNamespace extends DefaultNamespace implements Node
{
    public DOMNamespace(String prefix, String uri)
    {
        super(prefix, uri);
    }

    public DOMNamespace(Element parent, String prefix, String uri)
    {
        super(parent, prefix, uri);
    }

    // org.w3c.dom.Node interface
    // -------------------------------------------------------------------------
    public boolean supports(String feature, String version)
    {
        return DOMNodeHelper.supports(this, feature, version);
    }

    public String getNamespaceURI()
    {
        return DOMNodeHelper.getNamespaceURI(this);
    }

    // public String getPrefix() {
    // return DOMNodeHelper.getPrefix(this);
    // }
    public void setPrefix(String prefix) throws DOMException
    {
        DOMNodeHelper.setPrefix(this, prefix);
    }

    public String getLocalName()
    {
        return DOMNodeHelper.getLocalName(this);
    }

    public String getNodeName()
    {
        return getName();
    }

    // already part of API
    //
    // public short getNodeType();
    public String getNodeValue() throws DOMException
    {
        return DOMNodeHelper.getNodeValue(this);
    }

    public void setNodeValue(String nodeValue) throws DOMException
    {
        DOMNodeHelper.setNodeValue(this, nodeValue);
    }

    public Node getParentNode()
    {
        return DOMNodeHelper.getParentNode(this);
    }

    public NodeList getChildNodes()
    {
        return DOMNodeHelper.getChildNodes(this);
    }

    public Node getFirstChild()
    {
        return DOMNodeHelper.getFirstChild(this);
    }

    public Node getLastChild()
    {
        return DOMNodeHelper.getLastChild(this);
    }

    public Node getPreviousSibling()
    {
        return DOMNodeHelper.getPreviousSibling(this);
    }

    public Node getNextSibling()
    {
        return DOMNodeHelper.getNextSibling(this);
    }

    public NamedNodeMap getAttributes()
    {
        return DOMNodeHelper.getAttributes(this);
    }

    public Document getOwnerDocument()
    {
        return DOMNodeHelper.getOwnerDocument(this);
    }

    public Node insertBefore(Node newChild, Node refChild)
        throws DOMException
    {
        return DOMNodeHelper.insertBefore(this, newChild, refChild);
    }

    public Node replaceChild(Node newChild, Node oldChild)
        throws DOMException
    {
        return DOMNodeHelper.replaceChild(this, newChild, oldChild);
    }

    public Node removeChild(Node oldChild) throws DOMException
    {
        return DOMNodeHelper.removeChild(this, oldChild);
    }

    public Node appendChild(Node newChild) throws DOMException
    {
        return DOMNodeHelper.appendChild(this, newChild);
    }

    public boolean hasChildNodes()
    {
        return DOMNodeHelper.hasChildNodes(this);
    }

    public Node cloneNode(boolean deep)
    {
        return DOMNodeHelper.cloneNode(this, deep);
    }

    public void normalize()
    {
        DOMNodeHelper.normalize(this);
    }

    public boolean isSupported(String feature, String version)
    {
        return DOMNodeHelper.isSupported(this, feature, version);
    }

    public boolean hasAttributes()
    {
        return DOMNodeHelper.hasAttributes(this);
    }

    @Override
    public String getBaseURI()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public short compareDocumentPosition(Node other) throws DOMException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getTextContent() throws DOMException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTextContent(String textContent) throws DOMException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isSameNode(Node other)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String lookupPrefix(String namespaceURI)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String lookupNamespaceURI(String prefix)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEqualNode(Node arg)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object getFeature(String feature, String version)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getUserData(String key)
    {
        // TODO Auto-generated method stub
        return null;
    }
}


