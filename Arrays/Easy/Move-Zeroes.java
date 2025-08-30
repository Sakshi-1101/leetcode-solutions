class Solution {

    // TC: O(n)
    // SC: O(1)
    // In this approach, we use two pointers to move non-zero elements to the front and fill the rest with zeros.
    public void moveZeroes(int[] nums) {
        int i = 0; // slow pointer - tracks the position to swap non-zero
        int j = 0; // fast pointer - scans the array

        while(j < nums.length) {
            if(nums[j] != 0){
                swap(nums, i, j);
                i++ ;
            }
            j++;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // TC: O(n)
    // SC: O(1)
    // In this approach, we first move all non-zero elements to the front of the array, and then fill the remaining positions with zeros.
    public void moveZeroesApproach2(int[] nums) {
        int idx = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[idx] = nums[i];
                idx++;
            }
        }
        for(int i = idx; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}