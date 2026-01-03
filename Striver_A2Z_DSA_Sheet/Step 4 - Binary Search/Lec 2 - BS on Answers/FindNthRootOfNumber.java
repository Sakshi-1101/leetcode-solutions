public class FindNthRootOfNumber {

    public static void main(String[] args) {
        int n = 3;
        int m = 27;

        // int n = 4, m = 69;

        int rootBrute1 = findRootBrute1(n, m);
        int rootBrute2 = findRootBrute2(n, m);
        int rootOptimal1 = findRootOptimal1(n, m);
        int rootOptimal2 = findRootOptimal2(n, m);

        System.out.println(rootBrute1);
        System.out.println(rootBrute2);
        System.out.println(rootOptimal1);
        System.out.println(rootOptimal2);
    }

    // TC: O(1)
    // SC: O(1)
    // Approach: Using Java inbuilt function
    public static int findRootBrute1(int n, int m) {
        /*
            NOTE:
            1. Math.pow(m, 1 / n) -> will give wrong result
            => bcoz if we write (1 / n), Java performs INTEGER division because both operands are ints, 
               so for n > 1 → (1 / n) becomes 0. That would compute m^0 = 1, which is incorrect.
               Eg: n = 3, m = 27 => Math.pow(27, 1 / 3) = Math.pow(27, 0) = 1
            
            2. Math.pow(m, 1.0 / n) -> will give correct result
            => bcoz using (1.0 / n) forces floating-point division and correctly computes the nth root.
               Eg: n = 3, m = 27 => Math.pow(27, 1.0 / 3) = Math.pow(27, 0.3333333333333333) = 3

         */
        return (int)Math.floor(Math.pow(m, 1.0 / n));
    }
    
    // TC: O(M * log (base 2) N) -> Math.pow() complexity = log(base 2)N
    // SC: O(1)
    // Approach: Linearly traverse from i = 1 to m and for each ith value calculate i^n.
    public static int findRootBrute2(int n, int m) {

        for(int i = 1 ; i <= m ; i ++) {
            long val = (long)Math.pow(i, n);

            // found nth root
            if(val == m) {
                return i;
            }

            if(val > m) {
                break;
            }
        }

        // if nth root not found, return -1
        return -1;

    }

    // TC: O(log (base 2) M * log (base 2) N) -> Math.pow() complexity = log(base 2)N
    // SC: O(1)
    // Approach: In this we'll use binary search to find the nth root of m. The possible values for nth root can range from 1 to m.
    //           We'll calculate mid and then compute mid^n. If mid^n is equal to m, we found our answer.
    //           If mid^n is greater than m, we move our search to the left half (lo to mid - 1) else we move our search to the 
    //           right half (mid + 1 to hi).
    public static int findRootOptimal1(int n, int m) {
        int lo = 1;
        int hi = m;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // Calculate mid^n and keep as long to avoid integer overflow
            long val = (long)Math.pow(mid, n);

            if(val == m) { // found nth root
                return mid;
            } else if(val > m) { // if mid^n is greater than m, move to left half
                hi = mid - 1;
            } else { // if mid^n is less than m, move to right half
                lo = mid + 1;
            }
        }

        return -1;
    }

    // TC: O(log (base 2) M * N)
    // SC: O(1)
    // Approach: Similar to previous approach but instead of using Math.pow() to calculate mid^n, we'll create a helper function
    //           that calculates mid^n and compares it with m. This will help to avoid overflow such that we don't calculate full 
    //           mid^n if at any point it exceeds m.
    //           Example: n = 5, m = 10^9 => mid = 10^9/2 and calculating (10^9/2)^5 will overflow even long.
    public static int findRootOptimal2(int n, int m) {
        int low = 1;
        int high = m;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Calculate mid^n using a helper function
            long ans = getVal(mid, n, m);

            if (ans == 1) { // If mid^n equals m
                return mid;
            }else if (ans == 0) { // If mid^n < m
                low = mid + 1;
            }else { // If mid^n > m
                high = mid - 1;
            }
        }

        // Return -1 if not found
        return -1;
    }

    // Helper function to calculate mid^n and compare with m. This will help to avoid overflow such that we don't calculate 
    // full mid^n if at any point it exceeds m.
    private static int getVal(int mid, int n , int m) {
        long ans = 1;

        // return 1 if == m
        // return 0 if < m
        // return 2 if > m
        for(int i = 1 ; i <= n ; i ++) {
            ans *= mid;

            // at any point if ans exceeds m, you dont need to calculate further bcoz it will definitely be greater than m
            if(ans > m) {
                return 2;
            }
        }

        if(ans == m) {
            return 1;
        }

        return 0;
    }
}
