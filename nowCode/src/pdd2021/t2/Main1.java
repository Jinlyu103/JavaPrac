package pdd2021.t2;

import java.util.LinkedList;
import java.util.Scanner;

public class Main1 {
    //暴力尝试
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] matrix = new int[N][M];

            LinkedList<int[]> blanks = new LinkedList<>(); //没有士兵的格子
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    matrix[i][j] = sc.nextInt();
                    if (matrix[i][j] == 0) { //将士兵在的格子坐标加入队列中
                        blanks.addLast(new int[]{i, j});
                    }
                }
            }
            //全为士兵，或只有一个空格，或者没有士兵或者只有一个士兵的情况
            if (blanks.size() <= 1 || blanks.size() >= N * M - 1) {
                System.out.println(N * M - blanks.size());
            } else {
                //从空格出发，尝试暴力添加士兵
                int total = N * M - blanks.size(); //士兵总数
                int maxT = 1;

                int[] dx = {0, 0, 1, -1};
                int[] dy = {1, -1, 0, 0};

                while (!blanks.isEmpty()) {
                    int[][] tmp = new int[N][M];
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            tmp[i][j] = matrix[i][j]; //先复制一份
                        }
                    }

                    int[] curBlank = blanks.removeFirst();
                    LinkedList<int[]> queue = new LinkedList<>();
                    tmp[curBlank[0]][curBlank[1]] = 1; // 将当前位置放一个士兵，假设是从别处移动过来的
                    queue.offer(curBlank);
                    int team = 0;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int x = cur[0], y = cur[1];
                        tmp[x][y] = 0;
                        team++;
                        //查看相邻节点的情况
                        for (int i = 0; i < 4; i++) {
                            int nxt_x = x + dx[i], nxt_y = y + dy[i];
                            if (nxt_x < 0 || nxt_y < 0 || nxt_x >= N || nxt_y >= M || tmp[nxt_x][nxt_y] == 0) {
                                continue;
                            }
                            //tmp[nxt_x][nxt_y] = 0;
                            queue.offer(new int[]{nxt_x, nxt_y});
                        }
                    }
                    if (team <= total)
                        maxT = Math.max(maxT, team); //更新士兵总数
                    else {
                        team -= 1;
                        maxT = Math.max(maxT, team);
                    }
                }
                System.out.println(maxT);
            }
        }
    }
}
