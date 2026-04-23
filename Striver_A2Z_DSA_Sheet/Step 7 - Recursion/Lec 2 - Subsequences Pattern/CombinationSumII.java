import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII {

    public static void main(String[] args) {
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        int tar = 8;

        List<List<Integer>> ansBrute = generateCombinationsBrute(arr, tar);
        List<List<Integer>> ansOptimal = generateCombinationsOptimal(arr, tar);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(2^n * klog(set size))
    // SC: O(k * x)
    // Approach: 
    public static List<List<Integer>> generateCombinationsBrute(int[] arr, int tar) {
        // sort the array to get result in lexicographical order
        Arrays.sort(arr);

        Set<List<Integer>> res = new HashSet(); // to store all unique combinations to avoid duplicates
        List<Integer> curr = new ArrayList<>(); // to store a curr combination

        helperBrute(0, arr, tar, res, curr);

        // convert hashset back to list
        List<List<Integer>> ans = new ArrayList<>();
        ans.addAll(res);
        return ans;
    }

    // helper function to find all the combinations having sum = tar
    public static void helperBrute(int idx, int[] arr, int tar, Set<List<Integer>> res, List<Integer> curr) {
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

        // pick the curr element and make recursive call on next idx since we need to pick unique elements
        if(arr[idx] <= tar) {
            curr.add(arr[idx]); // add the picked element in the list
            helperBrute(idx + 1, arr, tar - arr[idx], res, curr); // call recursion on next idx again
            curr.remove(curr.size() - 1); // Backtrack by removing the last added element
        }

        // don't pick the curr element and move to next element
        helperBrute(idx + 1, arr, tar, res, curr);
    }

    // TC: O(2^n * k)
    // SC: O(k * x)
    // Approach: 
    public static List<List<Integer>> generateCombinationsOptimal(int[] arr, int tar) {
        // sort the array to get lexicographically sorted combinations
        Arrays.sort(arr);

        List<List<Integer>> res = new ArrayList<>(); // to store all combinations
        List<Integer> curr = new ArrayList<>(); // to store a curr combination

        helperOptimal(0, arr, tar, res, curr);

        return res;
    }

    // helper function to find all the unique combinations
    public static void helperOptimal(int idx, int[] arr, int tar, List<List<Integer>> res, List<Integer> curr) {
        // base case
        if(tar == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // idx -> the curr level
        // i -> looping from idx till n-1
        for(int i = idx ; i < arr.length ; i ++) {
            // skip duplicates on the same recursion level
            if(i > idx && arr[i] == arr[i - 1]) {
                continue;
            }

            // if curr ele value is > tar, no need to look for next ele since arr is sorted
            if(arr[i] > tar) {
                break;
            }

            // add the curr ele to list
            curr.add(arr[i]);

            // call recursion on next ele
            // we'll do (i+1) instead of (idx + 1) bcoz we want explore all the indexes from (idx+1) to (n-1) for curr idx level
            // and since i is actually loop at each level we are doing this.
            helperOptimal(i + 1, arr, tar - arr[i], res, curr);

            // delete the last added ele while backtracking
            curr.remove(curr.size() - 1);
        }
    }
    
}
