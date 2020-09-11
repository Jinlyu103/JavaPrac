package tencent2021.code1.t4;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    /**
     * 中位数
     * 偶数位经过排序后中位数为：第n/2-1，和n/2两数的平均数
     * 删除一个数的话，判断该数与以上两个数的大小关系
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i<n; i++){
                a[i] = sc.nextInt();
            }
            int[] res = solve(a);
            for (int r:res){
                System.out.println(r);
            }
        }
    }

    //暴力求解
    public static int[] solve1(int[] a){
        int[] res = new int[a.length];
        for (int j = 0; j<a.length; j++){
            int[] copy = new int[a.length-1];       //将a余下的元素复制一份
            int k = 0;
            for (int i = 0; i<a.length; i++){
                if (a[i] == a[j]){
                    continue;
                }
                copy[k++] = a[i];
            }
            Arrays.sort(copy);
            int mid = copy.length/2;
            res[j] = copy[mid];
        }
        return res;
    }

    public static int[] solve(int[] a){
        //复制一份a
        int n = a.length;
        int[] copy = new int[n];
        for (int i=0; i<n; i++){
            copy[i] = a[i];
        }
        Arrays.sort(copy);
        int lMid = copy[n/2-1];
        int rMid = copy[n/2];

        int[] res = new int[n];
        for (int i = 0; i<n; i++){
            if (a[i] >= rMid){
                res[i] = lMid;
            } else {
                res[i] = rMid;
            }
        }
        return res;
    }
}
