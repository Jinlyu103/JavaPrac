package byteDance2021.t4;
import java.util.*;

public class Main1 {
    static int ans = 0; //全局
    static List<Integer> selected = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        //输入n个正整数
        for (int i = 0; i<n; i++){
            a[i] = sc.nextInt();
        }
        for (int k = 0; k<=n; k++)
            backtrack(a,k,m); // 回溯
        System.out.println(ans);
    }

    public static void backtrack(int[] a, int k, int m){
        if (selected.size() == k){
            //求和
            int sum = 0;
            for(int i = 0; i<selected.size(); i++){
                sum+=a[selected.get(i)];
            }
            ans = Math.max(ans, sum%m);
        }
        for (int i = 0; i<a.length; i++){
            if (selected.contains(i)){
                continue;
            }
            selected.add(i);
            backtrack(a,k,m);
            selected.remove(selected.size()-1);
        }
    }
}
