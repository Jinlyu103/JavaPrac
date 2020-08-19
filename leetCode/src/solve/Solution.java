package solve;

import java.util.Stack;

public class Solution {
    /**
     * 130 被围绕的区域
     * 给定一个二维矩阵，包含X和O
     * 找到所有被X包围的区域，并将区域内所有的O用X填充
     * 注意：边界上的O不会被填充
     * 也就是说任何边界上的O或者与边界上的O间接或直接相连的O都不会被填充
     * 可以先将直接或间接与边界上的O相连的O进行标记，最后遍历board，将未被标记的O填充为X
     * 思路是：
     * 1、先找到所有边界上的O，置于栈中
     * 2、从栈中取出元素，进行深度优先搜索，标记与边界相连的O
     * 3、遍历经过标记的board，将未标记的O填充为X
     */
    public void solve(char[][] board){
        int m = board.length;
        if (m == 0){
            return;
        }
        int n = board[0].length;
        Stack<int[]> stk = new Stack<>();
        for (int i = 0; i<m; i++){
            for (int j = 0; j<n; j++){
                //判断（i,j)是否处于边界
                if (i==0 || j==0 || i == m-1 || j == n-1){
                    if (board[i][j] == 'O'){
                        int[] tmp = {i,j};
                        stk.add(tmp);
                    }
                }
            }
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        //DFS
        while (!stk.isEmpty()){
            int[] cur = stk.pop();
            board[cur[0]][cur[1]] = 'A'; //将边界标记为A
            //查看相邻节点情况
            for (int k = 0; k<4; k++){
                int nx = cur[0] +dx[k];
                int ny = cur[1] +dy[k];
                //相邻节点不在合法范围内，或者为X，或者已标记
                if (nx <= 0 || nx >= m-1 || ny<=0 || ny>=n-1 || board[nx][ny] =='X' || board[nx][ny] == 'A'){
                    continue;
                }
                stk.push(new int[]{nx, ny}); //相邻为O的节点入栈
            }
        }

        //遍历board
        for(int i = 0; i<m ; i++){
            for (int j = 0; j<n; j++){
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else continue;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = {{'O','O'},{'O','O'}};
        sol.solve(board);
    }
}
