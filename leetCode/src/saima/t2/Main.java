package saima.t2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * 小团无路可逃
     * 所在地方可以抽象成一颗有n个节点的树，小美在x位置，小团在y位置
     * 每个单位时间都可以选择不懂或者向相邻的位置转移
     * 问，最多经过多少个单位时间，小团会被追上
     */
    public static List<Integer> path = new ArrayList<>(); //小团已经走过的节点
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int x = sc.nextInt() - 1; //小美的位置
            int y = sc.nextInt() - 1; //小团的位置

            int[][] graph = new int[n][n]; //用邻接矩阵存放图，graph[u][v] = 1表示顶点u,v之间有一条边

            //初始化邻接矩阵
            for (int i = 0; i<n; i++){
                Arrays.fill(graph[i], Integer.MAX_VALUE); //所有节点之间没有边，距离为正无穷
            }
            for (int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if (i == j){
                        graph[i][j] = 0;
                    }
                }
            }

            for (int i = 0; i<n-1; i++){
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph[u-1][v-1] = 1;
                graph[v-1][u-1] = 1;
            }

            int start = x;
            int d = dfs(graph, x, y);
            System.out.println(d);
        }
    }

    //小团往某个方向逃跑的最远距离，即无路可逃时候的最远距离
    public static int dfs(int[][] graph, int x, int y){
        if (x == y){ //如果跑到了小美所在的地方，返回-1
            return -1;
        }
        int dis = 0;
        for (int i = 0; i<graph[y].length; i++){
            if (graph[y][i] == Integer.MAX_VALUE || path.contains(graph[y][i]) || graph[y][i] == y){
                continue;
            }
            int nxt = graph[y][i]; //小团下一个位置
            dis = dfs(graph,x,nxt);
            if (dis == -1){
                continue;
            }
        }
        return dis+1;
    }

    public static int[] dijkstra(int[][] graph, int start){
        int n = graph.length;
        int[] shortPath = new int[n];

        String[] path = new String[n]; //记录start到其它节点的路径
        for (int i = 0; i<n; i++){
            path[i] = new String(start+"->" +i);
        }
        int[] visited = new int[n]; //标记当前该顶点的最短路径是否已经求出,值为0或1

        //初始化，第一个顶点以求出
        shortPath[start] = 0;
        visited[start] = 1;
        for (int count = 1; count<n; count++){
            int k = -1; //选出一个距离初始顶点start最近的未标记顶点
            int dmin = Integer.MAX_VALUE;
            for (int i = 0; i<n; i++){
                if (visited[i] == 0 && graph[start][i] < dmin){
                    dmin = graph[start][i];
                    k = i;
                }
            }
            //将新选出的顶点标记位已求出的最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;
            visited[k] = 1;

            //以k为中间节点，修正从start到未访问各点的距离
            for (int i = 0; i<n; i++){
                if (visited[i] == 0 && graph[start][k] + graph[k][i] < graph[start][i]){
                    graph[start][i] = graph[start][k] + graph[k][i];
                    path[i] = path[k] + "->" + i;
                }
            }
        }
        for (int i = 0; i<n; i++){
            System.out.println("从"+start+"出发到"+i+"的最短路径为："+path[i]);
        }
        return shortPath;
    }
}
