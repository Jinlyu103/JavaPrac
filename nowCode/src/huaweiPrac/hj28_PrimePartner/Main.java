package huaweiPrac.hj28_PrimePartner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /**
     * 素数伴侣：
     * 若两个正整数的和为素数，则这两个正整数称为素数伴侣
     * 除了2以外所有的素数都是奇数
     * 偶数+奇数 = 奇数
     * 可以将n个整数分为奇数和偶数
     * 然后在他们之间，和是素数的连一条边，然后做匹配
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            long[] a = new long[n];

            //偶数部分
            ArrayList<Long> evens = new ArrayList<>();
            //奇数部分
            ArrayList<Long> odds = new ArrayList<>();

            for (int i = 0; i<n; i++){
                a[i] = sc.nextLong();
                if (a[i] % 2 == 0){ //偶数
                    evens.add(a[i]);
                }else { //奇数
                    odds.add(a[i]);
                }
            }
            long[] evensMatch = new long[evens.size()]; //每一个偶数的匹配情况
            int result = 0;
            for (int i =0; i<odds.size(); i++){
                int[] used = new int[evens.size()]; //第j个偶数是否匹配的标记
                if (find(odds.get(i), evens, used, evensMatch)){ //深度优先搜索
                    result ++;
                }
            }
            System.out.println(result);
        }
        sc.close();
    }

    /**
     * 判断n是否为素数
     * @param n
     * @return
     */
    public static boolean isPrime(long n){
        if (n <= 1){
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean find(long x, ArrayList<Long> evens, int[] used, long[] evensMatch){
        int j;
        for (j = 0; j<evens.size(); j++){
            if (isPrime(x + evens.get(j)) && used[j] == 0) {
                used[j] = 1;
                if (evensMatch[j] == 0 || find(evensMatch[j], evens, used, evensMatch)) {
                    evensMatch[j] = x;
                    return true;
                }
            }
        }
        return false;
    }
}
