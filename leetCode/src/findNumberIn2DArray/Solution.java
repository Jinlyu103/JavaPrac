package findNumberIn2DArray;

public class Solution {
    /**
     * 面试题04 二维数组中的查找
     * 利用数组行递增和列递增的特点，每一行的最后一个数是该行最大值，每一列的最后一个数为该列最大值
     * 那么，可以从第一行最后一个数开始查找，也就是比较右上角的数与target的大小
     * 若等于target，那么说明找到了，flag = true,跳出循环，返回flag
     * 若小于target，说明该行往前所有数都小于target，行标增加1
     * 若大于target，说明该列往下所有数都大于target，列标减小1
     * 注意边界条件：数组不为空
     * 行标、列表都在合法的范围内
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target){
        //考虑边界情况
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0;
        int j = m-1;
        boolean flag = false;
        while (i < n && j >= 0){
            if (matrix[i][j] == target){
                flag = true;
                break;
            } else if(target < matrix[i][j]){
                j --;
            } else{
                i ++;
            }
        }
        return flag;
    }
}
