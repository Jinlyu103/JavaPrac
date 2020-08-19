package nowcode.cacheKongLianshun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //相距最远的两名特工之间的距离
        int D = sc.nextInt();
        //可选建筑物坐标
        int[] buildings = new int[N];
        for (int i = 0; i < N; i++) {
            if (sc.hasNext()){
                buildings[i] = sc.nextInt();
            }
        }
        sc.close();
//        int left = 0, right = 2;
//        int res = 0;
//        while (left < N-2){
//            while (right < N && buildings[right] - buildings[left] <= D){
//                right ++;
//            }
//            if (right-1-left >=2){
//                int num = right - left - 1;
//                res += num*(num-1)/2;
//            }
//            left ++;
//        }
//        res = res % 99997867;
//        System.out.println(res);
        Main sol = new Main();
        System.out.println(sol.solve1(buildings,D));
    }

    private long solve1(int[] nums,int D){
        /**
         * 数组有序，可用双指针
         */
        int n = nums.length;
        int left = 0;
        int right = 2;
        long res = 0;

        while (left < n-2){
            while (right < n && nums[right] - nums[left] <= D){
                right ++;
            }
            if (right-1-left >=2){
                long num = right - left - 1;
                res += num*(num-1)/2;
            }
            left ++;
        }
        return (res % 99997867);
    }
    private int find(int[] nums, int left, int D){
        //固定left寻找right
        int lo = left + 2;
        int hi = nums.length;
        while (lo < hi){
            int mid = lo + (hi-lo)/2;
            if (nums[mid] - nums[left] <= D){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    private int solve(int[] buildings, int D){
        /**
         * 在buildings数组中找三个位置，使得相距最远的两个位置距离不超过D
         * 回溯
         */
        ans = 0;
        List<Integer> arr = new ArrayList<Integer>();
        backtrack(arr, buildings, D);
        return ans;
    }

    private void backtrack(List<Integer> arr, int[] buildings, int D){
        if (arr.size() == 3){
            ans ++;
            return;
        }
        for (int i = 0; i<buildings.length; i++){
            //当arr不为空时，如果所选位置与已选位置距离过大，退出本次循环
            if (arr.contains(buildings[i]) || !valid(arr, buildings[i],D) ){
                continue;
            }

            arr.add(buildings[i]);
            backtrack(arr,buildings,D);
            arr.remove(arr.size()-1);
        }
    }

    private boolean valid(List<Integer> arr, int pos, int D){
        if (arr.isEmpty())
            return true;
        for (Integer i:arr) {
            if (pos - i > D || pos < i)
                return false;
        }
        return true;
    }
}
