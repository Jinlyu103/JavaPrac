package huawei2021.t3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int P = sc.nextInt();
            int N = sc.nextInt();
            String[] guessW = new String[N];
            int[] rightPos = new int[N];
            int[] wrongPos = new int[N];

            for (int i = 0;i<N; i++){
                guessW[i] = sc.next();
                rightPos[i] = sc.nextInt();
                wrongPos[i] = sc.nextInt();
            }

            StringBuffer res = new StringBuffer();
            res.append(guessW[0]);
            System.out.println(res);

        }
    }
}
