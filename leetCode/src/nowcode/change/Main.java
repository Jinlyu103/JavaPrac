package nowcode.change;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static int INF = 1<<10;
    public int change(int n){
        /**
         * 硬币面值:[1,4,16,64]
         * 纸币面值1024，购买了价值为n的商品，返回收到的最少硬币
         * 即：用最少的硬币构成1024-n
         * 动态规划：dp[i]凑成价值i所需的最少硬币数
         * 初始化：dp[0] = 0,
         *        dp[i] = INF, i的范围：[1,1024-n]
         * 转移方程：
         *      dp[i] = min(dp[i-coins[j]],dp[i])  if i >= coins[i]
         *      dp[i] = min(i, dp[i]) if i < coins[i]
         */
        int[] coins = {1,4,16,64};
        int[] dp = new int[1024-n + 1];

        if (n == 1024){
            return 0;
        }
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 1; i <= 1024 - n; i++) {
            for (int j = 0; j < coins.length ; j++) {
                if (i >= coins[j]){
                    dp[i] = Math.min(dp[i - coins[j]]+1, dp[i]);
                } else {
                    dp[i] = Math.min(dp[i], i);
                }

            }
        }
        return dp[1024-n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            Main sol = new Main();
            System.out.println(sol.change(n));
        }
    }
}
