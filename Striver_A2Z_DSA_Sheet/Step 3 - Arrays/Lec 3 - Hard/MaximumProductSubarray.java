public class MaximumProductSubarray {

    public static void main(String[] args) {
        // int[] arr = {1, 2, -3, 0, -4, -5};
        int[] arr = {3, -1, 4};

        int ansBrute = maxProductBrute(arr);
        int ansOptimal1 = maxProductOptimal1(arr);
        int ansOptimal2 = maxProductOptimal2(arr);

        System.out.println(ansBrute);
        System.out.println(ansOptimal1);
        System.out.println(ansOptimal2);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this we'll use two nested loops to generate all the subarrays and for each subarray we'll compare the 
    //           product value with the maxProd value.
    public static int maxProductBrute(int[] arr) {
        int maxProd = Integer.MIN_VALUE;

        // generate all the subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            int prod = 1; // initialise to 1 instead of 0 bcoz if set to 0 the entire value will become 0
            for(int j = i ; j < arr.length ; j ++) {
                // for each subarray find the product
                prod *= arr[j];
                maxProd = Math.max(maxProd, prod); // calculate maxProd for each subarray
            }
        }

        return maxProd;
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this we'll calculate the prefix product and suffix product in a single traversal and compare it with the maxProd value.
    public static int maxProductOptimal1(int[] arr) {
        int maxProd = Integer.MIN_VALUE;
        int prefixProd = 1;
        int suffixProd = 1;

        // traverse the array to calculate prefix and suffix product
        for(int i = 0 ;  i < arr.length ; i ++) {

            // reset prefixProd and suffixProd if they become 0
            if(prefixProd == 0) {
                prefixProd = 1;
            }

            if(suffixProd == 0) {
                suffixProd = 1;
            }

            // calculate prefix and suffix product
            prefixProd *= arr[i];
            suffixProd *= arr[arr.length - i - 1];

            // calculate maxProd
            maxProd = Math.max(maxProd, Math.max(prefixProd, suffixProd));
        }

        return maxProd;

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: 
    public static int maxProductOptimal2(int[] arr) {
        // Result so far is the first element
        int result = arr[0];

        // maxProd = maximum product ending at current index
        // minProd = minimum product ending at current index
        // (We track minProd because a negative number can turn this into max)
        int maxProd = arr[0];
        int minProd = arr[0];

        // Start from index 1 because we already considered arr[0]
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];

            // IMPORTANT:
            // If current number is negative, multiplying flips signs.
            // So the best becomes worst, and worst becomes best.
            if (curr < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Choose whether to START NEW subarray or CONTINUE previous product chain.
            maxProd = Math.max(curr, maxProd * curr);
            minProd = Math.min(curr, minProd * curr);

            // Update global result
            result = Math.max(result, maxProd);
        }

        return result;

    }
    
}
