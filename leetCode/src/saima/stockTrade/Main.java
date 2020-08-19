package saima.stockTrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * 第i天： 1 2  3 4 5  6 ......
     * 股价：  1 2  1 2 3  2  3
     * 涨跌：  0 1 -1 1 1 -1 1 1 1 -1 1 1 1 1 -1
     * 第一次跌：第3天
     * 第二次跌：第6天 相差3
     *            10  相差4
     *            15  相差5
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        List<Integer> res = new ArrayList<>();
        while (sc.hasNext()){
            int n = sc.nextInt();

            int price = 1; //初始股价为1
            int downDate = 3;
            int offset = 3;

            for (int i = 1; i<=n; i++){
                if (i == downDate){ //跌一天
                    price -- ;
                    downDate += offset;
                    offset ++;
                } else if (i != 1){
                    price ++;
                } else continue;
            }
            res.add(price);
        }
        for (Integer p: res){
            System.out.println(p);
        }
        sc.close();
    }
}
