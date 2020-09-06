package solveNQueens;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 求解N皇后
     * 回溯
     * @param n
     * @return
     */
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n){
        List<char[]> board = new ArrayList<>(); //定义棋盘
        for (int i = 0; i<n; i++){
            char[] s = new char[n];
            for (int j=0; j<n; j++){
                s[j] = '.';
            }
            board.add(s);
        }
        backtrack(board, 0);
        return res;
    }

    public void backtrack(List<char[]> board, int row){
        if (board.size() == row){
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i<board.size(); i++){
                StringBuffer s = new StringBuffer();
                for (int j = 0; j<board.get(i).length; j++){
                    s.append(board.get(i)[j]);
                }
                tmp.add(s.toString());
            }
            res.add(tmp);
            return;
        }
        for (int col = 0; col<board.size(); col ++){
            if (!isValid(board, row, col)){
                continue;
            }
            board.get(row)[col] = 'Q';
            backtrack(board, row+1);
            board.get(row)[col] = '.';
        }
    }

    public boolean isValid(List<char[]> board, int row, int col){
        //第一行选第col列
        if (row == 0)
            return true;
        //判断列冲突
        for (int j = 0; j<board.size(); j++){
            if (board.get(j)[col] == 'Q'){ //列冲突
                return false;
            }
        }
        //判断对角线冲突
        int i = row-1;
        int j = col-1;
        while (i>=0 && j>=0){
            if (board.get(i)[j] == 'Q')
                return false;
            i--;
            j--;
        }

        i = row-1;
        j = col+1;
        while (i>=0 && j<board.size()){
            if (board.get(i)[j] == 'Q')
                return false;
            i--;
            j++;
        }
        return true;
    }
}
