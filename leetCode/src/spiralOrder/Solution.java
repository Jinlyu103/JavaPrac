package spiralOrder;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] spiralOrder(int[][] matrix){
        //按照顺时针打印矩阵
        int n = matrix.length;
        if (n == 0){
            return null;
        }
        int m = matrix[0].length;
        if (m == 0){
            return null;
        }
        int[] ans = new int[n*m];
        int top = 0;
        int bel = n-1;
        int left = 0;
        int right = m-1;
        int k = 0;
        while (true){
            for (int i = left; i <= right ; i++) { //左 -> 右
                ans[k++] = matrix[top][i];
            }
            if (++top > bel){
                break;
            }
            for (int i = top; i <= bel ; i++) { //上->下
                ans[k++] = matrix[i][right];
            }
            if (--right < left){
                break;
            }
            for (int i = right; i >= left ; i--) { //右 -> 左
                ans[k++] = matrix[bel][i];
            }
            if(--bel < top){
                break;
            }
            for(int i = bel; i>=top; i--){
                ans[k++] = matrix[i][left];
            }
            if (++left > right){
                break;
            }
        }
        return ans;
    }

    /**
     * 54. 螺旋矩阵
     * 给定一个包含m*n个元素的矩阵
     * 请按照顺时针打印矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder1(int[][] matrix){
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0){
            return res;
        }
        int n = matrix[0].length;
        if (n == 0){
            return res;
        }

        int top = 0;
        int down = m-1;
        int left = 0;
        int right = n-1;

        while (true){
            //left -> right
            for (int i = left; i <=right ; i++){
                res.add(matrix[top][i]);
            }
            // 判断是否为最后一行
            if (++top > down){
                break;
            }
            //top -> down
            for (int i = top; i <= down; i++){
                res.add(matrix[i][right]);
            }
            //判断是否为最后一列
            if (--right < left){
                break;
            }
            // right ->left
            for (int i = right; i>=left; i--){
                res.add(matrix[down][i]);
            }
            //判断是否为最后一行
            if (--down < top){
                break;
            }
            //down -> top
            for (int i = down; i>=top; i--){
                res.add(matrix[i][left]);
            }
            //判断是否为最后一列
            if (++left > right){
                break;
            }
        }
        return res;
    }

    /**
     * 59. 螺旋矩阵 II
     * 给定一个正整数n，生成一个包含1-n^2 的所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵
     */
    public int[][] generateMatrix(int n){
        int[][] matrix = new int[n][n];
        if (n == 0){
            return matrix;
        }

        int top = 0;
        int down = n-1;
        int left = 0;
        int right = n-1;
        int e = 1;
        while (true && e<=n*n){
            // left -> right
            for (int i = left; i<=right && e<=n*n; i++){
                matrix[top][i] = e;
                e ++;
            }
            //判断是否为最后一行
            if (++top > down){
                break;
            }
            // top -> down
            for (int i = top; i<=down && e<=n*n; i++){
                matrix[i][right] = e;
                e++;
            }
            //判断是否为最后一列
            if (--right < left){
                break;
            }
            // right -> left
            for (int i = right; i>=left && e<=n*n; i--){
                matrix[down][i] = e;
                e++;
            }
            //判断是否为最后一行
            if (-- down < top){
                break;
            }
            for (int i = down; i>=top && e < n*n; i--){
                matrix[i][left] = e;
                e ++;
            }
            //判断是否为最后一列
            if (++left > right){
                break;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[] ans = sol.spiralOrder(matrix);
        for (int x: ans) {
            System.out.print( x + ",");
        }
    }
}
