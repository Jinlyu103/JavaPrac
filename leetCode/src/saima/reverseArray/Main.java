package saima.reverseArray;

import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    /**
     * 翻转数组，问是否存在这样一个片段[l,r]，只将改片段翻转就可以使得整个数组升序排列
     * 1、l之前数组为升序
     * 2、[l,r]数组严格降序，并且其中的元素要比l之前所有数大
     * 3、r之后数组为升序，并且元素严格大于[l,r]之间的所有数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 2) {
            System.out.println("yes");
            exit(0);
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int l = -1, r = -1;

        //寻找l
        for (int i= 0; i<n; i++){
            if (a[i] > a[i+1]){
                l = i;
                break;
            }
        }
        //寻找r
        for (int i = l+1; i<n; i++){
            if (a[i] < a[i+1]){
                r = i;
                break;
            }
        }
        //翻转[l,r]
        while (l <= r){
            int tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
            l++;
            r--;
        }
        //遍历数组
        for (int i = 0; i<n-1; i++){
            if (a[i] > a[i+1]){
                System.out.println("no");
                exit(0);
            }
        }
        System.out.println("yes");
    }
}
