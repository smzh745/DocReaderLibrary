package device.s.docreader.office.fc.hssf.usermodel;


import device.s.docreader.office.fc.hssf.record.CFRuleRecord;
import device.s.docreader.office.fc.hssf.record.cf.PatternFormatting;


/**
 * High level representation for Conditional Formatting settings
 * 
 * @author Dmitriy Kumshayev
 *
 */
public class HSSFPatternFormatting implements device.s.docreader.office.fc.ss.usermodel.PatternFormatting
{
	private final CFRuleRecord cfRuleRecord;
	private final PatternFormatting patternFormatting;
	
	protected HSSFPatternFormatting(CFRuleRecord cfRuleRecord)
	{
		this.cfRuleRecord = cfRuleRecord; 
		this.patternFormatting = cfRuleRecord.getPatternFormatting();
	}

	protected PatternFormatting getPatternFormattingBlock()
	{
		return patternFormatting;
	}

	/**
	 * @see PatternFormatting#getFillBackgroundColor()
	 */
	public short getFillBackgroundColor()
	{
		return (short)patternFormatting.getFillBackgroundColor();
	}

	/**
	 * @see PatternFormatting#getFillForegroundColor()
	 */
	public short getFillForegroundColor()
	{
		return (short)patternFormatting.getFillForegroundColor();
	}

	/**
	 * @see PatternFormatting#getFillPattern()
	 */
	public short getFillPattern()
	{
		return (short)patternFormatting.getFillPattern();
	}

	/**
	 * @param bg
	 * @see PatternFormatting#setFillBackgroundColor(int)
	 */
	public void setFillBackgroundColor(short bg)
	{
		patternFormatting.setFillBackgroundColor(bg);
		if( bg != 0)
		{
			cfRuleRecord.setPatternBackgroundColorModified(true);
		}
	}

	/**
	 * @param fg
	 * @see PatternFormatting#setFillForegroundColor(int)
	 */
	public void setFillForegroundColor(short fg)
	{
		patternFormatting.setFillForegroundColor(fg);
		if( fg != 0)
		{
			cfRuleRecord.setPatternColorModified(true);
		}
	}

	/**
	 * @param fp
	 * @see PatternFormatting#setFillPattern(int)
	 */
	public void setFillPattern(short fp)
	{
		patternFormatting.setFillPattern(fp);
		if( fp != 0)
		{
			cfRuleRecord.setPatternStyleModified(true);
		}
	}
}
