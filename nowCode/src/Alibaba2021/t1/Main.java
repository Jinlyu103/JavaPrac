package Alibaba2021.t1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            long n = sc.nextLong();
            long ans = 0;
//            for (int i = 1; i<n+1; i++){
//                if (i < n){
//                    ans += n;
//                } else {
//                    ans += 1;
//                }
//            }
            ans = n*(n-1) + 1;
            System.out.println(ans);
        }
    }
}
