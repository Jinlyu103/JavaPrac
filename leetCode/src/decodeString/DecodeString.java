package decodeString;

import java.util.Stack;

public class DecodeString {
    /**
     * 考查栈的操作：
     * 1、遍历字符串s, 遇到数字，解析出数字（可能多位），数字入栈；遇到[或者字符，直接入栈
     * 2、遇到右括号，从栈中依次弹出字符，并将字符添加到strBuf中，直到弹出左括号
     * 将strBuf逆序，然后弹出栈顶的数字n，即为串strBuf重复次数
     * 3、将str重复之后，再压入栈中。继续遍历字符串s
     */
    int pos; //记录当前遍历到的字符串s的位置
    public String decString(String s){
        int n = s.length();
        Stack<String> stk = new Stack<>();

        pos = 0;
        while (pos<n){
            if (Character.isDigit(s.charAt(pos))){
                String digits = getDigits(s);  //获取数字并压栈
                stk.push(digits);
            }else if (Character.isLetter(s.charAt(pos))){
                String letters = getLetters(s); //获取字母压栈
                stk.push(letters);
            }else if (s.charAt(pos) == '['){
                stk.push("[");
                pos ++;
            }else { //遇到']'
                pos ++;
                String str = stk.pop(); //弹出栈顶字符串
                while (!stk.peek().equals("["))
                    str = stk.pop() + str;
                stk.pop() ; //弹出栈顶'['
                String dig = stk.pop(); //弹出栈顶数字
                int times = Integer.parseInt(dig);
                StringBuffer newStr = new StringBuffer();
                while (times > 0){
                    newStr.append(str);
                    times --;
                }
                stk.add(newStr.toString()); //将新的字符串压栈
            }
        }
        return getStringFromStk(stk);
    }

    //获取数字串
    public String getDigits(String s){
        StringBuffer res = new StringBuffer();
        while (pos < s.length() && Character.isDigit(s.charAt(pos))){
            res.append(s.charAt(pos));
            pos ++;
        }
        return res.toString();
    }

    //获取字母串
    public String getLetters(String s){
        StringBuffer res = new StringBuffer();
        while(pos < s.length() && Character.isLetter(s.charAt(pos))){
            res.append(s.charAt(pos));
            pos++;
        }
        return res.toString();
    }

    //返回栈中的字符串拼接而成的新的字符串
    public String getStringFromStk(Stack<String> stk){
        StringBuffer strBuf = new StringBuffer();
        for (String s: stk){
            strBuf.append(s);
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        DecodeString sol = new DecodeString();
        String s = "3[a2[c]]";
        System.out.println(sol.decString(s));
    }
}
