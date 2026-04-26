1class Solution {
2    public List<List<Integer>> combinationSum3(int k, int n) {
3        List<List<Integer>> res = new ArrayList<>();
4        List<Integer> currComb = new ArrayList<>(); 
5
6        helper(1, k, n, currComb, res);
7
8        return res;
9    }
10
11    private static void helper(int idx, int k, int sum, List<Integer> currComb, List<List<Integer>> res) {
12        // If the sum is zero and the number of elements is k
13        if(sum == 0 && currComb.size() == k) {
14            // Add the current combination to the answer
15            res.add(new ArrayList<>(currComb));
16            return;
17        }
18
19        // If the sum is less than or equal to zero or the number of elements exceeds k
20        if(sum <= 0 || currComb.size() > k) {
21            return;
22        }
23
24        // Iterate from the idx to 9
25        // idx -> 1 to 9
26        for(int i = idx ; i <= 9 ; i++) {
27            // If the current number is less than or equal to the sum
28            if(i <= sum)  {
29                currComb.add(i); // Add the number to the current combination
30                //Recursive call with updated sum and next number
31                helper(i + 1, k, sum - i, currComb, res);
32                currComb.remove(currComb.size() - 1); // Remove the last number to backtrack
33            }else { 
34                // If the number is greater than the sum, break the loop
35                break;
36            }
37        }
38    }
39}