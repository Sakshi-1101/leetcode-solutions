public class CountDigits {
    public static void main(String[] args) {
       int n = 2675;
       
       countDigitsBrute(n);
       countDigitsOptimal(n);
    }

    //brute force approach
    // TC: O(log10(n) + 1) , SC: O(1)
    public static void countDigitsBrute(int n) {
       if(n < 10){
           System.out.print(1);
           return;
       }
       
       int count = 0;
       while(n > 0){
           n = n / 10;
           count++;
       }
       
       System.out.print(count);
    }

    //optimal approach
    // TC: O(1), SC: O(1)
    public static void countDigitsOptimal(int n) {
       int digits = (int) (Math.log10(n) + 1);
       System.out.println(digits);
    }
}