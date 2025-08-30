
import javax.sound.sampled.BooleanControl;

public class MissingNumberInArray {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 5};

        int missingNumber = findMissingNumberInSortedArray(arr, arr.length);
        System.out.println("Missing number in sorted array: " + missingNumber);

        int ansBrute = findMissingNumberBrute(arr, arr.length);
        int ansBetter = findMissingNumberBetter(arr, arr.length);
        int ansOptimal1 = findMissingNumberOptimalApproach1(arr, arr.length);
        int ansOptimal2 = findMissingNumberOptimalApproach2(arr, arr.length);

        System.out.println("Missing number using brute force: " + ansBrute);
        System.out.println("Missing number using better approach: " + ansBetter);
        System.out.println("Missing number using optimal approach 1: " + ansOptimal1);
        System.out.println("Missing number using optimal approach 2: " + ansOptimal2);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: For each number from 0 to n, we check if it is present in the array by looping through the entire array.
    public static int findMissingNumberBrute(int[] arr, int n) {

        // Check for each number from 0 to n if it is present in the array
        for(int i = 0 ; i <= n ; i ++){

            boolean found = false;

            // for each number loop through entire array to check if it exists
            for(int j = 0 ; j < n ; j ++) {
                if(arr[j] == i){
                    found = true;
                    break; // as soon as you found the element, break the loop no need to check further
                }
            }

            if (found == false) { // if number i is not found in the array, then it is the missing number
                return i;
            }
        }

        return -1;
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: We are using a frequency array to keep track of the numbers present in the input array.
    public static int findMissingNumberBetter(int[] arr, int n) {
        int[] freq = new int[n + 1];

        for(int i = 0 ; i < n ; i ++) {
            freq[arr[i]]++;
        }

        for(int i = 0 ; i < freq.length ; i ++){
            if(freq[i] == 0) {
                return i;
            }
        }

        return -1;
        
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: We are able to find the missing number by subtracting the sum of the first n natural numbers from the expected sum of the array.
    public static int findMissingNumberOptimalApproach1(int[] arr, int n) {
        int totalSum = n * (n + 1) / 2;
        int sum = 0;

        for(int i = 0 ; i < n ; i ++){
            sum += arr[i];
        }

        return totalSum - sum;
        
    }

    /*
     * Two important properties of XOR are the following:
        * XOR of two same numbers is always 0 i.e. a ^ a = 0. ←Property 1.
        * XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.  ←Property 2

     * Now, let’s XOR all the numbers between 1 to N.
        xor1 = 1^2^.......^N

     * Let’s XOR all the numbers in the given array.
        xor2 = 1^2^......^N (contains all the numbers except the missing one).

     * Now, if we again perform XOR between xor1 and xor2, we will get:
        xor1 ^ xor2 = (1^1)^(2^2)^........^(missing Number)^.....^(N^N)

     * Here all the numbers except the missing number will form a pair and each pair will result in 0 according to the XOR property. 
     * The result will be = 0 ^ (missing number) = missing number (according to property 2).
     
     * So, if we perform the XOR of the numbers 1 to N with the XOR of the array elements, we will be left with the missing number.
     */
    // TC: O(N)
    // SC: O(1)
    public static int findMissingNumberOptimalApproach2(int[] arr, int n) {
        int xor1 = 0;
        int xor2 = 0;

        for(int i = 0 ; i < n ; i ++) {
            xor1 = xor1 ^ arr[i]; // XOR of all elements in the array
            xor2 = xor2 ^ i;      // XOR of all numbers from 0 to n-1
        }

        xor2 = xor2 ^ n; // xor of nth element because loop runs from 0 to n-1

        return xor1 ^ xor2; // XOR of both will give the missing number
    }

    // TC: O(N)
    // SC: O(1)
    // This approach will only work in sorted array
    public static int findMissingNumberInSortedArray(int[] arr, int n) {
       int val = 0;

       for(int i = 0 ; i < n ; i ++) {
            if(arr[i] != val) {
                return val;
            }

            val++;
       }

       return -1; // If no missing number is found
    }

}
