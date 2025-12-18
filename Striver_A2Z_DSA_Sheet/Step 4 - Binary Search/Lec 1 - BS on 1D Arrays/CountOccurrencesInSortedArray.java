public class CountOccurrencesInSortedArray {

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 3, 3, 3, 4};
        int x = 3;

        int countBrute = countOccurrencesBrute(arr, x);
        int countOptimal1 = countOccurrencesOptimal(arr, x);

        System.out.println(countBrute);
        System.out.println(countOptimal1);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Linearly traverse each element of array and find the count of occurrence of element
    public static int countOccurrencesBrute(int[] arr, int x) {
        int count = 0;

        for(int i = 0 ; i < arr.length ; i ++){
            if(arr[i] == x) {
                count++;
            }
        }

        return count;
    }

    // TC: O(2 * log (base 2) N)
    // SC: O(1)
    // Approach: In this approach, we'll find the first and last occurrence of the target element using binary search and 
    //           then calculate the count using the formula: count = last occurrence idx - first occurrence idx + 1
    public static int countOccurrencesOptimal(int[] arr, int x) {
        int[] pair = firstAndLastOccurence(arr, x);

        if(pair[0] == -1) { // if first occurrence is -1 means element not present
            return 0;
        }

        // count = last occurrence idx - first occurrence idx + 1
        int count = pair[1] - pair[0] + 1;

        return count;
    }

    private static int[] firstAndLastOccurence(int[] arr, int x) {
         //To find first occurrence, move left direction and discard right
        int fidx = -1;
        int lo = 0;
        int hi = arr.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(arr[mid] < x) {
                lo = mid + 1;
            } else if(arr[mid] > x) {
                hi = mid - 1;
            } else {
                fidx = mid; // store possible 1st occurrence idx
                hi = mid - 1; // look in the left half for smaller idx
            }
        }
        
        //To find last occurrence, move right direction and discard left
        int lidx = -1;
        lo = 0;
        hi = arr.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(arr[mid] < x) {
                lo = mid + 1;
            } else if(arr[mid] > x) {
                hi = mid - 1;
            } else {
                lidx = mid; // store possible last occurrence idx
                lo = mid + 1; // look in the right half for bigger idx
            }
        }

        return new int[]{fidx, lidx};
    }
    
}
