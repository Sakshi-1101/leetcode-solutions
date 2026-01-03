public class FindSqrtOfNumber {

    public static void main(String[] args) {
        // return square root. If ‘n’ is not a perfect square, then return the floor value of sqrt(n).
        // int n = 36;
        int n = 28;

        int sqrtBrute1 = findSqrtBrute1(n);
        int sqrtBrute2 = findSqrtBrute2(n);
        int sqrtOptimal = findSqrtOptimal(n);

        System.out.println(sqrtBrute1);
        System.out.println(sqrtBrute2);
        System.out.println(sqrtOptimal);
    }

    // TC: O(1)
    // SC: O(1)
    // Approach: Using the Java inbuilt function
    public static int findSqrtBrute1(int n) {
        
        return (int)Math.floor(Math.sqrt(n));

    }

    // TC: O(sqrt(n))
    // SC: O(1)
    // Approach: In this approach, we'll traverse from 1 to n and keep squaring the number until the square exceeds n.
    public static int findSqrtBrute2(int n) {
        int ans = 1;

        // checking i * i <= n bcoz this actually is i <= sqrt(n) but we check in this way to avoid floating point operations. 
        for(int i = 1 ; i * i <= n ; i ++) {
            ans = i;
        }

        return ans;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: Using Binary Search on the possible answers. In this approach, we'll try to find the square root by dividing the search space in half each time.
    //           The possible square root values can range from 1 to n. If the square of the mid value is equal to n, we found our answer.
    //           If the square of the mid value is less than n, we move our search to the right half (mid + 1 to hi) and if it's greater than n, 
    //           we move our search to the left half (lo to mid - 1).
    public static int findSqrtOptimal(int n) {
        // int ans = 1;

        int lo = 1;
        int hi = n;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(mid * mid > n) {
                hi = mid - 1;
            } else {
                // ans = mid;
                lo = mid + 1;
            }
        }

        // return ans;

        /*
            NOTE: 
            If we'll observe via dry runs, the ans will be at hi idx bcoz since we are trying to eliminate the zone after
            mid to hi which is the zone with no possible answers so hi will move to the left side. when we start, lo points to 
            the min possible ans while hi is pointing to the not possible ans so lo will move to the right and hi will always 
            move to left. Hence lo ends up pointing at the first place where the value is not possible and hi ends up pointing 
            to the first place where value is possible ans.
            so we don't have to maintain ans variable we can directly return hi variable value.
         */
        return hi;
    }
    
}
