public class PrintAllDivisors {

    //Example: n = 36 [1, 2, 3, 4, 6, 9, 12, 18, 36]
    public static void main(String[] args) {
        printDivisorsBrute(36);
        System.out.println();
        printDivisorsOptimal(36);
    }

    //TC: O(n)
    //SC: O(1)
    public static void printDivisorsBrute(int n) {
        for(int i = 1 ; i <= n ; i++){
            if(n % i == 0){
                System.out.print(i + " ");
            }
        }
    }

    //TC: O(sqrt(n))
    //SC: O(1)
    public static void printDivisorsOptimal(int n) {
        for(int i = 1 ; i * i <= n ; i ++){
            if(n % i == 0){
                int val1 = i;
                int val2 = n / i;

                System.out.print(val1 + " ");

                if(i != val2) { // To avoid printing the square root twice
                    System.out.print(val2 + " ");
                }
            }
        }
    }


}
