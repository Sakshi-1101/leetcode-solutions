public class PalindromeNumber {
    
    //TC: O(log10(n) + 1)
    //SC: O(1)
    public static boolean isPalindrome(int n) {
        int revNum = reverse(n);

        return revNum == n ? true : false;
    }

    private static int reverse(int n){
        int rev = 0;

        while(n != 0){
            int rem = n % 10;
            n /= 10;

            rev = (rev * 10) + rem;
        }

        return rev;
    }

    public static void main(String[] args) {
        boolean ans = isPalindrome(121);
        System.out.print(ans);
    }
}
