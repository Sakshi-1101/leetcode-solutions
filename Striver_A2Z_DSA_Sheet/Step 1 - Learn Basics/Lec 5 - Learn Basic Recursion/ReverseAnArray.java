
import java.util.*;

public class ReverseAnArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        // reverseArrayTwoPointer(arr, 0, arr.length - 1);
        reverseArrayOnePointer(arr, 0);

        Integer[] a = {1, 2, 3, 4, 5};
        reverseArrayUsingCollections(a);

        // for(int i = 0 ; i < arr.length; i++) {
        //     System.out.print(arr[i] + " ");
        // }
    }

    // TC: O(N)
    // SC: O(N)
    public static void reverseArrayTwoPointer(int[] arr, int i, int j) {
        if(i >= j) {
            return;
        }

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        reverseArrayTwoPointer(arr, i + 1, j - 1);

    }

    // TC: O(N)
    // SC: O(N)
    public static void reverseArrayOnePointer(int[] arr, int i){
        if(i >= arr.length / 2) {
            return;
        }

        int temp = arr[i];
        arr[i] = arr[arr.length - i - 1];
        arr[arr.length - i - 1] = temp;

        reverseArrayOnePointer(arr, i + 1);
    }

    // TC: O(N)
    // SC: O(1)
    public static void reverseArrayUsingCollections(Integer[] arr){
        Collections.reverse(Arrays.asList(arr));

         for(int i = 0 ; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
