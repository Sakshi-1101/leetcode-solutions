class Solution {
    public void rotate(int[] nums, int k) {
       int n = nums.length;
        if(n < k){
            k = k % n;
        }

        rotateArrayByRight(nums, k);
    }

    public static void rotateArrayByRight(int[] arr, int k) {
        reverse(arr, 0, arr.length - k - 1);
        reverse(arr, arr.length - k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    public static void reverse(int[] arr, int i, int j) {

        while(i <= j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

}