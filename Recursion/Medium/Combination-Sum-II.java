1class Solution {
2    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
3        // sort the array to get lexicographically sorted combinations
4        Arrays.sort(candidates);
5
6        List<List<Integer>> res = new ArrayList<>(); // to store all combinations
7        List<Integer> curr = new ArrayList<>(); // to store a curr combination
8
9        helperOptimal(0, candidates, target, res, curr);
10
11        return res;
12    }
13
14    // helper function to find all the unique combinations
15    public static void helperOptimal(int idx, int[] arr, int tar, List<List<Integer>> res, List<Integer> curr) {
16        // base case
17        if(tar == 0) {
18            res.add(new ArrayList<>(curr));
19            return;
20        }
21
22        // idx -> the curr level
23        // i -> looping from idx till n-1
24        for(int i = idx ; i < arr.length ; i ++) {
25            // skip duplicates on the same recursion level
26            if(i > idx && arr[i] == arr[i - 1]) {
27                continue;
28            }
29
30            // if curr ele value is > tar, no need to look for next ele since arr is sorted
31            if(arr[i] > tar) {
32                break;
33            }
34
35            // add the curr ele to list
36            curr.add(arr[i]);
37
38            // call recursion on next ele
39            // we'll do (i+1) instead of (idx + 1) bcoz we want explore all the indexes from (idx+1) to (n-1) for curr idx level
40            // and since i is actually loop at each level we are doing this.
41            helperOptimal(i + 1, arr, tar - arr[i], res, curr);
42
43            // delete the last added ele while backtracking
44            curr.remove(curr.size() - 1);
45        }
46    }
47}