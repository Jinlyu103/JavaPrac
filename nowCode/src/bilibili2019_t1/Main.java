package bilibili2019_t1;

import java.util.Scanner;

public class Main {
    /**
     * 扭蛋机：
     * 两台扭蛋机编号为2号和3号
     * 2号：塞 x个扭蛋，可以得到 2x+1
     * 3号：塞 x个扭蛋，可以得到 2x+2
     *
     * 恰好扭到 N 个
     * 走方格问题，初始位置x = 0
     * 每次有两种走法：走2或者走3
     * 问恰好走到 N时最短的步数
     * 根据最后一步的奇偶性，判断向前一步的选择
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int N = sc.nextInt();
            StringBuffer res = new StringBuffer();
            while (N > 0){
                if (N % 2 == 0){
                    N = (N-2)/2;
                    res.append(3);
                } else {
                    N = (N-1)/2;
                    res.append(2);
                }
            }
            res.reverse();
            System.out.println(res.toString());
        }
    }
}
