import java.util.*;

public class GenerateParantheses {

    public static void main(String[] args) {
        int n = 3;

        List<String> ansBrute = generateBrute(n);
        List<String> ansOptimal = generateOptimal(n);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(2^(2N)) * O(N) -> O(N) for validating the string and O(2^(2N)) for generating all the combinations of the string of length 2N
    // SC: O(N)
    // Approach: In this approach, we will generate all the combinations of the string of length 2N and check if it is a 
    //           valid parentheses string or not, if it is valid then add it to the result list. 
    public static List<String> generateBrute(int n) {
        List<String> res = new ArrayList<>();
        // call helper function to generate all the combinations of the string of length 2N and add only valid strings to res list.
        helper("", n, res);
        return res;
    }

    // helper function to generate all the combinations of the string of length 2N
    private static void helper(String currStr, int n, List<String> res) {
        // base case: if the length of the current string is equal to 2N, check if it is a valid parentheses string 
        if(currStr.length() == 2 * n) {
            // if it is valid, add it to the result list
            if(isValid(currStr)) {
                res.add(currStr);
            } 

            return;
        }

        // recursive calls to generate all the combinations of the string of length 2N by adding either '(' or ')' to the 
        // current string
        helper(currStr + "(", n, res);
        helper(currStr + ")", n, res);
    }

    // helper function to check if the given string is a valid parentheses string
    private static boolean isValid(String s) {
        int count = 0;

        // iterate through the string and count the number of opening and closing parentheses
        for(char ch: s.toCharArray()) {
            if(ch == '(') {
                count++;
            }else {
                count--;
            }

            // if at any point the count becomes -ve, it means that there are more closing parentheses than opening parentheses,
            if(count < 0) {
                return false;
            }
        }

        // if the count is 0 at the end, means it's valid return true else return false
        return count == 0;
    }

    // TC: O(n) * Catalan no. = O(4^n * sqrt(n)) -> time to build each string * no. of strings
    // SC: O(4^n * sqrt(n)) -> max recursion depth = 2n ~ n and total valid strings with length 2n = 2n * catalan no.
    // Approach: In this approach, we will generate only the valid parentheses strings of length 2N by keeping track of the 
    //           count of opening and closing parentheses and adding those to the result list. We will add '(' until the limit 
    //           is reached i.e. n and add ')' until it exceeds the count of '(' bracket.
    public static List<String> generateOptimal(int n) {
        List<String> res = new ArrayList<>();
        helper(0, 0, n, "", res);
        return res;
    }

    // helper function to generate only the valid parentheses strings of length 2N by keeping track of the count of opening and 
    // closing parentheses and adding those to the result list.
    private static void helper(int open, int close, int n, String curr, List<String> res) {
        // Base case
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }

        // Add '(' until limit is reached i.e. n
        if (open < n) {
            helper(open + 1, close, n, curr + "(", res);
        }

        // Add ')' until it exceeds the count of '(' bracket
        if (close < open) {
            helper(open, close + 1, n, curr + ")", res);
        }
    }

}
