package findKthLargest;

public class Solution {
    /**
     * 数组中的第k个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k){
        /**堆排序
         * 维护一个大小为 k的小顶堆，父节点的值不大于子节点
         * 堆顶：i = 0
         * 左边：2i+1
         * 右边：2i+2
         */
        int[] heap = new int[k];
        for (int i = 0; i<k; i++){
            heap[i] = nums[i];
        }
        buildMinHeap(heap); //构建小顶堆

        for (int i = k; i < nums.length ; i++) {
            if (nums[i] >= heap[0]){
                heap[0] = nums[i];
                heapSort(heap,k,0);
            }
        }
        return heap[0];
    }

    private void buildMinHeap(int[] heap){
        int n = heap.length;
        for (int i = n/2; i>= 0; i--){
            heapSort(heap, n, i);
        }
    }
    private void heapSort(int[] heap, int len, int idx){
        int l = 2*idx+1;
        int r = 2*idx+2;
        //比较左右孩子和当前节点
        int smallest = idx;
        if (l<len && heap[l] < heap[smallest])
            smallest = l;
        if (r<len && heap[r] < heap[smallest])
            smallest = r;
        if (smallest != idx){
            int tmp = heap[smallest];
            heap[smallest] = heap[idx];
            heap[idx] = tmp;
            heapSort(heap, len, smallest);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution sol =  new Solution();
        System.out.println(sol.findKthLargest(nums,k));
        StringBuffer s = new StringBuffer(10);
    }
}
