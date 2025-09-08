class Solution {
    public void sortColors(int[] nums) {
        int low = 0;              // Points to the next position for 0
        int mid = 0;              // Current element under consideration
        int high = nums.length - 1; // Points to the next position for 2

        // Process elements until mid pointer crosses high
        while (mid <= high) {
            if (nums[mid] == 0) {
                // If current element is 0, swap it to the front (low pointer)
                swap(nums, mid, low);
                mid++;
                low++;
            } else if (nums[mid] == 1) {
                // If current element is 1, just move mid forward
                mid++;
            } else { // arr[mid] == 2
                // If current element is 2, swap it to the end (high pointer)
                swap(nums, mid, high);
                high--;
                // Do not increment mid here, as the swapped element needs to be checked
            }
        }
    }

    // Swaps elements at two indices in the array
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
