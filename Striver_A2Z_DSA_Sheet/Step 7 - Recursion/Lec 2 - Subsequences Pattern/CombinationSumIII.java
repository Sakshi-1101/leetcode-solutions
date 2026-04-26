import java.util.*;

public class CombinationSumIII {
    
    public static void main(String[] args) {
        int k = 3;
        int n = 9;

        List<List<Integer>> ans = getCombinations(k, n);
        
        System.out.println(ans);
    }

    // TC: O(C(9, k) * k) -> total combinations = C(9,k) and for each combination we copy the list = O(k)
    // SC: O(k) (recursion stack space) + O(C(9, k) * k) (for storing all combinations in list)
    // Approach: In this approach, we will use backtracking to find all the combinations of k numbers that sum up to n. We'll 
    //           traverse through the numbers from 1 to 9 and for each number, we will add it in the sum and check for the next 
    //           number. We will also keep track of the current combination of numbers in a list. If the sum becomes equal to n 
    //           and the size of the current combination is k, we will add the current combination to the answer list. If the 
    //           sum exceeds n or the size of the current combination exceeds k, we will backtrack by removing the last added 
    //           number and continue with the next number.
    public static List<List<Integer>> getCombinations(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currComb = new ArrayList<>(); 

        helper(1, k, n, currComb, res);

        return res;
    }

    private static void helper(int idx, int k, int sum, List<Integer> currComb, List<List<Integer>> res) {
        // If the sum is zero and the number of elements is k
        if(sum == 0 && currComb.size() == k) {
            // Add the current combination to the answer
            res.add(new ArrayList<>(currComb));
            return;
        }

        // If the sum is less than or equal to zero or the number of elements exceeds k
        if(sum <= 0 || currComb.size() > k) {
            return;
        }

        // Iterate from the idx to 9
        // idx -> 1 to 9
        for(int i = idx ; i <= 9 ; i++) {
            // If the current number is less than or equal to the sum
            if(i <= sum)  {
                currComb.add(i); // Add the number to the current combination
                //Recursive call with updated sum and next number
                helper(i + 1, k, sum - i, currComb, res);
                currComb.remove(currComb.size() - 1); // Remove the last number to backtrack
            }else { 
                // If the number is greater than the sum, break the loop
                break;
            }
        }
    }

    
}
