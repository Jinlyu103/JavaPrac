package tencent2021.code1.t2;

import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import java.util.*;

public class Main {
    /**
     * 通知传递
     * 大团队中有n人，m个小团队
     * 已知每个小团队里的人数和每个人的编号：0~n
     * BFS
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int m  =sc.nextInt();
            List<HashSet<Integer>> smallTeams = new ArrayList<>(); //m个小团队
            for (int i = 0; i<m; i++){
                int x = sc.nextInt(); //第i个小团队的人数
                HashSet<Integer> t = new HashSet<>(); //第i个小团队
                for (int j = 0; j<x; j++){
                    int num = sc.nextInt();
                    t.add(num);
                }
                smallTeams.add(t);
            }

            HashSet<Integer> known = new HashSet<>(); //已知晓的人的编号
            HashSet<Integer> team = new HashSet<>(); //已经传播到的团队
            Queue<Integer> q = new LinkedList<>();
            q.offer(0);
            known.add(0);

            while (!q.isEmpty()){
                int cur = q.poll();
                for (int i = 0; i<smallTeams.size(); i++){
                    if (smallTeams.get(i).contains(cur) && !team.contains(i)){ //当前编号cur属于第i个团队，并且第i个团队没有被访问过，那么团队中所有人都知道了
                        for (Integer emp: smallTeams.get(i)){ //将第i个团队中的其他员工入队，并加入known中
                            if (emp != cur) {
                                known.add(emp);
                                q.offer(emp);
                            }
                            team.add(i); //第i个小团队已经访问
                        }
                    }
                }
            }
            System.out.println(known.size());
        }
    }
}
