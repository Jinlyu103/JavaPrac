package nowcode.byteDance_travel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /** 旅行商问题
     * 小明目前在做一份毕业旅行的规划。打算从北京出发，分别去若干个城市，
     * 然后再回到北京，每个城市之间均乘坐高铁，且每个城市只去一次。
     * 找到每个城市只访问一次并返回起点的最小车费花销
     */
    int ans = 1<<20;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] m = new int[n][n];
        for (int i=0;i<n;i++){
            for (int j = 0; j < n ; j++) {
                m[i][j] = sc.nextInt();
            }
        }
        sc.close();
        Main sol = new Main();
        System.out.println(sol.findSol(m,n));
    }

    private int findSol(int[][] m, int n){
        /**
         * 动态规划:
         * d(i,V):从i点出发，经过点集V各点一次之后回到出发点s的最短距离
         * d(i,V') = min{C_ik + d(k,V’-{k})} ，k∈V'
         * d(k,{}) = C_ik (k!=出发点)
         * 其中C_ik表示i到k的距离
         * 所以：
         *      d(k,V) = C_ik  if V为空，k!=出发点
         *             = min{C_kj, V-{j}} if V不为空且j∈V
         * 定义二维数组dp[N][M]表示d(i,V)的数据结构,N为城市的个数，M为V的子集（|V| = N-1）所以M= 2^(N-1)
         *用位运算表示：
         *          某个元素是否属于某个集合
         *          从集合中剔除某个元素
         */
        int[][] dp = new int[n][(1<<(n-1))];
        //计算第一列的值
        for (int i = 0; i < n; i++) {
            dp[i][0] = m[i][0];
        }
        //先更新列，再更新行
        for (int j = 1; j < (1<<(n-1)) ; j++) {
            for (int i = 0; i < n; i++) {
                dp[i][j] = 1<<20;
                //确保城市i不出现在j所代表的集合中,即数字j的二进制表示形式的第i位不为1
                if (((j>>(i-1)) & 1) != 1){
                    for (int k = 1; k<n; k++){
                        //确保城市k属于数字j所代表的集合
                        if (((j>>(k-1)) & 1) == 1){
                            //j ^ (1<<(k-1)) 表示从数字j代表的集合中删除第k个城市
                            if (dp[i][j] > m[i][k] + dp[k][j^(1<<(k-1))]){
                                dp[i][j] = m[i][k] + dp[k][j^(1<<(k-1))];
                            }
                        }
                    }
                }
            }
        }
        return dp[0][(1<<(n-1)) - 1];
    }

    private int findSol1(int[][] m, int n){
        /**
         * 回溯法求解
         * 超时
         */
        //存放已经访问过的城市
        List<Integer> cities = new ArrayList<Integer>();
        cities.add(0);
        backtrack(m,cities);
        return ans;
    }

    private void backtrack(int[][] m, List<Integer> cities){
        if (cities.size() == m.length){
            int cost = 0;
            int i = 0;
            for (int j = 1; j < cities.size() ; j++) {
                cost += m[i][j];
                i = j;
            }
            cost += m[i][0];
            ans = Math.min(ans,cost);
            return;
        }

        for (int i = 0; i < m.length ; i++) {
            if (cities.contains(i)){
                continue;
            }
            cities.add(i);
            backtrack(m, cities);
            cities.remove(cities.size()-1);
        }
    }
}
