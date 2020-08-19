package nowcode.pdd0802;

import java.util.*;

public class Main {
    /**
     * 还有一个棋子未到终点，距离终点有 K 个格子
     * 之后投了N次骰子
     * 第N次投出多多醒了，不记得是否到达终点
     */
    public static void flyChess(){
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] D = new int[N+1];
        //N次骰子的点数
        for (int i = 1; i<=N; i++){
            D[i] = sc.nextInt();
        }

        int[] res = new int[2]; //记录最终与终点的距离和回退次数
        if (win(K, N, D, res) || res[0] == 0){
            System.out.println("paradox");
        } else {
            System.out.println(res[0] + " "+ res[1]);
        }
    }

    /**
     *
     * @param K 初始时与终点的距离
     * @param N 掷骰子次数
     * @param D 每次的点数
     * @param res
     * @return 如果某次恰好到达终点，返回true
     */
    private static boolean win(int K, int N, int[] D, int[] res){
        //遍历数组D
        for (int i = 1; i<=N; i++){
            //第i次投骰子, 恰好到达终点
            if (K==D[i]){
                if (i!=N) //不是第N次到达的终点
                    return true;
                else { //第N次到达终点
                    res[0] = 0;
                }
            } else if (K > D[i]){ //前进D[i]步
                K -= D[i];
                res[0] = K;
            } else { //需要回退
                K = D[i] - K;
                res[1] ++; //回退次数+1
                res[0] = K;
            }
        }
        return false; //不能恰好到达终点
    }

    /**
     * 有N个骰子，要将它们分类
     * 同类型定义：
     *      将其中一个骰子通过若干次上下、左右或前后翻转后，其与另一个骰子对应的6面数字均相等
     */
    public static void chessClass(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //骰子个数
        int[][] numbers = new int[N+1][7]; //记录每个筛子当前上，下，左，右，前，后6面的数字
        for (int i = 1; i<=N; i++){
            for (int j = 1; j<=6; j++){
                numbers[i][j] = sc.nextInt();
            }
        }
        //每种类型骰子的个数
        //List<int[]> res = new ArrayList<>();
        Map<int[], Integer> res = new TreeMap<int[], Integer>();
        division(N, numbers, res);
        System.out.println(res.keySet().size());
        System.out.println(res.values());
    }

    /**
     *
     * @param N 骰子个数
     * @param numbers 每个骰子的上下左右前后6面点数
     * @param res
     */
    private static void division(int N, int[][] numbers, Map<int[],Integer> res){
        for (int i=1; i<=N; i++){
            if (res.size() == 0){
                res.put(numbers[i],1); //将第一个骰子作为一个类别
            }
            for (int[] c:res.keySet()){
                if (isSame(c, numbers[i])){ //归到同一类
                    res.put(c,res.get(c)+1);
                } else { //新增类
                    res.put(numbers[i],1);
                }
            }
        }
    }

    private static boolean isSame(int[] sample, int[] tmp){
        if (sample.equals(tmp)){
            return true;
        }
        //翻转后若可以相等，返回true

        //无论如何翻转都无法相等，返回false
        return false;
    }

    /**
     * N套中餐，M套晚餐，每种套餐均有热量值和美味值
     * 小多想知道，在美味值之和不少于T的情况下，最少可以摄入多少热量值
     */
    public static void eating(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        int[][] lunch = new int[N][2];
        int[][] dinner= new int[M][2];
        for (int i=0; i<N; i++){
            lunch[i][0] = sc.nextInt(); //热量值
            lunch[i][1] = sc.nextInt(); //美味值
        }
        //按美味值对午餐排序
        Arrays.sort(lunch, Comparator.comparing(o->o[1]));

        for (int j = 0 ; j<M; j++){
            dinner[j][0] = sc.nextInt();
            dinner[j][1] = sc.nextInt();
        }
        //按美味值对晚餐排序
        Arrays.sort(dinner,Comparator.comparing(o->o[1]));
        //经过排序之后

        if(T == 0){ //无须满足美味值
            System.out.println(0);
        } else {
            //暴力 复杂度太高
            int minK = Integer.MAX_VALUE;
            for (int i = 0; i<N; i++){
                for (int j = 0; j<M ; j++){
                    if (lunch[i][1] >= T){  //只吃午餐
                        minK = Math.min(minK, lunch[i][0]);
                    } else if (dinner[j][1] >= T){ //只吃晚餐
                        minK = Math.min(minK, dinner[j][0]);
                    } else if (lunch[i][1] + dinner[j][1] >= T){
                        minK = Math.min(minK, lunch[i][0] + dinner[j][0]);
                    }
                }
            }
            if (minK == Integer.MAX_VALUE){
                System.out.println(-1);
            }else
                System.out.println(minK);
        }
    }

    /**
     * 农场：6*6：共36块地组成
     * 其中有几块地已经盖了建筑。现有棉花，玉米，大豆，油菜，花生，小麦这6中作物
     * 现要在没有建筑的空地种植，要满足每一块地上的农作物不与前后左右相邻的四块地相同
     * 求解满足要求的种植方案
     */
    public static void farmer(){
        Scanner sc = new Scanner(System.in);
        String[] str = new String[6];
        for (int i=0; i<6; i++){
            str[i] = sc.nextLine();
        }
        int[] seeds = {1,2,3,4,5,6}; //6种作物

        int[][] blank = new int[6][6]; //记录空地位置
        int blankNum = 0;

        for (int i = 0; i<6; i++){
            Arrays.fill(blank[i],-1);
        }
        for (int i=0; i<6; i++){
            for (int j=0; j<6;j++){
                if (str[i].charAt(j) == '#'){
                    blank[i][j] = 0;//为空地
                    blankNum++;
                }
            }
        }
        if (blankNum == 0){
            System.out.println(0);
        }
        else if (blankNum == 1){
            System.out.println(6);
        }
        else {
            //DFS
            int ways = dfs(blank, blankNum, seeds);
        }
    }

    private static int dfs(int[][] blank, int blankNum, int[] seeds){
        if (blankNum == 0){
            return 0;
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        int choices = seeds.length;

        for (int i = 0; i<6; i++){
            for (int j=0;j<6;j++){
                if (blank[i][j] == 0) { //未种
                    for (int k = 0; k<6; k++){
                        if (i-1>=0 && blank[i-1][j] != seeds[k]){
                            return 1;
                        }
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        flyChess();
        chessClass();
    }
}
