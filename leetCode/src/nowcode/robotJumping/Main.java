package nowcode.robotJumping;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 机器人跳跃：
         * 在第k个建筑，能量值为E,下一步跳到k+1个建筑
         * 如果： H[k+1]>E,机器人将失去H[K+1]-E的能量，即 E -= H[K+1]-E
         * 如果： H[k+1]<E，机器人将得到 E-H[k+1]的能量，即 E += E - H[k+1]
         * 在跳跃的过程中保证能量值不为负数
         * 那么机器人起始能量值为多少，才能保证完成游戏
         * [超时]
         * 枚举起始能量值：下界：第一个建筑的高度
         *                上界： 建筑物中最大高度
         * 动态规划：
         * dp[i]: 到达第i个建筑机器人的能量状态
         * dp[i] = dp[i-1] - (h[i] - dp[i-1])   if h[i] > dp[i-1]
         *       = dp[i-1] + (dp[i-1] -h[i])    otherwise
         * ==> 2*dp[i-1] = dp[i] + h[i]
         *      dp[i-1] = (dp[i] + h[i]) /2, 向上取整
         * ==>
         *      dp[i] = (dp[i+1] + h[i+1])/2 向上取整
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] H = new int[N+1];
        int[] dp = new int[N+1];

        for (int i=1; i <N+1; i++){
            H[i] = sc.nextInt();
        }
        for (int i = N-1; i>=0; i--){
            dp[i] = (dp[i+1] + H[i+1])/2;
            if ((dp[i+1] + H[i+1])%2 != 0){
                dp[i] = dp[i]+1;
            }
        }
        System.out.println(dp[0]);
    }

    private static boolean canFinish(int[] H, int E){
        for (int i = 1; i < H.length; i++) {
            if (H[i] > E){
                E -= H[i] -  E;
            } else {
                E += E-H[i];
            }
            if (E < 0){
                return false;
            }
        }
        return true;
    }
}
