package saima.reverseArray;

import java.util.Scanner;

import static java.lang.System.exit;


public class Main1 {
    /**
     * 翻转数组，问是否存在这样一个片段[l,r]，只将改片段翻转就可以使得整个数组升序排列
     * 1、l之前数组为升序
     * 2、[l,r]数组严格降序，并且其中的元素要比l之前所有数大
     * 3、r之后数组为升序，并且元素严格大于[l,r]之间的所有数
     *
     * 思路：双指针
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n<=2){
            System.out.println("yes");
            exit(0);
        }
        int[] a = new int[n];

        for (int i = 0; i<n; i++){
            a[i] = sc.nextInt();
        }

        int l = 0, r = n-1;
        while (l<=r){
            if (a[l] > a[l+1] || a[r-1] > a[r]){
                break;
            }
            l++;
            r--;
        }
        while (l<r && a[l] <= a[l+1]){
            l++;
        }
        while (l<r && a[r] > a[r-1]){
            r--;
        }
        boolean flag = true;
        int start = l;
        while (start<r){
            if (a[start] < a[start+1]) {
                flag = false;
                break;
            }
            start ++;
        }
        if (flag)
            System.out.println("yes");
        else
            System.out.println("no");
    }
}
