package device.s.docreader.office.fc.hssf.model;

import device.s.docreader.office.fc.ddf.EscherOptRecord;
import device.s.docreader.office.fc.ddf.EscherProperties;
import device.s.docreader.office.fc.ddf.EscherProperty;
import device.s.docreader.office.fc.ddf.EscherSimpleProperty;
import device.s.docreader.office.fc.hssf.record.CommonObjectDataSubRecord;
import device.s.docreader.office.fc.hssf.record.NoteRecord;
import device.s.docreader.office.fc.hssf.record.NoteStructureSubRecord;
import device.s.docreader.office.fc.hssf.record.ObjRecord;
import device.s.docreader.office.fc.hssf.record.SubRecord;
import device.s.docreader.office.fc.hssf.usermodel.HSSFComment;
import device.s.docreader.office.fc.hssf.usermodel.HSSFShape;

import java.util.Iterator;
import java.util.List;


/**
 * Represents a cell comment.
 * This class converts highlevel model data from <code>HSSFComment</code>
 * to low-level records.
 *
 * @author Yegor Kozlov
 */
public final class CommentShape extends TextboxShape {

    private NoteRecord _note;

    /**
     * Creates the low-level records for a comment.
     *
     * @param hssfShape  The highlevel shape.
     * @param shapeId    The shape id to use for this shape.
     */
     public CommentShape( HSSFComment hssfShape, int shapeId )
    {
        super(hssfShape, shapeId);

        _note = createNoteRecord(hssfShape, shapeId);

        ObjRecord obj = getObjRecord();
        List<SubRecord> records = obj.getSubRecords();
        int cmoIdx = 0;
        for (int i = 0; i < records.size(); i++) {
            Object r = records.get(i);

            if (r instanceof CommonObjectDataSubRecord){
                //modify autofill attribute inherited from <code>TextObjectRecord</code>
                CommonObjectDataSubRecord cmo = (CommonObjectDataSubRecord)r;
                cmo.setAutofill(false);
                cmoIdx = i;
            }
        }
        //add NoteStructure sub record
        //we don't know it's format, for now the record data is empty
        NoteStructureSubRecord u = new NoteStructureSubRecord();
        obj.addSubRecord(cmoIdx+1, u);
    }

    /**
     * Creates the low level <code>NoteRecord</code>
     *  which holds the comment attributes.
     */
     private NoteRecord createNoteRecord( HSSFComment shape, int shapeId )
    {
        NoteRecord note = new NoteRecord();
        note.setColumn(shape.getColumn());
        note.setRow(shape.getRow());
        note.setFlags(shape.isVisible() ? NoteRecord.NOTE_VISIBLE : NoteRecord.NOTE_HIDDEN);
        note.setShapeId(shapeId);
        note.setAuthor(shape.getAuthor() == null ? "" : shape.getAuthor());
        return note;
    }

    /**
     * Sets standard escher options for a comment.
     * This method is responsible for setting default background,
     * shading and other comment properties.
     *
     * @param shape   The highlevel shape.
     * @param opt     The escher records holding the proerties
     * @return number of escher options added
     */
    protected int addStandardOptions( HSSFShape shape, EscherOptRecord opt )
    {
        super.addStandardOptions(shape, opt);

        //remove unnecessary properties inherited from TextboxShape
        List<EscherProperty> props = opt.getEscherProperties();
        for (Iterator<EscherProperty> iterator = props.iterator(); iterator.hasNext(); ) {
            EscherProperty prop = iterator.next();
            switch (prop.getId()){
                case EscherProperties.TEXT__TEXTLEFT:
                case EscherProperties.TEXT__TEXTRIGHT:
                case EscherProperties.TEXT__TEXTTOP:
                case EscherProperties.TEXT__TEXTBOTTOM:
                case EscherProperties.GROUPSHAPE__PRINT:
                case EscherProperties.FILL__FILLBACKCOLOR:
                case EscherProperties.LINESTYLE__COLOR:
                    iterator.remove();
                    break;
            }
        }

        HSSFComment comment = (HSSFComment)shape;
        opt.addEscherProperty( new EscherSimpleProperty( EscherProperties.GROUPSHAPE__PRINT, comment.isVisible() ? 0x000A0000 : 0x000A0002) );
        opt.addEscherProperty( new EscherSimpleProperty( EscherProperties.SHADOWSTYLE__SHADOWOBSURED, 0x00030003 ) );
        opt.addEscherProperty( new EscherSimpleProperty( EscherProperties.SHADOWSTYLE__COLOR, 0x00000000 ) );
        opt.sortProperties();
        return opt.getEscherProperties().size();   // # options added
    }

    /**
     * Return the <code>NoteRecord</code> holding the comment attributes
     *
     * @return <code>NoteRecord</code> holding the comment attributes
     */
    public NoteRecord getNoteRecord()
    {
        return _note;
    }

    @Override
    int getCmoObjectId(int shapeId){
        return shapeId;
    }

}
