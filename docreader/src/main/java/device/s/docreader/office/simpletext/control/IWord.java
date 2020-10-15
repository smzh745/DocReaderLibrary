/*
 * 文件名称:          IWord.java
 *  
 * 编译器:            android2.2
 * 时间:              上午9:56:16
 */
package device.s.docreader.office.simpletext.control;

import device.s.docreader.office.common.shape.IShape;
import device.s.docreader.office.java.awt.Rectangle;
import device.s.docreader.office.pg.animate.IAnimation;
import device.s.docreader.office.simpletext.model.IDocument;
import device.s.docreader.office.system.IControl;

/**
 * IWord 接口
 * <p>
 * <p>
 * Read版本:        Read V1.0
 * <p>
 * 作者:            ljj8494
 * <p>
 * 日期:            2012-7-27
 * <p>
 * 负责人:          ljj8494
 * <p>
 * 负责小组:         
 * <p>
 * <p>
 */
public interface IWord
{
    /**
     * @return Returns the highlight.
     */
    public IHighlight getHighlight();
    /**
     * 
     */
    public Rectangle modelToView(long offset, Rectangle rect, boolean isBack);
    /**
     * 
     */
    public IDocument getDocument();
    /**
     * 
     */
    public String getText(long start, long end);
    
    /**
     * @param x 为100%的值
     * @param y 为100%的值
     */
    public long viewToModel(int x, int y, boolean isBack);
    /**
     * 
     */
    public byte getEditType();
    
    /**
     * 
     * @param para
     * @return
     */
    public IAnimation getParagraphAnimation(int pargraphID);
    
    /**
     * 
     */
    public IShape getTextBox();
    
    /**
     * 
     */
    public IControl getControl();
    
    /**
     * 
     */
    public void dispose();
}
