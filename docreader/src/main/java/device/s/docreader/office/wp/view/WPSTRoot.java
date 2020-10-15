/*
 * 文件名称:          STRoot.java
 *  
 * 编译器:            android2.2
 * 时间:              下午5:22:20
 */
package device.s.docreader.office.wp.view;

import android.graphics.Canvas;

import device.s.docreader.office.constant.MainConstant;
import device.s.docreader.office.constant.wp.AttrIDConstant;
import device.s.docreader.office.constant.wp.WPAttrConstant;
import device.s.docreader.office.constant.wp.WPModelConstant;
import device.s.docreader.office.constant.wp.WPViewConstant;
import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.simpletext.control.IWord;
import device.s.docreader.office.simpletext.model.AttrManage;
import device.s.docreader.office.simpletext.model.IAttributeSet;
import device.s.docreader.office.simpletext.model.IDocument;
import device.s.docreader.office.simpletext.model.IElement;
import device.s.docreader.office.simpletext.view.AbstractView;
import device.s.docreader.office.simpletext.view.DocAttr;
import device.s.docreader.office.simpletext.view.IRoot;
import device.s.docreader.office.simpletext.view.IView;
import device.s.docreader.office.simpletext.view.PageAttr;
import device.s.docreader.office.simpletext.view.ParaAttr;
import device.s.docreader.office.simpletext.view.ViewContainer;
import device.s.docreader.office.simpletext.view.ViewKit;
import device.s.docreader.office.system.IControl;
import device.s.docreader.office.wp.model.WPDocument;


public class WPSTRoot extends AbstractView implements IRoot
{
    //
    private static TableLayoutKit tableLayout = new TableLayoutKit();

    public WPSTRoot(IWord container, IDocument doc, int elementIndex)
    {
        this.doc = doc;
        this.container = container;
        this.elementIndex = elementIndex;
        docAttr = new DocAttr();
        paraAttr = new ParaAttr();
        pageAttr = new PageAttr();
    }
    
    /**
     * 
     */
    public short getType()
    {
        return WPViewConstant.SIMPLE_ROOT;
    }
    
    /**
     * 
     */
    public IWord getContainer()
    {
        return container;
    }

    public IDocument getDocument()
    {
        return doc;
    }
    
    /**
     * 
     */
    public IControl getControl()
    {
        return container.getControl();
    }    


