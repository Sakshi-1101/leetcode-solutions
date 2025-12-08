class Solution {
    public int reversePairs(int[] nums) {
        // call merge sort and return the count of pairs
        return mergeSort(nums, 0, nums.length - 1);
    }

    public static int mergeSort(int[] arr, int i , int j) {
        int countPairs = 0; // Variable to count pairs
        
        // if the boundaries cross each other, return 0 as the total count of pairs
        if(i >= j) {
            return countPairs;
        }

        int mid = (i + j) / 2;

        // recursively divide the array and count pairs in left and right halves
        countPairs += mergeSort(arr, i, mid);
        countPairs += mergeSort(arr, mid + 1, j);

        // count cross pairs
        countPairs += countReversePairs(arr, i, mid, j);

        // merge the two halves
        mergeArray(arr, i , mid, j);

        return countPairs;

    }

    public static int countReversePairs(int[] arr, int low, int mid, int high) {
        int count = 0;
        int right = mid + 1;

        // traverse the left half
        for(int i = low ; i <= mid ; i ++ ){

             // Move 'right' forward as long as arr[i] > 2 * arr[right].
             /* 
                NOTE: 
                Use long to avoid integer overflow: 2 * arr[right] may overflow int when values are near 2^31-1
                TestCase: 
                    arr = [2147483647,2147483647,2147483647,2147483647,2147483647,2147483647]
                    output = 0

             */
            while(right <= high && (long) arr[i] > 2L * arr[right]) {
                right++;
            }

            // all the elements from mid+1 to right-1 will form reverse pairs with arr[i]
            count += (right - (mid + 1));
        }

        return count;
    }

    public static void mergeArray(int[] arr, int i , int mid, int j) {

        int[] ans = new int[j - i + 1]; // size of current subarray being merged 

        int fhalf = i;
        int shalf = mid + 1;
        int k = 0;

        while(fhalf <= mid && shalf <= j) {
            if(arr[fhalf] <= arr[shalf]) {
                ans[k] = arr[fhalf];
                fhalf++;
            } else { 
                ans[k] = arr[shalf];
                shalf++;
            }
            k++;
        }

        while(fhalf <= mid){
            ans[k] = arr[fhalf];
            fhalf++;
            k++;
        }

        while(shalf <= j){
            ans[k] = arr[shalf];
            shalf++;
            k++;
        }


        // copying back the sorted elements into original array
        // After you merge into ans, the sorted part exists only in ans.
        // But you still need the main arr to be sorted for the next merges.
        for(int l = i ; l <= j; l ++){
           arr[l] = ans[l - i]; // eg: l = 1 -> arr[1] = ans[1 - 0] where i = 0 bcoz original array i = 0 and j = arr.length - 1
        }

    }
    
}