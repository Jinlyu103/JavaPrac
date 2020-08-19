package letterCombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * 17. 电话号码的字母组合
     * 回溯
     */
    Map<Integer, String> hmap = new HashMap<Integer, String>();
    List<String> outputs = new ArrayList<>();

    private void init(){
        hmap.put(2, "abc");
        hmap.put(3, "def");
        hmap.put(4, "ghi");
        hmap.put(5, "jkl");
        hmap.put(6, "mno");
        hmap.put(7, "pqrs");
        hmap.put(8, "tuv");
        hmap.put(9, "wxyz");
    }

    private void backtrack(String s, String next_digits){
        if (next_digits.length() == 0){ //没有剩余的数字未匹配
            outputs.add(s);
            return;
        }

        int d = Integer.valueOf(next_digits.substring(0,1));
        String letters = hmap.get(d);
        for (int i = 0; i<letters.length(); i++){
            String letter = letters.substring(i,i+1); //做出选择
            backtrack(s+letter, next_digits.substring(1)); //回溯
        }
    }
    public List<String> letterCombinations(String digits){
        if (digits.length() == 0){
            return outputs;
        }
        init();
        backtrack("", digits);
        return outputs;
    }

    public static void main(String[] args) {
        String digits = "2342";
        Solution sol = new Solution();
        System.out.println(sol.letterCombinations(digits));
    }
}
