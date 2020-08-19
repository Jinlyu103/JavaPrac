package nowcode.huaWeiPrac.hj10_charNumbers;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            HashSet<Character> chars = new HashSet<>();
            for (int i = 0; i<str.length(); i++){
                chars.add(str.charAt(i));
            }
            System.out.println(chars.size());
        }
        sc.close();
    }
}
