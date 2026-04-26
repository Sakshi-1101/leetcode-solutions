import java.util.*;

public class SusbsetII {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2};

        List<List<Integer>> setBrute = subsetSumBrute(arr);
        List<List<Integer>> setOptimal = subsetSumOptimal(arr);

        System.out.println(setBrute);
        System.out.println(setOptimal);
    }

    // TC: O(2^N) * O(N) -> 2^N subsets and O(N) for copying each subset into hashset ( hashset computation)
    // SC: O(2^N) * O(N) -> 2^n subsets with each subset size upto n
    // Approach: In this approach, we will use backtracking to find all the subsets of the given array. We will have two choices 
    //           for each element in the array: include it in the subset or exclude it. We will explore both the choices recursively 
    //           until we reach the end of the array. When we reach the end of the array, we will add the formed subset to a hashset
    //           to avoid duplicates. Finally, we will convert the hashset to a list and return it.
    public static List<List<Integer>> subsetSumBrute(int[] arr) {
        // set to store unique subsets
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> curr = new ArrayList<>();

        // sort the array
        Arrays.sort(arr);

        helper(arr, 0, curr , ans);

        // putting unique subsets from set into a new arraylist
        return new ArrayList<>(ans);
    }

    // helper function to find all the subsets
    private static void helper(int[] arr, int idx, List<Integer> curr, Set<List<Integer>> ans) {
        // base case
        if(idx == arr.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // include the element
        curr.add(arr[idx]);
        helper(arr, idx + 1, curr, ans);
        curr.remove(curr.size() - 1); // remove the last added element while backtracking

        // exclude the element
        helper(arr, idx + 1, curr, ans);
    }
    
    // TC: O(2^N * N + LogN) -> in worst case (all unique elements), we generate all possible subsets, which is 2^N
    // SC: O(N * 2^N) + O(N) + O(N) ~ O(N * 2^N) -> O(N) = recursion stack, O(N) = temp storage of curr list 
    // Approach: In this approach, we will use backtracking to find all the subsets of the given array. We will have two choices for 
    //           each element in the array: include it in the subset or exclude it. We will explore both the choices recursively 
    //           until we reach the end of the array.
    public static List<List<Integer>> subsetSumOptimal(int[] arr) {
        Arrays.sort(arr); // Sort to handle duplicates

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        helper(arr, 0, curr, res);

        return res;
    }

    // helper function to generate all unique subsets
    private static void helper(int[] arr, int idx, List<Integer> curr, List<List<Integer>> res) {
        // Add current subset to result
        res.add(new ArrayList<>(curr));

        // Iterate over array from idx to n - 1
        for(int i = idx ; i < arr.length ; i ++) {
            
            // Skip duplicates
            if(i > idx && arr[i] == arr[i - 1]) {
                continue;
            }

            // add the curr element to the list
            curr.add(arr[i]);

            // call recursion on the next element index
            helper(arr, i + 1, curr, res);

            // backtracking: remove the last added element from the list
            curr.remove(curr.size() - 1);
        }
    }
}   