    public void doLayout()
    {
        IElement sec = ((WPDocument)doc).getTextboxSectionElementForIndex(elementIndex);
        AttrManage.instance().fillPageAttr(pageAttr, sec.getAttribute());
        IAttributeSet attr = doc.getSection(WPModelConstant.MAIN).getAttribute();
        int bodyWidth = 
        		(int)((AttrManage.instance().getPageWidth(attr) - AttrManage.instance().getPageMarginLeft(attr) - AttrManage.instance().getPageMarginRight(attr)) 
        				* MainConstant.TWIPS_TO_PIXEL);
        
        tableLayout.clearBreakPages();
        int dx = pageAttr.leftMargin;
        int dy = pageAttr.topMargin;
        setTopIndent(pageAttr.topMargin);
        setLeftIndent(pageAttr.leftMargin);
        
        int spanW = (isWrapLine ? pageAttr.pageWidth : bodyWidth) - pageAttr.leftMargin - pageAttr.rightMargin;
        //make sure the min layout width
        spanW = Math.max(IRoot.MINLAYOUTWIDTH, spanW);
        
        int spanH = Integer.MAX_VALUE;//pageAttr.pageHeight - pageAttr.topMargin - pageAttr.bottomMargin;
        // keep
        int flag = ViewKit.instance().setBitValue(0, WPViewConstant.LAYOUT_FLAG_KEEPONE, true);
        // 是否布局水平对齐方式，不自动换行和页面水平居中都不需要处理lineView水平对齐
        flag = ViewKit.instance().setBitValue(flag, WPViewConstant.LAYOUT_NOT_WRAP_LINE, 
            !isWrapLine || pageAttr.horizontalAlign == WPAttrConstant.PAGE_H_CENTER);
        
        long maxEnd = sec.getEndOffset();
        long currentLayoutOffset = sec.getStartOffset();
        int paraCount =  doc.getParaCount(maxEnd);
        if (paraCount == 0)
        {
            return;
        }
        IElement elem = doc.getParagraph(currentLayoutOffset);
        ParagraphView para;
        if (AttrManage.instance().hasAttribute(elem.getAttribute(), AttrIDConstant.PARA_LEVEL_ID))
        {
            elem = ((WPDocument)doc).getParagraph0(currentLayoutOffset);
            para = (ParagraphView)ViewFactory.createView(getControl(), elem, null, WPViewConstant.TABLE_VIEW);
        }
        else
        {   
            para = (ParagraphView)ViewFactory.createView(getControl(), elem, null, WPViewConstant.PARAGRAPH_VIEW);
        }
        appendChlidView(para);
        para.setStartOffset(currentLayoutOffset);
        para.setEndOffset(elem.getEndOffset());
        int contentHeight = 0;
        boolean firstPara = true;

        int breakType = 0;
        while (spanH > 0 && currentLayoutOffset < maxEnd && breakType != WPViewConstant.BREAK_LIMIT)
        {
            para.setLocation(dx, dy);
            // 表格段落
            if (para.getType() == WPViewConstant.TABLE_VIEW) 
            { 
                breakType = tableLayout.layoutTable(getControl(), doc, this, docAttr, pageAttr, paraAttr, 
                    (TableView)para, currentLayoutOffset, dx, dy, spanW, spanH, flag, false);
            }
            else
            {
                tableLayout.clearBreakPages();
                AttrManage.instance().fillParaAttr(getControl(), paraAttr, elem.getAttribute());
                breakType = LayoutKit.instance().layoutPara(getControl(), doc, docAttr, pageAttr, paraAttr, 
                    para, currentLayoutOffset, dx, dy, spanW, spanH, flag);
            }
            int paraHeight = para.getLayoutSpan(WPViewConstant.Y_AXIS);
            dy += paraHeight;
            currentLayoutOffset = para.getEndOffset(null);
            spanH -= paraHeight;
            contentHeight += paraHeight;
            maxParaWidth = Math.max(maxParaWidth, para.getLayoutSpan(WPViewConstant.X_AXIS));
            if (spanH > 0 && currentLayoutOffset < maxEnd)
            {
                elem = doc.getParagraph(currentLayoutOffset);
                if(elem == null)
                {
                    break;
                }
                if (AttrManage.instance().hasAttribute(elem.getAttribute(), AttrIDConstant.PARA_LEVEL_ID))
                {
                    elem = ((WPDocument)doc).getParagraph0(currentLayoutOffset);
                    para = (ParagraphView)ViewFactory.createView(getControl(), elem, null, WPViewConstant.TABLE_VIEW);                    
                }
                else
                {
                    para = (ParagraphView)ViewFactory.createView(getControl(), elem, null, WPViewConstant.PARAGRAPH_VIEW);
                }
                para.setStartOffset(currentLayoutOffset);
                appendChlidView(para);
            }
            firstPara = false;
        }
        
        if(!isWrapLine)
        {
            paraAlign(maxParaWidth);
        }
        
        layoutPageAlign(contentHeight, maxParaWidth);
        
        if(!isWrapLine)
        {
        	pageAttr.pageWidth = bodyWidth;
        }
    }  

    /**
     * 
     */
    private void paraAlign(int maxParaWidth)
    {
       IView paraView = getChildView();
       while (paraView != null)
       {
           paraAttr.horizontalAlignment = (byte)AttrManage.instance().getParaHorizontalAlign(paraView.getElement().getAttribute());
           IView line = paraView.getChildView();
           while (line != null)
           {
               if (line.getType() == WPViewConstant.LINE_VIEW)
               {
                   ((LineView)line).layoutAlignment(docAttr, pageAttr, paraAttr, ((ParagraphView)paraView).getBNView(), maxParaWidth, 0, false);
               }
               line = line.getNextView();
           }
           paraView = paraView.getNextView();
       }
    }
    
