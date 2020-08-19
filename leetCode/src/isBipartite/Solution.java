package isBipartite;

import java.util.*;

public class Solution {
    /**
     * 785. 判断二分图
     */
    public boolean isBipartite(int[][] graph){
        //深度优先搜索
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color,-1);//均未染色
        List<Boolean> colored = new ArrayList<>();
        //均未染色
        for (int i = 0; i < n; i++){
            colored.add(false);
        }
        LinkedList<Integer> stk = new LinkedList<>();

        //不一定是连通图
        while(colored.contains(false)){
            int idx = colored.indexOf(false); //没有染色的节点
            stk.addLast(idx); //idx节点入栈
            color[idx] = 0;   //idx节点染0色
            colored.set(idx,true); //已染色
            while (!stk.isEmpty()){
                int cur = stk.removeLast(); //访问当前节点
                int cur_color = color[cur];
                int nxt_color = cur_color^1;
                //查看相邻节点染色情况
                for (int j:graph[cur]) {
                    if (color[j] == nxt_color){
                        continue;
                    } else if (color[j] == cur_color){
                        return false;
                    } else { //相邻节点染色
                        color[j] = nxt_color;
                        colored.set(j, true);
                    }
                    stk.addLast(j);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] g = {{4},{},{4},{4},{0,2,3}};
        Solution sol = new Solution();
        System.out.println(sol.isBipartite(g));
    }
}
