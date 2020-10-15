package device.s.docreader.office.fc.hwpf.usermodel;

import device.s.docreader.office.fc.poifs.filesystem.DirectoryEntry;
import device.s.docreader.office.fc.poifs.filesystem.Entry;
import device.s.docreader.office.fc.util.Internal;
import device.s.docreader.office.fc.util.POIUtils;

import java.io.FileNotFoundException;
import java.io.IOException;


@ Internal
public class ObjectPoolImpl implements ObjectsPool
{
    private DirectoryEntry _objectPool;

    public ObjectPoolImpl(DirectoryEntry _objectPool)
    {
        super();
        this._objectPool = _objectPool;
    }

    public Entry getObjectById(String objId)
    {
        if (_objectPool == null)
            return null;

        try
        {
            return _objectPool.getEntry(objId);
        }
        catch(FileNotFoundException exc)
        {
            return null;
        }
    }

    @ Internal
    public void writeTo(DirectoryEntry directoryEntry) throws IOException
    {
        if (_objectPool != null)
            POIUtils.copyNodeRecursively(_objectPool, directoryEntry);
    }
}
