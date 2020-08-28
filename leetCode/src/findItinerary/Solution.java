package findItinerary;

import java.util.*;

public class Solution {
    /**
     * 332. 重新安排行程，类似题目753：破解保险箱
     * 思路：求解欧拉回路
     * 解析题意： 给定一个n个点m条边的图，要求从指定的顶点出发，经过所有的边恰好一次，使得路径的字典序最小
     * 【可以理解为给定起点 一笔画 的问题】
     * 欧拉图/半欧拉图的定义：
     *      通过图中所有边 恰好一次 且 行遍所有顶点的通路称为欧拉通路
     *      通过图中所有边 恰好一次 且 行遍所有顶点的回路称为欧拉回路
     *      具有欧拉回路的无向图称为欧拉图
     *      具有欧拉通路但不具有欧拉回路的无向图称为半欧拉图
     */

    Map<String, PriorityQueue<String>> map = new HashMap<>(); //优先队列：可以自动对其中的元素按照字典序排序
    List<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets){
        //处理节点, 有向图
        for (List<String> ticket: tickets){
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)){
                map.put(src, new PriorityQueue<>());
            }
            map.get(src).offer(dst); //目的地节点入队，自动排序
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String cur){
        while (map.containsKey(cur) && map.get(cur).size()>0){
            String tmp = map.get(cur).poll(); //访问相邻节点，并将边删除
            dfs(tmp); //递进
        }
        //当所有相邻节点都访问了，将当前节点入栈
        itinerary.add(cur);
    }
}