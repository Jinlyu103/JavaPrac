package getPermutation;

import java.util.HashSet;

public class Solution1 {
    private int[] factorial; //阶乘数组
    public String getPermutation(int n, int k){
        if (n == 1 && k==1){
            return "1";
        }
        HashSet<Integer> nums = new HashSet<>(); //存放已经选择的数
        calFactorial(n);
        int[] idx = {0}; //java不支持指针，这里用数组引用
        String res = backTrack1(n,k,idx, nums, "");
        return res;
    }
    /**回溯+剪枝（左右剪枝）
     * 当 k>(n-i)!时，可以跳过 i 分支，并且count = （n-i）!,继续i++
     * idx: 之前已经遍历过idx个全排列
     */
    public String backTrack1(int n, int k, int[] idx, HashSet<Integer> nums, String s){
        if (s.length() == n){
            if (idx[0] +1 == k){
                return s;
            } else{
                idx[0] ++; //跳过的全排列数+1
                return "";
            }
        }

        for (int i = 1; i<=n; i++){
            if (nums.contains(i)){
                continue;
            }
            if (k > idx[0] + factorial[n-nums.size()-1]) {
                idx[0] += factorial[n - nums.size()-1]; //跳过的全排列数
                continue;
            }
            nums.add(i);
            String ret = backTrack1(n,k,idx,nums,s+Integer.toString(i));
            if (ret.length() == n){
                return ret;
            }
            nums.remove(i);
        }
        return "";
    }

    /**
     * 计算阶乘
     */
    public void calFactorial(int n){
        factorial = new int[n+1];
        factorial[0] = 1;
        for (int i = 1; i<n+1; i++){
            factorial[i] = i*factorial[i-1];
        }
    }

    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        System.out.println(sol.getPermutation(9,233794));
    }
}
