public class CountInversions {

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 1, 4};

        int ansBrute = countInvBrute(arr);
        int ansOptimal = countInvOptimal(arr);


        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this approach, we'll use two nested loop to traverse the array and check for each pair that satisfies the condition.
    public static int countInvBrute(int[] arr) {
        int count = 0;

        // check for each pair
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {
                if(arr[i] > arr[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    // TC: O(N * LogN) -> as it is using merge sort.
    // SC: O(N) -> for the temporary array used during merging.
    // Approach: In this we use modified merge sort to count inversions while merging. We'll calculate the pairs as 
    //           soon as we sort the two halves and before merging them.
    public static int countInvOptimal(int[] arr) {
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

        // merge the two halves and count cross pairs
        countPairs += mergeArray(arr, i , mid, j);

        return countPairs;

    }

    public static int mergeArray(int[] arr, int i , int mid, int j) {
        int countPairs = 0; // Variable to count pairs

        int[] ans = new int[j - i + 1]; // size of current subarray being merged 

        int fhalf = i;
        int shalf = mid + 1;
        int k = 0;

        while(fhalf <= mid && shalf <= j) {
            if(arr[fhalf] <= arr[shalf]) {
                ans[k] = arr[fhalf];
                fhalf++;
            } else { // left element is > right element 
                countPairs += (mid - fhalf + 1); // all the elements to the right of the left elements will form pairs with the right element
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

        return countPairs;

    }
    
}
