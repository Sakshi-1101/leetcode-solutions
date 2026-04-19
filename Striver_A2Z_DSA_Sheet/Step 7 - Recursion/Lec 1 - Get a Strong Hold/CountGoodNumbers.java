public class CountGoodNumbers {

    public static void main(String[] args) {
        int n = 20;

        int ans = countGoodNumbers(n);
        int ansRec = countGoodNumbersRecursive(n);

        System.out.println(ans);
        System.out.println(ansRec);
    }

    static long mod = 1000000007;

    // TC: O(logn) + O(logn) = O(logn) -> pow(x,n) for both even and odd part
    // SC: O(1)
    // Approach: In this approach, we'll first calculate the count of even and odd positions. Then we'll calculate the number of 
    //           choices for even and odd positions using the pow function. Finally, we'll multiply the choices for even and odd 
    //           positions and apply modulo to prevent overflow.
    public static int countGoodNumbers(long n) {
        /*
            property used: (a * b) % mod = ((a % mod) * (b % mod)) % mod
         */

        // Count of positions:
        long even = (n + 1) / 2; // Even indices (0-based): ceil(n/2)
        long odd = n - even; // Odd indices: floor(n/2)

        // Compute:
        long evenPart = pow(5, even); // even positions → 5 choices each
        long oddPart = pow(4, odd); // odd positions  → 4 choices each

        // total = 5^even * 4^odd
        long ans = (evenPart * oddPart) % mod; // We apply modulo again because multiplication can overflow

        return (int)(ans);
    }

    // helper function to calculate x^n
    private static long pow(long x, long n) {

        // Base case:
        if (n == 0 || x == 1) {
            return 1;
        }

        // Divide problem into half → reduces complexity from O(n) to O(log n)
        long half = pow(x, n / 2);

        // If exponent is even: x^n = (x^(n/2)) * (x^(n/2))
        if (n % 2 == 0) {
            // Apply modulo to prevent overflow of large multiplication
            return (half * half) % mod;
        } 
        
        // If exponent is odd: x^n = (x^(n/2)) * (x^(n/2)) * x
        else {
            // Apply modulo at each step to keep number within bounds
            return (half * half % mod * x) % mod;
        }
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we'll use recursion to find the count of good numbers. We'll keep track of the current index 
    //           and for each index, we'll decide the number of choices we have based on whether it's an even or odd index. 
    //           For even indices, we have 5 choices and for odd indices, we have 4 choices. We'll multiply the choices for 
    //           each index and apply modulo to prevent overflow.
    // NOTE: BAD APPROACH bcoz This approach will work but it will give TLE for large inputs like n = 10^15 because of too many 
    //       recursive calls (10^15 calls).
    public static int countGoodNumbersRecursive(int n) {
        return (int)helper(n, 0);
    }

    private static long helper(int n, int idx) {
        // Base case: no positions left
        if(n == 0) {
            return 1;
        }

        // Decide choices based on index
        long ans = (idx % 2 == 0) ? 5 : 4;

        // Recursive call for the next position and multiply with current choices, also apply modulo to prevent overflow
        return (ans * helper(n - 1, idx + 1)) % mod;
    }
}

