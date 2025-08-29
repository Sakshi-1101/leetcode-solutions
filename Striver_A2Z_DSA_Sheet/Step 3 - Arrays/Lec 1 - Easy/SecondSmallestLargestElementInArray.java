
import java.util.Arrays;

public class SecondSmallestLargestElementInArray {

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 0};

        if (arr.length <= 1) {
            System.out.println("Second Smallest and Second largest = " + -1);
        }

        findElementBrute(arr);
        findElementBetter(arr);
        findElementOptimal(arr);

    }

    // TC: O(N log N)
    // SC: O(1)
    // NOTE: This approach only works if there are no duplicates
    public static void findElementBrute(int[] arr) {
        Arrays.sort(arr);

        System.out.println("Second largest = " + arr[arr.length - 2]);
        System.out.println("Second smallest = " + arr[1]);
    }

    // TC: O(N) ->  We do two linear traversals in our array
    // SC: O(1)
    public static void findElementBetter(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }

        int secMax = Integer.MIN_VALUE;
        int secMin = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != max && arr[i] > secMax) {
                secMax = arr[i];
            }

            if (arr[i] != min && arr[i] < secMin) {
                secMin = arr[i];
            }
        }

        System.out.println("Second largest = " + secMax);
        System.out.println("Second smallest = " + secMin);
    }

    // TC: O(N)
    // SC: O(1)
    public static void findElementOptimal(int[] arr) {
        int max = Integer.MIN_VALUE;
        int secMax = Integer.MIN_VALUE;

        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                secMax = max;
                max = arr[i];
            } 
            /* arr[i] != max -> we are checking this bcoz we want to find the 2nd largest distinct element & in case there is a 
               duplicate max value so that it should not be considered as second largest
            */
            else if (arr[i] > secMax && arr[i] != max) { 
                secMax = arr[i];
            }

            if (arr[i] < min) {
                secMin = min;
                min = arr[i];
            } else if (arr[i] < secMin && arr[i] != min) {
                secMin = arr[i];
            }
        }

        System.out.println("Second largest = " + secMax);
        System.out.println("Second smallest = " + secMin);
    }
}
