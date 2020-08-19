package nowcode.huaWeiPrac.baseConversion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next();
            System.out.println(Integer.decode(str));
        }
    }
}
