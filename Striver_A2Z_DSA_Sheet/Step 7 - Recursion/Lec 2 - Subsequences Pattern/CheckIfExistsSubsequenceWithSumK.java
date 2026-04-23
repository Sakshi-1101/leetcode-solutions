public class CheckIfExistsSubsequenceWithSumK {

    public static void main(String[] args) {
        int[] arr = {4, 9, 2, 5, 1};
        int tar = 10;

        boolean count = checkIfExistsSubseq(arr, tar);

        System.out.println(count);
    }
    
    // TC: O(2^n)
    // SC: O(n)
    // Approach: In this approach, we will use recursion to find if there exists a subsequence whose sum = target. We will have 
    //           two choices for each element in the array: include it in the subsequence or exclude it. We will explore both the 
    //           choices recursively until we reach the end of the array. When we reach the end of the array, we will check if 
    //           the sum of the formed subsequence is equal to the target. If it is, we will return true, otherwise we will return 
    //           false. Finally, we will return true if there exists a subsequence whose sum is equal to the target, otherwise we 
    //           will return false.
    public static boolean checkIfExistsSubseq(int[] arr, int tar) {
        return helperOptimal(0, arr, 0, tar);
    }

    // helper function to find the if a subseq exists or not where sum= tar
    private static boolean helperBetter(int idx, int[] arr, int sum, int tar) {
        // base case
        if(idx == arr.length) {
            // if sum = tar, means subseq exists
            if(sum == tar) {
                return true;
            }

            // if sum != tar, subseq doesn't exists
            return false;
        }

        //include the num
        boolean inc = helperBetter(idx + 1, arr, sum + arr[idx], tar);

        //exclude the num
        boolean exc = helperBetter(idx + 1, arr, sum, tar);

        // return true if subseq exists in either of the two choices
        return inc || exc;
    }

    private static boolean helperOptimal(int idx, int[] arr, int sum, int tar) {
        // if found, return true
        if(sum == tar) {
            return true;
        }

        if(idx == arr.length) {
            // true if matches else false 
            return sum == tar;
        }

        //include the num
        boolean inc = helperOptimal(idx + 1, arr, sum + arr[idx], tar);

        // early exit, if found a subseq during inc recursion step, no need to check the recursion path for exc
        if(inc) {
            return true;
        }

        //exclude the num
        boolean exc = helperOptimal(idx + 1, arr, sum, tar);

        // if not found in inc path, return the exc path result
        return exc;
    }
}
