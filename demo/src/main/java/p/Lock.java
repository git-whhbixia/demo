package p;

/**
 * Create by Hercules
 * 2021-06-16 10:36
 */
public class Lock {

    public void test(){
        synchronized(Lock.class){
            System.out.println("hello  world");
        }
    }
}
