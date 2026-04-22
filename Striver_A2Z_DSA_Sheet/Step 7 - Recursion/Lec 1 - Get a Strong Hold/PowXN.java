public class PowXN {

    public static void main(String[] args) {
        double x = 2.00;
        /*
            CONSTRAINT: -2^31 <= n <= 2^31 - 1
         */
        int n = -200000000;

        double ansBrute = powBrute(x, n);
        double ansOptimal = powOptimal(x, n);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
        
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we'll multiply x n times. Also we'll make sure if n is -ve and greater than the Integer range 
    //           then we'll convert it to long value and multiple it to -1 respectively.
    public static double powBrute(double x, int n) {
        // base case: any number to the power of 0 is 1 and if x = 1 then 1^n will always be 1.
        if(n == 0 || x == 1.0) {
            return 1.0;
        }

        long temp = n; // to avoid integer overflow

        // Handle negative exponents
        if(n < 0) {
            x = 1 / x;
            temp = temp * -1;
        }

        double ans = 1.0;

        // Multiply ans by x for n times
        for(int i = 0 ; i < temp ; i ++) {
            ans *= x;
        }

        return ans;
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we'll use recursion to multiply x n times. Also we'll make sure if n is -ve and greater than 
    //           the Integer range then we'll convert it to long value and multiple it to -1 respectively.
    public static double powOptimal(double x, int n) {
        long temp = n; // to avoid integer overflow

        // Handle negative exponents        
        if(n < 0) {
            return 1.0 / helper(x, temp * -1);
        }

        return helper(x,temp);
    }

    // helper function to find the x^n
    private static double helper(double x, long n) {
        // base case
        if(n == 0 || x == 1.0) {
            return 1.0;
        }

        /*
            NOTE: 
            The below recursion will not work bcoz it's too deep.
                  double ans = helper(x, n - 1);
            If you call => helper(2, 20000); then that's 20k recursive calls.
            Java has a limited call stack size, Usually handles ~10k?20k recursive calls (depends on JVM)
            So if n is large -> stack overflow

         */


        /*
            CORRECT APPROACH:
            Reduce problem by half each time.
            
            Case 1: n is even
            => Example: x^10 = x^5 ? x^5
                    So: x^n = x^(n/2) ? x^(n/2)
                Now, half = x^(n/2)
                        x^n = half ? half
            
            Case 2: n is odd
            => Example: x^5 = x ? x^4 where x^4 = x^2 ? x^2
                      : x^5 = x ? (x^2 ? x^2)
                      : x^5 = x ? (x^2)^2
                    So: x^n =  x ? x^(n/2) ? x^(n/2)
                Now, half = x^(n/2)
                        x^n = x ? half ? half
         */
        
        double half = helper(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
        
    }
    
}
