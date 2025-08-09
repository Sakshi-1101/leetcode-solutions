class Solution {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0 ; i < nums.length ; i ++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        int num = max;
        int den = min;
        
        while (den != 0) {
            int rem = num % den;
            num = den;
            den = rem;
        }
        return num;
        
    }
}