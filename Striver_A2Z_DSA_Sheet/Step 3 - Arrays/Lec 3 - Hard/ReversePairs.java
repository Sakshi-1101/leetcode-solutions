public class ReversePairs {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 3, 1};

        int ansBrute = countRevPairBrute(arr);
        int ansOptimal = countRevPairOptimal(arr);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this approach, we'll use two nested loop to traverse the array and check for each pair that satisfies the condition.
    public static int countRevPairBrute(int[] arr) {
        int count = 0;

        // check for each pair
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {
                if(arr[i] > 2 * arr[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    // TC: O(2N * logN)
        /* 
            NOTE: This algorithm is an extension of merge sort, and at every recursion level it performs two linear-time operations:
                    1. countPairs():  
                    Even though the function appears to use nested loops, the two pointers only 
                    move forward across the left and right halves. Each half is scanned exactly 
                    once, so the total work done here is O(N).
                    2. merge():  
                    This is the standard merge operation of merge sort, which also takes O(N) time.
                    Therefore, each recursion level requires:
                            O(N) (from countPairs) + O(N) (from merge) = O(2N)
                    Since merge sort divides the array in half each time, the recursion has O(log N)
                    levels. Multiplying the work per level by the number of levels:
                        Total Time = O(2N) × O(log N) = O(2N log N)
        */
    // SC: O(N) -> for the temporary array used during merging.
    // Approach: In this we use modified merge sort to count reverse pairs while merging. We'll calculate the pairs as 
    //           soon as we sort the two halves and before merging them.
    public static int countRevPairOptimal(int[] arr) {
         // call merge sort and return the count of pairs
        return mergeSort(arr, 0, arr.length - 1);
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
            while(right <= high && arr[i] > 2 * arr[right]) {
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
