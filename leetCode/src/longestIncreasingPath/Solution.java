package longestIncreasingPath;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    /**
     * 矩阵中的最长递增路径
     * 给定一个整数路径，找出最长增长路径的长度
     * (i,j)可以往上（i-1,j）,下（i+1,j），左（i，j-1），右（i，j+1)四个方向移动
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix){
        int m = matrix.length;
        if (m==0){
            return 0;
        }
        int n = matrix[0].length;
        int maxPath = 0;
        for (int i = 0; i<m; i++){
            for (int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j)+1);
            }
        }
        return maxPath;
    }

    //深度优先搜索，以i,j为起点的递增路径长度
    //递归超时
    private int dfs(int[][] matrix, int i, int j){
        int m = matrix.length;
        int n = matrix[0].length;
        int maxPath = 0;
        //向上移动（i-1）
        if (i-1>=0 && matrix[i][j]<matrix[i-1][j]){
            maxPath = Math.max(maxPath, dfs(matrix, i-1, j)+1);
        }
        //向下移动（i+1）
        if (i+1<m && matrix[i][j]<matrix[i+1][j]){
            maxPath = Math.max(maxPath, dfs(matrix, i+1,j)+1);
        }
        //向左移动 j-1
        if (j-1>=0 && matrix[i][j]<matrix[i][j-1]){
            maxPath = Math.max(maxPath, dfs(matrix, i, j-1)+1);
        }
        //向右移动 j+1
        if (j+1<n && matrix[i][j]<matrix[i][j+1]){
            maxPath = Math.max(maxPath,dfs(matrix, i, j+1)+1);
        }
        return maxPath;
    }

    public int longestIncreasingPath1(int[][] matrix){
        int m = matrix.length;
        if (m==0){
            return 0;
        }
        int n = matrix[0].length;
        if (n==0){
            return 0;
        }
        int maxPath = 0;
        int[][] path = new int[m][n];
        for (int i = 0; i<m; i++){
            Arrays.fill(path[i],0);
        }
        for (int i =0; i<m;i++){
            for (int j=0;j<n;j++){
                maxPath = Math.max(maxPath, dfs1(matrix, path, i, j));
            }
        }
        return maxPath;
    }

    private int dfs1(int[][] matrix, int[][] path, int i, int j){
        int m = matrix.length;
        int n = matrix[0].length;
        int maxPath = 0;
        //向上移动（i-1）
        if (i-1>=0 && matrix[i][j]<matrix[i-1][j]){
            if (path[i-1][j] == 0){
                path[i-1][j] = dfs1(matrix, path, i-1,j);
            }
            maxPath = Math.max(maxPath, path[i-1][j]+1);
        }
        //向下移动（i+1）
        if (i+1<m && matrix[i][j]<matrix[i+1][j]){
            if (path[i+1][j] == 0){
                path[i+1][j] = dfs1(matrix,path,i+1,j);
            }
            maxPath = Math.max(maxPath, path[i+1][j]+1);
        }
        //向左移动 j-1
        if (j-1>=0 && matrix[i][j]<matrix[i][j-1]){
            if (path[i][j-1] == 0){
                path[i][j-1] = dfs1(matrix,path,i,j-1);
            }
            maxPath = Math.max(maxPath, path[i][j-1]+1);
        }
        //向右移动 j+1
        if (j+1<n && matrix[i][j]<matrix[i][j+1]){
            if(path[i][j+1] == 0){
                path[i][j+1] = dfs1(matrix,path,i,j+1);
            }
            maxPath = Math.max(maxPath,path[i][j+1]+1);
        }
        path[i][j] = maxPath==0?1:maxPath;
        return path[i][j];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] nums = {{9, 9, 4},{6, 6, 8},{2, 1, 1}};
        int[][] nums2 ={{1,2}};
        System.out.println(sol.longestIncreasingPath1(nums));
    }
}
