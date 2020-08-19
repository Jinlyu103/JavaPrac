package decodeString;

import javax.security.auth.login.AccountExpiredException;
import java.util.*;

public class Solution {
    int i;
    public String decodeString(String s) {
        /**
         * 用栈存放字符：数字，[，和 ]前的字符
         * 当遇到]时，栈中的元素出栈，直到【和前面的数字
         * 重复之间的字符，重新入栈
         *
         * 具体操作：将字母、数字和括号看成是独立的TOKEN，并利用栈维护这些TOKEN。
         *      遍历这个栈：
         *          如果当前字符为数位，解析出一个数字（连续多个数位），并进栈
         *          如果当前的字符为字母或左括号，直接进栈
         *          如果当前字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，
         *          此时，取出栈顶的数字，就是这个字符串应该出现的次数，
         *          根据次数和字符串构造出新的字符串，并入栈
         *      重复以上操作，最终将栈中的元素按照从栈底到栈顶的顺序拼接。
         *      可以用不定长数组来模拟栈操作，方便从栈底向栈顶遍历。
         */
        String ans;
        //LinkedList对象是一个双向链表，实现了List接口和Deque接口，可被当做堆栈、队列来使用
        LinkedList<String> stack = new LinkedList<String>();

        int n = s.length();
        i = 0;

        while (i < n){
            char cur = s.charAt(i);
            if (Character.isDigit(cur)){
                String digits = getDigits(s); // 获取一个数字入栈
                stack.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '['){ //字母或者[直接入栈
                stack.addLast(String.valueOf(cur));
                i ++;
            } else{ // ]，开始出栈
                i ++;
                LinkedList<String> sub = new LinkedList<String>();
                while (! stack.peekLast().equals("[")){
                    sub.addLast(stack.removeLast());
                }
                Collections.reverse(sub);
                //左括号出栈
                stack.removeLast();
                //此时栈顶元素为sub对应的字符串出现的次数
                int repTime = Integer.parseInt(stack.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub); //获取sub中的字符串
                while (repTime > 0){
                    t.append(o);
                    repTime -- ;
                }
                //将构造好的字符串加入到栈中
                stack.addLast(t.toString());
            }
        }
        ans = getString(stack);
        return ans;
    }

    private String getDigits(String s){ //获取连续的多个数位
        StringBuffer res = new StringBuffer();
        while (Character.isDigit(s.charAt(i))){
            res.append(s.charAt(i++));
        }
        return res.toString();
    }

    private String getString(LinkedList<String> sub){
        StringBuffer res = new StringBuffer();
        for (String s: sub) {
            res.append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "3[a]2[bc]";
        System.out.println(sol.decodeString(s));
    }
}
