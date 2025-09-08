import java.util.*;

public class SortAnArrayOf0s1s2s {

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};

        int[] ans1 = sortBrute(arr);

        for(int i = 0 ; i < ans1.length ; i ++){
            System.out.print(ans1[i] + " ");
        }

        System.out.println();

        int[] ans2 = sortBetter(arr);

        for(int i = 0 ; i < ans2.length ; i ++){
            System.out.print(ans2[i] + " ");
        }

        System.out.println();

        int[] ans3 = sortMoreBetter(arr);

        for(int i = 0 ; i < ans3.length ; i ++){
            System.out.print(ans3[i] + " ");
        }

        System.out.println();


        int[] ans4 = sortOptimal(arr);

        for(int i = 0 ; i < ans4.length ; i ++){
            System.out.print(ans4[i] + " ");
        }

        System.out.println();
    }

    // TC: O(N*logN)
    // SC: O(1)
    // Approach: sort the array since there are only 3 types of number i.e. 0,1 and 2
    public static int[] sortBrute(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    // TC: O(N) + O(N) -> First O(N) for counting the number of 0’s, 1’s, 2’s, and second O(N) for placing them correctly in the original array.
    // SC: O(1)
    //Approach: we'll store the count of occurence of each number and then will add the occurence in the order of 0,1 and 2.
    public static int[] sortBetter(int[] arr) {
         int count0 = 0;
         int count1 = 0;
         int count2 = 0;

         // store count of occurence of each digit in the variables
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] == 0){
                count0++;
            }

            if(arr[i] == 1){
                count1++;
            }

            if(arr[i] == 2){
                count2++;
            }
        }

        int idx = 0;

        // add all the 0s to the array
        for(int i = 0 ; i < count0 ; i ++) {
            arr[idx] = 0;
            idx++;
        }

        // add all the 1s to the array
        for(int i = 0 ; i < count1 ; i ++) {
            arr[idx] = 1;
            idx++;
        }

        // add all the 2s to the array
        for(int i = 0 ; i < count2 ; i ++) {
            arr[idx] = 2;
            idx++;
        }

        return arr;
    }

    // TC: O(N) + O(N)
        /*
         * First loop → for (int num : arr) runs n times → O(n).
         * Second nested loop:
            * Outer loop runs 3 times (for 0,1,2 → constant).
            * Inner loop runs exactly count[0] + count[1] + count[2] = n.
         * So this step is also O(n).
         */
    // SC: O(1)
    // Approach: we'll store the count of occurence of each number and then will add the occurence in the order of 0,1 and 2. Same as above, just more shorter code.
    public static int[] sortMoreBetter(int[] arr){

        int[] count = new int[3]; // count[0], count[1], count[2]

        // this will store the count of occurence of 0,1 and 2
        for (int num : arr) {
            count[num]++;
        }

        // For each number, add it to the array as many times as it occurs.
        int idx = 0;
        for (int val = 0; val <= 2; val++) {
            for (int i = 0; i < count[val]; i++) {
                arr[idx++] = val;
            }
        }

        return arr;
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Using Dutch National Flag Algorithm / Three way partitioning alogrithm
    public static int[] sortOptimal(int[] arr) {
        int low = 0;              // Points to the next position for 0
        int mid = 0;              // Current element under consideration
        int high = arr.length - 1; // Points to the next position for 2

        // Process elements until mid pointer crosses high
        while (mid <= high) {
            if (arr[mid] == 0) {
                // If current element is 0, swap it to the front (low pointer)
                swap(arr, mid, low);
                mid++;
                low++;
            } else if (arr[mid] == 1) {
                // If current element is 1, just move mid forward
                mid++;
            } else { // arr[mid] == 2
                // If current element is 2, swap it to the end (high pointer)
                swap(arr, mid, high);
                high--;
                // Do not increment mid here, as the swapped element needs to be checked
            }
        }

        return arr;
    }

    // Swaps elements at two indices in the array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
