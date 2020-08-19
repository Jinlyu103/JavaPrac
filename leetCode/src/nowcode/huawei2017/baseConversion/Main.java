package nowcode.huawei2017.baseConversion;

import java.util.Scanner;

public class Main {
    /**
     * 进制转换
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(Integer.decode(str));
        }
    }
}
