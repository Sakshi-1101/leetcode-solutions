public class SplitArrayLargestSum {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        int k = 2;

        // split the array into k subarrays in such a way that the max subarray sum is min.
        int ansBrute = findMinOfMaxSumBrute(arr, k);
        int ansOptimal = findMinOfMaxSumOptimal(arr, k);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
        
    }

    // TC: O(sum - max + 1) * O(N) => O(N) -> time complexity of findSubarrayCount()
    // SC: O(1)
    // Approach: In this approach, we will try to find the max subarray sum by traversing through all the possible answers from 
    //           max value in array to summation of all the elements of the array. For each possible answer, we will check how 
    //           many subarrays are formed such that no subarray has sum greater than the possible answer. If the number of 
    //           subarrays formed is equal to k, we return that possible answer as the result. 
    public static int findMinOfMaxSumBrute(int[] arr, int k) {
        // edge case: when no. of elements in array < no. of subarrays to be formed
        if(arr.length < k) {
            return -1; // not possible 
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            max = Math.max(arr[i], max);
            sum += arr[i];
        }

        // define the range where ans will lie
        int start = max; // max value in array, so that each subarray get atleast one element in case k = arr.length
        int end = sum; // if there was only 1 subarray then the whole array will be that subarray only. Hence, max subarray sum = summation of array
    
        // traverse on the possible answers
        for(int i = start ; i <= end ; i ++) {
            // for i as max subarray sum, check how many subarrays you are able to form for the current max sum
            int countSubarrays = findSubarrayCount(arr, i);

            // if you are able to form k subarrays with max subarray sum as i return the ans since we need the min value so no need to check further for bigger values
            if(countSubarrays == k) {
                return i;
            }
        }

        //return max(arr[]) as there cannot exist any answer smaller than that.
        return start;
    
    }

    // function to find the number of subarrays formed such that no subarray has sum greater than maxSum and return that count.
    /* The code logic is as follows:
        1. Start with the first subarray and keep adding elements to it until adding another element would exceed maxSum.
        2. When the sum exceeds maxSum, start a new subarray with the current element.
        3. Repeat this process until all elements are processed.
        4. Return the total number of subarrays formed.
    */
    private static int findSubarrayCount(int[] arr, int maxSum) {
        // this implies currently we have 1 subarray with 0 sum
        int subArrays= 1;
        int subArrSum = 0;

        // traverse the array to form subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            // if the current element can be added to the current subarray and it doesn't exceed maxSum
            if(subArrSum + arr[i] <= maxSum) {
                subArrSum += arr[i]; // add the current element to the current subarray sum
            } else { // if the current element cannot be added to the current subarray
                subArrays++; // count the current element as the start of new subarray and increment subarray count
                subArrSum = arr[i]; // set the current element as the first element of the new subarray
            }
        }

        // return the number of subarrays formed
        return subArrays;
    }

    // TC: O(log (base 2) (sum - max + 1)) * O(N) => O(N) -> time complexity of findStudentCount()
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the min value of the maximum subarray sum. The idea is to
    //           use binary search on the range of possible maximum subarray sums (from max element to sum of all elements) and 
    //           check for each mid value. how many subarrays can be formed with that mid as the maximum subarray sum. If we can 
    //           form k or less subarrays, we try to find a smaller maximum subarray sum. If we cannot form k subarrays, we need 
    //           to increase the maximum subarray sum. We continue this process until we find the minimum possible maximum 
    //           subarray sum.
    public static int findMinOfMaxSumOptimal(int[] arr, int k) {
        // edge case: when no. of elements in array < no. of subarrays to be formed
        if(arr.length < k) {
            return -1; // not possible
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            max = Math.max(arr[i], max);
            sum += arr[i];
        }

        // define the range where ans will lie
        int lo = max; // max value in array, so that each subarray get atleast one element in case k = arr.length
        int hi = sum; // if there was only 1 subarray then the whole array will be that subarray only. Hence, max subarray sum = summation of array
        
        // initialize ans to lo bcoz lo is the minimum possible value of the max subarray sum
        int ans = lo;

        // binary search
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // for mid as max subarray sum, check how many subarrays you are able to form
            int countSubarrays = findSubarrayCount(arr, mid);

            // if you are able to form k subarrays with max subarray sum as mid look for smaller value since we 
            // need the min value of the max subarray sum
            if(countSubarrays <= k)  {
                ans = mid;
                hi = mid - 1; // discard bigger values and search on left half
            } else { // since current value couldn't form k subarrays, look for bigger value
                lo = mid + 1; // search in right half for bigger values
            }
        }
        
        return ans; // OR return lo;
    }
    
}
