public class Print1ToN {

    public static void main(String[] args) {
        print(5);
        forwardRecursion(1, 5);
        backwardRecursion(5, 5);
    }

    // TC: O(N)
    // SC: O(N) (recursion stack space)
    public static void print(int n) {
        if(n == 0) {
            return;
        }

        print(n - 1);
        System.out.println(n);
        
    }

    // TC: O(N)
    // SC: O(N) (recursion stack space)
    public static void forwardRecursion(int i, int n){
        
        // Base Condition.
        if(i > n){
            return;
        }

        System.out.println(i);

        // Function call to print i till i increments to n.
        forwardRecursion(i + 1, n);

    }

    // TC: O(N)
    // SC: O(N) (recursion stack space)
    public static void backwardRecursion(int i, int n){
        
        // Base Condition.
        if(i < 1) {
            return;
        }
        // Function call to print(n-1) integers.
        backwardRecursion(i - 1, n);

        System.out.println(i);

    }

}
