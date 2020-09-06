package PredictTheWinner;

public class Solution {
    /**
     * 486. 预测赢家
     * 给定一个表示分数的非负整数数组。玩家1从数组任意一端拿去一个分数，
     * 随后玩家2继续从剩余数组任意一端拿取一个分数
     * 回溯
     */
    public boolean PredictTheWinner(int[] nums){
        int n = nums.length;
        if (n == 1){
            return true;
        }
        int sum1 = 0;
        int sum2 = 0;
        return backtrack(sum1, sum2, true,0, n-1, nums);
    }

    //first表示是否先手取数
    public boolean backtrack(int role1, int role2, boolean first, int start, int end, int[] nums){
        if (start > end){
            if (role1 >= role2){
                return true;
            } else
                return false;
        }
        if(first){
            //取 start
            role1 += nums[start++];
            if (backtrack(role1, role2, false, start, end, nums))
                return true;
            start --;
            role1 -= nums[start];

            //取 end
            if (end != start){
                role1 += nums[end--];
                if (backtrack(role1, role2, false, start, end, nums))
                    return true;
                end++;
                role1 -= nums[end];
            }
        } else {
            //取start
            role2 += nums[start ++];
            if (backtrack(role1, role2, true, start, end, nums)){
                return true;
            }
            start --;
            role2 -= nums[start];

            //取 end
            if(start != end){
                role2 += nums[end--];
                if (backtrack(role1, role2, true, start, end, nums))
                    return true;
                end++;
                role2 -= nums[end];
            }
        }
        return false;
    }

    /**
     *dp[i][j]表示在[i,j]取数，先手得分-后手得分的最大值，i<=j （只考虑右上角，对角线上dp[i][i] = nums[i]）
     * dp[i][j]的值取决于：
     *      nums[i], dp[i+1][j]：表示先手取nums[i], 后手在[i+1,j]之间取数（此时后手变为先手,就可以理解为后手比先手得分高出多少）
     *      那么要求当前取数之后先手得分比后手高多少即为：
     *      nums[i] - dp[i+1][j]
     *
     *      nums[j], dp[i][j-1]: 表示先手取nums[j], 后手在[i,j-1]之间取数（同理，此时可以理解为后手比先手得分高出多少）
     *      即： nums[j] - dp[i][j-1]
     *
     * dp[i][j]取最大值
     * 最后：如果dp[0][n-1]大于等于0，返回true
     * 否则返回false
     * @param nums
     * @return
     */
    public boolean PredictTheWinner1(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i<n; i++){
            dp[i][i] = nums[i];
        }

        for (int i = n-1; i>=0; i--){
            for (int j = i+1; j<n; j++){
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        if (dp[0][n-1]>=0){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2,4,55,6,8};
        System.out.println(sol.PredictTheWinner1(nums));
    }
}
