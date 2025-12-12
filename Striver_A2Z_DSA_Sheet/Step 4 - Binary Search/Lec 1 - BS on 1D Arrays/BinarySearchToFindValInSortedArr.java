public class BinarySearchToFindValInSortedArr {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        // returns idx of the target element
        int ansIterative = binarySearchIterative(arr, target);
        int ansRecursive = binarySearchRecursive(arr, 0, arr.length - 1, target);

        System.out.println(ansIterative);
        System.out.println(ansRecursive);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Using iterative bianry search
    public static int binarySearchIterative(int[] arr, int tar) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {
            /*
                NOTE: To handle the overflow case where the search space can be from 0 to INT_MAX.
                      If at any point low = high = INT_MAX, then int mid = (low + high) / 2 => (2 * INT_MAX) / 2 => this value cannot
                      be stored in the integer. That's why calculate mid as follows.
            */
            int mid = low + ((high - low) / 2);

            if(arr[mid] < tar) {
                low = mid + 1;
            } else if(arr[mid] > tar) {
                high = mid - 1;
            } else {
                return mid; // element found
            }
        }

        // if element not found
        return -1;
    }

    // TC: O(logN)
    // SC: O(1)
    // Approach: Using recursive binary search
    public static int binarySearchRecursive(int[] arr, int low, int high, int tar) {
        // base case
        if(low > high) {
            return -1; // element not found
        }

        // calculate mid
        int  mid = low + ((high - low) / 2);

        if(arr[mid] < tar) {
            return binarySearchRecursive(arr, mid + 1, high, tar);
        } else if(arr[mid] > tar) {
            return binarySearchRecursive(arr, low, mid - 1, tar);
        } else {
            return mid; // element found
        }
    }

}
