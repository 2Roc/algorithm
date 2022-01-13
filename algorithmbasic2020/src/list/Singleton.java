package list;

public class Singleton {
    //懒汉式
    private static Singleton singleton;
    private Singleton(){}
    public static Singleton getSingleton(){
        if(singleton==null) {
            singleton = new Singleton();
        }
        //返回的是对象的引用，不是对象实例
            return singleton;
    }

    //饿汉式
    //private static Singleton singleton = new Singleton()

    public static void main(String[] args) {
        //Lock lock = new ReentrantLock();
        //lock.lockInterruptibly();
       //lock.newCondition();
        System.out.println(333%16);
        System.out.println(333&15);
        System.out.println(getSingleton()==getSingleton());
        System.out.println(getSingleton().equals(getSingleton()));
        System.out.println(getSingleton().equals(getSingleton()));
    }
}
