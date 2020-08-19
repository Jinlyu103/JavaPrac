package largestRectangleArea;

import javax.swing.*;
import java.util.LinkedList;

public class Solution {
    /**
     * 柱状图中最大的矩形
     * 构造矩形，以height[i]为高的矩形
     *      向左找到第一个小于height[i]的下标left_i
     *      向右找到第一个小于height[i]的下标right_i
     *      面积为：height[i] *（right_i*left_i）
     */
    public int largestRectangleArea(int[] heights){ //时间复杂度O(n^2)
        int n = heights.length;
        int maxS = 0;
        int left,right;
        for (int i = 0; i < n ; i++) {
            //向左找
            left = i;
            while (left > 0 && heights[left] >= heights[i]){
                left --;
            }
            //向右找
            right = i;
            while (right < n && heights[right] >= heights[i]){
                right ++;
            }
            maxS = Math.max(maxS, heights[i] *(right-1 - left));
        }
        return maxS;
    }

    public int largestRectangleArea1(int[] heights){
        /**
         * 维护一个单调递增的栈，栈中存放元素的下标
         * 当栈为空的时候入栈当前元素下标
         * 当栈不为空的时候，进行判断：
         *      栈顶元素所在位置是否高于当前位置，若是，计算以栈顶元素为高，以栈顶元素和当前位置之间的距离为宽的矩形面积，
         *      同时将栈顶元素弹出；
         *      否则，当前位置入栈
         */
        int n = heights.length;
        if (n==0){
            return 0;
        }
        if (n==1){
            return heights[0];
        }
        LinkedList<Integer> stk = new LinkedList<Integer>();
        int maxS = 0;
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && heights[stk.peekLast()] > heights[i]){
                //栈顶元素比当前元素严格高, 将栈顶元素出栈，并计算以栈顶元素为高的矩形面积
                //直到栈为空或者栈顶元素的高小于或等于当前高度
                int idx = stk.removeLast();
                while (!stk.isEmpty() && heights[stk.peekLast()] == heights[idx]){
                    //栈里可能会有相同高度 的矩形
                    stk.removeLast();
                }
                int curW = 0;
                if (stk.isEmpty()){
                    curW = i;
                }
                else{
                    curW = i - stk.peekLast() - 1;
                }
                maxS = Math.max(maxS, heights[idx] * curW);
            }
            stk.addLast(i);
        }
        while (!stk.isEmpty()){
            int idx = stk.removeLast();
            while (!stk.isEmpty() && heights[stk.peekLast()] == heights[idx]){
                stk.removeLast();
            }
            int curW = 0;
            if (stk.isEmpty()){
                curW = n;
            }
            else {
                curW = n - stk.peekLast() - 1;
            }
            maxS = Math.max(maxS, heights[idx] * curW);
        }
        return maxS;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] heights = {5,4,1,2};
        System.out.println(sol.largestRectangleArea1(heights));
    }
}
