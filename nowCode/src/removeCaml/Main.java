package removeCaml;

import java.util.*;

public class Main {
    /**
     * 去除驼峰式字符串
     * 驼峰式字符串定义：三个字符中，两个一样的字符夹着一个不一样的字符称为驼峰字符串
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s = sc.nextLine();

            if (s.length()<3){
                System.out.println(s);
                continue;
            }
            String res = solve(s); //去除驼峰串
            System.out.println(res);
        }
    }

    public static String solve(String s){
        StringBuffer res = new StringBuffer();
        int i = 0;
        while (i<s.length()){
            char pre = s.charAt(i);
            boolean flag = true; //标识没有出现驼峰
            int j = i+2; //判断[i,i+2]部分是否为驼峰串，若是，删除
            while (j<s.length()){
                char nxt = s.charAt(j);
                char mid = s.charAt(j-1);
                if (pre == nxt && pre != mid){ //发现了驼峰,指针i右移到j的位置（删除了i~j之间的驼峰
                    i = j;
                    flag = false;
                } else { //[i,i+2]不为驼峰，跳出内层循环
                    break;
                }
                j += 2 ;
            }
            if (flag){ //没有发现驼峰，将当前字符加入
                res.append(pre);
            }
            i ++;
        }
        if (s.length() == res.length())// 检查取出驼峰后的字符串是否还存在驼峰
            return res.toString();
        return solve(res.toString());
    }
}
