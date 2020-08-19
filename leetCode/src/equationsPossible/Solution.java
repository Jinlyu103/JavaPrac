package equationsPossible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    //用哈希表来表示parent数组
    Map<Character,Character> parent = new HashMap<Character, Character>();
    //rank记录每个根节点对应的树的深度
    Map<Character,Integer> rank = new HashMap<Character, Integer>();
    public boolean equationsPossible(String[] equations) {
        /**
         * 等式方程的可满足性问题：每一个等式长度为4，形式为a==b/a!=b
         * 给等式赋值，使得数组中所有元素都满足，则返回true，否则返回false
         *
         * 题解思路：并查集（顾名思义：合并集合、查找集合）
         * 若两个变量之间为==关系，那么这两个变量之间有连通性，应该属于同一个集合
         * 定义一个parent数组，用来存放每个节点的父节点，初始时，parent[x] = x
         * 然后遍历所有等式，合并集合
         * 遍历所有不等式，查找集合
         */
        boolean ans = true;

        //初始化parent和rank
        for (int i = 0; i < equations.length ; i++) {
            char first = equations[i].charAt(0);
            char sec = equations[i].charAt(3);
            if (!parent.containsKey(first)){
                parent.put(first, first);
                rank.put(first,1);
            }
            if (!parent.containsKey(sec)){
                parent.put(sec, sec);
                rank.put(sec,1);
            }
        }

        //遍历等式
        for (int i = 0; i< equations.length; i++){
            char first = equations[i].charAt(0);
            char sec = equations[i].charAt(3);
            if (equations[i].substring(1,3).equals("==")){
                if (first != sec){ // 如果两个变量不同，但有相等的关系，那么合并集合
                    merge(first, sec);
                }
            }
        }
        //遍历不等式
        for (int i = 0; i <equations.length ; i++) {
            char first = equations[i].charAt(0);
            char sec = equations[i].charAt(3);
            if (equations[i].substring(1,3).equals("!=")){
                if (find(first) == find(sec)) {
                    ans = false;
                    break;
                }
            }
        }
        return ans;
    }

    private void merge(char first, char sec){
        //先找代表元素
        char p_first = find(first);
        char p_sec = find(sec);
        if (rank.get(p_first) <= rank.get(p_sec)){
            parent.put(p_first, p_sec);
        } else {
            parent.put(p_sec, p_first);
        }
        if (rank.get(p_first) == rank.get(p_sec) && p_first != p_sec){
            rank.put(p_sec, rank.get(p_sec)+1 );
        }
    }

    private char find(char ch){
        if (parent.get(ch) == ch){
            return ch;
        } else {
            char p_ch = find(parent.get(ch));
            parent.put(ch,p_ch);
            return (parent.get(ch));
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] eqs = {"c==c","b==d","x!=z"};
        System.out.println(sol.equationsPossible(eqs));
    }
}
