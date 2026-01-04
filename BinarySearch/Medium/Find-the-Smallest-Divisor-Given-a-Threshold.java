class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        // edge case
        if(nums.length > threshold) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        // find the max value in the numsay
        for(int i = 0 ; i < nums.length ; i ++) {
            max = Math.max(max, nums[i]);
        }

        int lo = 1;
        int hi = max;

        int ans = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // get the sum for current divisor
            int sum = getSum(nums, mid);

            if(sum <= threshold) {
                ans = mid;
                hi = mid - 1; // look for more smaller divisor
            } else { // sum > threshold
                lo = mid + 1; // no point of going more left since current divisor is giving sum > thrshold
            }
        }

        return ans; // OR return lo;
        
    }

    private static int getSum(int[] nums, int div) {
        int sum = 0;

        for(int i = 0 ; i < nums.length ; i ++) {
            sum += (int)Math.ceil((double)nums[i] / div); // sum of ceil values of each nums[i] / div
        }

        return sum;
    }

}