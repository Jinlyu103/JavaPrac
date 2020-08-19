package nowcode.huaWeiPrac.hj12_stringRev;

import java.util.Scanner;

public class Main {
    /**
     * 字符串翻转
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            StringBuffer strBuf = new StringBuffer(str);
            strBuf.reverse();
            System.out.println(strBuf.toString());
        }
    }
}
