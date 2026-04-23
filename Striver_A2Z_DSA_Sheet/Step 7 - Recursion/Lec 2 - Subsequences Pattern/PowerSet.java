
import java.util.*;

public class PowerSet {

    public static void main(String[] args) {
        String s = "abc";

        List<String> ans1 = subsequences1(s);
        List<String> ans2 = subsequences2(s);

        System.out.println(ans1);
        System.out.println(ans2);
    }

    // TC: O(2^N * N) -> 2^n outsitde loop and n inside loop
    // SC: O(2^N * N) -> space used to create each subsequence using concatneation (O(n)) and store all possible subsequences O(2^n)
    // Approach: In this approach, we will use bit manipulation to find all the subsequences of a string. We will traverse from 0 
    //           to (2^n - 1) numbers and for each number, we will check which bits are set. If the bit is set, we will include 
    //           the corresponding char in the subsequence. We will concatenate the chars to form the subsequence and add it to 
    //           the result list if it's not empty.
    public static List<String> subsequences1(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();

        int totalSeq = (1 << n); // total subsequences = 2^n = (1 << n);

        // traverse from 0 to (2^n - 1) numbers
        for(int i = 0 ; i < totalSeq ; i ++) {
            String str = "";

            // traverse n bits for each of the ith subsequence 
            for(int bit = 0 ; bit < n ; bit ++) {
                // If bit of curr ith seq is set, include s.charAt(bit)
                if((i & (1 << bit)) != 0) {
                    str += s.charAt(bit);
                }
            }

            // don't add subsequence ("") -> this is ques requirement
            if(!str.isEmpty()) {
                res.add(str); // add the curr subsequence formed
            }
        }

        return res;
    }

    // TC: O(2^N) * O(N) -> there will be 2^n subsequences and for each subseq, string concatenation takes O(n) time.
    // SC: O(2^N) * O(N) -> O(n) is the recursion depth and storing 2^n subsequences in list.
    // Approach: In this approach, we will use recursion to find all the subsequences of a string. We will have two choices for
    //           each character in the string - either we can include it in the subsequence or we can exclude it. We will explore 
    //           both the choices recursively until we reach the end of the string. When we reach the end of the string, we will 
    //           add the formed subsequence to the result list if it's not empty.
    public static List<String> subsequences2(String s) {
        List<String> res = new ArrayList<>();

        helper(0, "", s, res);
        return res;
    }

    // helper function to find all the subsequences of a string
    private static void helper(int idx, String ans, String s, List<String> res) {
        // base case
        if(idx == s.length()) {
            // acc to ques -> don't add empty subseq
            if(!ans.isEmpty()) {
                res.add(ans);
            }

            return;
        }

        // Pick current character
        helper(idx + 1, ans + s.charAt(idx), s, res);

        // Don't pick current character
        helper(idx + 1, ans, s, res);

    }
    
}
