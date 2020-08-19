package byteDance2021.t3;

import java.util.Scanner;

public class Main {
    /**
     * N个视频，每个视频长度为 Li 秒
     * 要在其中插入M条广告
     * 一个视频里的两个广告必须间隔一段时间，希望间隔时间尽可能长
     * 问：间隔时间最大可以设置为多少
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] L = new int[n];

        for (int i = 0; i<n; i++){
            L[i] = sc.nextInt();
        }


    }
}
