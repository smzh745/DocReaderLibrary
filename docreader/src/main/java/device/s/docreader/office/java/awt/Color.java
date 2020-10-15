/*
 * %W% %E%
 *
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package device.s.docreader.office.java.awt;



public class Color implements java.io.Serializable
{


    public final static Color white = new Color(255, 255, 255);


    public final static Color WHITE = white;

    public final static Color lightGray = new Color(192, 192, 192);



    public final static Color LIGHT_GRAY = lightGray;

    public final static Color gray = new Color(128, 128, 128);

    public final static Color GRAY = gray;


    public final static Color darkGray = new Color(64, 64, 64);



    public final static Color DARK_GRAY = darkGray;


    public final static Color black = new Color(0, 0, 0);

    public final static Color BLACK = black;

    public final static Color red = new Color(255, 0, 0);


    public final static Color RED = red;


    public final static Color pink = new Color(255, 175, 175);


    public final static Color PINK = pink;

    public final static Color orange = new Color(255, 200, 0);


    public final static Color ORANGE = orange;


    public final static Color yellow = new Color(255, 255, 0);


    public final static Color YELLOW = yellow;

    public final static Color green = new Color(0, 255, 0);


    public final static Color GREEN = green;

    public final static Color magenta = new Color(255, 0, 255);


    public final static Color MAGENTA = magenta;


    public final static Color cyan = new Color(0, 255, 255);


    public final static Color CYAN = cyan;

    public final static Color blue = new Color(0, 0, 255);


    public final static Color BLUE = blue;


    transient private long pData;

    /**
     * The color value.
     * @serial
     * @see #getRGB
     */
    int value;

    /**
     * The color value in the default sRGB <code>ColorSpace</code> as
     * <code>float</code> components (no alpha).
     * If <code>null</code> after object construction, this must be an
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color value.
     * @serial
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    private float frgbvalue[] = null;

    /**
     * The color value in the native <code>ColorSpace</code> as
     * <code>float</code> components (no alpha).
     * If <code>null</code> after object construction, this must be an
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color value.
     * @serial
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    private float fvalue[] = null;

    /**
     * The alpha value as a <code>float</code> component.
     * If <code>frgbvalue</code> is <code>null</code>, this is not valid
     * data, so compute from the <code>int</code> color value.
     * @serial
     * @see #getRGBComponents
     * @see #getComponents
     */
    private float falpha = 0.0f;


    //private ColorSpace cs = null;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = 118526816881161077L;

    /**
     * Initialize JNI field and method IDs
     */
    private static native void initIDs();

    static
    {
        /** 4112352 - Calling getDefaultToolkit()
         ** here can cause this class to be accessed before it is fully
         ** initialized. DON'T DO IT!!!
         **
         ** Toolkit.getDefaultToolkit();
         **/

        /* ensure that the necessary native libraries are loaded */
        /*Toolkit.loadLibraries();
        if (!GraphicsEnvironment.isHeadless())
        {
            initIDs();
        }*/
    }

    /**
     * Checks the color integer components supplied for validity.
     * Throws an {@link IllegalArgumentException} if the value is out of
     * range.
     * @param r the Red component
     * @param g the Green component
     * @param b the Blue component
     **/
    private static void testColorValueRange(int r, int g, int b, int a)
    {
        boolean rangeError = false;
        String badComponentString = "";

        if (a < 0 || a > 255)
        {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if (r < 0 || r > 255)
        {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if (g < 0 || g > 255)
        {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if (b < 0 || b > 255)
        {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if (rangeError == true)
        {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                + badComponentString);
        }
    }

    /**
     * Checks the color <code>float</code> components supplied for
     * validity.
     * Throws an <code>IllegalArgumentException</code> if the value is out
     * of range.
     * @param r the Red component
     * @param g the Green component
     * @param b the Blue component
     **/
    private static void testColorValueRange(float r, float g, float b, float a)
    {
        boolean rangeError = false;
        String badComponentString = "";
        if (a < 0.0 || a > 1.0)
        {
            rangeError = true;
            badComponentString = badComponentString + " Alpha";
        }
        if (r < 0.0 || r > 1.0)
        {
            rangeError = true;
            badComponentString = badComponentString + " Red";
        }
        if (g < 0.0 || g > 1.0)
        {
            rangeError = true;
            badComponentString = badComponentString + " Green";
        }
        if (b < 0.0 || b > 1.0)
        {
            rangeError = true;
            badComponentString = badComponentString + " Blue";
        }
        if (rangeError == true)
        {
            throw new IllegalArgumentException("Color parameter outside of expected range:"
                + badComponentString);
        }
    }

    /**
     * Creates an opaque sRGB color with the specified red, green,
     * and blue values in the range (0 - 255).
     * The actual color used in rendering depends
     * on finding the best match given the color space
     * available for a given output device.
     * Alpha is defaulted to 255.
     *
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *        or <code>b</code> are outside of the range
     *        0 to 255, inclusive
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(int r, int g, int b)
    {
        this(r, g, b, 255);
    }

    /**
     * Creates an sRGB color with the specified red, green, blue, and alpha
     * values in the range (0 - 255).
     *
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>,
     *        <code>b</code> or <code>a</code> are outside of the range
     *        0 to 255, inclusive
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @param a the alpha component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public Color(int r, int g, int b, int a)
    {
        value = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
        testColorValueRange(r, g, b, a);
    }


    public Color(int rgb)
    {
        value = 0xff000000 | rgb;
    }


    public Color(int rgba, boolean hasalpha)
    {
        if (hasalpha)
        {
            value = rgba;
        }
        else
        {
            value = 0xff000000 | rgba;
        }
    }

    public Color(int rgb, int a)
    {
        value = ((a & 0xFF) << 24) | (rgb & 0xFFFFFF);
    }
    
    /**
     * Creates an opaque sRGB color with the specified red, green, and blue
     * values in the range (0.0 - 1.0).  Alpha is defaulted to 1.0.  The
     * actual color used in rendering depends on finding the best
     * match given the color space available for a particular output
     * device.
     *
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *        or <code>b</code> are outside of the range
     *        0.0 to 1.0, inclusive
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(float r, float g, float b)
    {
        this((int)(r * 255 + 0.5), (int)(g * 255 + 0.5), (int)(b * 255 + 0.5));
        testColorValueRange(r, g, b, 1.0f);
        frgbvalue = new float[3];
        frgbvalue[0] = r;
        frgbvalue[1] = g;
        frgbvalue[2] = b;
        falpha = 1.0f;
        fvalue = frgbvalue;
    }

    /**
     * Creates an sRGB color with the specified red, green, blue, and
     * alpha values in the range (0.0 - 1.0).  The actual color
     * used in rendering depends on finding the best match given the
     * color space available for a particular output device.
     * @throws IllegalArgumentException if <code>r</code>, <code>g</code>
     *        <code>b</code> or <code>a</code> are outside of the range
     *        0.0 to 1.0, inclusive
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @param a the alpha component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public Color(float r, float g, float b, float a)
    {
        this((int)(r * 255 + 0.5), (int)(g * 255 + 0.5), (int)(b * 255 + 0.5), (int)(a * 255 + 0.5));
        frgbvalue = new float[3];
        frgbvalue[0] = r;
        frgbvalue[1] = g;
        frgbvalue[2] = b;
        falpha = a;
        fvalue = frgbvalue;
    }


    public int getRed()
    {
        return (getRGB() >> 16) & 0xFF;
    }


    public int getGreen()
    {
        return (getRGB() >> 8) & 0xFF;
    }


    public int getBlue()
    {
        return (getRGB() >> 0) & 0xFF;
    }


    public int getAlpha()
    {
        return (getRGB() >> 24) & 0xff;
    }


    public int getRGB()
    {
        return value;
    }

    private static final double FACTOR = 0.7;


    public Color brighter()
    {
        int r = getRed();
        int g = getGreen();
        int b = getBlue();

        /* From 2D group:
         * 1. black.brighter() should return grey
         * 2. applying brighter to blue will always return blue, brighter
         * 3. non pure color (non zero rgb) will eventually return white
         */
        int i = (int)(1.0 / (1.0 - FACTOR));
        if (r == 0 && g == 0 && b == 0)
        {
            return new Color(i, i, i);
        }
        if (r > 0 && r < i)
            r = i;
        if (g > 0 && g < i)
            g = i;
        if (b > 0 && b < i)
            b = i;

        return new Color(Math.min((int)(r / FACTOR), 255), Math.min((int)(g / FACTOR), 255),
            Math.min((int)(b / FACTOR), 255));
    }


    public Color darker()
    {
        return new Color(Math.max((int)(getRed() * FACTOR), 0), Math.max(
            (int)(getGreen() * FACTOR), 0), Math.max((int)(getBlue() * FACTOR), 0));
    }

    public int hashCode()
    {
        return value;
    }


    public boolean equals(Object obj)
    {
        return obj instanceof Color && ((Color)obj).getRGB() == this.getRGB();
    }


    public String toString()
    {
        return getClass().getName() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue()
            + "]";
    }

    /**
     * Converts a <code>String</code> to an integer and returns the 
     * specified opaque <code>Color</code>. This method handles string
     * formats that are used to represent octal and hexidecimal numbers.
     * @param      nm a <code>String</code> that represents 
     *                            an opaque color as a 24-bit integer
     * @return     the new <code>Color</code> object.
     * @see        Integer#decode
     * @exception NumberFormatException  if the specified string cannot
     *                      be interpreted as a decimal, 
     *                      octal, or hexidecimal integer.
     * @since      JDK1.1
     */
    public static Color decode(String nm) throws NumberFormatException
    {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    /**
     * Finds a color in the system properties. 
     * <p>
     * The argument is treated as the name of a system property to 
     * be obtained. The string value of this property is then interpreted 
     * as an integer which is then converted to a <code>Color</code>
     * object. 
     * <p>
     * If the specified property is not found or could not be parsed as 
     * an integer then <code>null</code> is returned. 
     * @param    nm the name of the color property
     * @return   the <code>Color</code> converted from the system 
     * 		property.
     * @see      System#getProperty(String)
     * @see      Integer#getInteger(String)
     * @see      Color#Color(int)
     * @since    JDK1.0
     */
    public static Color getColor(String nm)
    {
        return getColor(nm, null);
    }

    /**
     * Finds a color in the system properties. 
     * <p>
     * The first argument is treated as the name of a system property to 
     * be obtained. The string value of this property is then interpreted 
     * as an integer which is then converted to a <code>Color</code>
     * object. 
     * <p>
     * If the specified property is not found or cannot be parsed as 
     * an integer then the <code>Color</code> specified by the second
     * argument is returned instead. 
     * @param    nm the name of the color property
     * @param    v    the default <code>Color</code>
     * @return   the <code>Color</code> converted from the system
     *		property, or the specified <code>Color</code>.
     * @see      System#getProperty(String)
     * @see      Integer#getInteger(String)
     * @see      Color#Color(int)
     * @since    JDK1.0
     */
    public static Color getColor(String nm, Color v)
    {
        Integer intval = Integer.getInteger(nm);
        if (intval == null)
        {
            return v;
        }
        int i = intval.intValue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }


    public static Color getColor(String nm, int v)
    {
        Integer intval = Integer.getInteger(nm);
        int i = (intval != null) ? intval.intValue() : v;
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, (i >> 0) & 0xFF);
    }


    public static int HSBtoRGB(float hue, float saturation, float brightness)
    {
        int r = 0, g = 0, b = 0;
        if (saturation == 0)
        {
            r = g = b = (int)(brightness * 255.0f + 0.5f);
        }
        else
        {
            float h = (hue - (float) Math.floor(hue)) * 6.0f;
            float f = h - (float) Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int)h)
            {
                case 0:
                    r = (int)(brightness * 255.0f + 0.5f);
                    g = (int)(t * 255.0f + 0.5f);
                    b = (int)(p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int)(q * 255.0f + 0.5f);
                    g = (int)(brightness * 255.0f + 0.5f);
                    b = (int)(p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int)(p * 255.0f + 0.5f);
                    g = (int)(brightness * 255.0f + 0.5f);
                    b = (int)(t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int)(p * 255.0f + 0.5f);
                    g = (int)(q * 255.0f + 0.5f);
                    b = (int)(brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int)(t * 255.0f + 0.5f);
                    g = (int)(p * 255.0f + 0.5f);
                    b = (int)(brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int)(brightness * 255.0f + 0.5f);
                    g = (int)(p * 255.0f + 0.5f);
                    b = (int)(q * 255.0f + 0.5f);
                    break;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }


    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals)
    {
        float hue, saturation, brightness;
        if (hsbvals == null)
        {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax)
            cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin)
            cmin = b;

        brightness = ((float)cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float)(cmax - cmin)) / ((float)cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else
        {
            float redc = ((float)(cmax - r)) / ((float)(cmax - cmin));
            float greenc = ((float)(cmax - g)) / ((float)(cmax - cmin));
            float bluec = ((float)(cmax - b)) / ((float)(cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    /**
     * Creates a <code>Color</code> object based on the specified values 
     * for the HSB color model. 
     * <p>
     * The <code>s</code> and <code>b</code> components should be
     * floating-point values between zero and one 
     * (numbers in the range 0.0-1.0).  The <code>h</code> component 
     * can be any floating-point number.  The floor of this number is 
     * subtracted from it to create a fraction between 0 and 1.  This 
     * fractional number is then multiplied by 360 to produce the hue
     * angle in the HSB color model.
     * @param  h   the hue component
     * @param  s   the saturation of the color
     * @param  b   the brightness of the color
     * @return  a <code>Color</code> object with the specified hue, 
     *                                 saturation, and brightness.
     * @since   JDK1.0
     */
    public static Color getHSBColor(float h, float s, float b)
    {
        return new Color(HSBtoRGB(h, s, b));
    }

    /**
     * Returns a <code>float</code> array containing the color and alpha
     * components of the <code>Color</code>, as represented in the default
     * sRGB color space.
     * If <code>compArray</code> is <code>null</code>, an array of length
     * 4 is created for the return value.  Otherwise, 
     * <code>compArray</code> must have length 4 or greater,
     * and it is filled in with the components and returned.
     * @param compArray an array that this method fills with 
     *			color and alpha components and returns
     * @return the RGBA components in a <code>float</code> array.
     */
    public float[] getRGBComponents(float[] compArray)
    {
        float[] f;
        if (compArray == null)
        {
            f = new float[4];
        }
        else
        {
            f = compArray;
        }
        if (frgbvalue == null)
        {
            f[0] = ((float)getRed()) / 255f;
            f[1] = ((float)getGreen()) / 255f;
            f[2] = ((float)getBlue()) / 255f;
            f[3] = ((float)getAlpha()) / 255f;
        }
        else
        {
            f[0] = frgbvalue[0];
            f[1] = frgbvalue[1];
            f[2] = frgbvalue[2];
            f[3] = falpha;
        }
        return f;
    }

    /**
     * Returns a <code>float</code> array containing only the color
     * components of the <code>Color</code>, in the default sRGB color
     * space.  If <code>compArray</code> is <code>null</code>, an array of
     * length 3 is created for the return value.  Otherwise,
     * <code>compArray</code> must have length 3 or greater, and it is
     * filled in with the components and returned.
     * @param compArray an array that this method fills with color 
     *		components and returns
     * @return the RGB components in a <code>float</code> array.
     */
    public float[] getRGBColorComponents(float[] compArray)
    {
        float[] f;
        if (compArray == null)
        {
            f = new float[3];
        }
        else
        {
            f = compArray;
        }
        if (frgbvalue == null)
        {
            f[0] = ((float)getRed()) / 255f;
            f[1] = ((float)getGreen()) / 255f;
            f[2] = ((float)getBlue()) / 255f;
        }
        else
        {
            f[0] = frgbvalue[0];
            f[1] = frgbvalue[1];
            f[2] = frgbvalue[2];
        }
        return f;
    }

    /**
     * Returns a <code>float</code> array containing the color and alpha
     * components of the <code>Color</code>, in the
     * <code>ColorSpace</code> of the <code>Color</code>.
     * If <code>compArray</code> is <code>null</code>, an array with
     * length equal to the number of components in the associated
     * <code>ColorSpace</code> plus one is created for
     * the return value.  Otherwise, <code>compArray</code> must have at
     * least this length and it is filled in with the components and
     * returned.
     * @param compArray an array that this method fills with the color and
     *		alpha components of this <code>Color</code> in its
     *		<code>ColorSpace</code> and returns
     * @return the color and alpha components in a <code>float</code> 
     * 		array.
     */
    public float[] getComponents(float[] compArray)
    {
        if (fvalue == null)
            return getRGBComponents(compArray);
        float[] f;
        int n = fvalue.length;
        if (compArray == null)
        {
            f = new float[n + 1];
        }
        else
        {
            f = compArray;
        }
        for (int i = 0; i < n; i++)
        {
            f[i] = fvalue[i];
        }
        f[n] = falpha;
        return f;
    }

    /**
     * Returns a <code>float</code> array containing only the color
     * components of the <code>Color</code>, in the
     * <code>ColorSpace</code> of the <code>Color</code>.
     * If <code>compArray</code> is <code>null</code>, an array with
     * length equal to the number of components in the associated
     * <code>ColorSpace</code> is created for
     * the return value.  Otherwise, <code>compArray</code> must have at
     * least this length and it is filled in with the components and
     * returned.
     * @param compArray an array that this method fills with the color
     *		components of this <code>Color</code> in its
     *		<code>ColorSpace</code> and returns
     * @return the color components in a <code>float</code> array.
     */
    public float[] getColorComponents(float[] compArray)
    {
        if (fvalue == null)
            return getRGBColorComponents(compArray);
        float[] f;
        int n = fvalue.length;
        if (compArray == null)
        {
            f = new float[n];
        }
        else
        {
            f = compArray;
        }
        for (int i = 0; i < n; i++)
        {
            f[i] = fvalue[i];
        }
        return f;
    }

    /**
     * Returns a <code>float</code> array containing the color and alpha
     * components of the <code>Color</code>, in the 
     * <code>ColorSpace</code> specified by the <code>cspace</code> 
     * parameter.  If <code>compArray</code> is <code>null</code>, an
     * array with length equal to the number of components in 
     * <code>cspace</code> plus one is created for the return value.
     * Otherwise, <code>compArray</code> must have at least this
     * length, and it is filled in with the components and returned.
     * @param cspace a specified <code>ColorSpace</code>
     * @param compArray an array that this method fills with the
     *		color and alpha components of this <code>Color</code> in
     *		the specified <code>ColorSpace</code> and returns
     * @return the color and alpha components in a <code>float</code> 
     * 		array.
     * /
    public float[] getComponents(ColorSpace cspace, float[] compArray)
    {
        if (cs == null)
        {
            cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        }
        float f[];
        if (fvalue == null)
        {
            f = new float[3];
            f[0] = ((float)getRed()) / 255f;
            f[1] = ((float)getGreen()) / 255f;
            f[2] = ((float)getBlue()) / 255f;
        }
        else
        {
            f = fvalue;
        }
        float tmp[] = cs.toCIEXYZ(f);
        float tmpout[] = cspace.fromCIEXYZ(tmp);
        if (compArray == null)
        {
            compArray = new float[tmpout.length + 1];
        }
        for (int i = 0; i < tmpout.length; i++)
        {
            compArray[i] = tmpout[i];
        }
        if (fvalue == null)
        {
            compArray[tmpout.length] = ((float)getAlpha()) / 255f;
        }
        else
        {
            compArray[tmpout.length] = falpha;
        }
        return compArray;
    }

    /**
     * Returns a <code>float</code> array containing only the color
     * components of the <code>Color</code> in the 
     * <code>ColorSpace</code> specified by the <code>cspace</code> 
     * parameter. If <code>compArray</code> is <code>null</code>, an array
     * with length equal to the number of components in 
     * <code>cspace</code> is created for the return value.  Otherwise,
     * <code>compArray</code> must have at least this length, and it is
     * filled in with the components and returned.
     * @param cspace a specified <code>ColorSpace</code>
     * @param compArray an array that this method fills with the color
     *		components of this <code>Color</code> in the specified
     * 		<code>ColorSpace</code>
     * @return the color components in a <code>float</code> array.
     * /
    public float[] getColorComponents(ColorSpace cspace, float[] compArray)
    {
        if (cs == null)
        {
            cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        }
        float f[];
        if (fvalue == null)
        {
            f = new float[3];
            f[0] = ((float)getRed()) / 255f;
            f[1] = ((float)getGreen()) / 255f;
            f[2] = ((float)getBlue()) / 255f;
        }
        else
        {
            f = fvalue;
        }
        float tmp[] = cs.toCIEXYZ(f);
        float tmpout[] = cspace.fromCIEXYZ(tmp);
        if (compArray == null)
        {
            return tmpout;
        }
        for (int i = 0; i < tmpout.length; i++)
        {
            compArray[i] = tmpout[i];
        }
        return compArray;
    }

    /**
     * Returns the <code>ColorSpace</code> of this <code>Color</code>.
     * @return this <code>Color</code> object's <code>ColorSpace</code>.
     * /
    public ColorSpace getColorSpace()
    {
        if (cs == null)
        {
            cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        }
        return cs;
    }

    // REMIND: this should really be a Ref!
    /**
     * The paint context used to generate a solid color pattern.
     * @see createContext()
     */
    //transient private PaintContext theContext;

    /**
     * Creates and returns a {@link PaintContext} used to generate a solid
     * color pattern.  This enables a <code>Color</code> object to be used
     * as an argument to any method requiring an object implementing the
     * {@link Paint} interface.
     * The same <code>PaintContext</code> is returned, regardless of
     * whether or not <code>r</code>, <code>r2d</code>,
     * <code>xform</code>, or <code>hints</code> are <code>null</code>.
     * @param cm the specified <code>ColorModel</code>
     * @param r the specified {@link Rectangle}
     * @param r2d the specified {@link Rectangle2D}
     * @param xform the specified {@link AffineTransform}
     * @param hints the specified {@link RenderingHints}
     * @return a <code>PaintContext</code> that is used to generate a
     *		solid color pattern.
     * @see Paint
     * @see PaintContext
     * @see Graphics2D#setPaint
     * /
    public synchronized PaintContext createContext(ColorModel cm, Rectangle r, Rectangle2D r2d,
        AffineTransform xform, RenderingHints hints)
    {
        PaintContext pc = theContext;
        if (pc == null || ((ColorPaintContext)pc).color != getRGB())
        {
            pc = new ColorPaintContext(getRGB(), cm);
            theContext = pc;
        }
        return pc;
    }

    /**
     * Returns the transparency mode for this <code>Color</code>.  This is
     * required to implement the <code>Paint</code> interface.
     * @return this <code>Color</code> object's transparency mode.
     * @see Paint
     * @see Transparency
     * @see #createContext
     */
    /*public int getTransparency()
    {
        int alpha = getAlpha();
        if (alpha == 0xff)
        {
            return Transparency.OPAQUE;
        }
        else if (alpha == 0)
        {
            return Transparency.BITMASK;
        }
        else
        {
            return Transparency.TRANSLUCENT;
        }
    }*/

}
