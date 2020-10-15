package device.s.docreader.office.common.borders;

import device.s.docreader.office.common.bg.BackgroundAndFill;

public class Line extends Border
{

	public Line()
	{
		setLineWidth((short)1);
	}

    public BackgroundAndFill getBackgroundAndFill()
    {
        return bgFill;
    }


    public void setBackgroundAndFill(BackgroundAndFill bgFill)
    {
        this.bgFill = bgFill;
    }
    
    public void setDash(boolean dash)
    {
    	this.dash = dash;
    }
    
    public boolean isDash()
    {
    	return dash;
    }
    
    public void dispose()
    {
    	if(bgFill != null)
    	{
    		bgFill = null;
    	}
    }
    
	// background
    private BackgroundAndFill bgFill;
    

    private boolean dash;
}
