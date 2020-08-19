package dailyTemperatures;

import java.util.Arrays;
import java.util.LinkedList;

public class Solution {
    public int[] dailyTemperatures(int[] T){
        /**
         * 根据气温列表，重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过改日的天数
         * 如果之后都不会升高，请在该位置用0来代替
         * 暴力法：时间复杂度O(n^2)，一定超时
         * 可以使用栈来做：
         *      维护一个单调递减的堆栈，
         *      当栈为空，或者当前元素小于栈顶元素对应的温度时，入栈
         *      当前元素大于栈顶元素对应的温度时，出栈，计算需要等待的天数，
         *      直到栈为空或者当前元素小于栈顶元素对应的温度，当前元素入栈
         *      最后，将栈中剩余的元素全部出栈，需要等待的天数对应为0
         */
        int n = T.length;
        if (n<=1){
            return new int[n];
        }
        LinkedList<Integer> stk = new LinkedList<Integer>();
        int[] ans = new int[n];

        for (int i = 0; i < n ; i++) {
            while (!stk.isEmpty() && T[stk.peekLast()] < T[i]){
                int idx = stk.removeLast();
                ans[idx] = i-idx;
            }
            stk.addLast(i);
        }
        while (!stk.isEmpty()){
            int idx = stk.removeLast();
            ans[idx] = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.dailyTemperatures(T)));
    }
}
