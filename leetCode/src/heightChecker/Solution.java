package heightChecker;

import java.util.Arrays;

public class Solution {
    public int heightChecker(int[] heights){
        /**
         * 高度检查器：要求学生按照 非递减 的高度顺序排列
         * 题目的本质是查找排序后和排序前不一样的位置，然后返回不一样的个数
         */
        int moveTimes = 0;
        int[] temp = heights.clone(); //复制一份数组并赋给temp
        Arrays.sort(temp);
        for (int i = 0; i < heights.length;i++){
            if (temp[i] != heights[i]){
                moveTimes++;
            }
        }
        return moveTimes;
    }
}
