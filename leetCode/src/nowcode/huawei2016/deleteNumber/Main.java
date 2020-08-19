package nowcode.huawei2016.deleteNumber;

import java.util.*;

public class Main {
    /**
     * 删数：
     * 有一个数组a[N],0~N-1
     * 要求每隔两个数删掉一个数，到末尾时循环至开头继续进行，求最后一个被删掉的数的原始下标位置
     * 0,1,2,3,4
     * 删掉2:0,1,3,4
     * 删掉0:1,3,4
     * 删掉4:1,3
     * 删掉1:3
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();

            if (n<2){
                System.out.println(n);
            } else {
                LinkedList<Integer> nums = new LinkedList<>();
                Map<Integer, Boolean> map = new HashMap<>();
                //初始化nums
                for (int i = 0; i<n; i++){
                    nums.add(i);
                }
                int cur = 0;
                while (map.size() < n-1){
                    cur = (cur+2)%nums.size();
                    map.put(nums.get(cur), true);
                    nums.remove(cur);
                }
                System.out.println(nums.get(0));
            }
        }
    }
}
