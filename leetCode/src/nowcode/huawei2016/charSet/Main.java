package nowcode.huawei2016.charSet;

import java.util.*;

public class Main {
    /**
     * 字符集合
     * 输入一个字符串，求该字符串包含的字符集合
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s = sc.nextLine();
            Hashtable<Character, Integer> map = new Hashtable<>();
            //Map<Character, Integer> map = new HashMap<>();
            StringBuffer str = new StringBuffer();
            for (int i = 0; i<s.length(); i++){
                if (map.containsKey(s.charAt(i))){
                    continue;
                }
                map.put(s.charAt(i), 1);
                str.append(s.charAt(i));
            }
            System.out.println(str.toString());
        }
    }
}
