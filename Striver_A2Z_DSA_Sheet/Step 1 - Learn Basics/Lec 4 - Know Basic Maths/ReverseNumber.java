public class ReverseNumber {

    public static void main(String[] args) {
        int n = 2675;
        reverseNumberBrute(n);
        reverseNumberOptimal(n);
    }

    //brute force approach
    public static void reverseNumberBrute(int n) {

        int ans = 0;
        int digits = (int) (Math.log10(n) + 1);

        while(n > 0) {
            int rem = n % 10;
            n = n / 10;

            int val = rem * (int)Math.pow(10, digits - 1);

            ans += val;
            digits--;
        }

        System.out.println(ans);
    }

    //optimal approach
    public static void reverseNumberOptimal(int n) {
        int revNum = 0;

        while(n > 0) {
            int rem = n % 10;
            n = n / 10;

            revNum = (revNum * 10) + rem;
        }

        System.out.println(revNum);
    }
}
