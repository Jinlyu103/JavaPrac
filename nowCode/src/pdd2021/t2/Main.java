package pdd2021.t2;

import java.util.*;

public class Main {
    /**
     * 地图为 N*M 的矩形，每个格子相邻有上下左右四个方向
     * 玩家可以在格子上放士兵，每个格子最多一个士兵，相邻的士兵为同一个队伍
     * 现在可以移动一次任意格子的士兵到任意空格子上，求移动之后可以得到的最大队伍士兵数
     *
     * BFS:
     * 将有士兵的格子坐标入栈，依次从栈中的格子出发进行BFS，并标记访问过的格子
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] matrix = new int[N][M];

            LinkedList<int[]> soldiers = new LinkedList<>(); //有士兵的格子
            HashSet<int[]> visited = new HashSet<>();


            int[] dx = {0,0,1,-1};
            int[] dy = {1,-1,0,0};

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = sc.nextInt();
                    if (matrix[i][j] == 1) { //将有士兵在的格子坐标加入队列中
                        soldiers.addLast(new int[]{i, j});
                    }
                }
            }
            if (soldiers.size() == 0){ //没有士兵
                System.out.println(0);
            } else {
                int nums = soldiers.size(); //士兵总人数
                int maxT = 1; //最大的队伍人数
                while (!soldiers.isEmpty()) { //这个while循环可以计算移动之前最大队伍人数
                    int[] cur = soldiers.removeFirst(); //当前出队的士兵所在坐标
                    int cur_x = cur[0], cur_y = cur[1];
                    for (int i = 0; i<4; i++){
                        int nxt_x = cur_x + dx[i];
                        int nxt_y = cur_y + dy[i];
                        //相邻格子坐标是否合法，相邻格子没有士兵,或者已经访问过，那么不考虑
                        if (nxt_x < 0 || nxt_y < 0 || nxt_x >= N || nxt_y >= M || matrix[nxt_x][nxt_y] == 1 || visited.contains(cur)){
                            continue;
                        }
                        //每个格子都记录队伍中的总人数，更新队伍中的总人数
                        matrix[nxt_x][nxt_y] = matrix[cur_x][cur_y] + matrix[nxt_x][nxt_y];
                        matrix[cur_x][cur_y] = matrix[nxt_x][nxt_y];

                        soldiers.addLast(new int[]{nxt_x, nxt_y});
                        maxT = Math.max(maxT, matrix[nxt_x][nxt_y]);
                    }
                }
                if (maxT == nums){ //不需要移动
                    System.out.println(maxT);
                } else {
                    //移动：移动少数士兵队伍中的人数
                }
//                System.out.println("maxT = "+maxT);
//                for (int i = 0; i<N; i++){
//                    for (int j = 0; j<M; j++){
//                        System.out.print(matrix[i][j] +" ");
//                    }
//                    System.out.println();
//                }
            }

        }
        sc.close();
    }
}
