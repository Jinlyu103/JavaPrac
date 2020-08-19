package nowcode.huawei2017.bottle;

import java.util.Scanner;

public class Main {
    /**
     * 汽水瓶：三个空瓶可换一瓶汽水
     * 手上有n个空汽水瓶,最多可换多少瓶汽水喝
     * 当 n % 3 == 0:
     *      可以换 n/3瓶，喝完剩下 n/3个空瓶，令n = n/3,继续判断
     * 当 n < 3 && n%3 == 2时：
     *      可以借一瓶， 喝完拿手上已有的两个空瓶加上借的瓶子还给老板
     * 当n < 3 && n%3==1 时，没有办法换
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (i < 10 && sc.hasNext()){
            int n = sc.nextInt();
            int cnt = 0;
            while (n >= 3 || n%3 > 1){
                if (n % 3 == 0){ //被3整除
                    cnt += n/3;
                    n = n/3;
                } else if (n % 3 == 2){
                    if (n > 3){
                        cnt += n/3;
                        n = n/3 + n%3; //新增的空瓶数，加上原来剩下的空瓶数
                    } else { //找老板借了一瓶，喝完之后全部还回去了
                        cnt += 1;
                        n = 0;
                    }
                } else { //余数为1
                    if (n > 3){
                        cnt += n/3;
                        n = n/3 + n%3;
                    } else {
                        n = 0;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
