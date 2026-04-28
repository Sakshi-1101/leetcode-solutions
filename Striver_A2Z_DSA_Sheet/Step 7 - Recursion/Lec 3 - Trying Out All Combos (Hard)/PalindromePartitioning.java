import java.util.*;

public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aabb";

        List<List<String>> ans = getPalinSubStrings(s);

        System.out.println(ans);
    }

    // TC: O(2^N * N) * O(N) ~ O(N^2 * 2^N) => total recursive calls = O(2^N) and for each call we are running a loop for n times 
    //                                       ie O(N) and checking palindrome is another O(N)
    /*
        Explanation of TC:
        - Number of ways to partition a string into substrings: 2^(N-1)
            bcoz For a string of length n, there are: n - 1 gaps. At each gap, we have a choice to either partition or not, 
            leading to 2^(n-1) possible partitions. Therefore total partitions = 2^(n-1) ~ O(2^N)
     */
    // SC: O(2^N * N) + O(N) => O(N * 2^N) for result storage + O(N) for recursion stack space
    // Approach: In this approach, we will try all possible partitions of the string and check if each partition is a palindrome. 
    //           If it is, we will add it to the current list of substrings and recursively call the function on the remaining 
    //           substring. We will backtrack by removing the last added substring after the recursive call.
    public static List<List<String>> getPalinSubStrings(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> currStr = new ArrayList<>();

        helper(s, 0, currStr, res);

        return res;
    }

    // helper function return the list of all palindromic substrings on the basis of different partitions
    private static void helper(String s, int idx, List<String> curr, List<List<String>> res) {
        // base case: If index reached the end of string, add the curr paritioning substrings to res
        if(idx == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        // Try all possible substrings starting from index
        for(int i = idx ; i < s.length() ; i ++) {
            // check if substring s[idx...i] is palindrome
            if(isPalindrome(s, idx, i)) {
                curr.add(s.substring(idx, i + 1)); // Add substring to curr list
                // call recursion on the remaining substring starting from index i + 1 for next partitioning
                helper(s, i + 1, curr, res);
                curr.remove(curr.size() - 1);  // Backtrack by removing the last added substring
            }
        }
    }

    // Function to check if a given substring is a palindrome
    private static boolean isPalindrome(String s, int start, int end) {
        while(start <= end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
    
}
