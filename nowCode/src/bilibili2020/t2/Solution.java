package bilibili2020.t2;

public class Solution {
    public int cal_max_common_factor (int[] L) {
        if (L == null || L.length == 0){
            return -1;
        }
        int res = L[0];
        for (int i = 1; i<L.length; i++){
            res = gcd(res,L[i]);
        }
        return res;
    }

    public int gcd(int x, int y){
        while (y>0){
            int r = x%y;
            x = y;
            y = r;
        }
        return x;
    }
}
