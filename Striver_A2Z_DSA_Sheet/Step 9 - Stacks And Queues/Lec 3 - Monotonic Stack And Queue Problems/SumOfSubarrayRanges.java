import java.util.Stack;

public class SumOfSubarrayRanges {

    public static void main(String[] args) {
        int[] arr = {4, -2, -3, 4, 1};
        int n = 5;

        int sumBrute = sumSubArrBrute(arr, n);
        System.out.println(sumBrute);

        int sumOptimal = sumSubArrOptimal(arr, n);
        System.out.println(sumOptimal);
    }

    // TC: O(n^2)
    // SC: O(1)
    // Approach: In this approach we will traverse the array and for each element we will find the smallest and largest element 
    //           in all the subarrays starting from that element and we will keep adding the difference of largest and smallest 
    //           element of each subarray to the sum variable and finally we will return the sum variable which will give us the 
    //           sum of ranges of all subarrays.
    public static int sumSubArrBrute(int[] arr, int n) {
        int sum = 0;
        
        // Traverse each starting index of subarrays 
        for(int i = 0 ; i < n ; i ++) {
            // Initialize smallest and largest for current subarray
            int min = Integer.MAX_VALUE; // or int min = arr[i];
            int max = Integer.MIN_VALUE; // or int max = arr[i];

            // Traverse subarrays starting from i
            for(int j = i ; j < n ; j ++) {
                min = Math.min(min, arr[j]); // Update smallest element seen so far
                max = Math.max(max, arr[j]); // Update largest element seen so far
                
                // Add the current range (max - min) to the total sum
                sum += (max - min);
            }
        }

        return sum;
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach we will find the sum of largest element in all the subarrays and sum of smallest element in 
    //           all the subarrays and then we will subtract the sum of smallest element from the sum of largest element to get 
    //          the sum of ranges of all the subarrays.
    public static int sumSubArrOptimal(int[] arr, int n) {

        // find the sum of smallest element in all the subarrays and sum of largest element in all the subarrays
        int sumSubArrayMin = getSumSubarrayMin(arr, n);
        int sumSubArrayMax = getSumSubarrayMax(arr, n);

        // subtract the sum of smallest element from the sum of largest element to get the sum of ranges of all the subarrays
        // to get the sum of ranges of all the subarrays
        int sum = sumSubArrayMax - sumSubArrayMin;
        return sum;
    }

    // find the sum of smallest element in all the subarrays
    public static int getSumSubarrayMin(int[] arr, int n) {
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

    // find the sum of largest element in all the subarrays
    public static int getSumSubarrayMax(int[] arr, int n) {
        int mod = (int)1e9 + 7; // Modulo value to prevent integer overflow
        long sum = 0;

        int[] nge = nge(arr, n); // find the nge idx for all the ele
        int[] pgee = pgee(arr, n); // find the pgee idx for all the ele
        
        for(int i = 0 ; i < n ; i ++) {

            // find the nge idx for the curr ele
            int ngeIdx = nge[i];

            // find the pge idx for the curr ele
            int pgeeIdx = pgee[i];

            // Count of elements to the left including current
            int leftCount = i - pgeeIdx;

            // Count of elements to the right including current
            int rightCount = ngeIdx - i;

            // count total subarrays where arr[i] is the min ele
            long totalSubarr = leftCount * rightCount * 1L; // 1L bcoz leftCount and rightCount can be large and their multiplication can exceed the range of int, so we use long to store the result of multiplication.

            // Contribution of curr ele = frequency of occurence (total subarrays) * curr ele value
            long totalContri = (totalSubarr * arr[i]) % mod;

            // Add contribution to sum
            sum = (sum + totalContri) % mod;
        }

        return (int)sum;



    }

    // function to find the idx of next greater element to the right for each of the array element
    public static int[] nge(int[] arr, int n) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];

        // Traverse array from right to left
        for(int i = n - 1 ; i >= 0 ; i --) {

            // Pop elements that are less than or equal to current
            while(!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }

            if(!st.isEmpty()) {
                ans[i] = st.peek();
            } else {
                ans[i] = n; // If stack is empty, NGE doesn't exist → set to n
            }

            // Push current index to stack
            st.push(i);

        }

        return ans;
    }

    // function to find the idx of previous greater or equal element to the left for each of the array element
    public static int[] pgee(int[] arr, int n) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];

        // Traverse array from left to right
        for(int i = 0 ; i < n ; i ++) {

            // Pop elements less than current
            while(!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }

            if(!st.isEmpty()) {
                ans[i] = st.peek();
            } else {
                ans[i] = -1; // If stack is empty, PGEE doesn't exist → set to -1
            }

            // Push current index to stack
            st.push(i);

        }

        return ans;
    }

}
