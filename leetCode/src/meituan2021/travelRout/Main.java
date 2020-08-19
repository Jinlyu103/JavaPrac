package meituan2021.travelRout;

import java.util.Scanner;

public class Main {
    /**
     * 统计旅行次数
     * 一段闭合的行程被认为是一次旅行
     * 记录按照时间顺序给出，那么第i张票和第i+1张票的首尾相连
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] info = new String[n][2];//n张票的出发地[0]，目的地[1]

        for (int i = 0; i<n; i++){
            info[i][0] = sc.next();
            info[i][1] = sc.next();
        }

        String first_start = "#"; //最开始的出发地
        int res = 0;
        for (int i = 0; i<n; i++){
            String start = info[i][0]; //第i张票的起点
            String end = info[i][1];
            if (first_start.equals("#")){
                first_start = start;
                continue;
            }
            if (end.equals(first_start)){
                res ++;
                first_start = "#";
            }
        }
        System.out.println(res);
    }
}
