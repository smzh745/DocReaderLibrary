package device.s.docreader.office.common;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CriticalLock
{
    private CriticalLock()
    {
    }    
    
    public static void lock()
    {
        reentrantLock.lock();
    }
    
    public static void unlock()
    {
        reentrantLock.unlock();
    }
    
    private static Lock reentrantLock = new ReentrantLock();
}
