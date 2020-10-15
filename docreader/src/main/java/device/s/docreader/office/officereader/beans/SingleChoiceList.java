/*
 * 文件名称:           SingleChoiceDialog.java
 *  
 * 编译器:             android2.2
 * 时间:               上午11:13:35
 */
package device.s.docreader.office.officereader.beans;



import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import device.s.docreader.R;


public class SingleChoiceList extends ListView
{

    public SingleChoiceList(Context context, int itemsResID)
    {
        super(context);
        setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        String[] items = context.getResources().getStringArray(itemsResID);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
            context, R.layout.select_dialog_singlechoice, android.R.id.text1, items);
        setAdapter(adapter);
    }
    /**
     * 
     */
    public void dispose()
    {
    }
}
