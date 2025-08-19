public class SumOfNNumbers {

    public static void main(String[] args) {
        int ans = sum(10, 0);
        System.out.println(ans);
    }

    // parameterised recursion
    // TC: O(N)
    // SC: O(N) for recursion stack
    public static int sum(int n, int val) {
        if(n == 0){
            return val;
        }

        return sum(n - 1, val + n);

    }

    //functional recursion
    // TC: O(N)
    // SC: O(N)
    public static int sum(int n) {
        if(n == 0){
            return 0;
        }

        int smallOutput = sum(n - 1);
        return n + smallOutput;
    }
}