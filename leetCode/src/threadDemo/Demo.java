package threadDemo;

public class Demo {
    public static void f1(){
        Thread t = Thread.currentThread();
        System.out.println("Thread in f1: " +t);
        System.out.println("thread state: "+t.getState());
        f2();
    }

    public static void f2(){
        Thread t = Thread.currentThread();
        System.out.println("Thread in f2: "+t);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.currentThread();
        Thread t1 = new Thread();
        System.out.println("Thread in main: "+t);

        f1();
        System.out.println("thread state: "+t1.getState());
        t1.start();
        System.out.println("thread state: "+t1.getState());

        //System.out.println("thread state: "+t1.getState());
    }
}
