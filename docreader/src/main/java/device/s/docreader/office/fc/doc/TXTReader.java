/*
 * 文件名称:          TXTReader.java
 *  
 * 编译器:            android2.2
 * 时间:              下午2:57:27
 */
package device.s.docreader.office.fc.doc;

import device.s.docreader.office.constant.MainConstant;
import device.s.docreader.office.constant.wp.WPModelConstant;
import device.s.docreader.office.simpletext.model.AttrManage;
import device.s.docreader.office.simpletext.model.IAttributeSet;
import device.s.docreader.office.simpletext.model.IDocument;
import device.s.docreader.office.simpletext.model.LeafElement;
import device.s.docreader.office.simpletext.model.ParagraphElement;
import device.s.docreader.office.simpletext.model.SectionElement;
import device.s.docreader.office.system.AbstractReader;
import device.s.docreader.office.system.IControl;
import device.s.docreader.office.wp.model.WPDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class TXTReader extends AbstractReader
{


    public TXTReader(IControl control, String filePath, String encoding)
    {
        this.control = control;
        this.filePath = filePath;
        this.encoding = encoding;
    }
    

    public boolean authenticate(String password)
    {
        if(encoding != null)
        {
            return true;
        }
        else
        {
            encoding = password;
            if(encoding != null)
            {
                try
                {  
                    control.actionEvent(MainConstant.HANDLER_MESSAGE_SUCCESS, getModel());
                    return true;
                }
                catch(Throwable e)
                {
                    control.getSysKit().getErrorKit().writerLog(e);
                }
            }  
        }
        
        return false;
    }
    
    /**
     * 
     */
    public Object getModel() throws Exception
    {
        if (wpdoc != null)
        {
            return wpdoc;
        }
        wpdoc = new WPDocument();
        if(encoding != null)
        {
            readFile();
        }        
        return wpdoc;
    }

    
    /**
     * 
     */
    public void readFile() throws Exception
    {
            // 建立章节
        SectionElement secElem = new SectionElement();
        // 属性
        IAttributeSet attr = secElem.getAttribute();
        // 宽度 default a4 paper
        AttrManage.instance().setPageWidth(attr, 11906);//;section.getPageWidth()); 
        // 高度 default a4 paper
        AttrManage.instance().setPageHeight(attr, 16838);//section.getPageHeight());
        // 左边距 default a4 paper
        AttrManage.instance().setPageMarginLeft(attr, 1800);//section.getMarginLeft());
        // 右边距 default a4 paper
        AttrManage.instance().setPageMarginRight(attr, 1800);//section.getMarginRight());
        // 上边距 default a4 paper
        AttrManage.instance().setPageMarginTop(attr, 1440);//section.getMarginTop());
        // 下边框 default a4 paper
        AttrManage.instance().setPageMarginBottom(attr, 1440);//section.getMarginBottom());            
        secElem.setStartOffset(offset);
        
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        String line;
        while ((line = br.readLine()) != null || offset == 0)
        {
            if (abortReader)
            {
                break;
            }
            /*if (line == null)
            {
                throw new Exception("Format error");
            }*/
            line = line == null ? "\n" : line.concat("\n");
            line = line.replace('\t', ' ');
            int len = line.length();
            if (len > 500)
            {
                int end = 200;
                int start = 0;
                while (end <= len)
                {
                    String str = line.substring(start, end).concat("\n");
                    
                    ParagraphElement paraElem = new ParagraphElement();
                    paraElem.setStartOffset(offset);
                    LeafElement leafElem = new LeafElement(str);
                    
                    leafElem.setStartOffset(offset);
                    offset += str.length();
                    leafElem.setEndOffset(offset);
                    paraElem.appendLeaf(leafElem);
                    paraElem.setEndOffset(offset);
                    wpdoc.appendParagraph(paraElem, WPModelConstant.MAIN);
                    if (end == len)
                    {
                        break;
                    }
                    start = end;
                    end += 100;
                    if (end > len)
                    {
                        end = len;
                    }
                }
            }
            else
            {
                ParagraphElement paraElem = new ParagraphElement();
                paraElem.setStartOffset(offset);
                LeafElement leafElem = new LeafElement(line);
                
                leafElem.setStartOffset(offset);
                offset += line.length();
                leafElem.setEndOffset(offset);
                paraElem.appendLeaf(leafElem);
                paraElem.setEndOffset(offset);
                wpdoc.appendParagraph(paraElem, WPModelConstant.MAIN);
            }
        }
        br.close();
        secElem.setEndOffset(offset);
        
        wpdoc.appendSection(secElem);      
    }
    
    
    /**
     * 
     *
     */
    public boolean searchContent(File file, String key) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        while ((line = br.readLine()) != null)
        {
            if (line.indexOf(key) > 0)
            {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * 
     *
     */
    public void dispose()
    {
        if (isReaderFinish())
        {
            wpdoc = null;
            filePath = null;
            control = null;
        }
    }
    
    //
    private long offset;
    //
    private String filePath;
    //
    private String encoding;
    //
    private IDocument wpdoc;
}
