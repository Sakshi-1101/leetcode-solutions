public class ArmstrongNumber {
    public static void main(String[] args) {
        //n = 153
        // output = true (1^3 + 5^3 + 3^3 = 153)
        boolean ans = findArmstrongNumber(153);
        System.out.println(ans);
    }
    
    //TC: O(log10(n) + 1)
    //SC: O(1)
    public static boolean findArmstrongNumber(int n) {
        int temp = n;
        int pow = (int)(Math.log10(temp) + 1); // count of digits

        int sum = 0;

        while(temp != 0){
            sum += Math.pow((temp % 10), pow);
            temp /= 10;
        }

        return sum == n;

    }
}
