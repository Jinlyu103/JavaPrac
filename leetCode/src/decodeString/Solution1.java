package decodeString;

import java.util.*;

public class Solution1 {
    //用两个栈，一个存放数字，一个存放字母和左括号
    int i; //遍历字符的指针
    public String decodeString(String s){
        Stack<Integer> numStk = new Stack<Integer>();
        Stack<String> charStk = new Stack<String>();
        List<String> anslist = new ArrayList<String>();

        i = 0;
        while (i < s.length()){
            if (Character.isDigit(s.charAt(i))){//当前字符为数位
                //获取一个数字加入数字栈
                StringBuilder numstr = new StringBuilder();
                while (Character.isDigit(s.charAt(i))){
                    numstr.append(s.charAt(i));
                    i ++;
                }
                int num = Integer.parseInt(numstr.toString());
                numStk.push(num);
            } else if(Character.isLetter(s.charAt(i)) || s.charAt(i) == '['){
                //当前字符直接入字符栈
                charStk.push(String.valueOf(s.charAt(i)));
                i++;
            }else{
                i ++;
                List<String> sub = new ArrayList<String>();
                while (!charStk.peek().equals("[")){
                    //当栈顶元素不为[时，继续出栈
                    sub.add(charStk.pop());
                }
                Collections.reverse(sub);

                String subs = getString(sub);
                charStk.pop();//栈顶左括号出栈
                int repeats = numStk.pop(); //重复次数
                StringBuffer repStr = new StringBuffer();

                while(repeats > 0){
                    repStr.append(subs);
                    repeats --;
                }
                //System.out.println(repStr);
                charStk.push(repStr.toString());
            }
        }
        while (!charStk.empty()){
            anslist.add(charStk.pop());
        }
        Collections.reverse(anslist);
        StringBuffer ans = new StringBuffer();
        for (String s1: anslist) {
            ans.append(s1);
        }
        return  ans.toString();
    }

    private String getString(List<String> sub){
        StringBuffer res = new StringBuffer();
        for (String s:sub) {
            res.append(s);
        }
        return res.toString();
    }
    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        String s = "3[a]2[bc]";
        System.out.println(sol.decodeString(s));
    }
}
