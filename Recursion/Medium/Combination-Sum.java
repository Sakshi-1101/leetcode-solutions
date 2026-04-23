1class Solution {
2    public List<List<Integer>> combinationSum(int[] candidates, int target) {
3       List<List<Integer>> res = new ArrayList<>(); // to store all combinations
4        List<Integer> curr = new ArrayList<>(); // to store a curr combination
5
6        helper(0, candidates, target, res, curr);
7
8        return res;
9    } 
10
11    // helper function to find all the combinations having sum = tar
12    public static void helper(int idx, int[] arr, int tar, List<List<Integer>> res, List<Integer> curr) {
13        // base case
14        if(idx == arr.length) {
15            //check if tar reached 0
16            if(tar == 0) {
17                // 
18                res.add(new ArrayList<>(curr));
19            }
20
21            return;
22        }
23
24        // pick the curr element and make recursive call on curr idx again since we can pick an element any no. of times
25        if(arr[idx] <= tar) {
26            curr.add(arr[idx]); // add the picked element in the list
27            helper(idx, arr, tar - arr[idx], res, curr); // call recursion on curr idx again
28            curr.remove(curr.size() - 1); // Backtrack by removing the last added element
29        }
30
31        // don't pick the curr element and move to next element
32        helper(idx + 1, arr, tar, res, curr);
33    }
34}