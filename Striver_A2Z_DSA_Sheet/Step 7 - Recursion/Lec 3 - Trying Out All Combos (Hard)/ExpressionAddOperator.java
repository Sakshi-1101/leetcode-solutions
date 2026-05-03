import java.util.*;

public class ExpressionAddOperator {

    public static void main(String[] args) {
        String s = "123";
        int tar = 6;

        List<String> res = new ArrayList<>();

        getExpressions(s, 0, "", 0, 0, tar, res);

        System.out.println(res);
    }

    // TC: O(4^n) => in each recursive call, we can choose 4 possibilities for each substring (+, -, *, or no operator in the case of the first number)
    // SC: O(n) => recursion stack space
    // AApproach: DFS Approach
    public static void getExpressions(String s, int idx, String exp, long valSoFar, long lastNum, int tar, List<String> res) {
        // base case: If we've reached the end of the string
        if(idx == s.length()) {
            // If the expression evaluates to the target, add it to result
            if(valSoFar == tar) {
                res.add(exp);
            }

            return;
        }

        // Loop through all substrings starting from 'idx' index
        // idx represents the level and i represent all the substring generation at each level 
        for(int i = idx; i < s.length() ; i ++) {
            // skip the leading zeros if string is "05" or "012", only allowed is "0"
            if(i > idx && s.charAt(idx) == '0') {
                return;
            }

            // generate the current number
            long currNum = Long.parseLong(s.substring(idx, i + 1));

            // if it is the first number
            if(idx == 0) {
                // go to the next number to start the operations
                // valSoFar = currNum and lastNum = currNum 
                getExpressions(s, i + 1, exp + currNum, currNum, currNum, tar, res);
            } else {
                // Add the current number with '+'
                // valSoFar = add the currNum and lastNum = currNum 
                getExpressions(s, i + 1, exp + "+" + currNum, valSoFar + currNum, currNum, tar, res);

                // Add the current number with '-'
                // valSoFar = subtract the currNum and lastNum = - currNum 
                getExpressions(s, i + 1, exp + "-" + currNum, valSoFar - currNum, -currNum, tar, res);

                // Add the current number with '*'
                // valSoFar = valSoFar - lastNum + (lastNum * currNum) and lastNum = lastNum * currNum
                getExpressions(s, i + 1, exp + "*" + currNum, valSoFar - lastNum + (lastNum * currNum), lastNum * currNum, tar, res);
            }
        }
    }
    
}
