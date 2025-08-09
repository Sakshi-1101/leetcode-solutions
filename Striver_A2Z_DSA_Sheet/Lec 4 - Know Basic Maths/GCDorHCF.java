public class GCDorHCF {
     public static void main(String[] args) {
        int ans1 = gcdBruteApproach(20,15);
        int ans2 = gcdBetterApproach(20,15);
        int ans3 = gcdOptimalApproach(20,15);
        System.out.print(ans2);
    }
    
    //  We iterate through all numbers from 1 up to the minimum of the two input numbers, checking if each number is a common factor 
    // of both input numbers. If a number is a common factor, we update our gcd variable to that number.
    // TC: O(min(n1, n2))
    // SC: O(1)
    public static int gcdBruteApproach(int n1, int n2) {
        int n = Math.min(n1, n2);
        int gcd = 0;
        
        for(int i = 1 ; i < n ; i ++){
            int val1 = n1 % i ;
            int val2 = n2 % i;
            
            if(val1 == 0 && val2 == 0){
                gcd = i;
            }
        }
       
       return gcd;
    }
    
    // We can optimise the time complexity of the previous approach. If we iterate from the minimum of N1 and N2 down to 1, 
    // we reduce the number of iterations because we start from the potentially largest common factor and work downwards. 
    // The time complexity of this approach remains O(min(N1, N2)) but in practice, it will execute fewer iterations on average.
    // TC: O(min(n1, n2))
    // SC: O(1)
    public static int gcdBetterApproach(int n1, int n2) {
        int n = Math.min(n1, n2);
        int gcd = 0;
        
        for(int i = n ; i > 0 ; i --){
            int val1 = n1 % i ;
            int val2 = n2 % i;
            
            if(val1 == 0 && val2 == 0){
                gcd = i;
                break; // We can break early since we found the largest common factor
            }
        }
       
       return gcd;
    }

    //modulo-based Euclidean algorithm (my approach)
    // TC: O(log(min(n1, n2)))
    // SC: O(1)
    public static int gcdOptimalApproach(int n1, int n2) {
        int num = Math.max(n1, n2);
        int den = Math.min(n1, n2);
        
        while (den != 0) {
            int rem = num % den;
            num = den;
            den = rem;
        }
        return num;
    }


    //iterative modulo-based Euclidean algorithm
    public static int gcdModuloEuclidean(int a, int b) {
    // Repeat until one of them becomes 0
        while(a > 0 && b > 0) {
            if(a > b) {
                // Replace a with remainder of a / b
                a = a % b;
            } else {
                // Replace b with remainder of b / a
                b = b % a;
            }
        }
        // Once loop ends, one number is 0
        if(a == 0) {
            return b; // b is the GCD
        }
        return a; // a is the GCD
    }
    
    //subtraction-based Euclidean algorithm
    // TC: O(max(n1, n2))
    // SC: O(1)
    public static int gcdSubtractionEuclidean(int a, int b) {
        // Continue until both numbers are not equal
        while (a != b) {
            if (a > b) {
                // Subtract smaller number from larger
                a = a - b;
            } else {
                b = b - a;
            }
        }
        // When a == b, that's the GCD
        return a;
    }

    //recursive modulo-based Euclidean algorithm
    // TC: O(log(min(n1, n2))
    // SC: O(log(min(n1, n2))
    public static int gcdRecursiveEuclidean(int a, int b) {
        if (b == 0) {
            return a; // Base case: GCD found
        }
        return gcdRecursiveEuclidean(b, a % b); // Recursive step
    }


}
