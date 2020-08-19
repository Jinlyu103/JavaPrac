package huaweiPrac.hj15_;

import java.util.Scanner;

public class Main {
    /**
     * 华为机试练习 hj15： 求int型正整数在内存中存储时1的个数
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int count = 0;
        while (n > 0){
            if (n%2 == 1){
                count ++;
            }
            n = n>>1;
        }
        System.out.println(count);
    }
}
