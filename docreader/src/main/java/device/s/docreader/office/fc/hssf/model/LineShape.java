package device.s.docreader.office.fc.hssf.model;

import device.s.docreader.office.common.shape.ShapeTypes;
import device.s.docreader.office.fc.ddf.EscherBoolProperty;
import device.s.docreader.office.fc.ddf.EscherClientAnchorRecord;
import device.s.docreader.office.fc.ddf.EscherClientDataRecord;
import device.s.docreader.office.fc.ddf.EscherContainerRecord;
import device.s.docreader.office.fc.ddf.EscherOptRecord;
import device.s.docreader.office.fc.ddf.EscherProperties;
import device.s.docreader.office.fc.ddf.EscherRecord;
import device.s.docreader.office.fc.ddf.EscherShapePathProperty;
import device.s.docreader.office.fc.ddf.EscherSpRecord;
import device.s.docreader.office.fc.hssf.record.CommonObjectDataSubRecord;
import device.s.docreader.office.fc.hssf.record.EndSubRecord;
import device.s.docreader.office.fc.hssf.record.ObjRecord;
import device.s.docreader.office.fc.hssf.usermodel.HSSFAnchor;
import device.s.docreader.office.fc.hssf.usermodel.HSSFShape;
import device.s.docreader.office.fc.hssf.usermodel.HSSFSimpleShape;


/**
 * Represents a line shape and creates all the line specific low level records.
 *
 * @author Glen Stampoultzis (glens at apache.org)
 */
public class LineShape extends AbstractShape
{
    /**
     * Creates the line shape from the highlevel user shape.  All low level
     * records are created at this point.
     *
     * @param hssfShape     The user model shape.
     * @param shapeId       The identifier to use for this shape.
     */
    LineShape( HSSFSimpleShape hssfShape, int shapeId )
    {
        spContainer = createSpContainer(hssfShape, shapeId);
        objRecord = createObjRecord(hssfShape, shapeId);
    }

    /**
     * Creates the lowerlevel escher records for this shape.
     */
    private EscherContainerRecord createSpContainer(HSSFSimpleShape hssfShape, int shapeId)
    {
        HSSFShape shape = hssfShape;

        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherSpRecord sp = new EscherSpRecord();
        EscherOptRecord opt = new EscherOptRecord();
        EscherRecord anchor = new EscherClientAnchorRecord();
        EscherClientDataRecord clientData = new EscherClientDataRecord();

        spContainer.setRecordId( EscherContainerRecord.SP_CONTAINER );
        spContainer.setOptions( (short) 0x000F );
        sp.setRecordId( EscherSpRecord.RECORD_ID );
        sp.setOptions( (short) ( (ShapeTypes.Line << 4) | 0x2 ) );

        sp.setShapeId( shapeId );
        sp.setFlags( EscherSpRecord.FLAG_HAVEANCHOR | EscherSpRecord.FLAG_HASSHAPETYPE );
        opt.setRecordId( EscherOptRecord.RECORD_ID );
        opt.addEscherProperty( new EscherShapePathProperty( EscherProperties.GEOMETRY__SHAPEPATH, EscherShapePathProperty.COMPLEX ) );
        opt.addEscherProperty( new EscherBoolProperty( EscherProperties.LINESTYLE__NOLINEDRAWDASH, 1048592 ) );
        addStandardOptions(shape, opt);
        HSSFAnchor userAnchor = shape.getAnchor();
        if (userAnchor.isHorizontallyFlipped())
        {
            sp.setFlags(sp.getFlags() | EscherSpRecord.FLAG_FLIPHORIZ);
        }
        if (userAnchor.isVerticallyFlipped())
        {
            sp.setFlags(sp.getFlags() | EscherSpRecord.FLAG_FLIPVERT);
        }
        anchor = createAnchor(userAnchor);
        clientData.setRecordId( EscherClientDataRecord.RECORD_ID );
        clientData.setOptions( (short) 0x0000 );

        spContainer.addChildRecord(sp);
        spContainer.addChildRecord(opt);
        spContainer.addChildRecord(anchor);
        spContainer.addChildRecord(clientData);

        return spContainer;
    }

    /**
     * Creates the low level OBJ record for this shape.
     */
    private ObjRecord createObjRecord(HSSFShape hssfShape, int shapeId)
    {
        HSSFShape shape = hssfShape;

        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord c = new CommonObjectDataSubRecord();
        c.setObjectType((short) ((HSSFSimpleShape)shape).getShapeType());
        c.setObjectId(  getCmoObjectId(shapeId) );
        c.setLocked(true);
        c.setPrintable(true);
        c.setAutofill(true);
        c.setAutoline(true);
        EndSubRecord e = new EndSubRecord();

        obj.addSubRecord(c);
        obj.addSubRecord(e);

        return obj;
    }

    public EscherContainerRecord getSpContainer()
    {
        return spContainer;
    }

    public ObjRecord getObjRecord()
    {
        return objRecord;
    }
    
    private EscherContainerRecord spContainer;
    private ObjRecord objRecord;
}
