package nowcode.huaWeiPrac.splitStr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * 字符串分隔: 连续输入字符串，请按照长度为8拆分每个字符串后输出到新的字符串数组
     * 长度不是8的整数倍的字符串请在后面补数字0，空字符串不处理
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int k = 0; k<2; k++){
            String s = sc.nextLine();
            StringBuffer strBuf = new StringBuffer(s);
            //int n = strBuf.length()%8==0 ? strBuf.length()/8 : strBuf.length()/8 +1;

            for (int i = 0; i<strBuf.length()%8; i++){
                strBuf.append(0);
            }
            List<String> sList = new ArrayList<String>();
            int i = 0;
            while (i<=strBuf.length()-8){
                sList.add(strBuf.substring(i,i+8));
                System.out.println(strBuf.substring(i,i+8));
                i += 8;
            }
        }
    }
}
