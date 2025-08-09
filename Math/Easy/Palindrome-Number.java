class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }

        int rev = reverse(x);
        return x == rev ? true : false;
    }

    public static int reverse (int n){
        long rev = 0;

         while(n != 0){
            int rem = n % 10;
            n /= 10;

            rev = (rev * 10) + rem;
        }

        return (int)rev;
    }
}