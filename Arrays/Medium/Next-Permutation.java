class Solution {
    public void nextPermutation(int[] nums) {
        int idx = -1; // breakpoint

        // find the breakpoint -> a point till where there value is inreasing ang post this it drops.
        for(int i = nums.length - 2 ; i >= 0 ; i --) {
            if(nums[i] < nums[i + 1]){
                idx = i;
                break;
            }
        }

        // edge case: [5,4,3,2,1] next permutation is [1,2,3,4,5]
        if(idx == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // find the next largest element for the breakpoint element
        for(int i = nums.length - 1 ; i > idx ; i --) {
            if(nums[i] > nums[idx]) {
                swap(nums, i, idx);
                break;
            }
        }

        // reverse the numsay betwee [idx + 1 .... end] to get the smallest renumsangement. 
        // Also as we know all the elements after breakpoint idx are in the desc order starting from idx we can reverse it to make it in asc order and get the samllest renumsangement.
        reverse(nums, idx + 1, nums.length - 1);
    }

    public static void reverse(int[] nums, int i , int idx) {
        int j = i;
        int k = idx;

        while(j < k) {
            int temp = nums[j];
            nums[j] = nums[k];
            nums[k] = temp;

            j++;
            k--;
        }
    }

    public static void swap(int[] nums, int i, int idx) {
        int temp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = temp;
    }
}