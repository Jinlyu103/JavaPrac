package huaweiPrac.hj16_shoppingList;

import java.util.*;

public class Main {
    /**
     * 华为机试练习 hj16:购物单
     * 可转化为背包问题
     * 有依赖的01背包问题（精明的预算方案）
     * 思路：考虑到每个主件最多只有两个附件，因此我们可以通过转化，将原问题转化为01背包问题来解决。
     *      1、在用01背包之前，需要对输入数据进行处理，把每一种物品归类，即把每一个主件和它的附件看作一类物品。
     *      2、分好类之后，就可以使用01背包算法。在取某件物品的时候只需要从以下四种方案中取最大的那种方案：
     *          1）只取主件
     *          2）取主件+附件1
     *          3）取主件+附件2
     *          4）取主件+附件1+附件2
     *          得到以下状态转移方程：
     *      f[i,j] = max(f[i-1,j],
     *                  f[i-1, j-a[i,0]] + a[i,0] * b[i,0],
     *                  f[i-1,j-a[i,0]-a[i,1]]+a[i,0]*b[i,0]+a[i,1]*b[i,1]，
     *                  f[i-1,j-a[i,0]-a[i,2]]+a[i,0]*b[i,0]+a[i,2]*b[i,2]，
     *                  f[i-1,j-a[i,0]-a[i,1]-a[i,2]]+a[i,0]*b[i,0]+a[i,1]*b[i,1]+a[i,2]*b[i,2])
     *     其中，f[i,j]表示用j元钱，买前i类物品所得的最大值。a[i,0]，a[i,1]，a[i,2]分别表示主件，第i类物品第1,2个附件的价格
     *     b[i,0], b[i,1], b[i,2]分别表示主件，第1个附件，第2个附件的重要度
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //总钱数
        int m = sc.nextInt(); //希望购买的物品个数
        int[][] goods = new int[m+1][3];

        int[][] f = new int[m+1][N+1];
        int min = Integer.MAX_VALUE;

        //key为主件的编号，value为该主件用到的附件
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int j = 1; j<=m; j++){
            goods[j][0] = sc.nextInt(); //物品的价格v
            goods[j][1] = sc.nextInt() * goods[j][0]; //物品的重要度
            goods[j][2] = sc.nextInt(); //物品是主件还是附件，q = 0表示主件，q>0 表示附件,其中q为所属主件的编号
            min = Math.min(goods[j][0], min);
            //输入的同时对物品进行分类
            if (goods[j][2] == 0 && !map.containsKey(j)){ //如果物品j为主件，并且map中不包含这个主件
                ArrayList<Integer> list = new ArrayList<>();
                //list.add(j);
                map.put(j, list);
            } else if (goods[j][2] >0){ //物品j为附件
                if (map.containsKey(goods[j][2])){
                    ArrayList<Integer> tmp = map.get(goods[j][2]);
                    tmp.add(j);
                    map.put(goods[j][2], tmp);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(j);
                    map.put(goods[j][2], list);
                }
            } else {
                continue;
            }
        }
        //要求在不超过N元的情况下，k件物品的价格和重要度乘积总和最大
        //int k = 0; //为上一个购买的主件
        for (int i = 1; i<=m; i++){
            for (int j = min; j<=N; j++){
                if (goods[i][2] != 0){//附件必须依赖主件，因此不单独考虑购买附件。
                    // 因此，若第i件物品是附件的话，用j元钱购买前i件物品的价值和最大值等于用j元钱购买前i-1件物品的总价值和
                    f[i][j] = f[i-1][j]; //加上这一条语句居然就过了！！！！
                    continue;
                }
                f[i][j] = f[i-1][j];
                if (j>=goods[i][0]){ //买第i类物品的主件
                    int t = f[i-1][j-goods[i][0]] + goods[i][1];
                    if (t > f[i][j]){
                        f[i][j] = t;
                        //k = i;
                    }
                }
                //第i类物品的附件
                ArrayList<Integer> list = map.get(i);
                //买第i类物品的第一个附件
                if (list != null && list.size()> 0 && j>=goods[i][0] + goods[list.get(0)][0]){
                    int t = f[i-1][j-goods[i][0] - goods[list.get(0)][0]] + goods[i][1] + goods[list.get(0)][1];
                    if (t > f[i][j]){
                        f[i][j] = t;
                        //k = i;
                    }
                }
                //买第i类物品的第2个附件
                if (list != null && list.size() > 1 && j>=goods[i][0] + goods[list.get(1)][0]){
                    int t = f[i-1][j-goods[i][0] - goods[list.get(1)][0]] + goods[i][1] + goods[list.get(1)][1];
                    if (t>f[i][j]) {
                        f[i][j] = t;
                        //k = i;
                    }
                }
                //买第i类物品的两个附件
                if (list != null && list.size() >1 && j>=goods[i][0] + goods[list.get(0)][0] + goods[list.get(1)][0]){
                    int t = f[i-1][j- goods[i][0] -goods[list.get(0)][0] - goods[list.get(1)][0]] + goods[i][1] + goods[list.get(0)][1] + goods[list.get(1)][1];
                    if (t > f[i][j]){
                        f[i][j] = t;
                        //k = i;
                    }
                }
            }
        }
        System.out.println(f[m][N]);
    }
}
