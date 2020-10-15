/*
 * 文件名称:           FileRenameDialog.java
 *  
 * 编译器:             android2.2
 * 时间:               下午2:41:49
 */
package device.s.docreader.office.officereader.filelist;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import device.s.docreader.R;
import device.s.docreader.office.constant.DialogConstant;
import device.s.docreader.office.system.IControl;
import device.s.docreader.office.system.IDialogAction;
import device.s.docreader.office.system.dialog.MessageDialog;

import java.io.File;
import java.util.Vector;


public class FileRenameDialog extends FileNameDialog
{
    /**
     * 
     * @param context
     * @param action
     * @param model
     * @param dialogID
     * @param titleResID
     */
    public FileRenameDialog(IControl control, Context context, IDialogAction action, Vector<Object> model, int dialogID, int titleResID)
    {
        super(control, context, action, model, dialogID, titleResID);
        initDialog();
    }
    
    /**
     * 
     */
    public void initDialog()
    {
        if (model != null)
        {
            File file = (File)model.get(0);
            String name = file.getName();
            if (file.isFile())
            {
                textView.setText(R.string.dialog_file_name);
                name = name.substring(0, name.lastIndexOf('.'));
            }
            else
            {
                textView.setText(R.string.dialog_folder_name);
            }
            editText.setText(name);
            editText.addTextChangedListener(watcher);
        }
    }
    
    /**
     * 
     */
    public void onClick(View v)
    {
        if (v == ok)
        {
            if(model == null)
            {
                dismiss();
                return;
            }
            File file = (File)model.get(0);
            String exe = "";
            if (file.isFile())
            {
                exe = file.getName();
                exe = exe.substring(exe.lastIndexOf('.'));
            }
            
            File newFile;
            String filePath = file.getParent();
            String name = editText.getText().toString().trim();
            if (filePath.endsWith(File.separator))
            {
                newFile = new File(filePath + name + exe);
            }
            else
            {
                newFile = new File(filePath + File.separator + name + exe);
            }
            Vector<Object> vector = new Vector<Object>();
            vector.add(file);
            vector.add(newFile);
            if (!newFile.exists())
            {
                action.doAction(dialogID, vector);
                dismiss();
            }
            else
            {
                CharSequence text = getContext().getResources().getText(R.string.error_file_type);
                String message = text.toString().replace("%s", name);
                new MessageDialog(control, getContext(), action, null, 
                    DialogConstant.MESSAGE_DIALOG_ID, 
                    R.string.error_file_type, message).show();
            }
        }
        else
        {
            dismiss();
        }
    }
    
    /**
     * 监听text
     */
    private TextWatcher watcher = new TextWatcher()
    {
        /**
         * 
         *
         */
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            
        }

        /**
         * 
         *
         */
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            ok.setEnabled(checkFileName(s.toString()));
        }

        /**
         * 
         *
         */
        public void afterTextChanged(Editable s)
        {
            
        }       
    };
 
    /**
     * 判断文件名是否合法
     * @param fileName
     * @return
     */
    public boolean checkFileName(String fileName)
    {
        if (model != null && ((File)model.get(0)).getName().equals(fileName))
        {
            return false;
        }
        return isFileNameOK(fileName);
    }
}
