package findMedianSortedArrays;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        //寻找两个有序数组的中位数
        int n = nums1.length;
        int m = nums2.length;
        if (n<m){
            return findMedianSortedArrays(nums2,nums1);
        }
        else if(n == 0){
            return 0.0;
        }
        else{
            int left = (n+m+1) / 2;
            int right = (n+m+2) / 2;
            return (findKthSortedArrays(nums1, nums2, left) + findKthSortedArrays(nums1, nums2, right)) / 2 ;
        }
    }

    public double findKthSortedArrays(int[] nums1, int[] nums2, int k){
        //寻找两个数组合并之后（非真正合并）的第k个数
        int n = nums1.length;
        int m = nums2.length;
        if (n>m){ //保证nums1 的长度小于等于 nums2的长度
            return findKthSortedArrays(nums2, nums1, k);
        }
        else if(n == 0){
            return nums2[k-1];
        }
        else if(k == 1){
            return Math.min(nums1[0], nums2[0]);
        }
        else{
            int i = Math.min(k/2, n) - 1;
            int j = Math.min(k/2, m) - 1;
            if (nums1[i] > nums2[j]){
                int[] tmp = new int[m-j-1];
                int p = 0;
                int q = j+1;
                while (q < m){
                    tmp[p++] = nums2[q++];
                }
                return findKthSortedArrays(nums1, tmp, k-j-1);
            }
            else{
                int[] tmp = new int[n-i-1];
                int p = 0;
                int q = i+1;
                while(q<n){
                    tmp[p++] = nums1[q++];
                }
                return findKthSortedArrays(tmp, nums2, k-i-1);
            }
        }
    }

    public static void main(String[] atgs){
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        Solution sol = new Solution();
        System.out.println(sol.findMedianSortedArrays(nums1, nums2));
    }
}
