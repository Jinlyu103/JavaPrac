package minimalSteps;

import java.util.*;

public class Solution {
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    int m,n;
    public int minimalSteps(String[] maze){
        m = maze.length;
        n = maze[0].length();
        List<int[]> stones = new ArrayList<int[]>();//记录石头位置
        List<int[]> buttons = new ArrayList<int[]>(); //记录机关位置
        int sx=-1,sy=-1; //起点
        int tx=-1,ty=-1; //终点
        for(int i = 0; i<m;i++){
            for (int j = 0; j <n ; j++) {
                if (maze[i].charAt(j) == 'S'){
                    sx = i;
                    sy = j;
                }
                if (maze[i].charAt(j) == 'T'){
                    tx=i;
                    ty=j;
                }
                if (maze[i].charAt(j)=='O'){
                    stones.add(new int[]{i,j});
                }
                if (maze[i].charAt(j) == 'M'){
                    buttons.add(new int[]{i,j});
                }
            }
        }
        int nb = buttons.size();
        int ns = stones.size();
        //预处理，计算起点到除了墙壁以外其他点的距离
        int[][] startDist = bfs(sx, sy, maze);
        if (nb == 0){//没有机关
            return startDist[tx][ty];
        }

        // 从某个机关到其他机关 / 起点与终点的最短距离。
        int[][] dist = new int[nb][nb + 2];
        for (int i = 0; i < nb; i++) {
            Arrays.fill(dist[i], -1);
        }
        // 中间结果
        int[][][] dd = new int[nb][][];
        for (int i = 0; i < nb; i++) {
            //从第i个机关出发，到每一个除墙壁之外的其他点的距离（包括起点，终点）
            int[][] d = bfs(buttons.get(i)[0], buttons.get(i)[1], maze);
            dd[i] = d;
            // 从某个机关到终点不需要拿石头的距离
            dist[i][nb + 1] = d[tx][ty];
        }

        for (int i = 0; i < nb; i++) {
            int tmp = -1;
            for (int k = 0; k < ns; k++) {
                int midX = stones.get(k)[0], midY = stones.get(k)[1];
                if (dd[i][midX][midY] != -1 && startDist[midX][midY] != -1) {
                    if (tmp == -1 || tmp > dd[i][midX][midY] + startDist[midX][midY]) {
                        tmp = dd[i][midX][midY] + startDist[midX][midY];
                    }
                }
            }
            dist[i][nb] = tmp;
            for (int j = i + 1; j < nb; j++) {
                int mn = -1;
                for (int k = 0; k < ns; k++) {
                    int midX = stones.get(k)[0], midY = stones.get(k)[1];
                    if (dd[i][midX][midY] != -1 && dd[j][midX][midY] != -1) {
                        if (mn == -1 || mn > dd[i][midX][midY] + dd[j][midX][midY]) {
                            mn = dd[i][midX][midY] + dd[j][midX][midY];
                        }
                    }
                }
                dist[i][j] = mn;
                dist[j][i] = mn;
            }
        }

        // 无法达成的情形
        for (int i = 0; i < nb; i++) {
            if (dist[i][nb] == -1 || dist[i][nb + 1] == -1) {
                return -1;
            }
        }

        // dp 数组， -1 代表没有遍历到
        int[][] dp = new int[1 << nb][nb];
        for (int i = 0; i < 1 << nb; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < nb; i++) {
            dp[1 << i][i] = dist[i][nb];
        }

        // 由于更新的状态都比未更新的大，所以直接从小到大遍历即可
        for (int mask = 1; mask < (1 << nb); mask++) {
            for (int i = 0; i < nb; i++) {
                // 当前 dp 是合法的
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < nb; j++) {
                        // j 不在 mask 里
                        if ((mask & (1 << j)) == 0) {
                            int next = mask | (1 << j);
                            if (dp[next][j] == -1 || dp[next][j] > dp[mask][i] + dist[i][j]) {
                                dp[next][j] = dp[mask][i] + dist[i][j];
                            }
                        }
                    }
                }
            }
        }

        int ret = -1;
        int finalMask = (1 << nb) - 1;
        for (int i = 0; i < nb; i++) {
            if (ret == -1 || ret > dp[finalMask][i] + dist[i][nb + 1]) {
                ret = dp[finalMask][i] + dist[i][nb + 1];
            }
        }
        return ret;
    }

    //计算(x,y)出发到各个点的初始距离，BFS
    private int[][] bfs(int x, int y, String[] maze){
        int[][] ret = new int[m][n];
        for (int i=0; i<m;i++){
            Arrays.fill(ret[i],-1);
        }
        ret[x][y] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        while (!queue.isEmpty()){
            int[] p = queue.poll();
            int cur_x = p[0];
            int cur_y = p[1];
            for (int j = 0; j<4; j++){
                int nx = cur_x+dx[j];
                int ny = cur_y+dy[j];
                if (inBound(nx,ny) && maze[nx].charAt(ny) !='#' && ret[nx][ny]==-1){
                    ret[nx][ny] = ret[cur_x][cur_y]+1;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
        return ret;
    }

    private boolean inBound(int x, int y){
        return x>=0 && x<m && y>=0 && y<n;
    }
}
