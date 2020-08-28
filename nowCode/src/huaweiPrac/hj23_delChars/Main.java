package huaweiPrac.hj23_delChars;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * 删除字符串中出现次数最少的字符
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.next();
            Map<Character, Integer> map = new HashMap<>(); //统计每个字符出现的个数
            for (int i = 0; i<str.length(); i++){
                char cur = str.charAt(i);
                if (map.containsKey(cur)){
                    map.put(cur, map.get(cur)+1);
                }
                else {
                    map.put(cur,1);
                }
            }
            int minTimes = 21;
            for (Character c:map.keySet()){
                minTimes = Math.min(minTimes, map.get(c));
            }
            StringBuffer strB = new StringBuffer();
            for (int i = 0; i<str.length(); i++){
                char cur = str.charAt(i);
                if (minTimes == map.get(cur)){
                    continue;
                }
                strB.append(cur);
            }
            System.out.println(strB.toString());
        }
    }
}
