
import java.util.HashSet;

public class RemoveDuplicatesInplaceSortedArray {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,3,3};

        removeDuplicatesInPlaceBetterApproach(arr);
        removeDuplicatesInPlaceOptimalApproach(arr);
    }

    // TC: O(n)
    // SC: O(n) -> You’re using a HashSet to store at most all n elements in the worst case (when all are unique).Hence extra space = O(n).
    public static void removeDuplicatesInPlaceBetterApproach(int[] arr) {
        /*
         * HashSet is a collection that stores unique elements only.
         * It uses a HashMap internally, where each value you add is stored as a key in the map (values are just dummy objects).
         * Uniqueness is guaranteed by hashing + equals() check.
         */
        HashSet<Integer> set = new HashSet<>();
        
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]); // adds only unique elements
        }

        int i = 0;
        int uniqueEleCount = set.size();

        // copy elements from HashSet back to the array at the starting positions
        for(int key: set){
            arr[i] = key;
            i++;
        }
        
        // print only first i elements or the unique elements
        for(int k = 0 ; k < uniqueEleCount ; k ++){
            System.out.println(arr[k]);
        }
    }

    // TC: O(n)
    // SC: O(1)
    // in this approach we are removeing duplicates in place
    public static void removeDuplicatesInPlaceOptimalApproach(int[] arr){
        int i = 0;
        
        // we move ‘j’ till we don't get a number arr[j] which is different from arr[i]. 
        //As we got a unique number we will increase the i pointer and update its value by arr[j]
        for(int j = 1 ; j < arr.length ; j ++){

            if(arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }

        // print only first i elements or the unique elements
        for(int k = 0 ; k <= i ; k ++){
            System.out.println(arr[k]);
        }
    }
}
