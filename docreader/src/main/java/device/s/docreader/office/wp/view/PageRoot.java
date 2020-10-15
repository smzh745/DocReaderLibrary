/*
 * 文件名称:          PageRoot.java
 *  
 * 编译器:            android2.2
 * 时间:              下午9:21:56
 */
package device.s.docreader.office.wp.view;

import android.graphics.Canvas;

import device.s.docreader.office.constant.EventConstant;
import device.s.docreader.office.constant.MainConstant;
import device.s.docreader.office.constant.wp.WPModelConstant;
import device.s.docreader.office.constant.wp.WPViewConstant;
import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.simpletext.control.IWord;
import device.s.docreader.office.simpletext.model.IDocument;
import device.s.docreader.office.simpletext.view.AbstractView;
import device.s.docreader.office.simpletext.view.IRoot;
import device.s.docreader.office.simpletext.view.IView;
import device.s.docreader.office.simpletext.view.ViewContainer;
import device.s.docreader.office.system.IControl;
import device.s.docreader.office.wp.control.Word;

import java.util.ArrayList;
import java.util.List;


public class PageRoot extends AbstractView implements IRoot
{
        
    public PageRoot(Word word)
    {
        this.word = word;
        layoutThread = new LayoutThread(this);
        wpLayouter = new WPLayouter(this);
        viewContainer =  new ViewContainer();
        pages = new ArrayList<PageView>();
        canBackLayout = true;
        
    }

    /**
     * 
     */
    public short getType()
    {
        return WPViewConstant.PAGE_ROOT;
    }

    public int doLayout(int x, int y, int w, int h, int maxEnd, int flag)
    {
        try
        {
            IDocument doc = getDocument();
            setParaCount(doc.getParaCount(WPModelConstant.MAIN));
            wpLayouter.doLayout();
            if (!wpLayouter.isLayoutFinish() && !word.getControl().getMainFrame().isThumbnail())
            {
                layoutThread.start();
                word.getControl().actionEvent(EventConstant.SYS_SET_PROGRESS_BAR_ID, true);
                //word.getControl().getMainFrame().getActivity().setProgressBarIndeterminateVisibility(true);
            }
            // auto test code
            else
            {
                word.getControl().actionEvent(EventConstant.WP_LAYOUT_COMPLETED, true);
                word.getControl().actionEvent(EventConstant.SYS_AUTO_TEST_FINISH_ID, true);
            }
        }
        catch (Exception e)
        {
            word.getControl().getSysKit().getErrorKit().writerLog(e);
        }
        return WPViewConstant.BREAK_NO;
    }
    

    public synchronized void draw(Canvas canvas, int originX, int originY, float zoom)
    {
        super.draw(canvas, originX, originY, zoom);
    }
    

    public Rectangle modelToView(long offset, Rectangle rect, boolean isBack)
    {
        //IView view = getView(offset, WPViewConstant.PAGE_VIEW, isBack);
        IView view = viewContainer.getParagraph(offset, isBack);
        if (view != null)
        {
            view.modelToView(offset, rect, isBack);
            
            IView p = view.getParentView();
            while (p!= null && p.getType() != WPViewConstant.PAGE_ROOT)
            {
                /*if (p.getType() == WPViewConstant.TABLE_CELL_VIEW)
                {
                    rect.x += p.getX() + p.getLeftIndent();
                    rect.y += p.getY() + p.getTopIndent();
                }
                else*/
                {
                    rect.x += p.getX();
                    rect.y += p.getY();
                }
                p = p.getParentView();
            }
        }
        rect.x += getX();
        rect.y += getY();
        return rect;
        
    }
    
    

    public long viewToModel(int x, int y, boolean isBack)
    {
        x -= getX();
        y -= getY();
        IView view = getChildView();
        if (view != null && y > view.getY())
        {
            while (view != null)
            {
                if (y >= view.getY() && y <= view.getY() + view.getHeight() + MainConstant.GAP / 2)
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
    

    public IDocument getDocument()
    {
        return word.getDocument();
    }
    

    public IWord getContainer()
    {
        return word;
    }
    

    public IControl getControl()
    {
        return word.getControl();
    }
    

    public boolean canBackLayout()
    {
        return canBackLayout && !wpLayouter.isLayoutFinish();
    }

    /**
     * 
     */
    public synchronized void backLayout()
    {
        wpLayouter.backLayout();
        word.postInvalidate();
        // auto test code
        if (wpLayouter.isLayoutFinish())
        {
            word.getControl().actionEvent(EventConstant.SYS_AUTO_TEST_FINISH_ID, true);
            word.getControl().actionEvent(EventConstant.WP_LAYOUT_COMPLETED, true);
        }
        word.getControl().actionEvent(EventConstant.SYS_UPDATE_TOOLSBAR_BUTTON_STATUS, null);
        //
        LayoutKit.instance().layoutAllPage(this, word.getZoom());
        //
        word.layoutPrintMode();
    }


    public int getParaCount()
    {
        return paraCount;
    }


    public void setParaCount(int paraCount)
    {
        this.paraCount = paraCount;
    }
    


    public int getPageCount()
    {
        return getChildCount();
    }
    
    /**
     * 
     */
    public int getChildCount()
    {
        if (pages != null)
        {
            return pages.size();
        }
        return 1;
    }
    
    /**
     * 
     */
    public ViewContainer getViewContainer()
    {
        return this.viewContainer;
    }

    /**
     * 
     */
    public void addPageView(PageView pv)
    {
        pages.add(pv);
    }
    
    /**
     * 
     */
    public PageView getPageView(int pageIndex)
    {
        if (pageIndex < 0 || pageIndex >= pages.size())
        {
            return  null;
        }
        return pages.get(pageIndex);
    }
    

    public boolean checkUpdateHeaderFooterFieldText()
    {
    	boolean hasTotalPageCode = false;
    	for(PageView page : pages)
    	{
    		hasTotalPageCode = hasTotalPageCode || page.checkUpdateHeaderFooterFieldText(pages.size());
    	}
    	
    	return hasTotalPageCode;
    }

    public synchronized void dispose()
    {
        super.dispose();
        canBackLayout = false;
        if (layoutThread != null)
        {
            layoutThread.dispose();
            layoutThread = null;
        }
        if (wpLayouter != null)
        {
            wpLayouter.dispose();
            wpLayouter = null;
        }
        if (viewContainer != null)
        {
            viewContainer.dispose();
            viewContainer = null;
        }
        if (pages != null)
        {
            pages.clear();
            pages = null;
        }
        word = null;
    }
    
    // 文档的段落总数
    private int paraCount;
    // 是否可后台布局
    private boolean canBackLayout;
    // 后台布局线程
    private LayoutThread layoutThread;
    // 视图组件
    private Word word;
    //
    private WPLayouter wpLayouter;
    //
    private ViewContainer viewContainer;
    //
    private List<PageView> pages;
}
