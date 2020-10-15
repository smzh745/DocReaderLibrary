
package device.s.docreader.office.wp.view;

import android.graphics.Canvas;

import device.s.docreader.office.constant.wp.WPViewConstant;
import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.simpletext.model.IElement;
import device.s.docreader.office.simpletext.view.AbstractView;
import device.s.docreader.office.simpletext.view.IView;


public class CellView extends AbstractView
{

    /**
     * 
     */
    public CellView(IElement elem)
    {
        this.elem = elem;
    }
    
    

    public void draw(Canvas canvas, int originX, int originY, float zoom)
    {
//        if (isMergedCell() && !isFirstMergedCell())
//        {
//            return;
//        }
//        Paint paint = new Paint();
//        int rowHeight = getParentView().getHeight();
//        paint.setStyle(Style.STROKE);
//        int dX = (int)(x * zoom) + originX;
//        int dY = (int)(y * zoom) + originY;
//        int w = (int)(getLayoutSpan(WPViewConstant.X_AXIS) * zoom);
//        int h = (int)(Math.max(height, rowHeight) * zoom);
//        //
//        canvas.drawRect(dX, dY,  dX + w, dY + h, paint);
//        
//        if (background != -1)
//        {
//            int old =  paint.getColor();
//            paint.setColor(background);
//            paint.setStyle(Style.FILL);
//            canvas.drawRect(dX + 1, dY + 1,  dX + w, dY + h, paint);
//            
//            paint.setColor(old);
//        }
        
        super.draw(canvas, originX, originY, zoom);
    }

    public Rectangle modelToView(long offset, Rectangle rect, boolean isBack)
    {
        IView view = getView(offset, WPViewConstant.LINE_VIEW, isBack);
        if (view != null)
        {
            view.modelToView(offset, rect, isBack);
        }
        rect.x += getX() + getLeftIndent();
        rect.y += getY() + getTopIndent();
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
                if (y >= view.getY() && y < view.getY() + view.getLayoutSpan(WPViewConstant.Y_AXIS))
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
    public short getType()
    {
        return WPViewConstant.TABLE_CELL_VIEW;
    }


    public boolean isFirstMergedCell()
    {
        return isFirstMergedCell;
    }



    public void setFirstMergedCell(boolean isFirstMergedCell)
    {
        this.isFirstMergedCell = isFirstMergedCell;
    }



    public boolean isMergedCell()
    {
        return isMergedCell;
    }


    public void setMergedCell(boolean isMergedCell)
    {
        this.isMergedCell = isMergedCell;
    }


    public boolean isValidLastCell()
    {
        if (getNextView() == null)
        {
            return true;
        }
        CellView nextCell = ((CellView)getNextView());
        if (isMergedCell())
        {
            return nextCell.isValidLastCell();
        }
        if (nextCell.getStartOffset(null) == 0 && nextCell.getEndOffset(null) == 0)
        {
            return nextCell.isValidLastCell();
        }
        
        return false;
    }
    
    /**
     * @return Returns the index.
     */
    public short getColumn()
    {
        return column;
    }


    /**
     * @param index The index to set.
     */
    public void setColumn(short column)
    {
        this.column = column;
    }


    public void setBackground(int color)
    {
        this.background = color;
    }
    
    protected int getBackground()
    {
        return this.background;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        super.dispose();
    }
    
    // first merge cell
    private boolean isFirstMergedCell;
    // merge cell 
    private boolean isMergedCell;
    // index in row
    private short column;
    //
    private int background = -1;
}
