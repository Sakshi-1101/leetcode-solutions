import java.util.*;

public class CombinationSum {

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int tar = 7;

        List<List<Integer>> ans = generateCombinations(arr, tar);

        System.out.println(ans);
    }

    // TC: O(2^t * k) -> due to exploring all combinations up to the target with copying each valid combination of average length k.
    // SC: O(k * x) -> to store all valid combinations, where x is the number of combinations and k is their average length.
    // Approach: In this approach, we will use backtracking to find all the combinations of numbers that sum up to the target. 
    //           We will have two choices for each element in the array: include it in the combination or exclude it. We will 
    //           explore both the choices recursively until we reach the end of the array. When we reach the end of the array, 
    //           we will check if the sum of the formed combination is equal to the target. If it is, we will add the combination 
    //           to the result list. Finally, we will return the list of all combinations whose sum is equal to the target.
    public static List<List<Integer>> generateCombinations(int[] arr, int tar) {
        List<List<Integer>> res = new ArrayList<>(); // to store all combinations
        List<Integer> curr = new ArrayList<>(); // to store a curr combination

        helper(0, arr, tar, res, curr);

        return res;
    } 

    // helper function to find all the combinations having sum = tar
    public static void helper(int idx, int[] arr, int tar, List<List<Integer>> res, List<Integer> curr) {
        // base case
        if(idx == arr.length) {
            //check if tar reached 0
            if(tar == 0) {
                // Whenever storing a list in backtracking → always store a copy bcoz the list is passed by reference and will be 
                // modified in future recursive calls, so we need to store a copy of the current list at that moment.
                res.add(new ArrayList<>(curr));
            }

            return;
        }

        // pick the curr element and make recursive call on curr idx again since we can pick an element any no. of times
        if(arr[idx] <= tar) {
            curr.add(arr[idx]); // add the picked element in the list
            helper(idx, arr, tar - arr[idx], res, curr); // call recursion on curr idx again
            curr.remove(curr.size() - 1); // Backtrack by removing the last added element
        }

        // don't pick the curr element and move to next element
        helper(idx + 1, arr, tar, res, curr);

    }
    
}
