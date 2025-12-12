public class ImplementUpperBound {
    
    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 15, 19};
        int x = 9;

        int ansBrute = upperBoundBrute(arr, x);
        int ansOptimal = upperBoundOptimal(arr, x);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Using linear search to traverse each element of array to find the first idx where arr[i] > x
    public static int upperBoundBrute(int[] arr, int x) {

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] > x) {
                return i; // First index where element > x
            }
        }

        return arr.length;  // Return length if no such element found
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: Using Binary search since it is a sorted array. In this we'll try to find the first index where arr[i] > x
    public static int upperBoundOptimal(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length; // Default value if not found

        while(low <= high) {
            int mid = low + ((high - low) / 2);

            if(arr[mid] <= x) {
                low = mid + 1;
            } else { // arr[mid] > x
                ans = mid; // Store possible answer
                high = mid - 1; // look for more smaller idx
            }
        }

        return ans;
    }

}
