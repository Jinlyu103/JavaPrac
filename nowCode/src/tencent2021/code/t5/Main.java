package tencent2021.code.t5;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int Q = sc.nextInt();
        int[][] intervals = new int[Q][2];
        for (int i = 0; i<Q; i++){
            intervals[i][0] = sc.nextInt(); // l
            intervals[i][1] = sc.nextInt(); // r
        }

        //int[] res = new int[Q];
        for (int i = 0; i<Q; i++){
            int l = intervals[i][0];
            int r = intervals[i][1];

            String subs = s.substring(l-1, r);

            //f[n] = f[k-1] + f[j+1]+1   if s[k,j]回文

            int[] f = new int[subs.length()];
            Arrays.fill(f, Integer.MAX_VALUE);

            boolean[][] dp = new boolean[subs.length()][subs.length()];
            int start = 0;
            int maxLen = 0;
            int cnt = 0;
            int num = subs.length();

            for (int j = 0; j<subs.length(); j++){
                for (int k = 0; k<=j; k++){
                    if (subs.charAt(j) == subs.charAt(k) && j-k < 3){
                        dp[k][j] = true;
                    } else if (subs.charAt(j) == subs.charAt(k)){
                        dp[k][j] = dp[k+1][j-1];
                    } else {
                        dp[k][j] = false;
                    }
                    if (dp[k][j]) {
                        if (k>0 && j< subs.length()-1){
                            f[j] = Math.min(f[j], f[k-1] + f[j+1]+1);
                        }
                        else if (j < subs.length()-1)
                            f[j] = Math.min(f[j], f[j+1]+1);
                        else if (k > 0)
                            f[j] = Math.min(f[j], f[k-1] + 1);
                        else
                            f[j] = 1;
                    }
                }
            }
            System.out.println(f[subs.length()-1]);
        }
    }
}
