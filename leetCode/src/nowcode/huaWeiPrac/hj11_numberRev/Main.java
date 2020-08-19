package nowcode.huaWeiPrac.hj11_numberRev;

import java.util.Scanner;

public class Main {
    /**
     * 数字颠倒
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            StringBuffer strBuf = new StringBuffer(String.valueOf(n));
            strBuf.reverse();
            System.out.println(strBuf.toString());
        }
    }
}
