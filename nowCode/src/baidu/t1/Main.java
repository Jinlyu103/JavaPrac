package baidu.t1;

class A{
    public int f(int a, int b){
        return a*b;
    }
}

class B extends A{
    public int f(int a, int b){
        return a/b;
    }
}

public class Main {
    public static void main(String[] args) {
        A a = new B();
        B b = new B();

        System.out.println(a.f(30,5) + b.f(30,5));
    }
}
