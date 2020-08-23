package judgePoint24;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 679. 24点游戏
     * 有4 张1-9的数字牌，需要判断能否通过+，-，*，/运算得到24
     * 注意：除法运算/表示实数除法
     * 每次从列表中取出两个数，然后将这两个数进行运算，用运算结果取代列表中的两数
     * 最后列表中会剩下一个数，等于24的话返回true
     */
    float d = 0.001f;
    public boolean judgePoint24(int[] nums){
        /**
         * 回溯
         */
        //定义操作符
        char[] ops = {'+','-','*','/'};
        //记录参与运算的数, 由于除法是实数除法，因此列表中存放浮点数
        List<Float> calculated = new ArrayList<Float>();

        for (int i = 0; i< nums.length; i++){
            calculated.add((float) nums[i]);
        }

        return backtrack(calculated);
    }

    public boolean backtrack(List<Float> calculated){
        if (calculated.size() == 1){
            if (Math.abs(calculated.get(0) -24.0) <= d){
                return true;
            }
        }

        for (int i = 0; i<calculated.size(); i++){
            for (int j = i+1; j<calculated.size(); j++){
                List<Float> tmp = new ArrayList<>();
                for (int p = 0; p<calculated.size(); p++){
                    if (p !=i && p!=j)
                        tmp.add(calculated.get(p));
                }
                float op1 = calculated.get(i);
                float op2 = calculated.get(j);
                //float res = 0;
                tmp.add(op1+op2);
                if (backtrack(tmp)){
                    return true;
                }
                tmp.remove(tmp.size()-1);
                //相减
                tmp.add(op1-op2);
                if (backtrack(tmp)){
                    return true;
                }
                tmp.remove(tmp.size()-1);
                //交换相减
                tmp.add(op2-op1);
                if (backtrack(tmp)){
                    return true;
                }
                tmp.remove(tmp.size()-1);
                //相乘
                tmp.add(op1*op2);
                if (backtrack(tmp)){
                    return true;
                }
                tmp.remove(tmp.size()-1);
                //相除
                if (op2!=0){
                    tmp.add(op1/op2);
                    if (backtrack(tmp)){
                        return true;
                    }
                    tmp.remove(tmp.size()-1);
                }
                //交换相除
                if (op1 !=0){
                    tmp.add(op2/op1);
                    if (backtrack(tmp)){
                        return true;
                    }
                    tmp.remove(tmp.size()-1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,3,8,8};
        System.out.println(sol.judgePoint24(nums));
    }
}
