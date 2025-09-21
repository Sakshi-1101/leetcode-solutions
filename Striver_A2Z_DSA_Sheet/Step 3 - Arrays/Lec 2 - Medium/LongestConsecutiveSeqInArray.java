import java.util.*;

public class LongestConsecutiveSeqInArray {

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};

        int ansBrute = longConsecSeqBrute(arr);
        int ansBetter = longConsecSeqBetter(arr);
        int ansOptimal = longConsecSeqOptimal(arr);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: For each element, we'll check for the next consecutive element in the array using linear search.
    public static int longConsecSeqBrute(int[] arr) {
        // base case
        if(arr.length <= 1) {
            return arr.length;
        }

        int len = 1; // length of longest consecuive sequence

        for(int i = 0 ; i < arr.length ; i ++) {
            int val = arr[i];
            int count = 1; // count the curr element

            // check for the next consecutive element and then keep incrementing the val to check for next element in the sequence until we don't find the next element
            /*
             * NOTE:
             * searchNext(arr, val + 1) uses the old value of val + 1 for searching.
             * After confirming that val + 1 exists, you increment val to move your pointer to that number.
             * Example with val = 1:
                * Call: searchNext(arr, 2) → true.
                * Then you do val++ → now val = 2.
                * On next iteration, call searchNext(arr, 3) (because of val + 1).
                * Hence, no double increment.
             */
            while(searchNext(arr, val +1) == true) {
                val++; // increment the val to check for the next element
                count++;
            }

            len = Math.max(len, count);
        }

        return len;
    }

    // linear search for the element
    public static boolean searchNext(int[] arr, int ele) {
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] == ele) {
                return true;
            }
        }

        return false;
    }

    // TC: O(N * logN) + O(N)
    // SC: O(1)
    // Approach: Sort the array and then check for each element if the next element is consecutive or not. If yes, increment the count, else reset the count to 1.
    public static int longConsecSeqBetter(int[] arr) {
        // base case
        if(arr.length <= 1) {
            return arr.length;
        }

        // sort the array
        Arrays.sort(arr);

        int count = 1;
        int len = 1; // length of longest seq

        // traverse the sorted array and check for consecutive elements
        // also, if we find duplicate elements, we can ignore them since it won't contribute to the length of the consecutive sequence
        for(int i = 1 ; i < arr.length; i ++) {
            if(arr[i] == arr[i - 1] + 1) { // if found, increment the count and update the len
                count++;
                len = Math.max(len, count);
            } else { // if not found, reset the count to 1
                count = 1;
            }
        }

        return len;
        
    }

    // TC: O(N) + O(N) + O(N) = O(3N)
    /*
     * O(N) -> for adding elements to the hashset
     * O(N) + O(N) -> After that for every starting element, we are finding the consecutive elements. 
     *                Though we are using nested loops, the set will be traversed at most twice in the worst case. 
     *                So, the time complexity is O(2*N) instead of O(N2).
     */
    // SC: O(N)
    // Approach: Using HashSet to store the elements and then for each element, check if it is the starting number of a potential subsequence i.e. ele - 1 doesn't exist in the set
    //           If yes, then check for the next consecutive elements in the set and count the length of the subsequence
    //           Finally, return the maximum length of the subsequence found.
    /*
     * we will focus solely on finding sequences only for those numbers that can be the starting numbers of the sequences. 
     * This targeted approach narrows down our search and improves efficiency.
     */
    public static int longConsecSeqOptimal(int[] arr) {
        // base case
        if(arr.length <= 1) {
            return arr.length;
        }

        HashSet<Integer> set = new HashSet<>();

        // adding all the elements to the hashset
        for(int key: arr) {
            set.add(key);
        }

        int len = 1;

        for(int ele: set) {
            // check if the ele is the starting number i.e. ele - 1 doesn't exist
            if(!set.contains(ele - 1)) {
                int count = 1; // count the first element i.e. starting number of potential subsequence
                int startEle = ele;

                // check for the next consecutive elements in the hashset and loop till we don't find the next element
                // if found, increment the count and update the startEle to the next element
                while(set.contains(startEle + 1)) {
                    startEle += 1;
                    count++;
                }

                len = Math.max(len, count);
            }
        }

        return len;
    
    }
    
}
