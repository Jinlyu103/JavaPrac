package xiecheng2021.t1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * 有长度为a 和b的瓷砖
     * 任取k块，要求按照递增的顺序输出所有可能的道路长度
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int k = sc.nextInt();
        if (k == 0){
            System.out.println(0);
        } else {
            Map<Integer, Integer> res = new TreeMap<>();
            if (a == b){
                res.put(a*k, 1);
                System.out.println(res.keySet());
            } else if (a < b){
                res = board(a,b,k);
            } else {
                res = board(b,a,k);
            }
            System.out.println("[" + res.keySet().stream().map(Objects::toString).collect(Collectors.joining(",")) + "]");
        }
    }

    public static Map<Integer,Integer> board(int shorter, int longer, int k){
        Map<Integer,Integer> res = new TreeMap<>();
        for (int i = 0; i <=k ; i++){
            //用i块longer和k-i块shorter建造
            int board = longer * i + shorter*(k-i);
            if (!res.containsKey(board))
                res.put(board,1);
        }
        return res;
    }
}
