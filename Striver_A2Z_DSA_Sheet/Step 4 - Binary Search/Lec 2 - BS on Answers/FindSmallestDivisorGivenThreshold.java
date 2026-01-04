public class FindSmallestDivisorGivenThreshold {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int limit = 8; // threshold

        int ansBrute = findSmallestDivisorBrute(arr, limit);
        int ansOptimal = findSmallestDivisorOptimal(arr, limit);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
 
    }

    // TC: O(max(arr[i])) * O(N) -> O(N) => TC of getSum() 
    // SC: O(1)
    // Approach: In this brute force approach, we try all possible divisors from 1 to the maximum value in the array.
    //           For each divisor, we calculate the sum of the ceil values of each arr[i] / divisor. If the sum is less than or 
    //           equal to the threshold, we return that divisor as the answer. If no such divisor is found, we return -1.
    public static int findSmallestDivisorBrute(int[] arr, int limit) {
        int max = Integer.MIN_VALUE;

        // find the max value in the array
        for(int i = 0 ; i < arr.length ; i ++) {
            max = Math.max(max, arr[i]);
        }

        // traverse on the divisor range [1....max]
        for(int div = 1 ; div <= max ; div ++) {
            int sum = getSum(arr, div); // get the sum for the current possible divisor

            // if sum is less than threshold, return the divisor (no need to check further since we need smallest divisor)
            if(sum <= limit) {
                return div;
            }
        }

        // if not found
        return -1;
    }

    // calculating the sum for the given divisor
    private static int getSum(int[] arr, int div) {
        int sum = 0;

        for(int i = 0 ; i < arr.length ; i ++) {
            sum += (int)Math.ceil((double)arr[i] / div); // sum of ceil values of each arr[i] / div
        }

        return sum;
    }

    // TC: O(log (base 2) max(arr[i])) * O(N) => TC of getSum()
    // SC: O(1)
    // Approach: In this we'll use binary search to find the smallest divisor. We set the search range from 1 to the maximum value in the array.
    //           For each mid value (possible divisor), we calculate the sum of the ceil values of each arr[i] / mid.
    //           If the sum is less than or equal to the threshold, we update our answer and try to find a smaller divisor.
    //           If the sum is greater than the threshold, we increase our divisor.
    public static int findSmallestDivisorOptimal(int[] arr, int limit) {
        // edge case: When divisor is not possible
        /*
            NOTE: The minimum possible sum we can get is when we take the maximum divisor possible.
                  This will make each arr[i] / divisor = 1 (since divisor >= arr[i]), thus ceil value will also be 1.
                  So, minimum possible sum = length of the array.
                  If this minimum possible sum is greater than the threshold, it means no divisor can give us a sum <= threshold.
                  Hence, we return -1 in such cases.
                  Example: arr = [2, 3, 4], threshold = 2
                           Minimum possible sum = 3 (when divisor >= 4)
                           Since 3 > 2, return -1
         */
        if(arr.length > limit) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        // find the max value in the array
        for(int i = 0 ; i < arr.length ; i ++) {
            max = Math.max(max, arr[i]);
        }

        int lo = 1;
        int hi = max;

        int ans = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // get the sum for current divisor
            int sum = getSum(arr, mid);

            if(sum <= limit) {
                ans = mid;
                hi = mid - 1; // look for more smaller divisor
            } else { // sum > threshold
                lo = mid + 1; // no point of going more left since current divisor is giving sum > threshold, look for bigger value
            }
        }

        return ans; // OR return lo;
    }
    
}
