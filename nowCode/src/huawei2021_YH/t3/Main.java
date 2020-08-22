package huawei2021_YH.t3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第三题
 * brick可以左右移动，不能旋转
 * 注意brick有突出的话只能向下突出，并且中间没有空的
 * 俄罗斯方块最多4列
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String frame = sc.next();
        String brick = sc.next();

        //预处理，将frame和brick分别转换成矩阵的形式
        int[] arrF = new int[4]; //当做最多4列来做
        int[] arrB = new int[4];
        int fMaxH = 0, bMaxH = 0;

        for (int i = 0, j=0; i<frame.length(); i++,j++){
            arrF[i] = frame.charAt(i) - '0';
            fMaxH = Math.max(fMaxH, arrF[i]);
            if (j<brick.length()){
                arrB[j] = brick.charAt(j) - '0';
                bMaxH = Math.max(bMaxH, arrB[j]);
            }
        }

        //定义frame和brick的二维矩阵,列数都为4, 行数为各自最大高度
        int[][] fMatrix = new int[fMaxH][4];
        int[][] bMatrix = new int[bMaxH][4];

        //初始化二维矩阵fMatrix
        for(int j = 0; j<4; j++){
            int cnt = 0;
            for (int i = arrF[j] - 1; i>=0; i--){ //初始化FMatrix第i行第j列，行数从下往上放的
                if (arrF[j] > cnt){
                    fMatrix[i][j] = 1;
                    cnt ++;
                }
            }
        }
        //初始化BMatrix
        for (int j = 0; j<brick.length(); j++){
            int cnt = 0;
            for (int i = 0; i<arrB[j]; i++){ //BMatrix从上往下放
                if (arrB[j] > cnt){
                    bMatrix[i][j] = 1;
                    cnt ++;
                }
            }
        }
        int[][] tmp = new int[fMaxH+bMaxH][4];
        //合并，分情况
        for (int j = 0; j<fMaxH+bMaxH; j++){
            //brick的首列与frame的第i列对齐
            for (int i = 0; i<4; i++){
                if (i + brick.length() > 4){
                    break;
                }
                if (fMaxH + bMaxH - j > 2){
                    tmp[j][i] = bMatrix[j][i];
                }

            }
        }
    }

    //消除判断
    public static boolean isFullLine(int[][] matrix,int line){
        for (int i = 0; i<4; i++){
            if (matrix[line][i] == 0) return false;
        }
        return true;
    }

    //消除操作
    public static void clearLine(int[][] matrix, int line, int totalLines){
        if (line == 0){ //消除第0行
            Arrays.fill(matrix[line], 0);
            return;
        }
        for (int i = line; i<totalLines; i++){
            for (int j = 0; j<4; j++){ //上面的行下移
                matrix[i][j] = matrix[i-1][j];
            }
        }
        //将最上面一行全部赋值为0
        Arrays.fill(matrix[totalLines - line - 2], 0);
    }
}
