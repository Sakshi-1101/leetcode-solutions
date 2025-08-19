public class FibonacciUptoNthTerm {

    public static void main(String[] args){
        int n = 5;
        printFibonacci(n, 0, 1);
    }


    // TC: O(N)
    // SC: O(N) {due to recursion stack}
    public static void printFibonacci(int n, int a, int b){
       if(n < 0){
          return;
       }
        
       System.out.print(a + " ");
       printFibonacci(n - 1, b, a + b);
    }
}

