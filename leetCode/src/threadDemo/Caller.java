package threadDemo;

public class Caller extends Thread {
    private String message;
    private Call call;

    public Caller(String message, Call call){
        this.message = message;
        this.call = call;
        start();
    }

    public void run(){
        synchronized (call){
            call.action(message);
        }
    }
}

class Call{
    public Call(){}

    public void action(String message){
        System.out.print("[ "+ message );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(" ]");
    }
}

class Test{
    public static void main(String[] args) {
        Call c = new Call();
        Caller ca1 = new Caller("Hello", c);
        Caller ca2 = new Caller("World",c);
        Caller ca3 = new Caller("Synchronized",c);
    }
}
