package nowcode.huawei2016.sudoKu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    //数独求解
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int[][] data = new int[9][9];
            ArrayList<HashSet<Integer>> row = new ArrayList<>();
            ArrayList<HashSet<Integer>> col = new ArrayList<>();
            ArrayList<HashSet<Integer>> squ = new ArrayList<>(); //?

            for (int i = 0; i<9; i++){
                row.add(new HashSet<Integer>());
                col.add(new HashSet<Integer>());
                squ.add(new HashSet<Integer>());
            }

            for (int i = 0; i<9; i++){
                for (int j = 0; j<9; j++){
                    data[i][j] = sc.nextInt();
                    if (data[i][j] !=0){
                        row.get(i).add(data[i][j]);
                        col.get(j).add(data[i][j]);
                        squ.get(i/3 *3 + j/3).add(data[i][j]); //?
                    }
                }
            }
            dfs(data, row, col, squ, 0);

            for (int i = 0; i<9; i++){
                for (int j = 0; j <9; j++){
                    if (j!=8){
                        System.out.print(data[i][j] +" ");
                    } else
                        System.out.println();
                }
            }
        }
    }

    public static boolean dfs(int[][] data, ArrayList<HashSet<Integer>> row, ArrayList<HashSet<Integer>> col, ArrayList<HashSet<Integer>> squ, int idx){
        if (idx == 81){
            return true;
        }
        int m = idx/9;
        int n = idx%9;
        int k = m/3 *3 +n/3;

        if (data[m][n] !=0){
            return dfs(data, row,col,squ, idx+1);
        } else {
            for (int i = 1; i<=9; i++){
                if (!row.get(m).contains(i) && !col.get(n).contains(i) && !squ.get(k).contains(i)){
                    data[m][n] = i;
                    row.get(m).add(i);
                    col.get(n).add(i);
                    squ.get(k).add(i);
                    if (dfs(data,row,col,squ,idx+1))
                        return true;
                    data[m][n] = 0;
                    row.get(m).remove(i);
                    col.get(n).remove(i);
                    squ.get(k).remove(i);
                }
            }
            return false;
        }
    }
}
