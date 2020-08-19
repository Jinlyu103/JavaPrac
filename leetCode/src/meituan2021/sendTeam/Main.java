package meituan2021.sendTeam;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * 将同一个小区的订单按照编号顺序排序，并分行输出
     * 优先输出最小的订单编号较小的小区订单集合。订单编号1，。。。，n
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //订单数量
        int m = sc.nextInt(); //已知关系数量

        //邻接表
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();

        for (int i = 0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (!graph.containsKey(a)){ //添加a的邻接节点
                HashSet<Integer> set = new HashSet<>();
                set.add(b);
                graph.put(a, set);
            } else { //更新a的邻接节点
                HashSet<Integer> set = graph.get(a);
                set.add(b);
                graph.put(a,set);
            }
            if (!graph.containsKey(b)){ //添加b的邻接节点
                HashSet<Integer> set = new HashSet<>();
                set.add(a);
                graph.put(b,set);
            } else { //更新b的邻接节点
                HashSet<Integer> set = graph.get(b);
                set.add(a);
                graph.put(b,set);
            }
        }

        int[] bitMap = new int[n];
        Arrays.fill(bitMap, -1);
        List<Boolean> visited = new ArrayList<>();
        //连通图
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i<=n && !visited.contains(i); i++){
            q.offer(i); //从第i号订单开始遍历
            List<Integer> nodes = new ArrayList<>();
            nodes.add(i);
            while (!q.isEmpty()){
                int cur = q.poll();
                for (int k = 0; k<m; k++){

                }
            }
            Collections.sort(nodes);
            System.out.println(nodes.stream().map(Objects::toString).collect(Collectors.joining(" ")));
        }



    }
}
