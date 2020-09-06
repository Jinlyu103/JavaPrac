package bilibili2020.t1;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    /**
     * candies
     */
    public int[] find_children (int candiesNeed, int[] candies) {
        Map<Integer, Integer> map = new HashMap<>(); //key为需求，value为孩子编号
        int[] res = new int[2];
        List<int[]> list = new ArrayList<>();
        Arrays.fill(res, -1);
        for (int i = 0; i<candies.length; i++){
            if (map.containsKey(candies[i])){
                int idx1 = map.get(candies[i]);
                int idx2 = i;
                list.add(new int[]{idx1,idx2});
            }
            map.put(candiesNeed - candies[i], i);
        }

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]){
                    return Integer.compare(o1[0], o2[0]);
                } else
                    return Integer.compare(o1[1], o2[1]);
            }
        });
        if (list.size() > 0)
            res = list.get(0);
        return res;
    }
}
