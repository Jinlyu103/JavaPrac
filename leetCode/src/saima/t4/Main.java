package saima.t4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    /**
     * 测试默契度
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int m = sc.nextInt(); //序列元素的最大值
            int n = sc.nextInt(); //序列的长度
            int[] seq = new int[n];
            //输入序列元素
            for (int i = 0; i<n; i++){
                seq[i] = sc.nextInt();
            }

            List<List<Integer>> tuples = new ArrayList<>();
            Stack<Integer> stk = new Stack<>(); //维护一个单调递增的栈，存放x
            int max = 0;
            for (int l = 1; l<=m; l++){
                for (int r = l; r<=m; r++){
                    //判断所选的l和r是否满足条件
                    if (l<=r && isValid(l,r,m,seq,stk)){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(l); //将l，r加入到tmp中
                        tmp.add(r);
                        tuples.add(tmp);
                    } else if (!isValid(l,r,m,seq,stk)){
                        max = Math.max(max, tuples.size());

                    }
                }
            }
        }
    }

    public static boolean isValid(int l, int r, int m, int[] seq, Stack<Integer> stk){
        boolean flag = false;//是否存在可以保留的x
        for (Integer x:seq){
            if (x<l || (x>r && x<m+1)){
                if (x >= stk.peek()){
                    stk.push(x);
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }
}
