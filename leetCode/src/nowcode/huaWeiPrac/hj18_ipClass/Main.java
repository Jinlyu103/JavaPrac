package nowcode.huaWeiPrac.hj18_ipClass;

import java.util.Scanner;

public class Main {
    /**
     * 解析IP地址和对应的掩码，进行分类识别
     * 要求按照A/B/C/D/E类地址归类
     * 不合法的地址和掩码单独归类
     *
     * 当子网掩码错误时，不再判断IP是否有效，错误直接+1，进行下次循环
     * 当一个IP属于ABCDE中某类时，也属于私有IP，私有IP和其分类都应该+1
     *
     * 思路：
     * 1、判断子网掩码是否有效：
     *      判断是否为255.255.255.255，若是，错误+1，继续下次循环
     *      判断其他子网掩码是否正确。根据子网掩码二进制规律：
     *          开头为连续的1，然后为0。按位取反，然后加1，得到新的二进制位，通过判断二进制中的1的个数来判断是否为合法的子网掩码
     *          合法的子网掩码，按位取反，加1后二进制位中只有一个1
     *
     *       或者：将掩码转换为二进制字符串，如果第一个0的位置下标 < 最后一个1的位置下标
     *              那么不合法
     *              否则为合法掩码
     *
     * 2、判断IP是否有效，并分类
     *      分别比较IP地址中的四个十进制数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt_A = 0;
        int cnt_B = 0;
        int cnt_C = 0;
        int cnt_D = 0;
        int cnt_E = 0;
        int wrong_ = 0;
        int pri_ip = 0;
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            String[] str = s.split("~"); //str[0]为IP地址，str[1]为掩码
            if (!validMask(str[1])){
                wrong_ ++;
                continue;
            }
            //判断IP是否有效，并分类
            String[] ip = str[0].split("\\.");
            if (ip.length == 4){
                int[] ipArr = new int[4];
                for (int i = 0; i<4; i++){
                    if (ip[i].equals("")){
                        ipArr[i] = -1;
                        continue;
                    }
                    ipArr[i] = Integer.valueOf(ip[i]);
                }
                if ((ipArr[0]>=0&&ipArr[0]<=255)
                    &&(ipArr[1]>=0&&ipArr[1]<=255)
                    &&(ipArr[2]>=0&&ipArr[2]<=255)
                    &&(ipArr[3]>=0&&ipArr[3]<=255)){
                    if (ipArr[0]>=1&&ipArr[0]<=126){
                        cnt_A++;
                        if (ipArr[0]==10){
                            pri_ip++;
                        }
                    }else if (ipArr[0]>=128&&ipArr[0]<=191){
                        cnt_B++;
                        if (ipArr[0]==172 && (ipArr[1]>=16&&ipArr[1]<=31)){
                            pri_ip++;
                        }
                    } else if (ipArr[0]>=192&&ipArr[0]<=223){
                        cnt_C++;
                        if (ipArr[0]==192&&ipArr[1]==168){
                            pri_ip++;
                        }
                    } else if (ipArr[0]>=224&&ipArr[0]<=239){
                        cnt_D++;
                    } else if (ipArr[0]>=240&&ipArr[0]<=255){
                        cnt_E++;
                    }
                } else {
                    wrong_++;
                }
            } else {
                wrong_++;
            }
        }
        System.out.println(cnt_A+" "+cnt_B+" "+cnt_C+" "+cnt_D+" "+cnt_E+" "+wrong_+" "+pri_ip);
        sc.close();
    }

    //判断掩码是否合法：比较二进制字符串中第一个0的位置和最后一个1的位置来判断掩码是否合法
    public static boolean validMask(String mask){
        String[] maskArr = mask.split("\\.");
        StringBuffer binMask = new StringBuffer();
        for (int i = 0; i<maskArr.length; i++){
            //将第i个十进制数转换为二进制
            StringBuffer bin = new StringBuffer(Integer.toBinaryString(Integer.parseInt(maskArr[i])));
            //高位补0，补齐8位
            int len = bin.length();
            bin.reverse();
            for (int j = 0; j<8-len; j++){
                bin.append("0");
            }
            bin.reverse();
            binMask.append(bin);
        }
        //比较第一个0的位置和最后一个1的位置
        if (binMask.indexOf("0") < binMask.lastIndexOf("1")){
            return false;
        }
        return true;
    }
}
