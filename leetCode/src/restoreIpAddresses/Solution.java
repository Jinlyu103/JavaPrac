package restoreIpAddresses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution {
    /**
     * 给定一个只包含数字的字符串，复原它并返回所有可能的IP地址的格式
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s){
        List<Integer> nums = new ArrayList<>(); //存放拆分之后的整数
        List<String> res = new ArrayList<>();   //存放结果IP地址
        if (s.length() > 4*3){
            return res;
        }
        backtrack(nums,s,0,res);
        return res;
    }

    /**
     * 回溯计算可行的IP地址，并将其置于res中
     * @param nums
     * @param s
     * @param res
     */
    private void backtrack(List<Integer> nums, String s, int start, List<String> res){
        //nums.size() == 4表示刚好将字符串拆分为4个满足条件的整数；
        //start == s.length的时候，说明字符串s已经遍历完了
        if (nums.size() == 4 && s.length() == start){
            String ip = nums.stream().map(Objects::toString).collect(Collectors.joining("."));
            res.add(ip);
            return;
        }
        //寻找可能拆分出来的整数
        if (start < s.length() && s.substring(start, start+1).equals("0")){
            nums.add(0);
            backtrack(nums,s,start+1,res);
            nums.remove(nums.size()-1);
            return;
        }
        for (int i = 1; i<=Math.min(3, s.length() - start); i++){
            int tmp = Integer.valueOf(s.substring(start, start+i));
            if (tmp > 255){
                return;
            }
            nums.add(tmp); //将数字添加到nums中
            backtrack(nums, s, start+i, res);
            nums.remove(nums.size()-1); //移除最后一个元素
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "25525511135";
        String s1 = "010010";
        System.out.println(sol.restoreIpAddresses(s1));
    }
}
