package saima.meituan2021_t2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * n个人分配的n个区域，编号小的人有优先选择的权利
     * 编号为1~n
     * 输出每个人最终的去处
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){//可能有多组测试用例
            int n = sc.nextInt();
            int[][] will = new int[n+1][n+1]; //每个人有n个意向
            //输入n个人的意向
            for (int i = 1; i<n+1; i++){
                for (int j = 1; j<n+1; j++)
                    will[i][j] = sc.nextInt();
            }
            int[] place = new int[n+1]; //place[i]表示第i个人的去处
            Map<Integer, Integer> arranged = new HashMap<>(); //已经分配好的去处，键为去处编号，值为人员的编号
            for (int i = 1; i<n+1; i++){
                for (int j = 1; j<n+1; j++){
                    if (!arranged.containsKey(will[i][j])){
                        place[i] = will[i][j]; //第i个人去了他第j个意向地区
                        arranged.put(will[i][j],i);
                        break;
                    }
                }
            }
            for (int i = 1;i<n+1;i++){
                System.out.println(place[i]);
            }
        }
    }
}
