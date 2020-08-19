package minArray;

public class Solution {
    public int minArray(int[] numbers){
        //旋转数组性质：左排序数组任一元素 >= 右排序数组任一元素
        //最小元素在右排序数组的首位
        int n = numbers.length;
        int left = 0;
        int right = n-1;
        while(left<right){
            int mid = (left+right)/2;
            //中间值mid在左排序数组中，最小值一定在[mid+1,right]范围内
            if (numbers[right] < numbers[mid]){
                left = mid+1;
            }
            //mid在右排序数组中，最小值一定在[left,mid]范围内
            else if (numbers[right] > numbers[mid]){
                right = mid;
            } else {
                right --;
            }
        }
        return numbers[left];
    }
}
