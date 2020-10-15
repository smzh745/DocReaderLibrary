package device.s.docreader.office.common;


public interface ICustomDialog
{
    //password dialog
    public static final byte DIALOGTYPE_PASSWORD = 0;
    //txt encode dialog
    public static final byte DIALOGTYPE_ENCODE = 1;
    //loading dialog
    public static final byte DIALOGTYPE_LOADING = 2;
    //error dialog
    public static final byte DIALOGTYPE_ERROR = 3;
    //
    public static final byte DIALOGTYPE_FIND = 4;
    


    public void showDialog(byte type);

    public void dismissDialog(byte type);
}
