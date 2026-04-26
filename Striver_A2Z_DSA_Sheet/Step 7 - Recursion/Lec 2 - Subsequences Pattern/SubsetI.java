import java.util.*;

public class SubsetI {

    public static void main(String[] args) {
        int[] arr = {5, 2, 1};

        List<Integer> sumBitMask = subsetSumBitMask(arr);
        List<Integer> sumRecursive = subsetSumRecursive(arr);

        System.out.println(sumBitMask);
        System.out.println(sumRecursive);
    }

    // TC: O(2^N * N) + O((2^N)*log(2^n)) ~ O(2^N * N)
    // SC: O(2^N) -> to store 2^n subset sums in arraylist
    // Approach: In this approach, we will use bit manipulation to generate all possible subsets of the given array. Each subset 
    //           can be represented by a bitmask, where the i-th bit indicates whether the i-th element of the array is included 
    //           in the subset or not. We will iterate through all possible bitmasks from 0 to 2^n - 1, calculate the sum of the 
    //           corresponding subset for each bitmask, and store it in a list. Finally, we will sort the list of sums in 
    //           increasing order before returning it.
    public static List<Integer> subsetSumBitMask(int[] arr) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();

        // total subsets: 2^n = (1 << n)
        int totalSeq = 1 << n ;

        // Loop through all possible subsets represented by bitmasks
        for(int i = 0 ; i < totalSeq ; i ++) {
            int sum = 0; // sum of current subset

            // Check each bit in the mask
            for(int bit = 0 ; bit < n ; bit ++) {
                // If the i-th bit is set, include arr[i] in sum
                if((i & (1 << bit)) != 0) {
                    sum += arr[bit];
                }
            }

            ans.add(sum); // store sum
        }

        // Sort sums in increasing order
        Collections.sort(ans);
        return ans;
    }

    // TC: O(2^n) + O(2^n * log(2^n))
    // SC: O(2^n)
    // Approach: In this approach, we will use recursion to generate all possible subsets of the given array. We will define a 
    //           helper function that takes the current index, the current sum of the subset, and the list to store the sums. 
    //           The helper function will explore two possibilities for each element: including it in the subset or excluding it. 
    //           When we reach the end of the array (base case), we will add the current sum to the list. Finally, we will sort 
    //           the list of sums in increasing order before returning it.
    public static List<Integer> subsetSumRecursive(int[] arr) {
        List<Integer> ans = new ArrayList<>();

        helper(arr, 0, 0, ans);

        Collections.sort(ans);
        return ans;
    }

    // helper function to find all the subset sums
    private static void helper(int[] arr, int idx, int sum, List<Integer> ans) {
        // base case
        if(idx == arr.length) {
            ans.add(sum);
            return;
        }

        // include the element
        helper(arr, idx + 1, sum + arr[idx], ans);

        // exclude the element
        helper(arr, idx + 1, sum, ans);
    }
    
}
