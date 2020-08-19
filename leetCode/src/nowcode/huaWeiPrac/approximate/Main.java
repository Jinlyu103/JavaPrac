package nowcode.huaWeiPrac.approximate;

import java.util.Scanner;

public class Main {
    /**
     * 写出一个程序，接收一个正浮点数值，输出该数值的近似整数值
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float n = sc.nextFloat();
        int x = (int) n;
        if (n-x >= 0.5)
            System.out.println(x+1);
        else
            System.out.println(x);
    }
}
