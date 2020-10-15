/*
 * 文件名称:          RowView.java
 *  
 * 编译器:            android2.2
 * 时间:              下午2:09:35
 */
package device.s.docreader.office.wp.view;

import android.graphics.Rect;

import device.s.docreader.office.constant.wp.WPViewConstant;
import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.simpletext.model.IElement;
import device.s.docreader.office.simpletext.view.AbstractView;
import device.s.docreader.office.simpletext.view.IView;


public class RowView extends AbstractView
{

    /**
     * 
     * @param elem
     */
    public RowView(IElement elem)
    {
        this.elem = elem;
    }

    /**
     * 
     */
    public short getType()
    {
        return WPViewConstant.TABLE_ROW_VIEW;
    }

    public boolean intersection(Rect rect, int originX, int originY, float zoom)
    {
        return true;
    }
    

    public boolean isExactlyHeight()
    {
        return isExactlyHeight;
    }


    public void setExactlyHeight(boolean isExactlyHeight)
    {
        this.isExactlyHeight = isExactlyHeight;
    }
    
    /**
     * get cell view for index this is row
     */
    public CellView getCellView(short index)
    {
        int t = 0;
        CellView cellView = (CellView)getChildView();
        while (cellView !=  null)
        {
            if (t == index)
            {
                break;
            }
            t++;
            cellView = (CellView)cellView.getNextView();
        }
        return cellView;
    }

    

    public Rectangle modelToView(long offset, Rectangle rect, boolean isBack)
    {
        IView view = getView(offset, WPViewConstant.TABLE_CELL_VIEW, isBack);
        if (view != null)
        {
            view.modelToView(offset, rect, isBack);
        }
        rect.x += getX();
        rect.y += getY();
        return rect;        
    }    
    

    public long viewToModel(int x, int y, boolean isBack)
    {
        x -= getX();
        y -= getY();
        //IView view = getView(x, y, WPViewConstant.LINE_VIEW, isBack);
        IView view = getChildView();
        if (view != null && y > view.getY())
        {
            while (view != null)
            {
                if (y >= view.getY() && y < view.getY() + view.getLayoutSpan(WPViewConstant.Y_AXIS) 
                    && x >= view.getX() && x <= view.getX() + view.getLayoutSpan(WPViewConstant.X_AXIS))
                {
                    break;
                }
                view = view.getNextView();
            }
        }
        view = view == null ? getChildView() : view;
        if (view != null)
        {
            return view.viewToModel(x, y, isBack);
        }
        return -1;
    }
    
    
    /**
     * 
     */
    public void dispose()
    {
        super.dispose();
    }
    
    //
    private boolean isExactlyHeight;
    
}
