package isNumber;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 判断字符串s是否表示字符
     * @param s
     * @return
     */
    public boolean isNumber(String s){
        if (s==null || s.length() == 0){
            return false;
        }
        s = s.trim();
        try {
            double d = Double.parseDouble(s); //将s转为浮点数
        } catch (Exception e){
            return false;
        }
        char c = s.charAt(s.length()-1);
        return (c>='0' && c<='9') || c == '.';
    }

    /**
     * 有限状态自动机求解
     * @param s
     * @return
     */
//    public boolean isNumber1(String s){
//        Map[] states = {
//               new HashMap<>(){{put(' ',0); put('s',1);put('d',2);put('.',4);}},        //0.该状态下可以遇到四种合法字符转移到4个不同状态中去
//               new HashMap<>(){{put('d',2); put('.',4);}},                              //1.
//               new HashMap<>(){{put('d', 2); put('e', 5); put('.', 3); put('b', 8);}},  //2.
//               new HashMap<>(){{put('d',3); put('e', 5); put('b', 8);}},                             //3.
//               new HashMap<>(){{put('d',3);}},                                          //4.
//               new HashMap<>(){{put('s',6); put('d',7);}},                              //5.
//               new HashMap<>(){{put('d',7);}},                                          //6.
//               new HashMap<>(){{put('d',7); put('b', 8);}},                             //7.
//               new HashMap<>(){{put('b',8);}}                                           //8.
//        };
//        int p = 0; //当前所处状态
//        char t;
//        for (char c: s.toCharArray()){
//            if (c>='0' && c<='9'){ //数字
//                t = 'd';
//            } else if (c == '+' || c == '-'){
//                t = 's';
//            } else if (c == ' '){
//                t = 'b';
//            } else if (c == '.'){
//                t = c;
//            } else if (c == 'e' || c == 'E'){
//                t = 'e';
//            } else {
//                t = '?';
//            }
//            if (!states[p].containsKey(t)) return false;
//            p = (int) states[p].get(t);
//        }
//        return p == 2 || p == 3 || p == 7 || p == 8;
//    }
}
