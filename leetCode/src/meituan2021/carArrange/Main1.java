package meituan2021.carArrange;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] info = new String[n][2];//n张票的出发地[0]，目的地[1]

        for (int i = 0; i<n; i++){
            info[i][0] = sc.next();
            info[i][1] = sc.next();
        }

        Stack<String> stk = new Stack<>();
        int res = 0;
        for (int i = 0; i<n; i++){
            String start = info[i][0]; //第i张票的起点
            String end = info[i][1];
            stk.push(start);
            if (stk.contains(end)){
                int index = stk.indexOf(end);
                int stkSize = stk.size();
                for (int j = 0; j<stkSize-index; j++){
                    stk.pop();
                }
                res ++;
            }
        }
        System.out.println(res);
    }
}
