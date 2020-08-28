package huaweiPrac.hj20_validPassword;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * 密码验证合格程序
     * 密码要求：
     *  长度超过8位
     *  包括大小写字母，数字，其他符号，四种至少三种
     *  不能有相同长度大于2的子串重复
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Boolean> res = new ArrayList<>();
        while (sc.hasNext()){
            String pw = sc.nextLine();
            boolean flag = isValid(pw);
            res.add(flag);
        }

        for (Boolean b : res){
            if (b){
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }

    /**
     * 验证密码是否有效
     * @param pw
     * @return
     */
    public static boolean isValid(String pw){
        if (pw.length() <= 8){ // 长度不符合要求
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigits = false;
        boolean hasOthers = false;
        int cnt=0;

        for (int i = 0; i<pw.length(); i++){
            if (!hasUpperCase && Character.isUpperCase(pw.charAt(i))){
                hasUpperCase = true;
                cnt ++;
                continue;
            }
            if (!hasLowerCase && Character.isLowerCase(pw.charAt(i))){
                hasLowerCase = true;
                cnt ++;
                continue;
            }
            if (!hasDigits && Character.isDigit(pw.charAt(i))){
                hasDigits = true;
                cnt++;
                continue;
            }
            if (!hasOthers && !Character.isLetterOrDigit(pw.charAt(i))){
                hasOthers = true;
                cnt++;
                continue;
            }
        }
        if(cnt < 3){
            return false;
        }

        for (int i = 0; i<=pw.length()-3; i++){
            String sub = pw.substring(i, i+3);
            int idx = pw.lastIndexOf(sub);
            if (idx > i){
                return false;
            }
        }
        return true;
    }
}
