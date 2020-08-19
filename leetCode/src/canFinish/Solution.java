package canFinish;

import java.util.*;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites){
        if (numCourses <=1 || prerequisites.length == 0){
            return true;
        }
        int[] flags = new int[numCourses]; //flag[i] = 0,未被DFS访问 ，1表示被当前节点激活，-1表示被其他节点激活

        List<List<Integer>> pres = new ArrayList<>();

        for (int i = 0; i<numCourses; i++){
            pres.add(new ArrayList<>());
        }
        //预处理
        for (int i = 0; i<prerequisites.length; i++){
            pres.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for(int i = 0 ; i < numCourses;i++){
            if(!dfs(pres, flags, i))
                return false;
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> pres, int[] flags, int i){
        if (flags[i] == 1) //出现了环路
            return false;
        if (flags[i] == -1){ //该课程为其他课程的先决条件
            return true;
        }
        flags[i] = 1;
        for (Integer j: pres.get(i)){
            if (!dfs(pres, flags, j))
                return false;
        }
        flags[i] = -1;
        return true;
    }

    /**
     * 有些课程无须先决条件
     * 有些课程需要多个先决条件
     * @param learned 已经学习的课程置位true
     * @param preC 先决课程列表
     * @return true表示当前课程可以学习，false表示不满足条件
     */
    private boolean preLearned(Map<Integer, Boolean> learned, ArrayList<Integer> preC){
        for (Integer i: preC){ //如果先决条件列表中有一门课未学习，返回false
            if (!learned.get(i)){
                return false;
            }
        }
        return true;
    }


    public boolean canFinish1(int numCourses, int[][] prerequisites){
        //BFS
        List<Integer> canLearn = new ArrayList<>(); //某个阶段可以学习的课程列表，无先决课程或先决课程已经学习
        Map<Integer, Integer> learned = new HashMap<>(); //记录已经学习的课程
        Map<Integer, ArrayList<Integer>> pres = new HashMap<>(); //记录课程的先决关系，图

        for (int i=0; i<numCourses; i++){ //初始化，所有课程均未学习
            learned.put(i,0);
        }
        for (int i=0; i<prerequisites.length; i++){//构建课程邻接表
            if (pres.containsKey(prerequisites[i][0])){
                ArrayList<Integer> tmp = pres.get(prerequisites[i][0]);
                tmp.add(Integer.valueOf(prerequisites[i][1]));
                pres.put(prerequisites[i][0], tmp);
            } else {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(Integer.valueOf(prerequisites[i][1]));
                pres.put(prerequisites[i][0], tmp);
            }
        }

        for (int i=0; i<numCourses; i++){
            if (!pres.containsKey(i)){//第i门课无先决条件，直接学习,加入canLearn列表
                canLearn.add(i);
            }
        }

        int count = 0; //记录学习的课程门数
        while (canLearn.size()>0){
            for (int k = 0; k<canLearn.size(); k++){
                learned.put(canLearn.get(k),1); //学习课程canLearn.get(k)
                pres.remove(canLearn.get(k));
                count++;
            }
            canLearn.clear();
            for (Integer k: pres.keySet()){ //查看需要先决条件的课程，是否能够学习
                if (learned.get(k) == 0){ //该门课程未学习
                    if (isContain(pres.get(k), learned)){
                        canLearn.add(k);
                        pres.remove(k);
                    }
                }
            }
        }
        if (count<numCourses-1){
            return false;
        }
        return true;
    }

    //如果先决条件中有一门课程未学习，那么返回false；否则返回true
    private boolean isContain(ArrayList<Integer> cArr, Map<Integer, Integer> learned){
        for (Integer k: cArr){
            if (learned.get(k) == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] pre = {{2,0},{2,1}};
        System.out.println(sol.canFinish(n,pre));

        String str=new String("hello");
        
        if(str=="hello")
        {
            System.out.println("true");
        }
        else     {
            System.out.println("false");
        }
    }
}
