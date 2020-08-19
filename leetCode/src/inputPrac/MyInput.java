package inputPrac;

import java.util.Scanner;

public class MyInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //创建Scanner对象
        while (sc.hasNext()){
            //第一行包含两个整数，n和m
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[n];
            //第二行给出n个整数a1,...,an
            for (int i = 0; i < n; i++){
                a[i] = sc.nextInt();
            }
            System.out.println("一共输入了两行数据，第一行：");
            System.out.println("n="+n+",m="+m);
            System.out.println("第二行：");
            for (int i:a) {
                System.out.print(i+",");
            }
        }
    }
}
