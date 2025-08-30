
import java.util.ArrayList;

public class MoveAllZerosToTheEndOfTheArray {

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 3, 0, 4, 0, 1};

        moveZeroesBrute(arr);
        moveZeroesOptimal(arr);

        for(int i = 0 ; i < arr.length ; i ++) {
            System.out.println(arr[i]);
        }
    }

    // TC: O(n)
    // SC: O(n)
    // In this approach, we use an ArrayList to store non-zero elements and then fill the original array.
    public static void moveZeroesBrute(int[] arr) {
        ArrayList<Integer> al = new ArrayList<>();

        //copy non-zero elements to arraylist
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] != 0) {
                al.add(arr[i]);
            }
        }

        int count = al.size();

        // copy back non-zero elements to the array
        for(int i = 0 ; i < count ; i ++) {
            arr[i] = al.get(i);
        }

        // fill remaining elements with zeros
        for(int i = count; i < arr.length; i++) {
            arr[i] = 0;
        }
    }


    // TC: O(n)
    // SC: O(1)
    // In this approach, we use two pointers to swap non-zero elements to the front and all the zeros to the end.
    public static void moveZeroesOptimal(int[] arr) {
        int i = 0;
        int j = 0;

        while(j < arr.length) {
            if(arr[j] != 0){
                swap(arr, i, j);
                i++ ;
            }
            j++;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}