package merge;

public class Solution {
    /**
     * 88. 合并两个有序数组
     * 给你两个有序整数数组nums1和nums2，请将nums2合并到nums1中，使nums1成为一个有序数组
     */
    public void merge(int[] nums1, int m, int[] nums2, int n){
        if (nums1.length < m+n){
            return;
        }
        for (int i = 0; i<n; i++){
            int pos = findPosition(nums1, m, nums2[i]);// 寻找插入位置
            if (pos == m){ //插入末尾
                nums1[pos] = nums2[i];
            } else {//插入中间
                for (int j = m-1; j >= pos; j--){
                    nums1[j+1] = nums1[j];
                }
                nums1[pos] = nums2[i];
            }
            m++; //插入之后nums1中元素数量加一
        }
    }

    /**
     * 二分法查找插入位置
     * @param nums1
     * @param m
     * @param x
     * @return
     */
    public int findPosition(int[] nums1, int m, int x){
        int le = 0;
        int ri = m-1;
        while (le <= ri){
            int mid = (le+ri)/2;
            if (nums1[mid] == x){
                return mid;
            }
            else if (nums1[mid] > x){
                ri = mid - 1;
            }
            else {
                le = mid+1;
            }
        }
        return le;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;
        sol.merge(nums1, m, nums2,n);
        for (int i = 0; i<nums1.length; i++){
            System.out.println(nums1[i]);
        }
    }
}
