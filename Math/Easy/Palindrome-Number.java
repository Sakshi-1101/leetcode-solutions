class Solution {

    //TC: O(log10(n) + 1)
    //SC: O(1)
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }

        int rev = reverse(x);
        return x == rev ? true : false;
    }

    public static int reverse (int n){
        long rev = 0;

         while(n != 0){
            int rem = n % 10;
            n /= 10;

            rev = (rev * 10) + rem;
        }

        return (int)rev;
    }

    // This approach is a little better as don't have to reverse entire number, we'll reverse only half of it.
    //TC: O(log10(n))
    //SC: O(1)
     public boolean isPalindromeApproach2(int x) {
        // Step 1: Handle special cases
        // Negative numbers are never palindromes (due to the minus sign)
        // Numbers ending in 0 are not palindromes unless the number is 0 itself
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        // 'reversed' will store the reversed half of the number
        int reversed = 0;

        // Step 2: Reverse only half of the number
        // Stop when the original part becomes less than or equal to the reversed part
        // This means we have processed at least half the digits
        while (x > reversed) {
            int lastDigit = x % 10;      // Get the last digit
            reversed = reversed * 10 + lastDigit; // Append it to 'reversed'
            x /= 10;                     // Remove the last digit from the original number
        }

        // Step 3: Compare the halves
        // Case 1: Even length → both halves should match (x == reversed)
        // Case 2: Odd length → ignore the middle digit in 'reversed' by dividing by 10
        return (x == reversed) || (x == reversed / 10);
    }
}