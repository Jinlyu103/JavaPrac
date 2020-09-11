package tencent2021.code1.t5;

import java.util.Scanner;

public class Main {
    /**
     * 红黑棋
     * 2n个棋子排成一行，n黑色（1~n），n红色（1~n）
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            String str = sc.nextLine();
            int[] nums = new int[2*n];
            for (int i = 0; i<2*n; i++){
                nums[i] = sc.nextInt();
            }


        }

    }
}
