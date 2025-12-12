public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        int target = 2;

        int ansIterMyApp = searchIterativeMyApproach(arr, target);
        int ansIter = searchIterative(arr, target); 
        int ansRecur = searchRecursive(arr, 0, arr.length - 1, target);

        System.out.println(ansIterMyApp);
        System.out.println(ansIter);
        System.out.println(ansRecur);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Using iterative binary search. If element found return mid else return low as the position to insert 
    //           target element.
    public static int searchIterativeMyApproach(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {
            int mid = low + ((high - low) / 2);

            if(arr[mid] < target) {
                low = mid + 1;
            } else if(arr[mid] > target) {
                high = mid - 1;
            } else {
                return mid; // element found at mid idx
            }
        }

        // element not found, low is the correct position to insert target
        return low;
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Same as finding the lower bound of the target element bcoz we are checking if arr[mid] >= target. 
    //           Hence, ans = mid is the correct position to insert target element if not present.
    public static int searchIterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length; // Default value if not found

        while(low <= high) {
            int mid = low + ((high - low) / 2);

            if(arr[mid] >= target) {
                ans = mid; // Store possible answer
                high = mid - 1; // look for more smaller idx
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: Using recursive binary search. If element found return mid else return low as the position to insert
    public static int searchRecursive(int[] arr, int low, int high, int target){
        if(low > high) {
            return low; // position to insert target if not found
        }

        int mid = low + ((high - low) / 2);

        if(arr[mid] < target) {
            return searchRecursive(arr, mid + 1, high, target);
        } else if(arr[mid] > target) {
            return searchRecursive(arr, low, mid - 1, target);
        } else {
            return mid; // element found at mid idx
        }
    }
    
}
