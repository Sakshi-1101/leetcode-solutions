class Solution {
    public boolean check(int[] arr) {
        int count = 1;

        for(int i = 0 ; i < arr.length - 1 ; i ++){
            if(arr[i] <= arr[i + 1]){
                count++;
            } else {
                boolean res = reverseRotateArray(arr, count, arr.length);
                return res;
            }
        }

        return true; // if count == arr.length
    }

    public static boolean reverseRotateArray(int[] arr, int count, int n){

        int[] ans = new int[arr.length];

        for(int i = 0 ; i < n ; i ++){
            ans[i] = arr[(i + count) % n];
        }

        for(int i = 0 ; i < ans.length - 1 ; i ++){
            if(ans[i] > ans[i + 1]){
                return false;
            }
        }

        return true;
    }
}