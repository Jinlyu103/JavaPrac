package byteDance2021.t2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String[] codes = new String[t];

        for (int i = 0; i<t; i++){
            codes[i] = sc.next();
        }

        for (int i = 0; i<t; i++){
            int res = solve(0, codes[i]); //处理第i个字符串
            System.out.println(res);
        }
    }

    //删除最少的字符，使之不包含0010，那么删除的字符一定是0,1
    //子串str[start:] 不包括0010
    public static int solve(int start, String str){
        if (str.length() - start < 4){ //剩余字符串长度小于4
            return 0;
        }

        int res = str.length(); //删除的字符数
        int idx = str.indexOf("0010"); //注意缩进！！！与上一行是对齐的
        if (idx == -1){
            return 0;
        }
        for (int i = idx; i<idx+4; i++){
            String newStr = str.substring(0,i) + str.substring(i+1); //重新递归的字符串为删掉i字符之后形成的新串
            res = Math.min(res, solve(0, newStr) + 1); //删除第i个字符，继续递归,从新串的idx+1开始？
        }
        return res;
    }
}
