package minWindowSubstring;

public class Solution {
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }
        char[] charArrayS = s.toCharArray(); //将字符串转化为字符数组
        char[] charArrayT = t.toCharArray();

        //ascii('z) = 122
        int[] winFreq = new int[123]; //滑动窗口中字符频数
        int[] tFreq = new int[123];  //记录字符串T中字符频数
        for (char c : charArrayT) { //for-each循环
            tFreq[c] ++; //初始化T中的字符频数
        }
        //滑动窗口内部_包含_多少T中的字符，对应字符频数超过不重复计算
        int distance = 0;
        int minLen = sLen + 1;
        int begin = 0;

        int left = 0;
        int right = 0;
        //[left,right),左开右闭
        while(right < sLen) {
            if (tFreq[charArrayS[right]] == 0) { //s中当前字符不在t中出现, right右移
                right ++;
                continue;
            }
            //s中right所指向的字符在t中出现时，distance++
            if (winFreq[charArrayS[right]] < tFreq[charArrayS[right]]) {
                distance ++;
            }
            winFreq[charArrayS[right]] ++;
            right ++;

            //当滑动窗口中包含了t中所有字符（可行解），准备移动左指针left（找最优解）
            while (distance == tLen) {
                if(right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }

                if (tFreq[charArrayS[left]] == 0) {
                    left ++;
                    continue;
                }

                if(winFreq[charArrayS[left]] == tFreq[charArrayS[left]]) {
                    distance --;
                }

                winFreq[charArrayS[left]] --;
                left ++;
            }
        }
        if (minLen == sLen + 1) {
            return "";
        }
        return s.substring(begin, begin+minLen);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Solution sol = new Solution();
        System.out.println(sol.minWindow(s,t));
    }
}