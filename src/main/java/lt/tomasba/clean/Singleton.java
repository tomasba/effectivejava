package lt.tomasba.clean;

/**
 * 3.
 *
 * need a singleton?
 *  - use ENUM
 */

// singletons may be evil (sometimes called an antipattern) when testing, bugs prone. issues with serializable
// ensuring exactly same instance is deserialized.
//
// public class Singleton {
//
//    public static final Singleton SINGLETON = new Singleton();
//    private Singleton(){
//    }
//
//}

// if you want a singleton - use enum
// disatvantage - you're not allowed to extend from another class.
// but you're allowed to implement interfaces.

public enum Singleton {
    INSTANCE;
}