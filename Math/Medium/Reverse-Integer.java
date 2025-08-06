class Solution {
    // NOTE: The % and / operations in Java preserve the sign when dealing with negative numbers.
    public int reverseApproach1(int x) {
        int rev = 0;

        while (x != 0) {
            int rem = x % 10;
            x /= 10;

            // Check for overflow BEFORE actually multiplying and adding
            // --------------------------------------------------------
            // Integer.MAX_VALUE =  2147483647
            // Integer.MIN_VALUE = -2147483648

            // If rev is already greater than 214748364,
            // then rev * 10 will definitely overflow even before adding rem.
            // If rev is exactly 214748364, we are on the edge of overflow.
            // Adding a digit > 7 will push it beyond Integer.MAX_VALUE (2147483647)
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && rem > 7)) {
                return 0;
            }
            // Similarly, for negative side:
            // If rev is less than -214748364, then rev * 10 will underflow
            // If rev is exactly -214748364, then adding a digit < -8 will underflow
            // because Integer.MIN_VALUE = -2147483648
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && rem < -8)) {
                return 0;
            }

            rev = rev * 10 + rem;
        }

        return rev;
    }

    public int reverseApproach2(int n) {
        long revNum = 0;

        while(n != 0) {
            long rem = n % 10;
            n = n / 10;

            revNum = (revNum * 10) + rem;
        }

         if (revNum < Integer.MIN_VALUE || revNum > Integer.MAX_VALUE) {
            return 0;
        }
        else{
            return (int) revNum;
        }
    }
}
