public class CheckForPrime {

    // Prime number: a number that have exactly two divisors: 1 and itself
    public static void main(String[] args) {
        boolean ans = checkPrime(10);
        System.out.println(ans);
    }

    // TC: O(n)
    // SC: O(1)
    public static boolean checkPrimeBrute(int n){
        int count = 0;
        for(int i = 1; i <= n; i++){
            if(n % i == 0){
                count++;
            }
        }
        
        if(count == 2){
            return true; // n is prime
        }else{
            return false; // n is not prime
        }
    }

    // TC: O(sqrt(n))
    // SC: O(1)
    public static boolean checkPrimeOptimal(int n){
        int count = 0;
        for(int i = 1 ; i * i <= n ; i ++){
            if(n % i == 0){
                int val1 = i;
                int val2 = n / i;

                count++;

                if(i != val2) { // To avoid counting the square root twice
                    count++;
                }
            }
        }
        
        if(count == 2){
            return true; // n is prime
        }else{
            return false; // n is not prime
        }
    }

    //my approach
    // TC: O(sqrt(n))
    // SC: O(1)
    public static boolean checkPrime(int n) {
        if(n <= 1){
            return false;
        }

        for(int i = 2 ; i * i <= n ; i ++){
            if(n % i == 0) {
                return false;
            }
        }

        return true;
    }
    
}
