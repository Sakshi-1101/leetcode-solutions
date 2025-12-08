
import java.util.Arrays;

public class MergeTwoSortedArraysWithoutExtraSpace {

    public static void main(String[] args) {

        int[] nums1 = {-5, -2, 4, 5, 0, 0, 0};
        int[] nums2 = {-3, 1, 8};
        int m = 4;
        int n = 3;

        int[] ansBrute = mergeArraysBrute(nums1, nums2);

        for(int i = 0 ; i < ansBrute.length ; i ++) {
            System.out.print(ansBrute[i] + " ");
        }

        System.out.println();

        int[] ansOptimal = mergeArraysOptimal(nums1, nums2, m, n);

        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            System.out.print(ansOptimal[i] + " ");
        }
    }

    // TC: O((m+n)log(m+n)) due to sorting
    // SC: O(1)
    // Approach: In this approach, we first copy all elements of the second array into the first array where we have 0s. 
    //           Then we sort the first array and return it.
    public static int[] mergeArraysBrute(int[] a1, int[] a2) {
        
        // Copy all elements of a2 at the end of a1 where we have 0s
        for(int i = 0 ; i < a2.length ; i ++) {
            a1[a1.length - 1 - i] = a2[i];
        }

        // Sort the merged array
        Arrays.sort(a1);

        return a1;
    }
    

    // TC: O(m + n)
    // SC: O(1)
    // Approach: In this approach, we use three pointers.One points at the last valid index (excluding zeros) of nums1, 
    //           one points at the last valid index of nums2 and the last pointer points to last index of nums1. 
    //           We compare the elements pointed by the first two pointers and place the larger element at the position 
    //           pointed by the third pointer. We repeat this process until we have traversed all elements of nums2. 
    //           If there are remaining elements in nums2, we copy them to nums1.
    public static int[] mergeArraysOptimal(int[] a1, int[] a2, int m, int n) {

        // initialize three pointers
        int i = m - 1; // a1.length - a2.length - 1
        int j = n - 1; // a2.length - 1
        int k = m + n - 1; // a1.length - 1

        // start comparing from the end and merge
        while(i >= 0 && j >= 0) {

            // place the larger element at the end of a1
            if(a1[i] > a2[j]){
                a1[k] = a1[i];
                i--;
                k--;
            } else {
                a1[k] = a2[j];
                j--;
                k--;
            }
        }

        // if there are remaining elements in a2, copy them to a1
        while(j >= 0) {
            a1[k] = a2[j];
            j--;
            k--;
        }
        
        /*
            NOTE: Remaining nums1 elements are already in correct position bcoz if num2 has already been completely traversed,
                  then all remaining nums1 elements are smaller than the smallest element of nums2 which is already placed correctly in nums1.
         */
        
        return a1;
    }

}
