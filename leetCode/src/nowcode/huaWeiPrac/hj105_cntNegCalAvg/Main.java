package nowcode.huaWeiPrac.hj105_cntNegCalAvg;

import java.util.Scanner;

public class Main {
    /**
     * 输入任意个整数，统计其中的负数个数，并求所有非负数的平均值
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int negs = 0; //统计负数
        int poss = 0; //统计正数
        int sumPoss = 0;

        while (sc.hasNext()){
            int n = sc.nextInt();
            if (n >= 0){
                poss ++;
                sumPoss += n;
            } else {
                negs++;
            }
        }
        System.out.println(negs);
        float avg = 0.0f;
        if (poss > 0) {
            avg = ((float) sumPoss)/poss;
        }
        System.out.println(String.format("%.f", avg)); //输出保留一位小数
    }
}
