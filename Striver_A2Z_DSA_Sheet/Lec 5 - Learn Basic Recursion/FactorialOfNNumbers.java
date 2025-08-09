public class FactorialOfNNumbers {
    public static void main(String[] args) {
        int ans = factorial(5);
        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(N)
    public static int factorial(int n) {
        if( n == 0){
            return 1;
        }

        int smallOutput = factorial(n -1);
        return n * smallOutput;
    }



}
