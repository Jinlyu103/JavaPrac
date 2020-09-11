package shenxinfu2021.t1;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        /**
         * leetCode 42题：接雨水
         */
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int W = sc.nextInt(), L = sc.nextInt();
            int[] h = new int[L+1]; //L个测量高度
            int max = 0;
            for (int i = 1; i<L+1; i++){
                h[i] = sc.nextInt();
                max = Math.max(max, h[i]);
            }

            h[0] = max;
            //将h转换为石板与河道底部的高度差
            for (int i = 1; i<L+1; i++){
                h[i] = max - h[i];
            }

            Stack<Integer> stk = new Stack<>(); //维护一个单调递减,栈中存放下标
            int area = 0;
            for (int i = 0; i<L+1; i++){
                while (!stk.isEmpty() && h[stk.peek()] < h[i]){
                    int cur = stk.pop();
                    if(stk.isEmpty()){
                        break;
                    }
                    int l = stk.peek();
                    int r = i;
                    int hi = Math.min(h[l], h[r]) - h[cur];
                    area += hi * (r-l-1);
                }
                stk.push(i);
            }
            System.out.println(area*W);
        }
    }
}
