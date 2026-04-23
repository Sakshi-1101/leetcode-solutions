
public class CountSubsequencesWithSumK {

    public static void main(String[] args) {
        int[] arr = {4, 9, 2, 5, 1};
        int tar = 10;

        int count = countSubseq(arr, tar);

        System.out.println(count);
    }

    // TC: O(2^n) -> two choices at each recursion step
    // SC: O(n) -> recursion depth stack space 
    // Approach: In this approach, we will use recursion to find the count of subsequences whose sum is equal to the target. We 
    //           will have two choices for each element in the array: include it in the subsequence or exclude it. We will explore 
    //           both the choices recursively until we reach the end of the array. When we reach the end of the array, we will 
    //           check if the sum of the formed subsequence is equal to the target. If it is, we will count it as 1, otherwise we 
    //           will count it as 0. Finally, we will return the total count of subsequences whose sum is equal to the target.
    public static int countSubseq(int[] arr, int tar) {
        // returns the count of subseq whose sum = tar
        return helper(0, arr, 0, tar);
    }

    // helper function to find the count of subseq where sum= tar
    private static int helper(int idx, int[] arr, int sum, int tar) {
        // base case
        if(idx == arr.length) {
            // count subseq if sum = tar
            if(sum == tar) {
                return 1;
            }

            // if sum != tar, don't count
            return 0;
        }

        //include the num
        int incCount = helper(idx + 1, arr, sum + arr[idx], tar);
        //exclude the num
        int excCount = helper(idx + 1, arr, sum, tar);

        // return total count of subseq where sum = tar
        return incCount + excCount;
    }
    
}
