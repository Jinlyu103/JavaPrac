package huawei2021.t1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            List<Integer> nums = new ArrayList<>();

            String str = sc.nextLine();
            String[] strArr = str.split(" ");
            for (int i = 0; i<strArr.length; i++){ //提取整数
                int n = Integer.parseInt(strArr[i]);
                nums.add(n);
            }
            List<Long> res = solve(nums);
            for (int i = 0; i<res.size(); i++){
                Long cur = res.get(i);
                if (i != res.size()-1){
                    System.out.print(cur + " ");
                } else {
                    System.out.println(cur + " ");

                }
            }
        }
        sc.close();
    }

    //处理整数n
    public static List<Long> solve(List<Integer> nums){
        int n = nums.size();
        List<String> biStr = new ArrayList<>();
        for (int i = 0; i<n; i++){
            int a = nums.get(i);
            StringBuffer binary = new StringBuffer(Integer.toBinaryString(a));
            //高位补0
            binary.reverse();
            if (binary.length() % 2 != 0){ //长度为奇数，需要高位补一个0，再交换
                binary.append(0);
            }
            //交换每两个bit位置
            for (int j = 0; j<binary.length()-1; j += 2){
                char bit_j = binary.charAt(j);
                char bit_nxt_j = binary.charAt(j+1);
                binary.setCharAt(j, bit_nxt_j);
                binary.setCharAt(j+1, bit_j);
            }

            //高位补0
            int len = binary.length();
            for (int j = 0; j<32-len; j++){
                binary.append(0);
            }
            binary.reverse();
            biStr.add(binary.toString());
        }
        //将所有的二进制串串成一个大字符串，整个右移
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i<biStr.size(); i++){
            strBuf.append(biStr.get(i));
        }

        String last2 = strBuf.substring(strBuf.length() - 2);
        strBuf.delete(strBuf.length() - 2, strBuf.length());
        //strBuf.append(last2, 0,2);
        String s = last2 + strBuf.toString(); //构成新字符串

        //从strBuf中每隔32位截取一个整数
        List<Long> res = new ArrayList<>();
        for (int i = 0; i<s.length()/32; i++){
            StringBuffer bi = new StringBuffer(s.substring(i*32, (i+1)*32));
            //将二进制转换为整数

            int j = 0; // 移除高位的0
            while (bi.charAt(0) == '0'){
                bi.deleteCharAt(0);
                j++;
            }
            //System.out.println(bi);
            j = bi.length()-1;
            long a = 0l;
            long b = Long.valueOf(bi.toString(), 2); //二进制串转换成Long型，radix参数为第一个数字字符串的进制
            System.out.println("b = " + b);
            while (j>0){
                int cur = Integer.parseInt(bi.substring(bi.length()-j-1, bi.length()-j));
                a = a + ((long)2<<(j-1)) * cur;
                j--;
            }
            res.add(a);
        }
        return res;
    }

    //处理整数n
    public static List<String> solve1(List<Integer> nums){
        int n = nums.size();
        List<String> biStr = new ArrayList<>();
        for (int i = 0; i<n; i++){
            int a = nums.get(i);
            StringBuffer binary = new StringBuffer(Integer.toBinaryString(a));
            //高位补0
            binary.reverse();
            if (binary.length() % 2 != 0){ //长度为奇数，需要高位补一个0，再交换
                binary.append(0);
            }
            //交换每两个bit位置
            for (int j = 0; j<binary.length()-1; j += 2){
                char bit_j = binary.charAt(j);
                char bit_nxt_j = binary.charAt(j+1);
                binary.setCharAt(j, bit_nxt_j);
                binary.setCharAt(j+1, bit_j);
            }

            //高位补0
            int len = binary.length();
            for (int j = 0; j<32-len; j++){
                binary.append(0);
            }
            binary.reverse();
            biStr.add(binary.toString());
        }
        //将所有的二进制串串成一个大字符串，整个右移
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i<biStr.size(); i++){
            strBuf.append(biStr.get(i));
        }

        String last2 = strBuf.substring(strBuf.length() - 2);
        strBuf.delete(strBuf.length() - 2, strBuf.length());
        //strBuf.append(last2, 0,2);
        String s = last2 + strBuf.toString(); //构成新字符串

        //从strBuf中每隔32位截取一个整数
        List<String> res = new ArrayList<>();
        for (int i = 0; i<s.length()/32; i++){
            StringBuffer bi = new StringBuffer(s.substring(i*32, (i+1)*32));
            //将二进制转换为整数

            int j = 0; // 移除高位的0
            while (bi.charAt(0) == '0'){
                bi.deleteCharAt(0);
                j++;
            }
            //System.out.println(bi);
            j = bi.length()-1;
            int a = 0;
            while (j>0){
                int cur = Integer.parseInt(bi.substring(bi.length()-j-1, bi.length()-j));
                a += (2<<(j-1)) * cur;
                j--;
            }
            res.add(a+"");
        }
        return res;
    }
}
