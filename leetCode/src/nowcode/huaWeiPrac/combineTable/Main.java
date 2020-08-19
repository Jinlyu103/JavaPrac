package nowcode.huaWeiPrac.combineTable;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i<n; i++){
            int idx = sc.nextInt();
            int val = sc.nextInt();
            if (map.containsKey(idx)){
                map.put(idx, map.get(idx)+val);
            } else {
                map.put(idx, val);
            }
        }
        for (Integer k:map.keySet()){
            System.out.println(k + " "+map.get(k));
        }
    }
}
