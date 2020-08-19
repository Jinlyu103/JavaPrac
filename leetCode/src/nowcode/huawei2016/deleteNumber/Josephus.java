package nowcode.huawei2016.deleteNumber;

import java.util.Scanner;

public class Josephus {
    /**
     * 删数问题本质上就是约瑟夫环的问题: N = 5, M = 3
     * 约瑟夫环问题描述：N个人围成一圈，第一个人从1开始报数，报M的将被杀掉，下一个人接着从1开始报。
     *                 如此反复，最后剩下一个，求最后的胜利者。
     * 公式：
     *      f(N,M) = (f(N-1, M) + M) %N
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int res = f(n,m);
            System.out.println(res);
        }
    }

    public static int f(int n, int m){
        int p = 0;
        for (int i = 2; i<=n; i++){
            p = (p+m) %i;
        }
        return p; //返回结果是数组中的下标，如果是序号的话，需要加1
    }
}
