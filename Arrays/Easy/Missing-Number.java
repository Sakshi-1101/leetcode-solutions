class Solution {

    // TC: O(N)
    // SC: O(1)
    /*
     * Approach: 
     * In this approach, we calculate the expected sum of numbers from 0 to n using the formula n*(n+1)/2. 
     * We then subtract the actual sum of the elements in the array from this expected sum to find the missing number.
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int totalSum = n * (n + 1) / 2;
        int sum = 0;

        for(int i = 0 ; i < n ; i ++){
            sum += nums[i];
        }

        return totalSum - sum;
        
    }
}