public class RecursiveImplementationOfAtoi {

    public static void main(String[] args) {
        String s = " -12345cd";

        int num = atoiRecursive(s);

        System.out.println(num);
    }

    // TC: O(N)
    // SC: O(N) -> recursive stack space
    // Approach: In this approach, we will first skip the leading spaces and then check for the sign of the number. After that, 
    //           we will skip the leading 0s and then call a helper function to calculate the final ans by traversing the string 
    //           and appending the digits to the ans variable. We will also check for the overflow condition in the helper function 
    //           and return Integer.MIN_VALUE or Integer.MAX_VALUE accordingly.
    public static int atoiRecursive(String s) {
        int sign = 1; // 1 represents + sign and -1 represents - sign
        int i = 0;

        // skip leading spaces
        while(i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

       // check the sign and store the correct value to append in final ans
       if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // skip leading 0s
        while(i < s.length() && s.charAt(i) == '0') {
            i++;
        }

        // call the helper function to calculate the final ans
        return helper(s, i, sign, 0);
    }

    // helper function to calculate the final ans by traversing the string and appending the digits to the ans variable
    // Note: we are using long data type for ans variable to handle the overflow condition 
    //       (ans = ans * 10 + digit can overflow int for large inputs like -91283472332)
    private static int helper(String s, int i, int sign, long ans) {
        // base case: if we have reached the end of the string or the current character is not a digit, return the ans so far
        if(i >= s.length() || !Character.isDigit(s.charAt(i))){
            return (int)(sign * ans);
        }

        // append the current digit to the ans variable
        ans = ans * 10 + (s.charAt(i) - '0');

        // check the overflow condition
        if (sign * ans <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (sign * ans >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        // recursive call for the next character in the string
        return helper(s, i + 1, sign, ans);
    }
    
}
