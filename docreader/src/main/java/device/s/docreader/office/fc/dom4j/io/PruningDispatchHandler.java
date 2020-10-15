

package device.s.docreader.office.fc.dom4j.io;

import device.s.docreader.office.fc.dom4j.ElementPath;


class PruningDispatchHandler extends DispatchHandler
{
    public void onEnd(ElementPath elementPath)
    {
        super.onEnd(elementPath);

        if (getActiveHandlerCount() == 0)
        {
            elementPath.getCurrent().detach();
        }
    }
}

