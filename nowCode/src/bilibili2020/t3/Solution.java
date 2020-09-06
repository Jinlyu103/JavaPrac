package bilibili2020.t3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * 打印n行n列的菱形
     * 如果n为偶数，返回空
     * @param n
     * @return
     */
    public String print_diamond (int n) {
        if (n % 2 == 0 || n<0){
            return "";
        }
        StringBuffer strBuf = new StringBuffer();
        int mid = n/2;
        for (int i = 0; i<n; i++) { //第i行
            List<String> left = new ArrayList<>();
            //StringBuffer left = new StringBuffer();
            int num;
            if (i>mid) {
                num = n - i;
            } else {
                num = i+1;
            }
            int j = mid;
            while (j >= 0) {
                if (num != 0) {
                    left.add(num+"");
                    num--;
                } else {
                    left.add("*");
                }
                j--;
            }
            List<String> right = new ArrayList<>();
            for (int k = 1; k<left.size(); k++){
                right.add(left.get(k));
            }
            Collections.reverse(left);
            StringBuffer row = new StringBuffer();
            for (int k = 0; k<left.size(); k++){
                row.append(left.get(k));
            }
            for (int k = 0; k<right.size(); k++){
                row.append(right.get(k));
            }
            if (i != n-1){
                strBuf.append(row.toString() + '|');
            } else {
                strBuf.append(row.toString());
            }
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 11;
        System.out.println(sol.print_diamond(n));
    }
}
