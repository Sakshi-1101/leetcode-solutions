public class KthMissingPositiveNumber {

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 10};
        int k = 4;

        int ansExtBrute = findKthNumExtremeBruteMyApproach(arr, k);
        int ansBrute = findKthNumBrute(arr, k);
        int ansOptimal = findKthNumOptimal(arr, k);

        System.out.println(ansExtBrute);
        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(n + k) * O(n) for checkVal function
    // SC: O(1)
    // Approach: In this we'll linearly traverse the array from [1...arr.length + k] and keep track of the count of missing 
    //           numbers. When the count reaches k, we return the current number. 
    public static int findKthNumExtremeBruteMyApproach(int[] arr, int k) {
        int miss = 0; // count of missing numbers

        // traverse all the numbers from 1 till total elements (arr.length + k) that will be there if no element was missing
        for(int val = 1; val <= (arr.length + k) ; val ++) {
            // check if the current no. exists or not 
            boolean found = checkVal(arr, val);

            if(!found) {
                miss++; // if not found increment the counter

                if(miss == k) { // if the counter reaches k, return the kth missing element
                    return val;
                }
            }
        }

        // if not found
        return -1;
    }

    // function to check if a value exists in the array
    private static boolean checkVal(int[] arr, int val) {
        for(int i = 0 ; i < arr.length ; i ++) {
            if(val == arr[i]) {
                return true;
            } else if(arr[i] > val){ // as the array is sorted, early exit if current element is greater than val
               break;
            }
        }

        return false;
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: We iterate through the array and for each element less than or equal to k, we increment k.
    //           The code logic is based on the observation that each time we encounter an element in the array that is less than or equal to k,
    //           it means that there is one less missing positive number before k, so we increment k to account for that.
    //           Finally, when we finish iterating through the array, k will represent the kth missing positive number.
    public static int findKthNumBrute(int[] arr, int k) {
        // iterate through the array
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] <= k) { 
                k++; // increment k as this number is not missing so now our kth missing number will be pointing to next number to maintain the correct position
            } else { // arr[i] > k
                break; // Stop if current number is greater than k
            }
        }

        return k;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: In this we'll use binary search on the array to find the kth missing positive number.
    /* 
        Logic:
        1. For each mid element in the binary search, we calculate the count of missing numbers till that mid element using the formula:
           missingNoCount = arr[mid] - (mid + 1)
           This works because in a perfect sequence of positive integers, the value at index mid should be mid + 1.
           Any difference between arr[mid] and (mid + 1) indicates how many numbers are missing up to that point.
        
        2. If the count of missing numbers till mid (missingNoCount) is less than k, it means we need to look for more missing numbers
           on the right side of mid, so we move our low pointer to mid + 1.

        3. If missingNoCount is greater than or equal to k, it means we have enough missing numbers on the left side, so we move our high pointer to mid - 1.

        4. The loop continues until low exceeds high. At this point, low will be positioned at the index where the kth missing number would fit in,
           and we can calculate the kth missing number using the formula: (hi + 1 + k) or (lo + k).

    */
    public static int findKthNumOptimal(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int missingNoCount = arr[mid] - (mid + 1); // count of missing numbers till arr[mid]

            //if missingNoCount < k, then there are less than k missing numbers before mid, so we move to the right else move to the left
            if(missingNoCount < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return (hi + 1 + k); // OR return (lo + k);
    }
    
}
