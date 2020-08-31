package primes_table;

import java.util.Scanner;

public class Solution {
    /**
     * 欲构造n以内的素数表
     *      1、令x=2
     *      2、将2x，3x，4x直到 ax<n 标记位非素数
     *      3、令x为下一个没有被标记的非素数的数，重复2；直到所有的数都已经尝试完毕
     * 1、创建prime为boolean[n],初始化其所有元素为true
     * 2、令x=2
     * 3、如果x是素数，则对于（i=2； x*i<n; i++),令prime[i*x] = false
     * 4、令x++，如果x<n，重复3，否则结束
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[] isPrime = new boolean[100]; //标记100以内的素数
        for (int i = 0; i<isPrime.length; i++){
            isPrime[i] = true;
        }

        for (int i = 2; i<isPrime.length; i++){
            if (isPrime[i]){
                for (int k = 2; k*i<isPrime.length; k++){
                    isPrime[i*k] = false;
                }
            }
        }

        //输出100以内的素数
        for (int i= 2; i<isPrime.length; i++){
            if (isPrime[i]){
                System.out.println(i);
            }
        }
    }
}
