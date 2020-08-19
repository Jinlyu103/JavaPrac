package palindromePairs;

import javax.swing.*;
import java.util.*;

public class Solution {
    /**
     * 336. 回文对：
     * 回文串：正读反读都一样
     * 找出words中不同的索引对（i,j)，使得words[i]+words[j]，可拼接成回文串
     * @param words
     * @return
     */
    List<String> wordsRev = new ArrayList<String>();//所有字符串的翻转串
    Map<String, Integer> indices = new HashMap<String, Integer>(); //用哈希表存储翻转串

    public List<List<Integer>> palindromePairs(String[] words){
        /**
         * 思路1：枚举前缀和后缀
         * 假设两个字符串s1,s2，s1+s2是一个回文串，长度分别为len1, len2.分以下情况讨论：
         *      len1 = len2: s1为s2的翻转
         *      len1 > len2: 可将s1拆分为左右两部分：t1和t2,其中t1是s2的翻转，t2是一个回文串
         *      len1 < len2: 可将s2拆分为左右两部分：t1和t2，其中t1是s1的翻转，t2是一个回文串
         *
         * 那么，对每一个字符串，另其为s1和s2中较长的那一个，然后找到可能和它工程会问串的字符串即可
         * 即枚举每一个字符串k的每一个前缀和后缀，判断其是否为回文串。如果是，就查询其剩余部分的翻转是否在words列表中
         * 注意：空串也是回文串
         */
        int n = words.length;

        for (String s:words){ //将String类型转换为StringBuffer，再进行翻转，最后转换为String
            wordsRev.add(new StringBuffer(s).reverse().toString());
        }

        for (int i = 0; i<n; i++){ //将字符串翻转并放入哈希表
            indices.put(wordsRev.get(i),i);
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i<n; i++){
            String w = words[i]; //枚举每一个字符串k/w
            int m = w.length();
            if (m == 0){ //空字符串，继续下一个
                continue;
            }
            //枚举k的每一个后缀，前缀
            for (int j = 0; j<=m; j++){
                if (isPalindrome(w,j,m-1)){ //如果w[j:m-1]是回文串，需要判断0~j-1是否在wordsRev中
                    int leftId = findWord(w, 0, j-1);
                    if (leftId != -1 && leftId != i){
                        res.add(Arrays.asList(i, leftId));
                    }
                }
                if (j !=0 && isPalindrome(w, 0, j-1)){ //如果w[0,j-1]是回文串，需要判断j~m-1是否在wordsRev中
                    int rightId = findWord(w, j, m-1);
                    if (rightId != -1 && rightId != i){
                        res.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return res;
    }

    /**
     * 判断单词[begin,end]部分是否回文
     * @param w
     * @param begin
     * @param end
     * @return
     */
    private boolean isPalindrome(String w, int begin, int end){
        int len = end - begin +1;
        for (int i = 0; i< len/2; i++){
            if (w.charAt(begin+i) != w.charAt(end-i))
                return false;
        }
        return true;
    }

    /**
     * 在indices 中寻找单词w的 [begin, end]部分
     * @param w
     * @param begin
     * @param end
     * @return
     */
    private int findWord(String w, int begin, int end){
        if (indices.containsKey(w.substring(begin,end+1))){
            return indices.get(w.substring(begin, end+1));
        }
        return -1;
    }
}
