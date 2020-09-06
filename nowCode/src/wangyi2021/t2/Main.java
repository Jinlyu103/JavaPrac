package wangyi2021.t2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i<T; i++){
            int N = sc.nextInt();
            int[][] directions = new int[N][2];
            for (int j = 0; j<N; j++){
                directions[i][0] = sc.nextInt(); //x: 尝试的方向，0,1,2,3
                directions[i][1] = sc.nextInt(); //y: 尝试是否成功，1成功，-1不成功
            }
            //先找到终点
        }
    }
}
