1class Solution {
2    public List<List<Integer>> subsetsWithDup(int[] nums) {
3         Arrays.sort(nums); // Sort to handle duplicates
4
5        List<List<Integer>> res = new ArrayList<>();
6        List<Integer> curr = new ArrayList<>();
7
8        helper(nums, 0, curr, res);
9
10        return res;
11    }
12
13    // helper function to generate all unique subsets
14    private static void helper(int[] arr, int idx, List<Integer> curr, List<List<Integer>> res) {
15        // Add current subset to result
16        res.add(new ArrayList<>(curr));
17
18        // Iterate over array from idx to n - 1
19        for(int i = idx ; i < arr.length ; i ++) {
20            
21            // Skip duplicates
22            if(i > idx && arr[i] == arr[i - 1]) {
23                continue;
24            }
25
26            // add the curr element to the list
27            curr.add(arr[i]);
28
29            // call recursion on the next element index
30            helper(arr, i + 1, curr, res);
31
32            // backtracking: remove the last added element from the list
33            curr.remove(curr.size() - 1);
34        }
35    }
36}