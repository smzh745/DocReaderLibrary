package device.s.docreader.office.fc.hwpf.usermodel;

import device.s.docreader.office.fc.hwpf.model.ListFormatOverride;
import device.s.docreader.office.fc.hwpf.model.ListFormatOverrideLevel;
import device.s.docreader.office.fc.hwpf.model.ListTables;
import device.s.docreader.office.fc.hwpf.model.PAPX;
import device.s.docreader.office.fc.hwpf.model.POIListLevel;
import device.s.docreader.office.fc.util.POILogFactory;
import device.s.docreader.office.fc.util.POILogger;


public final class ListEntry extends Paragraph
{
    private static POILogger log = POILogFactory.getLogger(ListEntry.class);

    POIListLevel _level;
    ListFormatOverrideLevel _overrideLevel;

    ListEntry(PAPX papx, Range parent, ListTables tables)
    {
        super(papx, parent);

        if (tables != null && _props.getIlfo() < tables.getOverrideCount())
        {
            ListFormatOverride override = tables.getOverride(_props.getIlfo());
            _overrideLevel = override.getOverrideLevel(_props.getIlvl());
            _level = tables.getLevel(override.getLsid(), _props.getIlvl());
        }
        else
        {
            log.log(
                POILogger.WARN,
                "No ListTables found for ListEntry - document probably partly corrupt, and you may experience problems");
        }
    }

    public int type()
    {
        return TYPE_LISTENTRY;
    }
}
