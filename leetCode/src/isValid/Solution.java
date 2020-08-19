package isValid;

import java.util.Stack;

public class Solution {
    /**
     * 20. 有效的括号
     * 给定只包括 （，），{，}，【，】的字符串，判断字符串是否有效
     */
    public boolean isValid(String s){
        if (s.length() == 0){
            return true;
        }
        if (s.length() % 2==1){
            return false;
        }
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i<s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) =='['){
                stk.push(s.charAt(i)); //左括号入栈
            } else if (s.charAt(i) == ')' && !stk.isEmpty() && stk.peek() == '(') {
                stk.pop();
            } else if (s.charAt(i) == '}' && !stk.isEmpty() && stk.peek() == '{') {
                stk.pop();
            } else if (s.charAt(i) == ']' && !stk.isEmpty() && stk.peek() == '[') {
                stk.pop();
            } else {
                return false;
            }
        }
        return stk.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "){";
        System.out.println(sol.isValid(s));
        int a = 1,b = 32;
        System.out.println(a<<b);
        System.out.println(1<<32);
    }
}