    /**
     * 
     */
    private void layoutPageAlign(int contentHeight, int maxParaWidth)
    {
        int spanHeight = pageAttr.pageHeight - pageAttr.topMargin - pageAttr.bottomMargin;
        int addSpan = 0;
        switch(pageAttr.verticalAlign)
        {
            case WPAttrConstant.PAGE_V_CENTER:
                addSpan = (spanHeight - contentHeight) / 2;
                break;
            case WPAttrConstant.PAGE_V_BOTTOM:
                addSpan = spanHeight - contentHeight;
                break;
            default:
                break;
        }
        // word 中text只有当文本高度小于shape高度才需要做垂直布局
        if (addSpan < 0)
        {
            return;
        }
        setY(addSpan);
        setTopIndent(addSpan);
        //
        if (pageAttr.horizontalAlign == WPAttrConstant.PAGE_H_CENTER)
        {
            int pageWidth = pageAttr.pageWidth - pageAttr.leftMargin - pageAttr.rightMargin;
            addSpan = (pageWidth - maxParaWidth) / 2;
            IView view = getChildView();
            while (view != null)
            {
                paraAttr.horizontalAlignment = (byte)AttrManage.instance().getParaHorizontalAlign(view.getElement().getAttribute());
                IView line = view.getChildView();
                while (line != null && line.getType() == WPViewConstant.LINE_VIEW)
                {                    
                    ((LineView)line).layoutAlignment(docAttr, pageAttr, paraAttr, ((ParagraphView)view).getBNView(), maxParaWidth, 0, false);
                    line.setX(line.getX() + addSpan);
                    line = line.getNextView();
                }
                view = view.getNextView();
            }
        }
    }
    
    public String getText()
    {
        String text = "";
        IView view = getChildView();
        while (view != null)
        {
            text +=((ParagraphView)view).getText();
            view = view.getNextView();
        }
        
        return text;
    }
    

    public void draw(Canvas canvas, int originX, int originY, float zoom)
    {
        canvas.save();
        canvas.clipRect(originX, originY, originX + pageAttr.pageWidth * zoom, originY + (pageAttr.pageHeight  - pageAttr.bottomMargin) * zoom);
        super.draw(canvas, originX, originY, zoom);
        canvas.restore();
    }      
    
    /**
     * 
     *
     */
    public boolean canBackLayout()
    {
        return false;
    }

    /**
     * 
     *
     */
    public void backLayout()
    {
        
    }

    public Rectangle modelToView(long offset, Rectangle rect, boolean isBack)
    {
        IView view = getView(offset, WPViewConstant.PARAGRAPH_VIEW, isBack);
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
        IView view = getChildView();
        while (view != null)
        {
            if (y >= view.getY() && y < view.getY() + view.getLayoutSpan(WPViewConstant.Y_AXIS))
            {
                break;
            }
            view = view.getNextView();
        }
        if (view != null)
        {
            return view.viewToModel(x, y, isBack);
        }
        return -1;
    }   

    
    /**
     * 
     */
    public ViewContainer getViewContainer()
    {
        return null;
    }
    
    public int getAdjustTextboxWidth()
    {
    	return maxParaWidth + pageAttr.leftMargin + pageAttr.rightMargin;
    }
    
    /**
     * 
     */
    public void setWrapLine(boolean b)
    {
        this.isWrapLine = b;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        super.dispose();
        doc = null;
        container = null;
        pageAttr = null;
        paraAttr = null;
        docAttr = null;
    }

    //
    private boolean isWrapLine;
    //
    private int elementIndex;
    //
    private IDocument doc;
    //
    private PageAttr pageAttr;
    //
    private ParaAttr paraAttr;
    //
    private DocAttr docAttr;
    //
    private IWord container;
    //max paragraph width
    private int maxParaWidth;
}
