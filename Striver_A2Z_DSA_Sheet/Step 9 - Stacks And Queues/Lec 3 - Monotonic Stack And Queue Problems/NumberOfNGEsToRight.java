public class NumberOfNGEsToRight {

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 7, 5, 8, 10, 6};
        int n = 8;

        int idx = 3;
        int ans = numNgeForOneIdx(arr, idx, n);
        System.out.println(ans);
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach we will traverse the array from the given index to the end of the array and for each element we 
    //           will check if it is greater than the element at the given index. If it is greater then we will increment the 
    //           count variable and finally we will return the count variable which will give us the number of next greater 
    //           elements to the right of the given index.
    public static int numNgeForOneIdx(int[] arr, int idx, int n) {
        int count = 0;

        // traverse from the given idx ele till the end in right direction to get all nge for arr[idx] ele
        for(int i = idx + 1; i < n ; i ++) {
            if(arr[i] > arr[idx]) {
                count++;
            }
        }

        return count;
    }
    
}
