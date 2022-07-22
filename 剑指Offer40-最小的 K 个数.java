class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> heapq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            if (heapq.size() >= k) {
                if (!heapq.isEmpty() && num < heapq.peek()) {
                    heapq.poll();
                    heapq.add(num);
                }
            } else
                heapq.add(num);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heapq.poll();
        }
        return res;
    }
}

class Solution2 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length - 1);
    }
    private int[] quickSort(int[] arr, int k, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        if (i > k) return quickSort(arr, k, l, i - 1);
        if (i < k) return quickSort(arr, k, i + 1, r);
        return Arrays.copyOf(arr, k);
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}