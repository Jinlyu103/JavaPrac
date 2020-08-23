package tencent2021.code.t4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    /**
     * 刷板子
     * 所有板全部刷完需要的最小次数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        //存放第i块木板的情况
        //Map<Integer, Integer> colored = new TreeMap<>();
        for (int i = 0; i<n; i++){
            a[i] = sc.nextInt();
            //colored.put(i, a[i]); //待刷
        }
        //双指针
        int cnt = 0; //次数
        int l = 0;
        int r = n-1;
        int c = 0;
        while (l<n){
            while (l<n-1 && a[l]==a[l+1]){
                l++;
            }
            cnt += a[l] - c;
            l++;
            if (l< n && a[l] > a[l-1]){
                c = a[l] - a[l-1];
            } else if (l<n){
                c = a[l-1] - a[l];
            }
        }
        System.out.println(cnt);
    }

    public static boolean allColored(Map<Integer, Integer> colored){
        for (Integer k: colored.keySet()){
            if (colored.get(k) > 0){
                return false;
            }
        }
        return true;
    }
}
