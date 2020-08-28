package huawei2021.t2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();

            List<Integer> w = new ArrayList<>(); //宽度
            List<Integer> h = new ArrayList<>(); //高度
            boolean flag = false; //标识宽度数据是否获取完毕
            for (int i = 1; i<str.length(); ){
                StringBuffer digits = new StringBuffer();
                while (Character.isDigit(str.charAt(i))){
                    digits.append(str.charAt(i));
                    i++;
                }
                if (!flag && digits.length() > 0){ //获取宽度
                    if (digits.charAt(0) == '0'){
                        System.out.println(0);
                        exit(0);
                    }
                    w.add(Integer.parseInt(digits.toString()));
                } else if (digits.length() == 0){ //遇到逗号或者],[
                    if (str.charAt(i) == ']'){
                        flag = true;
                        i++;
                    } else if (str.charAt(i) == '[' || str.charAt(i) == ','){
                        i++;
                    } else {
                        System.out.println(0);
                        exit(0);
                    }
                } else { //获取高度
                    if (digits.charAt(0) == '0'){
                        System.out.println(0);
                        exit(0);
                    }
                    h.add(Integer.parseInt(digits.toString()));
                }
            }

            int n = w.size();
            int[] preW = new int[n+1]; // 需要计算矩形的总宽度
            preW[0] = 0;
            for (int i = 1; i<=n; i++){
                preW[i] = preW[i-1] + w.get(i-1);
            }

            int res = 0; //初始化矩形面积
            for (int i = 0; i<n; i++){
                for (int j = i+1; j<=n; j++){
                    int minH = Integer.MAX_VALUE;
                    for (int k = i; k<j; k++){ //获取i,j之间高度最小值
                        minH = Math.min(minH, h.get(k));
                    }
                    int newArea = (preW[j] - preW[i]) * minH;
                    res = Math.max(res, newArea);
                }
            }
            System.out.println(res);
        }
    }
}
