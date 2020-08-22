package updateBoard;

import javax.security.sasl.SaslClient;
import java.util.Stack;

public class Solution {
    /**
     * 529. 扫雷
     * 给定一个代表游戏版的二维字符矩阵
     * ‘M'代表未挖出的地雷
     * ’E'代表未挖出的空方块
     * ’B‘代表没有相邻地雷已挖出的空方块
     * 数字1~8代表有多少块地雷与这块已挖出的方块相邻
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard(char[][] board, int[] click){
        //深度优先搜索
        int x = click[0], y = click[1];

        if (board[x][y] == 'M'){ //挖出地雷，结束游戏
            board[x][y] = 'X';
            return board;
        }
        //上下左右，四个对角线
        int[][] neb = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};
        Stack<int[]> stk = new Stack<>();
        stk.push(click);
        while (!stk.isEmpty()){
            int[] cur = stk.pop();
            int cur_x = cur[0], cur_y = cur[1];
            //检查相邻的空方块是否存在地雷
            int cnt = 0;
            for (int k = 0; k<8; k++){
                int[] delta = neb[k];
                int nxt_x = cur_x+delta[0];
                int nxt_y = cur_y+delta[1];
                if (!inBound(board, nxt_x, nxt_y) || board[nxt_x][nxt_y] == 'B'){
                    continue;
                }
                if (board[nxt_x][nxt_y] == 'M'){ //相邻有地雷
                    cnt++;
                }
            }
            if (cnt > 0){ // 相邻有地雷
                board[cur_x][cur_y] = (char) (cnt + '0');
            } else { //相邻没有地雷，相邻节点入栈
                board[cur_x][cur_y] = 'B';
                for (int k = 0; k<8; k++) {
                    int[] delta = neb[k];
                    int nxt_x = cur_x + delta[0];
                    int nxt_y = cur_y + delta[1];
                    if (!inBound(board, nxt_x, nxt_y) || board[nxt_x][nxt_y] == 'B') {
                        continue;
                    }
                    stk.add(new int[]{nxt_x, nxt_y});
                }
            }
        }
        return board;
    }

    public boolean inBound(char[][] board, int x, int y){
        int m = board.length;
        int n = board[0].length;
        return (x>=0 && x<m) && (y>=0 && y<n);
    }
}
