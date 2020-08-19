package kthSmallest;

public class Solution {
    public int kthSmallest(int[][] matrix, int k){
        /**
         * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素
         * 维护一个大顶堆
         */
        int m = matrix.length;
        if (m == 0)
            return -1;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;
        int[] heap = new int[k];
        int hIdx = 0;

        while (i<m && j<n){
            heap[hIdx++] = matrix[i][j];
            if (j < n-1){
                j++;
            } else {
                j = 0;
                i++;
            }
            if (hIdx >= k)
                break;
        }

        buildMaxHeap(heap);
        while (i <m && j < n){
            if (matrix[i][j] <= heap[0]){
                heap[0] = matrix[i][j];
                heapSort(heap,k,0);
            }
            if (j <n-1){
                j++;
            }else {
                j = 0;
                i++;
            }
        }
        return heap[0];
    }

    private void buildMaxHeap(int[] heap){
        int len = heap.length;
        for (int i = len/2; i>=0; i--){
            heapSort(heap,len,i);
        }
    }

    private void heapSort(int[] heap, int len, int idx){
        int largest = idx;
        int l = 2*idx+1;
        int r = 2*idx+2;

        if (l < len && heap[l] > heap[largest])
            largest = l;
        if (r < len && heap[r] > heap[largest])
            largest = r;

        if (largest != idx){
            int tmp = heap[largest];
            heap[largest] = heap[idx];
            heap[idx] = tmp;
            heapSort(heap, len, largest);
        }
    }
}
