package floodFill;

import java.util.Stack;

public class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor){
        /**
         * 从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点
         * 再记录这四个方向上符合条件 的 像素点与他们对应四个方向上像素值
         */
        int m = image.length;
        int n = image[0].length;

        int oldColor = image[sr][sc]; //原来的颜色
        if (newColor == oldColor){
            return image;
        }
        Stack<int[]> stk = new Stack<>();

        int[] cur = {sr,sc};
        stk.add(cur); //初始坐标入栈
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        while (!stk.isEmpty()){
            cur = stk.pop();
            int cur_x = cur[0];
            int cur_y = cur[1];
            image[cur_x][cur_y] = newColor; //渲染当前节点
            //查看相邻节点情况
            for (int i = 0; i<4; i++){
                int nxt_x = cur_x + dx[i];
                int nxt_y = cur_y + dy[i];
                if (!inBound(image, nxt_x, nxt_y) || image[nxt_x][nxt_y] != oldColor){
                    continue;
                }
                //相邻节点入栈
                stk.push(new int[]{nxt_x, nxt_y});
            }
        }
        return image;
    }

    public boolean inBound(int[][] image, int x, int y){
        int m = image.length;
        int n = image[0].length;
        return (x>=0 && x<m && y>=0 && y<n);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] image = {{0,0,0},{0,1,1}};
        int c = 1;
        int sr = 1, sc = 1;
        System.out.println(sol.floodFill(image, sr,sc,c));
    }
}
