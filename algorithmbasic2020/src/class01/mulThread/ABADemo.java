package class01.mulThread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference<Integer> refer = new AtomicReference<>(128);
    static AtomicStampedReference<Integer> stamp = new AtomicStampedReference<>(100,1);

    //手写自旋锁
    static AtomicReference<Thread> threadRef = new AtomicReference<>();

    public static void main(String[] args) {
        Integer a1 = 1000000;
        int a2 = 1457777777;
        Short s1 = 122;
        short s2 = 277;
        Float f1 = 2.12f;
        float f2 = 3.33f;
        System.out.println(f1==f2);
        System.out.println(a1==a2);
        System.out.println(a1.equals(a2));
        System.out.println(s1.equals(a2));
        System.out.println(s1==a2);
        System.out.println(s1.equals(a2));
        System.out.println(a1.equals(s2));
        System.out.println(a1==s2);
        System.out.println(a1.equals(s1));
//        System.out.println(a1==s1);
        System.out.println(a2==s2);
        new Thread(()->{
            try {
                refer.compareAndSet(128,129);
                refer.compareAndSet(129,128);
            }catch(Exception e){
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                //会有ABA的问题,但是当处于-128～127的整数时候才能更改
                System.out.println(refer.compareAndSet(128, 2019) + "\t" + refer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3");
//        new Thread(()->{
//            try {
//                new Thread({
//
//                })
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        })
    }
}
