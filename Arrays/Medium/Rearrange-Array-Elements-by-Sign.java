class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];

        // resultant array must start from a positive element so we initialize the positive index as 0 and negative index as 1
        int posIdx = 0;
        int negIdx = 1;

        for(int i = 0 ; i < nums.length ; i ++) {
            if(nums[i] > 0) {
                ans[posIdx] =  nums[i];
                posIdx += 2;
            } else {
                ans[negIdx] = nums[i];
                negIdx += 2;
            }
        }

        return ans;
        
    }
}