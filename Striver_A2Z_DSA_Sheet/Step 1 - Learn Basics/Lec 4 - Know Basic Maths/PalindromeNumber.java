public class PalindromeNumber {

    public static void main(String[] args) {
        boolean ans = isPalindromeApproach1(121);
        System.out.print(ans);
    }

    //TC: O(log10(n) + 1)
    //SC: O(1)
    public static boolean isPalindromeApproach1(int n) {
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

    //TC: O(n)
    //SC: O(n) — Converting an int to a String creates a new string object with n characters in memory.
    public boolean isPalindromeApproach2(int x) {
        String s = String.valueOf(x); // Convert to String
        int n = s.length(); // Store the String length to int n

        for (int i=0; i<n/2; i++) {
            // We check whether the elements at the same distance from
            // beginning and from ending are same, if not we return false
            if (s.charAt(i) != s.charAt(n-i-1)) return false;
        }

        // if no flaws are found we return true
        return true;
    }

    //TC: O(n) — String reversal takes O(n) time, and string comparison also takes O(n) in worst case.
    //SC: O(n) — StringBuilder creates a copy of the original string to reverse it.
    public static boolean isPalindromeApproach3(int x) {
        String value = String.valueOf(x);
        StringBuilder sb = new StringBuilder(value);

        return sb.reverse().toString().equals(value);
    }

}
