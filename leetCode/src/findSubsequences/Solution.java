package findSubsequences;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    /**
     * 491. 递增子序列
     * 相等的数字被视为递增的一种情况
     * 比如[4,6,7,7]
     * 先取第一元素，向后取6,7,7，可以构成[4,6],[4,7]
     * 先取第二元素，向后取7,7，可以构成[6,7]
     * 需要注意的是，每次都只能顺序向后取元素，不能第二个元素取7，再回头取6
     *
     * 那么可以用回溯 + 剪枝
     *
     * 定义一个seq序列，存放合法元素的下标，合法元素指的是序列中下标递增，且下标所对应的值也是递增的
     * 深度优先搜索
     * 超时
     */
//    List<List<Integer>> res = new ArrayList<>();
//    public List<List<Integer>> findSubsequences(int[] nums){
//        if (nums.length <= 1){
//            return res;
//        }
//        List<Integer> seq = new ArrayList<>(); //seq中存放元素下标
//        for (int l = 2; l <= nums.length; l++)
//            dfs(nums, seq, 0, l);
//        return res;
//    }
//
//    public void dfs(int[] nums, List<Integer> seq, int start, int len){
//        if (seq.size() == len){
//            List<Integer> tmp = new ArrayList<>();
//            for (Integer idx: seq){
//                tmp.add(nums[idx]);
//            }
//            if (!res.contains(tmp)){
//                res.add(tmp);
//            }
//            return;
//        }
//
//        for (int i = start; i<nums.length; i++){
//            if (!valid(nums, seq, i)){ //判断是否合法
//                continue;
//            }
//            seq.add(i); //添加合法下标
//            dfs(nums, seq, i+1, len);
//            seq.remove(seq.size()-1);
//        }
//    }
//
//    //序列中下标递增，并且对应的值递增
//    public boolean valid(int[] nums, List<Integer> seq, int idx){
//        if (seq.size() == 0 || (seq.get(seq.size()-1) < idx && nums[seq.get(seq.size()-1)] <= nums[idx])){
//            return true;
//        }
//        return false;
//    }

    /**
     * 递归， 时间复杂度 O(2^n)
     * 使用栈stack保存递增子序列
     * 使用列表res 保存答案
     * 栈中最后放入元素在nums中的下标记为last
     * 当前即将放入的元素下标为pos
     *      如果在区间[last+1,pos)中没有与pos值相同的元素，将pos加入栈中，last更新为pos,pos更新为pos+1（即下一个考查元素）
     *      如果有与pos位置值相同的元素，丢弃当前值（pos位置的元素），继续考查下一个元素
     * 递进阶段,都产生两个子调用：
     *      如果当前值符合要求，加入栈中。将放入新值后的stack，放入res.继续处理下一个数
     *      将当前值丢弃，无论能不能放入stack中，都选择不放入。继续递归处理下一个数
     * 回归阶段，将stack最后一个元素弹出
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        if (nums.length<=1){
            return res;
        }

        dfs(nums, -1, 0, stack, res);
        return res;
    }

    public void dfs(int[] nums, int last, int pos, Stack<Integer> stack, List<List<Integer>> res){
        if (nums.length == pos){ // 到达末尾，直接返回
            return;
        }
        if ((stack.isEmpty() || nums[pos] >= stack.peek()) && isFirst(nums, last, pos)){
            stack.push(nums[pos]);
            if (stack.size()>=2){
                List<Integer> tmp = new ArrayList<>();
                for (Integer i: stack){
                    tmp.add(i);
                }
                res.add(tmp);
            }
            dfs(nums, pos, pos+1, stack, res);
            stack.pop();
        }
        dfs(nums, last, pos+1, stack, res); //pos处的数直接丢弃，继续递进
    }

    public boolean isFirst(int[] nums, int last, int pos){
        for (int i = last+1; i<pos; i++){
            if (nums[i] == nums[pos]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,6,7,7};
        System.out.println(sol.findSubsequences(nums));
    }
}
