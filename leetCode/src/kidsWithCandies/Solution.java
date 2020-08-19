package kidsWithCandies;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<Boolean>();
        int maxCandies = 0;
        for (int i = 0; i < candies.length ; i++) {
            maxCandies = Math.max(maxCandies, candies[i]);
        }
        for (int i = 0; i < candies.length ; i++) {
            res.add((candies[i] + extraCandies) >= maxCandies);
        }
        return res;
    }
}
