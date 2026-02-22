public class SringToIntegerAtoi {

    /*
        MAX = 2147483647
        MIN = -2147483648

        OVERFLOW CONDITION:
        - For positive: 
            if result > 214748364 → overflow 
            if result == 214748364 and digit > 7 → overflow 
        - For negative: 
            if result > 214748364 → overflow 
            if result == 214748364 and digit > 8 → overflow

        - The negative one is for UNDERFLOW CONDITION but we are considering MIN without -ve sign
    */

    public static void main(String[] args) {
        String s = "  -042cd34";

        int ans = atoi(s);
        int ansRecursive = atoiRecursive(s);


        System.out.println(ans);
        System.out.println(ansRecursive);
        
    }

    // TC: O(N)
    // SC: O(N) -> recursion stack
    // Approach: In this approach, we use a helper function to perform the recursive conversion of the string to an integer. The 
    //           main function first handles the initial parsing of the string, including skipping leading spaces, determining the
    //           sign, and skipping leading zeros. Then it calls the helper function, which processes each character recursively 
    //           until it encounters a non-digit character or reaches the end of the string. The helper function also checks for 
    //           overflow conditions and returns the appropriate clamped value if necessary. Finally, the main function returns 
    //           the computed integer value with the correct sign.
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

        return (int)helper(s, i, 0, sign);
    }

    private static long helper(String s, int i, int ans, int sign) {
        // Base case: if i reaches end of string or encounters a non-digit char
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
            return (int)(sign * ans);
        }

        int digit = s.charAt(i) - '0';

        // create the integer value
        ans = ans * 10 + digit;

        // Clamp overflow
        if (sign * ans <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (sign * ans >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        // recursion on next char of string
        return helper(s, i + 1, ans, sign);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we iterate through the string to parse it and convert it to an integer. We first skip any 
    //           leading spaces, then check for an optional sign character ('+' or '-'). After that, we skip any leading zeros. 
    //           We then iterate through the characters of the string, converting each digit character to its integer value and 
    //           building the final result. During this process, we also check for overflow conditions. If we detect that adding 
    //           another digit would cause overflow, we return the appropriate clamped value (Int MAX or Int MIN). Finally, we 
    //           return the computed integer value with the correct sign.
    public static int atoi(String s) {
        // base case: empty string
        if(s.length() == 0) {
            return 0;
        }

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

        int ans = 0; // to store final integer value

        // traverse the string till a non-digit is encountered
        while(i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // check the overflow condition
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // create the integer value
            ans = ans * 10 + digit;
            i++;
        }

        // multiply the correct sign value to the final ans
        return ans * sign;
    }
    
    
}
