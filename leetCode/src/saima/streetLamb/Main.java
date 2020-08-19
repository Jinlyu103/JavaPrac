package saima.streetLamb;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 计算两个坐标连续的路灯之间的最远距离maxD，d = maxD/2
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();

        int[] lamb = new int[n];
        for (int i = 0; i<n; i++){
            lamb[i] = sc.nextInt();
        }
        Arrays.sort(lamb);

        double d = Math.max(lamb[0], l-lamb[n-1]);

        for (int i = 1; i<n; i++){
            d = Math.max(d, (lamb[i] - lamb[i-1])/2.0);
        }
        d = Math.max(d, l - lamb[n-1]);
        System.out.println(String.format("%.2f", d));
    }
}
