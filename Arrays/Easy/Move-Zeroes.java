class Solution {
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
}