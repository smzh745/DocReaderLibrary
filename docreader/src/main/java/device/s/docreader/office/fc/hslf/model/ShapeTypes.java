package device.s.docreader.office.fc.hslf.model;


import device.s.docreader.office.fc.hslf.exceptions.HSLFException;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Contains all known shape types in PowerPoint
 *
 * @author Yegor Kozlov
 */
public final class ShapeTypes implements device.s.docreader.office.common.shape.ShapeTypes
{
    /**
     * Return name of the shape by id
     * @param type  - the id of the shape, one of the static constants defined in this class
     * @return  the name of the shape
     */
    public static String typeName(int type)
    {
        String name = (String)types.get(Integer.valueOf(type));
        return name;
    }

    public static HashMap types;
    static
    {
        types = new HashMap();
        try
        {
            Field[] f = device.s.docreader.office.common.shape.ShapeTypes.class.getFields();
            for (int i = 0; i < f.length; i++)
            {
                Object val = f[i].get(null);
                if (val instanceof Integer)
                {
                    types.put(val, f[i].getName());
                }
            }
        }
        catch(IllegalAccessException e)
        {
            throw new HSLFException("Failed to initialize shape types");
        }
    }

}
