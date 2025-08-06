class Solution {
    // NOTE: The % and / operations in Java preserve the sign when dealing with negative numbers.
    public int reverse(int n) {
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
