package nowcode.xor;

import java.util.Scanner;

public class Solution {
    //定义字典树结构
    private static class TrieTree{
        TrieTree[] next = new TrieTree[2];
        int count = 1;
    }

    public int solve(int[] a, int m){
        /**
         * 数组a中的数进行两两异或，统计结果大于m的个数
         * 直接求解超时，复杂度O(n^2)
         */
        int ans = 0;
        TrieTree tTree = buildTireTree(a);
        for (int i = 0; i<a.length;i++){
            ans += queryTrieTree(tTree, a[i],m,31);
        }
        return ans/2;
    }

    private static int queryTrieTree(TrieTree tTree, int a, int m, int index){
        if (tTree == null){
            return 0;
        }
        TrieTree cur = tTree;
        for (int i = index; i>=0;i--){
            int aDigit = (a>>i) & 1;
            int mDigit = (m>>i) & 1;
            if (aDigit == 1 && mDigit == 1){
                if (cur.next[0] == null){
                    return 0;
                }
                cur = cur.next[0];
            } else if (aDigit == 0 && mDigit == 1){
                if(cur.next[1] == null){
                    return 0;
                }
                cur = cur.next[1];
            } else if (aDigit == 1 && mDigit == 0){
                int p = queryTrieTree(cur.next[1],a,m,i-1);
                int q = cur.next[0] == null?0:cur.next[0].count;
                return p+q;
            } else if (aDigit == 0 && mDigit == 0){
                int p = queryTrieTree(cur.next[0], a,m,i-1);
                int q = cur.next[1]==null?0:cur.next[1].count;
                return p+q;
            }
        }
        return 0;
    }

    private static TrieTree buildTireTree(int[] a){
        //创建字典树
        TrieTree tTree = new TrieTree();
        for (int i = 0; i < a.length; i++){
            TrieTree cur = tTree;
            for (int j = 31; j>=0; j--){
                int digit = (a[i]>>j)&1;
                if (cur.next[digit] == null){
                    cur.next[digit] = new TrieTree();
                } else {
                    cur.next[digit].count++;
                }
                cur = cur.next[digit];
            }
        }
        return tTree;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //创建Scanner对象
        Solution sol = new Solution();
        while (sc.hasNext()) {
            //第一行包含两个整数，n和m
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[n];
            //第二行给出n个整数a1,...,an
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            System.out.println(sol.solve(a, m));
        }
    }
}
