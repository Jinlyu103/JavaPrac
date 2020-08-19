package removeBoxes;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 给出颜色（数字表示）不同的盒子。经过若干轮操作去掉盒子，直到所有盒子都去掉
     * 每轮可以移除具有相同颜色的连续的k个盒子（k>=1)
     * 这样一轮可以得到k*k个积分
     * 当将所有盒子都去掉，求能获得的最大积分和
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes){
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return f(boxes, dp, 0, n-1, 0);
    }

    /**
     * f(l,r,k)表示移除区间[l,r]加上该区间右边等于a[r]的k个元素组成的这个序列的最大积分
     * 最后返回 f(1,n,0),下标从1开始
     * 转移方程：
     *      f(l,r,k) = max{ f(l,r-1,0) + (K+1)^2, max_{l<=i<=r-1}_{[f(l,i,k+1) + f(i+1,r-1,0)] * s(a[i] == a[r])} }
     *      其中：
     *          f(l,r-1,0) + (K+1)^2：表示将a[r]和后面k个数一起删除，再删除[l,r-1]区间
     *          [f(l,i,k+1) + f(i+1,r-1,0)] * s(a[i] == a[r]): 表示当a[i]（l<=i<r)等于a[r]的时候，考虑先删掉[i+1,r-1]这个
     *          区间，再删除[l,i]区间和后面的k+1个个a[r]相等的数构成的序列，其中 s(x)为选择函数：
     *                  s(x) = 1  x == true
     *                       = 0  x == false
     * @param boxes
     * @param dp
     * @param l
     * @param r
     * @param k
     * @return
     */
    public int f(int[] boxes, int[][][] dp, int l, int r, int k){
        if (l>r)
            return 0;
        if (dp[l][r][k] != 0)
            return dp[l][r][k];
        while (r>l && boxes[r] == boxes[r-1]){
            r--;
            k++;
        }
        dp[l][r][k] = f(boxes,dp,l,r-1,0) + (k+1)*(k+1);
        for (int i= l; i<=r; i++){
            if (boxes[i] == boxes[r]){
                dp[l][r][k] = Math.max(dp[l][r][k], f(boxes, dp, l, i, k+1) + f(boxes, dp, i+1, r-1, 0));
            }
        }
        return dp[l][r][k];
    }
}
