package trap;

import java.util.Stack;

public class Solution {
    /**
     * 42. 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height){
        int ans = 0;
        Stack<Integer> stk = new Stack<>(); //维护一个单调递减的栈
        for (int i = 0; i<height.length; i++){
            //如果栈不为空并且栈顶元素的高度比当前高度要低的话，可以承接雨水
            while (!stk.isEmpty() && height[stk.peek()] < height[i]){
                int cur = stk.pop();
                if (stk.isEmpty())
                    break;
                int l = stk.peek();
                int r = i;
                int h = Math.min(height[r], height[l]) - height[cur];
                ans += (r-l-1) * h;
            }
            stk.push(i);
        }
        return ans;
    }
}
