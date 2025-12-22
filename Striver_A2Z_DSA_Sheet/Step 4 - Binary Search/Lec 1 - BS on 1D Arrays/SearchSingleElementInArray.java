
import java.util.HashMap;

public class SearchSingleElementInArray {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};

        int ansBrute1 = searchElementBrute1(arr);
        int ansBrute2 = searchElementBrute2(arr);
        int ansBrute3 = searchElementBrute3(arr);
        int ansBetter = searchElementBetter(arr);
        int ansOptimal = searchElementOptimal(arr);

        System.out.println(ansBrute1);
        System.out.println(ansBrute2);
        System.out.println(ansBrute3);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this we'll linearly traverse the array in jumps of 2 to check for adjacent elements. If we find a pair where 
    //           the two adjacent elements are not equal, then the first element of that pair is the single element.
    public static int searchElementBrute1(int[] arr) {
        // if array contains only 1 element
        if(arr.length == 1) {
             return arr[0];
        }

        // traverse the array
        for(int i = 0 ; i < arr.length ; i += 2) {
            /* check if two adjacent elements are not equal that means arr[i] is single bcoz it's a sorted array so arr[i + 1]
               must be greater than arr[i] for which the duplicate of arr[i + 1] will be present at arr[i + 2]
            */
            if(arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }

        // If not found in pairs, the single element is the last one
        // testcase: [1,1,2]
        return arr[arr.length - 1];
    }
    
    // TC: O(nlogn) -> due to hashmap operations
    // SC: O(n) -> due to hashmap usage
    // Approach: In this we'll use a hashmap to store the frequency of each element in the array. Then we'll traverse the hashmap
    //           to find the element with frequency 1.
    public static int searchElementBrute2(int[] arr) {
        // if array contains only 1 element
        if(arr.length == 1) {
            return arr[0];
        }
    
        HashMap<Integer, Integer> map = new HashMap<>();

        // traverse the array and add the frequency of each element in the array
        for(int i = 0 ; i < arr.length ; i ++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // traverse the map to find the ele with count 1
        for(Integer ele: map.keySet()) {
            if(map.get(ele) == 1) {
                return ele;
            }
        }

        // If not found in pairs, the single element is the last one
        return arr[arr.length - 1];
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this we'll linearly traverse the array to check for the single element by comparing each element with its 
    //           neighbors. If an element is not equal to both its neighbors, then it is the single element. We need to handle the edge
    //           cases for the first and last elements separately.
    public static int searchElementBrute3(int[] arr) {
        // If array has only one element, return it
        if (arr.length == 1) {
            return arr[0];
        }

        // Loop through the array
        for (int i = 0; i < arr.length; i++) {
            // Check if it's the first element and not equal to the next
            if (i == 0) { // edge case: bcoz it doesn't have a left element to compare with
                if (arr[i] != arr[i + 1]) {
                    return arr[i];
                }
            }
            // Check if it's the last element and not equal to the previous
            else if (i == arr.length - 1) { // edge case: bcoz it doesn't have a right element to compare with
                if (arr[i] != arr[i - 1]) {
                    return arr[i];
                }
            }
            // Check if the current element is not equal to both neighbors that means it's a single element
            // if the current element matches with either left or right element then that element appears twice
            else {
                if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1])
                    return arr[i];
            }
        }

        // Dummy return if no element found
        return -1;
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will use the property of XOR operation where a ^ a = 0 and a ^ 0 = a. So, if we XOR all 
    //           the elements of the array, the duplicate elements will cancel each other out and we will be left with the single element.
    public static int searchElementBetter(int[] arr) {
        int xor = 0; // initialize xor to 0

        // traverse the array and perform XOR operation
        for(int i = 0 ; i < arr.length ; i ++) {
            xor ^= arr[i];
        }

        // after all duplicate elements cancel out, we are left with the single element
        return xor;
    }

    // TC: O(NlogN)
    // SC: O(1)
    // Approach: Binary search on the array to find the single element. The key insight is that in a sorted array with all 
    //           elements appearing twice except one, we can use binary search to determine which half contains the single 
    //           element. We check the mid element and its neighbors to see if it is the single element. If not, we determine 
    //           which half to search next based on the index parity and the equality of adjacent elements.
    //           We continue this process until we find the single element.
    public static int searchElementOptimal(int[] arr) {
        int n = arr.length;

        // edge case 1: if arr has single element
        if(n == 1) {
            return arr[0];
        }

        // edge case 2: check for 0th element bcoz there is no left half to check
        if(arr[0] != arr[1]) {
            return arr[0];
        }

        // edge case 3: check for (n-1)th i.e. last element bcoz there is no right half to check
        if(arr[n - 1] != arr[n - 2]) {
            return arr[n - 1];
        }

        // since we have already checked for 0th and (n-1)th element, we'll reduce our search space to [1..(n-2)]
        int lo = 1;
        int hi = n - 2;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            // check if arr[mid] is the single element
            if(arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
                return arr[mid];
            }

            // identify the left or right half
            /*
                Conditions explanation:
                (1) LEFT HALF -> (even idx, odd idx) element values should be equal
                    1. mid % 2 == 1 && arr[mid] == arr[mid - 1]
                    => mid % 2 == 1 -> we are standing at odd idx, so we need to check if the element is equal to previous element
                                    at the even idx (mid - 1)
                    2. mid % 2 == 0 && arr[mid] == arr[mid + 1]
                    => mid % 2 == 0 -> we are standing at the even idx, so we need to check if the element is equal to next element
                                    at the odd idx (mid + 1)
                (2) RIGHT HALF -> (odd idx, even idx) element values should be equal
                    1. mid % 2 == 1 && arr[mid] == arr[mid + 1]
                    => mid % 2 == 1 -> we are standing at odd idx, so we need to check if the element is equal to next element
                                    at the even idx (mid + 1)
                    2. mid % 2 == 0 && arr[mid] == arr[mid - 1]
                    => mid % 2 == 0 -> we are standing at the even idx, so we need to check if the element is equal to previous element
                                    at the odd idx (mid - 1)
             */
            if((mid % 2 == 1 && arr[mid] == arr[mid - 1]) || (mid % 2 == 0 && arr[mid] == arr[mid + 1])) { // left half
                lo = mid + 1; // discard left half since single element will be in right half
            } else { // right half
                hi = mid - 1; // discard right half since element will be in left half
            }
        }

        // if no single element found
        return -1;
    }
}  
