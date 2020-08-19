package nowcode.huaWeiPrac;

import java.util.*;

public class Main {
    /**
     * 提取不重复的整数
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = str.length(); i>0; i--){
            int p = Integer.valueOf(str.substring(i-1,i));
            if (map.containsKey(p)){
                continue;
            }
            res.add(p);
            map.put(p,1);
        }
        for (Integer i:res){
            System.out.print(i);
        }
    }
}
