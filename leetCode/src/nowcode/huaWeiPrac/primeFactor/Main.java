package nowcode.huaWeiPrac.primeFactor;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * 质数因子：输入一个正整数，按照从小到大的顺序输出它所有质因子
     * 分解质因数：对n进行分解质因数，应先找到一个最小的质数k,然后按以下步骤执行：
     * 1、如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可
     * 2、如果n != k，但n能被k整除，则应打印出k的值，并用n除以k的商，作为新的正整数n，重复执行第一步
     * 3、如果n不能被k整除，则用k+1作为k的值，重复执行第一步
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        List<Long> res = new ArrayList<>();
        for (long i = 2; i<=n; i++){
            while (i != n){
                if (n % i == 0){
                    res.add(i);
                    n = n/i;
                }
                else break;
            }
        }
        res.add(n);
        Collections.sort(res);
        System.out.println(res.stream().map(Objects::toString).collect(Collectors.joining(" ")) + " ");
    }
}
