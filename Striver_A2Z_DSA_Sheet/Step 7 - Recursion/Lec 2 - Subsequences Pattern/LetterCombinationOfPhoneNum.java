
import java.util.*;

public class LetterCombinationOfPhoneNum {

    // number to letter mapping
    static final String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        String digits = "34";

        List<String> ans = getLetterCombinations(digits);

        System.out.println(ans);
    }

    // TC: O(4^n * n) -> each digit has upto 4 choices of letters & total numbers = n => 4 ^n and Build string of length n → O(n)
    // SC: O(4^n * n) + O(n) + O(n) => O(n) recursion stack space, O(N) curr string storage & O(4^N *N) for final result storage
    // Approach:
    public static List<String> getLetterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        StringBuilder curr = new StringBuilder();

        // if string is empty, no combination possible
        if(digits.length() == 0) {
            return res;
        }

        helper(digits, 0, curr, res);

        return res;
    }
    
    // helper function to generate all the letter combinations
    private static void helper(String digits, int idx, StringBuilder curr, List<String> res) {
        // base case if index reaches the end of digits string means we traverse all digits of the string
        if(idx == digits.length()) {
            // add the combination to the string
            res.add(curr.toString());
            return;
        }

        // fetch the letter string for the corresponding curr digit
        /*
            NOTE: If you do, 
                    String s = map[digits.charAt(idx)]; -> Sgtring s = map['2'] x
                    bcoz ASCII of '2' = 50 => String s = map[50] which is not there.
         */
        String s = map[digits.charAt(idx) - '0']; // doing - '0' returns int value

        // traverse all the letters for the digit
        for(char ch: s.toCharArray()) {
            curr.append(ch); // add the char to the curr string
            // recursively call function with the next index
            helper(digits, idx + 1, curr, res);
            curr.deleteCharAt(curr.length() - 1); // remove the last added char while backtracking
        }
    }
    
}
