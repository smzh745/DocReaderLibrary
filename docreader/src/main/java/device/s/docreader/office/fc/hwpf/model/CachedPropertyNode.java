package device.s.docreader.office.fc.hwpf.model;

import device.s.docreader.office.fc.hwpf.sprm.SprmBuffer;
import device.s.docreader.office.fc.util.Internal;

import java.lang.ref.SoftReference;


@ Internal
public final class CachedPropertyNode extends PropertyNode<CachedPropertyNode>
{
    protected SoftReference<Object> _propCache;

    public CachedPropertyNode(int start, int end, SprmBuffer buf)
    {
        super(start, end, buf);
    }

    protected void fillCache(Object ref)
    {
        _propCache = new SoftReference<Object>(ref);
    }

    protected Object getCacheContents()
    {
        return _propCache == null ? null : _propCache.get();
    }

    /**
     * @return This property's property in compressed form.
     */
    public SprmBuffer getSprmBuf()
    {
        return (SprmBuffer)_buf;
    }

}
