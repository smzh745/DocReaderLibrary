
package device.s.docreader.office.fc.doc;

import android.os.Handler;

import device.s.docreader.office.common.ICustomDialog;
import device.s.docreader.office.constant.DialogConstant;
import device.s.docreader.office.fc.fs.storage.HeaderBlock;
import device.s.docreader.office.fc.fs.storage.LittleEndian;
import device.s.docreader.office.system.FileReaderThread;
import device.s.docreader.office.system.IControl;
import device.s.docreader.office.system.IDialogAction;
import device.s.docreader.office.thirdpart.mozilla.intl.chardet.CharsetDetector;
import device.s.docreader.office.wp.dialog.TXTEncodingDialog;

import java.io.FileInputStream;
import java.util.Vector;


public class TXTKit
{
    //
    private static TXTKit kit = new TXTKit();
    //
    public static TXTKit instance()
    {
        return kit;
    }


    public void readText(final IControl control, final Handler handler, final String filePath)
    {              
        try
        {
            FileInputStream in = new FileInputStream(filePath);
            byte[] b = new byte[16];
            in.read(b);            
            long signature = LittleEndian.getLong(b, 0);
            if (signature == HeaderBlock._signature // doc, ppt, xls
                || signature == 0x0006001404034b50L) // docx, pptx, xls
            {
                in.close();
                control.getSysKit().getErrorKit().writerLog(new Exception("Format error"), true);
                return;
            }
            signature = signature & 0x00FFFFFFFFFFFFFFL;
            if (signature == 0x002e312d46445025L)
            {
                in.close();
                control.getSysKit().getErrorKit().writerLog(new Exception("Format error"), true);
                return;
            }
            in.close();
            
            String code = control.isAutoTest() ? "GBK" : CharsetDetector.detect(filePath);
            if (code != null)
            {
                new FileReaderThread(control, handler, filePath, code).start();
                return;
            }
            
            if(control.getMainFrame().isShowTXTEncodeDlg())
            {
                Vector<Object> vector = new Vector<Object>();
                vector.add(filePath);
                IDialogAction da = new IDialogAction()
                {                    
                    /**
                     * 
                     *
                     */
                    public IControl getControl()
                    {
                        return control;
                    }
                    
                    /**
                     * 
                     *
                     */
                    public void doAction(int id, Vector<Object> model)
                    {
                        if (TXTEncodingDialog.BACK_PRESSED.equals(model.get(0)))
                        {
                            control.getMainFrame().getActivity().onBackPressed();
                        }
                        else
                        {
                            new FileReaderThread(control, handler, filePath, model.get(0).toString()).start();
                        }
                    }
                    
                    /**
                     * 
                     *
                     */
                    public void dispose()
                    {
                        
                    }
                };                

                new TXTEncodingDialog(control, control.getMainFrame().getActivity(), da, 
                    vector, DialogConstant.ENCODING_DIALOG_ID).show();
                
            }
            else
            {
                String encode = control.getMainFrame().getTXTDefaultEncode();
                if(encode == null)
                {
                    ICustomDialog dlgListener = control.getCustomDialog();
                    if(dlgListener != null)
                    {
                        dlgListener.showDialog(ICustomDialog.DIALOGTYPE_ENCODE);
                    }
                    else
                    {
                        new FileReaderThread(control, handler, filePath, "UTF-8").start();
                    }
                }
                else
                {
                    new FileReaderThread(control, handler, filePath, encode).start();
                }
                return;
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }
    
    /**
     * 
     */
    public void reopenFile(final IControl control, final Handler handler, final String filePath, String encode)
    {
        new FileReaderThread(control, handler, filePath, encode).start();
    }
}
