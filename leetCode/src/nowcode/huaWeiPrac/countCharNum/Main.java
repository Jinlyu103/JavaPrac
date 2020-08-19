package nowcode.huaWeiPrac.countCharNum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String c = sc.next();
        int count = 0;
        for (int i = 0; i<str.length(); i++){
            if (str.substring(i,i+1).equalsIgnoreCase(c)){
                count++;
            }
        }
        System.out.println(count);
    }
}
