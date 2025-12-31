public class FindPeakElementInArray {

    public static void main(String[] args) {
        // Constraint: arr[i] != arr[i + 1] for all valid i.
        int[] arr = {1, 2, 1, 3, 5, 6, 4};
        // int[] arr  = {1,1,1};

        int ansBrute1 = findPeakBrute1(arr);
        int ansBrute2 = findPeakBrute2(arr);
        int ansOptimal = findPeakOptimal(arr);
        int ansOptimalAnotherApproach = findPeakOptimalAnotherApproach(arr); // for testcase [1,1,1]

        System.out.println(ansBrute1);
        System.out.println(ansBrute2);
        System.out.println(ansOptimal);
        System.out.println(ansOptimalAnotherApproach);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Linearly traverse the array and check if each element is greater than its neighbors. If it is, return its index as the peak element.
    //           Also handle edge cases for the first and last elements.
    public static int findPeakBrute1(int[] arr) {
        int n = arr.length;

        // check for arr with only one element
        if(n == 1) {
            return 0;
        }

        /* NOTE: imagine that arr[-1] = arr[n] = -∞. In other words, an element is always considered to be strictly greater 
                 than a neighbor that is outside the array.
        */

        // Check for the 0th element
        if(arr[1] < arr[0]) {
            return 0;
        }

        // Check for the (n-1)th last element
        if(arr[n - 2] < arr[n - 1]) {
            return n - 1;
        }

        // check for all the elements from [1...(n-2)]
        for(int i = 1 ; i <= arr.length - 2; i ++) {
            if(arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                return i;
            }
        }

        // if no peak element found
        return -1;

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Linearly traverse the array and for each element, check the left and right conditions to determine if it's a peak. 
    //           Handle edge cases for the first and last elements using short-circuit evaluation to avoid out-of-bounds access.
    public static int findPeakBrute2(int[] arr) {
        int n = arr.length;

        // traverse the array
        for (int i = 0; i < n; i++) {

            /*
                LEFT CONDITION:
                - If i == 0, there is no left neighbor, so we consider the left condition to be automatically true.
                - Otherwise, check if current element is greater than or equal to its left neighbor.
                
                NOTE:
                The '||' operator is short-circuiting.
                If (i == 0) is true, Java does NOT evaluate arr[i - 1], which prevents out-of-bounds access.
            */
            boolean left = (i == 0) || (arr[i] >= arr[i - 1]);

            /*
                RIGHT CONDITION:
                - If i == n - 1, there is no right neighbor, so we consider the right condition to be automatically true.
                - Otherwise, check if current element is greater than or equal to its right neighbor.
                
                Again, short-circuiting prevents arr[i + 1] from being accessed when i == n - 1.
            */
            boolean right = (i == n - 1) || (arr[i] >= arr[i + 1]);

            /*
                If both left and right conditions are satisfied, then arr[i] is greater than or equal to its neighbors
                (or is at the boundary), hence it is a peak element.
            */
            if (left && right) {
                return i;  // Return the index of any one peak
            }
        }

        // This line is practically unreachable because a peak always exists in any array, but added for safety.
        return -1;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: In this approach, we use binary search to find a peak element. We check the mid element and determine 
    //           which half of the array to search next based on the slope.
    //           We also handle edge cases for the first and last elements before starting the binary search.
    public static int findPeakOptimal(int[] arr) {
        int n = arr.length;

        // check for arr with only one element
        if(n == 1) {
            return 0;
        }

        // Check for the 0th element
        if(arr[1] < arr[0]) {
            return 0;
        }

        // Check for the (n-1)th last element
        if(arr[n - 2] < arr[n - 1]) {
            return n - 1;
        }

        // apply binary search for search space [1...(n-2)]
        int lo = 1;
        int hi = n - 2;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // check if arr[mid] is peak element
            if(arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            }
            // check if arr[mid] is in left half (increasing slope) -> peak will be on right
            else if (arr[mid] > arr[mid - 1]) {
                lo = mid + 1; // discard left half
            } 
            // check if arr[mid] is in right half (decreasing slope) -> peak will be on left
            else if(arr[mid] > arr[mid + 1]) {
                hi = mid - 1;  // discard right half
            } 
            // TestCase: [1,5,1,2,1] if arr[mid] is at dip, then discard any one half since peak can be either in left or right half
            else {
                lo = mid + 1; // OR hi = mid - 1 (you can move anyside)
            }
        }

        // This line is practically unreachable because a peak always exists in any array, but added for safety.
        return -1; 

    }
    
    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: This approach works same as findPeakOptimal, it's just that it's work in case arr = [1,1,1]
    public static int findPeakOptimalAnotherApproach(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        // Binary search loop
        while (low < high) {
            // Find mid point
            int mid = (low + high) / 2;

            // If mid element is greater than next
            if (arr[mid] > arr[mid + 1]) {
                // Move to left half
                high = mid;
            } else {
                // Move to right half
                low = mid + 1;
            }
        }

        // Return peak index
        return low;
    }
}
