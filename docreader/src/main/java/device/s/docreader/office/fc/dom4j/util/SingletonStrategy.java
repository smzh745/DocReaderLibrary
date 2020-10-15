

package device.s.docreader.office.fc.dom4j.util;


public interface SingletonStrategy {
    /**
     * return a singleton instance of the class specified in setSingletonClass
     */
    Object instance();

    /**
     * reset the instance to a new instance for the implemented strategy
     */
    void reset();

    /**
     * set a singleton class name that will be used to create the singleton
     * based on the strategy implementation of this interface. The default
     * constructor of the class will be used and must be public.
     */
    void setSingletonClassName(String singletonClassName);
}

