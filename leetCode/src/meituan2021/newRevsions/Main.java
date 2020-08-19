package meituan2021.newRevsions;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    /**
     * 输入n,输出不超过n的正整数中有多少对新定义的逆序对
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Map<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        for (int i = 1; i<=n; i++){
            //为新定义的逆序对，并且逆序数也不超过n
            if (isNewRev(i) && i*4 <= n){
                cnt++;
                map.put(i, i*4);
            }
        }
        System.out.println(cnt);
        if (cnt != 0){
            for (Integer i: map.keySet()){
                System.out.println(i + " " +map.get(i));
            }
        }
    }

    /**
     * 判断x是否为新定义的逆序对
     * @param x
     * @return
     */
    public static boolean isNewRev(int x){
        int rev = 0;
        int tmp = x;
        //求x的逆序结果，存放在rev中
        while (tmp > 0){
            rev = rev*10 + tmp%10;
            tmp = tmp/10;
        }
        if (x*4 == rev){
            return true;
        }
        return false;
    }
}
