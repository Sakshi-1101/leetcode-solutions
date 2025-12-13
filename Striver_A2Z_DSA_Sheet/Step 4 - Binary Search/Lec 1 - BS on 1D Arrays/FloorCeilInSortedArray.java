import java.util.Arrays;

public class FloorCeilInSortedArray {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        // int x = 20;
        int x = 25;

        int[] ans1 = floorAndCeilApproach1(arr, x);

        for(int i = 0 ; i < ans1.length ; i ++) {
            System.out.println(ans1[i]);
        }

        int[] ans2 = floorAndCeilApproach2(arr, x);

        for(int i = 0 ; i < ans2.length ; i ++) {
            System.out.println(ans2[i]);
        }

        int[] ans3 = floorAndCeilApproach3(arr, x);

        for(int i = 0 ; i < ans3.length ; i ++) {
            System.out.println(ans3[i]);
        }
    }

    // TC: O(log (base2) N)
    // SC: O(1)
    /*
        Approach: In this we'll find the floor (largest <= x) and ceil (smallest >= x) using binary search.
                  So we'll keep updating the floor and ceil values when we find arr[mid] < x and arr[mid] > x respectively.
                  If arr[mid] == x, then both floor and ceil will be arr[mid].
     */
    public static int[] floorAndCeilApproach1(int[] arr, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        int floor = -1;
        int ceil = -1;

        while(lo <= hi) {
            int mid = lo + ((hi - lo) / 2);

            if(arr[mid] < x) {
                floor = arr[mid]; // update floor
                lo = mid + 1;
            } else if(arr[mid] > x) {
                ceil = arr[mid]; // update ceil
                hi = mid - 1;
            } else { // arr[mid] == x
                // when x is present in array so floor = ceil = x
                ceil = arr[mid];
                floor = arr[mid];
                break;
            }
        }

        return new int[]{floor, ceil};
        
    }

    // TC: O(2N) -> O(N) for finding floor and O(N) for finding ceil
    // SC: O(1)
    /* Approach: To calculate ceil, the approach is same as calculating lower bound of element bcoz in ceil 
                 also we need to find the smallest idx where arr[i] >= x.
                 To calculate floor, the approach is similar to upper bound of element with a slight variation. Here,
                 floor is the largest idx where arr[i] <= x. (in upper bound condition was arr[i] < x)
    */
    public static int[] floorAndCeilApproach2(int[] arr, int x) {
        int floor = -1;
        int ceil = -1;

        // Find floor
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] <= x) {
                floor = arr[mid];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        // Find ceil
        lo = 0; 
        hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] >= x) {
                ceil = arr[mid];
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return new int[]{floor, ceil};

    }
    
    // TC: O(log (base2) N)
    // SC: O(1) -> the ans array will always have 2 elements
    // Approach:
    /*
        Binary search stops exactly between the floor and ceil indexes:
                 ? high        ? low
        [ .... [floor]   x   [ceil] .... ]

        Because:
        1. Elements < x keep pushing low forward
        2. Elements > x keep pulling high backward

        The moment they cross (low > high):
        - high stops at the largest index where arr[high] < x ? this becomes floor
        - low stops at the smallest index where arr[low] > x ? this becomes ceil
     */
    public static int[] floorAndCeilApproach3(int[] arr, int x) {
        int[] ans = new int[2];

        int low = 0; 
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > x) {
                high = mid - 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                Arrays.fill(ans, arr[mid]); // when x is present in array so floor = ceil = x
                return ans;
            }
        }

        // if loop breaks, high and low will stop at the correct position for floor(x) and ceil(x) respectively
        /*
            When x is smaller than all elements ? floor doesn't exist (high = -1 -> floor = -1)
            When x is larger than all elements ? ceil doesn't exist (low = arr.length -> ceil = -1)
         */
        ans[0] = (high == -1) ? -1 : arr[high]; // floor
        ans[1] = (low == arr.length) ? -1 : arr[low]; // ceil

        return ans;

    }
}
