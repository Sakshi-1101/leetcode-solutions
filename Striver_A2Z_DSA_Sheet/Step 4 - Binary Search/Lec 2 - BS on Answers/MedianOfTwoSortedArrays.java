public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};

        double ansBrute = findMedianBrute(arr1, arr2);
        double ansBetter = findMedianBetter(arr1, arr2);
        double ansOptimal = findMedianOptimal(arr1, arr2);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(n1 + n2)
    // SC: O(n1 + n2)
    // Approach: Merging two sorted arrays and then finding the median of the merged array.
    public static double findMedianBrute(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] mergeList = new int[n1 + n2];

        int i = 0;
        int j = 0;
        int k = 0;

        // merge the two sorted arrays to create a new merged sorted array
        while(i < n1 && j < n2) {
            if(arr1[i] < arr2[j]) {
                mergeList[k] = arr1[i];
                i++;
            } else {
                mergeList[k] = arr2[j];
                j++;
            }

            k++;
        }

        // if there are remaining elements in arr1, add them to merged array
        while(i < n1) {
            mergeList[k] = arr1[i];
            i++;
            k++;
        }

        // if there are remaining elements in arr2, add them to merged array
        while(j < n2) {
            mergeList[k] = arr2[j];
            j++;
            k++;
        }
        
        // calculate the length of merged sorted array
        int n = n1 + n2;

        // if n is even, median will be the average of the two middle elements.
        if(n % 2 == 0) {
            return (double)(mergeList[n / 2 - 1] + mergeList[n / 2]) / 2.0;
        } else { // if n is odd, median will be the middle value of array
            return (double)mergeList[n / 2];
        }

    }
    
    // TC: O(n1 + n2)
    // SC: O(1)
    // Approach: In this we'll optimise the space complexity of previous approach by not creating a merged array. Instead, we'll 
    //           keep track of the middle elements while merging. We'll use two pointers to traverse both arrays and a counter to 
    //           keep track of the number of elements processed so far. When the counter reaches the middle indices, we'll store 
    //           those elements to calculate the median. Finally, we'll calculate the median based on whether the total number of 
    //           elements is even or odd.
    public static double findMedianBetter(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        // total number of elements
        int n = n1 + n2;

        // pointers for both arrays
        int i = 0;
        int j = 0;
        
        // represent the imaginary third array's index. This mean it will tell us if there would have been a merged array, where will the current element be placed.
        int counter = 0;

        /*
            idx1 and idx2 represent the middle indices of the merged sorted array.
            If the total number of elements (n) is even, idx1 will be n/2 - 1 and idx2 will be n/2.
            ele1 and ele2 will store the elements at these indices respectively.
         */
        int idx1 = n / 2 - 1;
        int idx2 = n / 2;
        int ele1 = -1;
        int ele2 = -1;

        // traverse both arrays until we reach the end of one of them
        while(i < n1 && j < n2) {
            // NOTE: At each step, we are moving in such a way that the elements will be checked in sorted order. It's just not getting stored, rest is same as merging two sorted arrays.
            if(arr1[i] < arr2[j]) {
                if(counter == idx1) { // if current index matches idx1, store the element. This will be our first middle element.
                    ele1 = arr1[i];
                }
                if(counter == idx2) { // if current index matches idx2, store the element. This will be our second middle element.
                    ele2 = arr1[i];
                }

                i++;

            } else {
                if(counter == idx1) { // if current index matches idx1, store the element. This will be our first middle element.
                    ele1 = arr2[j];
                }

                if(counter == idx2) { // if current index matches idx2, store the element. This will be our second middle element.
                    ele2 = arr2[j];
                }

                j++;
            }

            counter ++;
        }

        // process remaining elements of arr1, if any
        while(i < n1) {
           if(counter == idx1) {
                ele1 = arr1[i];
            }

            if(counter == idx2) {
                ele2 = arr1[i];
            }

            counter++;
            i++;
        }

        // process remaining elements of arr2, if any
        while(j < n2) {
            if(counter == idx1) {
                ele1 = arr2[j];
            }

            if(counter == idx2) {
                ele2 = arr2[j];
            }

            counter++;
            j++;
        }

        // calculate and return the median based on whether n is even or odd
        if(n % 2 == 0) {
            return (double)(ele1 + ele2) / 2;
        } else {
            return (double)ele2;
        }

    }

    // TC: O(log (base 2) min(n1, n2)) -> because we are performing binary search on the smaller array
    // SC: O(1)
    // Approach: In this approach, we will use binary search to find the valid symmetry configuration. We will perform binary 
    //           search on the smaller array to find the correct partitioning of both arrays such that all elements on the left 
    //           side of the partition are less than or equal to all elements on the right side. Once we find this valid partition, 
    //           we can easily calculate the median based on the maximum of the left elements and minimum of the right elements.
    public static double findMedianOptimal(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        // length of merged sorted array
        int n = n1 + n2;

        // to ensure that we always do binary search on the smaller array, we check if n1 is greater than n2. If it is, we swap 
        // the arrays and their lengths. This way, we can guarantee that we are performing binary search on the smaller array, 
        // which helps in optimizing the time complexity of our algorithm.
        if(n1 > n2) {
            return findMedianOptimal(arr2, arr1);
        }

        // number of elements that should be on the left half of the merged sorted array. 
        // This is calculated as (n1 + n2 + 1) / 2 to ensure that this works for both even and odd total number of elements. 
        int leftEleCount = (n1 + n2 + 1) / 2;

        // setting lo and hi for binary search
        int lo = 0;
        int hi = n1; // we can have at max n1 elements on the left side from arr1, so hi is set to n1.

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
                // if n is even, median will be the avg of the max of left elements and min of right elements. 
                if(n % 2 == 0) {
                    return (double)(Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else { // if n is odd, median will be the max of left elements 
                    return Math.max(l1, l2);
                }
            } 
            // if l1 > r2, it means that we need to discard right half and move left.
            else if(l1 > r2) {
                hi = mid1 - 1;
            } else { // if l2 > r1, it means that we need to discard left half and move right.
                lo = mid1 + 1;
            }
        }

        // this is dummy value, code will never reach here
        return 0.0;
    }
}

