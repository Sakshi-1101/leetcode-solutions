public class KthElementOfTwoSortedArray {
    
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k = 5;

        /*
            NOTE: The Brute and Better approach will be similar to Ques: Median of Two sorted arrays
         */

        // Optimal approach is also similar to the above mentioned question with minor tweaks
        int ansOptimal = findKthElementOptimal(arr1, arr2, k);

        System.out.println(ansOptimal);
    }

    // TC: O(log (base 2) min(n1, n2)) -> because we are performing binary search on the smaller array
    // SC: O(1)
    // Approach: In this binary search we'll use concept of symmetry. We want to find the kth element in the merged sorted array, 
    //           which means we want to have k elements on the left side of the symmetry. We will perform binary search on the 
    //           smaller array to find the correct partitioning of both arrays such that all elements on the left side of the 
    //           partition are less than or equal to all elements on the right side. Once we find this valid partition, we can 
    //           easily calculate the kth element based on the maximum of the left elements.
    public static int findKthElementOptimal(int[] arr1, int[] arr2, int k) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        // length of merged sorted array
        int n = n1 + n2;

        // to ensure that we always do binary search on the smaller array, we check if n1 is greater than n2. If it is, we swap 
        // the arrays and their lengths. This way, we can guarantee that we are performing binary search on the smaller array, 
        // which helps in optimizing the time complexity of our algorithm.
        if(n1 > n2) {
            return findKthElementOptimal(arr2, arr1, k);
        }

        // number of elements that should be on the left half of the merged sorted array. 
        // we are taking k bcoz we want to find the kth element in the merged sorted array, which means we want to have k elements on the left side of the symmetry. 
        int leftEleCount = k;

        // setting lo and hi for binary search
        
        // lo => if k > n2, we need to pick (k-n2) elements from arr1 to have k elements on left. If k is smaller, then we have a choice to pick nothing. 
        int lo = Math.max(0, k - n2);
        // hi => we can pick atmax either k or n1 elements from arr1 whichever is smaller.
        int hi = Math.min(n1, k);

        while(lo <= hi) {
            // mid1 represents the number of elements we are taking from arr1 for the left half of the symmetry. 
            // mid2 represents the number of elements we are taking from arr2 for the left half of the symmetry. 
            int mid1 = (lo + hi) / 2;
            int mid2 = leftEleCount - mid1;

            // setting default values for l1, l2, r1 and r2. We use Integer.MIN_VALUE and Integer.MAX_VALUE to handle edge cases
            // where there are no elements on one side of the symmetry from either array.
            int l1 = Integer.MIN_VALUE; // l1 -> largest element on the left side of the symmetry from arr1
            int l2 = Integer.MIN_VALUE; // l2 -> largest element on the left side of the symmetry from arr2
            int r1 = Integer.MAX_VALUE; // r1 -> smallest element on the right side of the symmetry from arr1
            int r2 = Integer.MAX_VALUE; // r2 -> smallest element on the right side of the symmetry from arr2

            // assigning values to l1, l2, r1 and r2 based on mid1 and mid2. We check if mid1 and mid2 are within the bounds of 
            // their respective arrays before accessing the elements to avoid ArrayIndexOutOfBoundsException.
            if(mid1 < n1) {
                r1 = arr1[mid1];
            }

            if(mid2 < n2) {
                r2 = arr2[mid2];
            }

            if(mid1 - 1 >= 0) {
                l1 = arr1[mid1 - 1];
            }

            if(mid2 - 1 >= 0) {
                l2 = arr2[mid2 - 1];
            }

            // if below condition is true, it means this is a valid symmetry where all elements on the left side are less than or equal to all elements on the right side.
            if(l1 <= r2 && l2 <= r1) {
                // the kth element will be the max of the left elements bcoz we have k elements on the left side of the symmetry,
                // so the kth element will be the largest element among those k elements, which is the max of l1 and l2.
                return Math.max(l1, l2);   
            } 
            // if l1 > r2, it means that we need to discard right half and move left.
            else if(l1 > r2) {
                hi = mid1 - 1;
            } else { // if l2 > r1, it means that we need to discard left half and move right.
                lo = mid1 + 1;
            }
        }

        // this is dummy value, code will never reach here
        return 0;
    }


    
}
