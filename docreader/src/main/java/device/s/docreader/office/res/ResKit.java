/*
 * 文件名称:          ResKit.java
 *  
 * 编译器:            android2.2
 * 时间:              下午9:03:08
 */

package device.s.docreader.office.res;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class ResKit
{
    //
    private static ResKit kit = new ResKit();

    /**
     * 
     */
    public ResKit()
    {
        try
        {
            res = new HashMap<String, String>();
            // load "ResConstant"
            Class cls = Class.forName("device.s.docreader.office.res.ResConstant");
            // get all fields
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields)
            {
                res.put(field.getName(), (String)field.get(null));
            }
        }
        catch(Exception e)
        {

        }
    }
    
    /**
     * 
     */
    public static ResKit instance()
    {
        return kit;
    }
    
    /**
     * 
     */
    public boolean hasResName(String resName)
    {
        return res.containsKey(resName);
    }
    
    /**
     * 
     * @param resName
     * @return
     */
    public String getLocalString(String resName)
    {
        return res.get(resName);
    }

    //
    private Map<String, String> res;
}
