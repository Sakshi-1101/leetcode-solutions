
public class MaximumSubarraySum {

    public static void main(String[] args) {
        int[] arr = {-2,-3,4,-1,-2,1,5,-3};

        int ans1 = maxSumSubarrayBrute(arr);
        int ans2 = maxSumSubarrayBetter(arr);
        int ans3 = maxSumSubarrayOptimal(arr);

        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);

        printMaxSumSubarray(arr);

    }

    // TC: O(N^3)
    // SC: O(1)
    // Approach: we will run 2 loops to get all the subarrays and a third loop to calculate the sum of each subarray
    public static int maxSumSubarrayBrute(int[] arr){
        int maxSum = Integer.MIN_VALUE;

        // generating all subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i ; j < arr.length ; j ++) {
                int sum = 0;

                // calculating sum of subarray from index i to j
                for(int k = i ; k <= j ; k ++){
                    sum += arr[k];
                }

                maxSum = Math.max(maxSum, sum);

            }
        }

        return maxSum;

    }

    // TC: O(N^2)
    // SC: O(1)
    /*
     * Approach: we will run 2 loops to get all the subarrays and in the 2nd loop we'll also calculate the sum of each subarray 
     *           but instead of calculating the sum from scratch, we will keep adding the next element to the previous sum.
    */
    public static int maxSumSubarrayBetter(int[] arr){
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0 ; i < arr.length ; i ++) {
            int sum = 0;

            for(int j = i ; j < arr.length ; j ++) {
                sum += arr[j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
    
    // TC: O(N)
    // SC: O(1)
    // Approach: Using Kadane's Algorithm
    public static int maxSumSubarrayOptimal(int[] arr){
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0 ; i < arr.length ; i ++) {
            sum += arr[i];
            
            /*
             * We check maxsum before resetting sum to zero because if we reset sum first (when sum < 0), we would lose the chance to record a negative maximum sum. 
             * This is important in cases where all elements in the array are negative, ensuring that maxsum correctly reflects the largest (least negative) value.
             */
            maxSum = Math.max(sum, maxSum);

            // if sum is less than 0, then we reset it to 0 because adding a negative sum to any future subarray would only decrease its total sum.
            if(sum < 0){
                sum = 0;
            }

        }

        // To consider the sum of the empty subarray
        if(maxSum < 0){
            maxSum = 0;
        }
        
        return maxSum;
    }

    /*
     * FOLLOW UP QUESTION: TO PRINT THE SUBARRAY WITH MAXIMUM SUM
     */

     // TC: O(N)
     // SC: O(1)
     // Approach: Using Kadane's Algorithm. Additionally, we'll keep track of the start and end indices of the maximum sum subarray.
     public static void printMaxSumSubarray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int ansStart = -1;
        int ansEnd = -1;

        for(int i = 0 ; i < arr.length ; i ++) {
            // to set the start index of the next subarray
            if(sum == 0){
                start = i; // setting the start to the current index because this could be the beginning index of the new subarray for which the sum has to be calculated
            }

            /*
             * we need to do this step after checking sum=0 bcoz if we do it before that will mean 
             * you’re saying “if the sum just became 0 after including this element, start a new subarray at the current index.”
             * But we are doing it after checking sum=0, which means if the running sum had dropped to 0 at the previous step, you’re saying “let’s try a new subarray starting here.”
            */
            sum += arr[i];
            
            if(sum > maxSum){
                maxSum = sum;
                ansStart = start; // as we have found a new maxSum, we'll update the ansStart to "start" i.e. from wherever we started the subaray where sum=0
                ansEnd = i; // ansEnd=i because if you are standing at current idx and you got the sum > maxSum, then the current idx "i" is the current end of the subarray
            }

             // If sum < 0: discard the sum calculated
            if(sum < 0){
                sum = 0;
            }

        }

        // To consider the sum of the empty subarray
        if(maxSum < 0){
            maxSum = 0;
        }
        
        // printing the subarray
        for(int i = ansStart ; i <= ansEnd ; i ++) {
            System.out.print(arr[i] + " ");
        }

     }
}
