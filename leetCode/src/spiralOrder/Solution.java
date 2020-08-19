package spiralOrder;

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

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[] ans = sol.spiralOrder(matrix);
        for (int x: ans) {
            System.out.print( x + ",");
        }
    }
}
