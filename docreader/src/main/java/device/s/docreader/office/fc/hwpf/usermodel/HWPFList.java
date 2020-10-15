package device.s.docreader.office.fc.hwpf.usermodel;

import device.s.docreader.office.fc.hwpf.model.ListFormatOverride;
import device.s.docreader.office.fc.hwpf.model.POIListData;
import device.s.docreader.office.fc.hwpf.model.POIListLevel;
import device.s.docreader.office.fc.hwpf.model.StyleSheet;
import device.s.docreader.office.fc.hwpf.sprm.CharacterSprmCompressor;
import device.s.docreader.office.fc.hwpf.sprm.ParagraphSprmCompressor;


/**
 * This class is used to create a list in a Word document. It is used in
 * conjunction with {@link
 * org.apache.poi.arc.fc.hwpf.HWPFDocument#registerList(HWPFList) registerList} in
 * {@link org.apache.poi.arc.fc.hwpf.HWPFDocument HWPFDocument}.
 *
 * In Word, lists are not ranged entities, meaning you can't actually add one
 * to the document. Lists only act as properties for list entries. Once you
 * register a list, you can add list entries to a document that are a part of
 * the list.
 *
 * The only benefit of this that I see, is that you can add a list entry
 * anywhere in the document and continue numbering from the previous list.
 *
 * @author Ryan Ackley
 */
public final class HWPFList
{
    private POIListData _listData;
    private ListFormatOverride _override;
    private boolean _registered;
    private StyleSheet _styleSheet;

    /**
     *
     * @param numbered true if the list should be numbered; false if it should be
     *        bulleted.
     * @param styleSheet The document's stylesheet.
     */
    public HWPFList(boolean numbered, StyleSheet styleSheet)
    {
        _listData = new POIListData((int)(Math.random() * System.currentTimeMillis()), numbered);
        _override = new ListFormatOverride(_listData.getLsid());
        _styleSheet = styleSheet;
    }

    /**
     * Sets the character properties of the list numbers.
     *
     * @param level the level number that the properties should apply to.
     * @param chp The character properties.
     */
    public void setLevelNumberProperties(int level, CharacterProperties chp)
    {
        POIListLevel listLevel = _listData.getLevel(level);
        int styleIndex = _listData.getLevelStyle(level);
        CharacterProperties base = _styleSheet.getCharacterStyle(styleIndex);

        byte[] grpprl = CharacterSprmCompressor.compressCharacterProperty(chp, base);
        listLevel.setNumberProperties(grpprl);
    }

    /**
     * Sets the paragraph properties for a particular level of the list.
     *
     * @param level The level number.
     * @param pap The paragraph properties
     */
    public void setLevelParagraphProperties(int level, ParagraphProperties pap)
    {
        POIListLevel listLevel = _listData.getLevel(level);
        int styleIndex = _listData.getLevelStyle(level);
        ParagraphProperties base = _styleSheet.getParagraphStyle(styleIndex);

        byte[] grpprl = ParagraphSprmCompressor.compressParagraphProperty(pap, base);
        listLevel.setLevelProperties(grpprl);
    }

    public void setLevelStyle(int level, int styleIndex)
    {
        _listData.setLevelStyle(level, styleIndex);
    }

    public POIListData getListData()
    {
        return _listData;
    }

    public ListFormatOverride getOverride()
    {
        return _override;
    }

}
