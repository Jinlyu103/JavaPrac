package nowcode.huaWeiPrac.lastWordLength;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strArr = str.split(" ");
        System.out.println(strArr[strArr.length-1].length());
    }
}
