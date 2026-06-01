import java.util.Stack;

public class SumOfSubarrayMinimums {
    
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5};
        int n = 4;

        int ansBrute = sumSubarrMinBrute(arr, n);
        System.out.println(ansBrute);

        int ansOptimal = sumSubarrMinOptimal(arr, n);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Appproach: In this approach we will traverse the array and for each element we will find the minimum element in all the 
    //            subarrays starting from that element and we will keep adding the minimum element of each subarray to the sum 
    //            variable and finally we will return the sum variable which will give us the sum of minimum elements of all 
    //            subarrays.
    public static int sumSubarrMinBrute(int[] arr, int n) {
        int mod = (int)1e9 + 7; // Modulo value to prevent integer overflow
        int sum = 0;

        // Traverse each starting index of subarrays
        for(int i = 0 ; i < n ; i ++) {
            // Initialize the minimum as the current element
            int min = arr[i];

             // Traverse all subarrays starting at index i
            for(int j = i ; j < n ; j ++) {
                // Update the minimum in the current subarray
                min = Math.min(min, arr[j]);

                // Add the current minimum to the total sum
                sum = (sum + min) % mod;
            }
        }

        return sum;
    }
    
    // TC: O(5N) -> o(2N) for finding nse idx + O(2N) for finding psee idx + O(N) for calculating the sum using the nse and psee idx
    // SC: O(4N) -> O(N) for nse idx + O(N) for psee idx + O(N) for ans array + O(N) for stack space in worst case
    // Approach: In this approach we will use the concept of next smaller element to the right and previous smaller or equal 
    //           element to the left to find the contribution of each element in the sum of minimums of all subarrays. For each 
    //           element, we will find the number of subarrays in which it is the minimum element and then we will multiply that 
    //           count with the value of the element to get its contribution to the sum. Finally, we will add the contribution of
    //           all elements to get the final sum.
    public static int sumSubarrMinOptimal(int[] arr, int n) {
        int mod = (int)1e9 + 7; // Modulo value to prevent integer overflow
        long sum = 0;

        int[] nse = nse(arr, n); // find the nse idx for all the ele
        int[] psee = psee(arr, n); // find the psee idx for all the ele
        
        for(int i = 0 ; i < n ; i ++) {

            // find the nse idx for the curr ele
            int nseIdx = nse[i];

            // find the pse idx for the curr ele
            int pseeIdx = psee[i];

            // Count of elements to the left including current
            int leftCount = i - pseeIdx;

            // Count of elements to the right including current
            int rightCount = nseIdx - i;

            // count total subarrays where arr[i] is the min ele
            long totalSubarr = leftCount * rightCount * 1L; // 1L bcoz leftCount and rightCount can be large and their multiplication can exceed the range of int, so we use long to store the result of multiplication.

            // Contribution of curr ele = frequency of occurence (total subarrays) * curr ele value
            long totalContri = (totalSubarr * arr[i]) % mod;

            // Add contribution to sum
            sum = (sum + totalContri) % mod;
        }

        return (int)sum;
    }

    // function to find the idx of next smallest element to the right for each of the array element
    public static int[] nse(int[] arr, int n) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];

        // Traverse array from right to left
        for(int i = n - 1 ; i >= 0 ; i --) {

            // Pop elements that are greater or equal to current
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            if(!st.isEmpty()) {
                ans[i] = st.peek();
            } else {
                ans[i] = n; // If stack is empty, NSE doesn't exist → set to n
            }

            // Push current index to stack
            st.push(i);

        }

        return ans;
    }

    // function to find the idx of previous smallest or equal element to the left for each of the array element
    public static int[] psee(int[] arr, int n) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];

        // Traverse array from left to right
        for(int i = 0 ; i < n ; i ++) {

            // Pop elements greater than current
            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            if(!st.isEmpty()) {
                ans[i] = st.peek();
            } else {
                ans[i] = -1; // If stack is empty, pse doesn't exist → set to -1
            }

            // Push current index to stack
            st.push(i);

        }

        return ans;
    }

}
