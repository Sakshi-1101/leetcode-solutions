class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0 ; i < nums.length ; i ++) {
            sum += nums[i];
            
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
        
        return maxSum;

    }
}