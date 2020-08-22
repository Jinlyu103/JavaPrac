package huawei2021_YH.t3;


import java.util.Scanner;

/**
 * 第三题
 * brick可以左右移动，不能旋转
 * 注意brick有突出的话只能向下突出，并且中间没有空的
 * 俄罗斯方块最多4列
 */
public class Main1 {
    //错位相加的方式求解
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String frame = sc.next();
        String brick = sc.next();
        int lenF = frame.length(), lenB = brick.length();

        int add0 = lenF - lenB;
        int res = -99999999;
        for (int i = add0; i>=0; i--){
            String tmp = brick;
            char[] arr = new char[lenF];
            for (int j = 0; j<i; j++){
                tmp += "0";
            }
            for (int k = lenF-1; k>=0; k--){
                arr[k] +=frame.charAt(k) - '0';
                if (k-(add0-i)>=0){
                    arr[k] += tmp.charAt(k-(add0-i)) - '0';
                }
            }

            int maxTmp = -999999, minTmp =999999, maxH = -999999;
            for (int j= 0; j<arr.length;j++){
                maxTmp = Math.max(maxTmp, arr[i]);
                minTmp = Math.min(minTmp, arr[i]);
                maxH = Math.max(maxH, maxTmp - minTmp);
            }
            res = Math.max(maxH, res);
        }
        System.out.println(res);
    }
}
