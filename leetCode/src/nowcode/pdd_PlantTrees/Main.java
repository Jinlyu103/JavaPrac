package nowcode.pdd_PlantTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //树的品种数量
        int[] A = new int[N+1]; //每个品种树的数量
        int M=0; //树的总量，坑数
        for (int i = 1; i<=N; i++){
            A[i] = sc.nextInt();
            M += A[i];
        }
        //种树方案
        List<String> arrange = new ArrayList<>();
        /**
         * DFS，剪枝优化
         * 剪枝思路：每次搜索之前判断放弃剩余坑数和任意品种数的数量之间的关系：
         *      如果剩余坑数（free）为偶数，只要某一品种树的数目大于 free/2,就不能保证相邻两棵树品种不同
         *      如果剩余坑数（free）为奇数，只要某一品种树的数目大于（free+1）/2，就不能保证... ...
         *
         */
        if (dfs(0, A, N, M, arrange)){
            System.out.println(String.join(" ", arrange));
        } else {
            System.out.println("-");
        }
    }

    /**
     *
     * @param pos 当前要种树的坑位
     * @param A   每个品种的数量
     * @param N   品种数
     * @param M   总坑位数
     * @param arrange 种树方案
     * @return
     */
    private static boolean dfs(int pos, int[] A, int N, int M, List<String> arrange){
        if (pos == M){ //没有剩余坑位
            return true;
        }
        int free = M-pos;
        if (!checkFreeEnough(N, A, free)){
            return false;
        }
        for (int i = 1; i<=N; i++){
            //当pos为0，或者品种i不为0并且相邻的树品种不为i
            if (pos == 0 || (A[i] != 0 && i != Integer.valueOf(arrange.get(pos-1)))){
                //做出选择
                A[i] --;
                arrange.add(String.valueOf(i));
                //回溯
                if (dfs(pos+1, A, N, M, arrange)){
                    return true;
                }
                //某一条分路无法继续，树种错了，撤销选择
                A[i] ++; //将该品种的树的数量归位
                arrange.remove(arrange.size()-1);
            }
        }
        //无法找到合适的方案
        return false;
    }

    private static boolean checkFreeEnough(int N, int[] A, int free){
        for (int i = 1; i<=N; i++){
            if (A[i] > (free+1)/2){
                return false;
            }
        }
        return true;
    }
}
