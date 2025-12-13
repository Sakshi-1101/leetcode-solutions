public class LastOccurenceInArray {

    public static void main(String[] args) {
        int[] arr = {3, 4, 13, 13, 13, 20, 40};
        int target = 13;

        // return last occurence idx
        int ansBrute = lastOccurenceBrute(arr, target);
        int ansOptimal = lastOccurenceOptimal(arr, 0, arr.length - 1, target);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
        
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: Linearly traverse the array from the end to find the last occurence bcoz from the end the first time the 
    //           element is ocurring will be it's last occurence idx.
    public static int lastOccurenceBrute(int[] arr, int tar) {

        // traverse the array from end
        for(int i = arr.length - 1 ; i >= 0 ; i --) {
            if(arr[i] == tar){ // if found return the idx no need to check further since we need last occurence
                return i;
            }
        }

        // if element not found
        return -1;
    }

    // TC: O(log (base 2) N)
    // SC: O(1)
    // Approach: In this we'll apply binary search to find the last occurence of the target element. 
    //           So when we find the target element at mid, we won't return mid instead we'll update the ans with mid
    //           and move our search space to the right side of mid bcoz there is a possibility of the target element to 
    //           occur at a further right idx since array is sorted so it will be present in right half only not in left half.
    public static int lastOccurenceOptimal(int[] arr, int lo, int hi, int tar) {
        int ans = -1; // if element not found default ans will be -1
        
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(arr[mid] > tar) {
                hi = mid - 1;
            } else if(arr[mid] < tar) {
                lo = mid + 1;
            } else { // arr[mid] == tar
                ans = mid; // store the possible last occurence idx
                lo = mid + 1; // move to right side to check for further occurence
            }
        }

        return ans;
    
    }
    
}
