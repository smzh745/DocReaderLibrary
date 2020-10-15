package device.s.docreader.office.fc.hssf.usermodel;


import device.s.docreader.office.fc.hssf.record.CFRuleRecord;
import device.s.docreader.office.fc.hssf.record.cf.BorderFormatting;


/**
 * High level representation for Border Formatting component
 * of Conditional Formatting settings
 * 
 * @author Dmitriy Kumshayev
 *
 */
public final class HSSFBorderFormatting implements device.s.docreader.office.fc.ss.usermodel.BorderFormatting
{
	private final CFRuleRecord cfRuleRecord;
	private final BorderFormatting borderFormatting;
	
	protected HSSFBorderFormatting(CFRuleRecord cfRuleRecord)
	{
		this.cfRuleRecord = cfRuleRecord;
		this.borderFormatting = cfRuleRecord.getBorderFormatting();
	}

	protected BorderFormatting getBorderFormattingBlock()
	{
		return borderFormatting;
	}

	public short getBorderBottom()
	{
		return (short)borderFormatting.getBorderBottom();
	}

	public short getBorderDiagonal()
	{
		return (short)borderFormatting.getBorderDiagonal();
	}

	public short getBorderLeft()
	{
		return (short)borderFormatting.getBorderLeft();
	}

	public short getBorderRight()
	{
		return (short)borderFormatting.getBorderRight();
	}

	public short getBorderTop()
	{
		return (short)borderFormatting.getBorderTop();
	}

	public short getBottomBorderColor()
	{
		return (short)borderFormatting.getBottomBorderColor();
	}

	public short getDiagonalBorderColor()
	{
		return (short)borderFormatting.getDiagonalBorderColor();
	}

	public short getLeftBorderColor()
	{
		return (short)borderFormatting.getLeftBorderColor();
	}

	public short getRightBorderColor()
	{
		return (short)borderFormatting.getRightBorderColor();
	}

	public short getTopBorderColor()
	{
		return (short)borderFormatting.getTopBorderColor();
	}

	public boolean isBackwardDiagonalOn()
	{
		return borderFormatting.isBackwardDiagonalOn();
	}

	public boolean isForwardDiagonalOn()
	{
		return borderFormatting.isForwardDiagonalOn();
	}

	public void setBackwardDiagonalOn(boolean on)
	{
		borderFormatting.setBackwardDiagonalOn(on);
		if( on )
		{
			cfRuleRecord.setTopLeftBottomRightBorderModified(on);
		}
	}

	public void setBorderBottom(short border)
	{
		borderFormatting.setBorderBottom(border);
		if( border != 0)
		{
			cfRuleRecord.setBottomBorderModified(true);
		}
	}

	public void setBorderDiagonal(short border)
	{
		borderFormatting.setBorderDiagonal(border);
		if( border != 0)
		{
			cfRuleRecord.setBottomLeftTopRightBorderModified(true);
			cfRuleRecord.setTopLeftBottomRightBorderModified(true);
		}
	}

	public void setBorderLeft(short border)
	{
		borderFormatting.setBorderLeft(border);
		if( border != 0)
		{
			cfRuleRecord.setLeftBorderModified(true);
		}
	}

	public void setBorderRight(short border)
	{
		borderFormatting.setBorderRight(border);
		if( border != 0)
		{
			cfRuleRecord.setRightBorderModified(true);
		}
	}

	public void setBorderTop(short border)
	{
		borderFormatting.setBorderTop(border);
		if( border != 0)
		{
			cfRuleRecord.setTopBorderModified(true);
		}
	}

	public void setBottomBorderColor(short color)
	{
		borderFormatting.setBottomBorderColor(color);
		if( color != 0)
		{
			cfRuleRecord.setBottomBorderModified(true);
		}
	}

	public void setDiagonalBorderColor(short color)
	{
		borderFormatting.setDiagonalBorderColor(color);
		if( color != 0)
		{
			cfRuleRecord.setBottomLeftTopRightBorderModified(true);
			cfRuleRecord.setTopLeftBottomRightBorderModified(true);
		}
	}

	public void setForwardDiagonalOn(boolean on)
	{
		borderFormatting.setForwardDiagonalOn(on);
		if( on )
		{
			cfRuleRecord.setBottomLeftTopRightBorderModified(on);
		}
	}

	public void setLeftBorderColor(short color)
	{
		borderFormatting.setLeftBorderColor(color);
		if( color != 0)
		{
			cfRuleRecord.setLeftBorderModified(true);
		}
	}

	public void setRightBorderColor(short color)
	{
		borderFormatting.setRightBorderColor(color);
		if( color != 0)
		{
			cfRuleRecord.setRightBorderModified(true);
		}
	}

	public void setTopBorderColor(short color)
	{
		borderFormatting.setTopBorderColor(color);
		if( color != 0)
		{
			cfRuleRecord.setTopBorderModified(true);
		}
	}
}
