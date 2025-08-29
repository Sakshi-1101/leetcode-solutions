public class CheckIfArrayIsSorted {

    public static void  main(String[] args) {
        int[] arr = {5,4,6,7,8};

        boolean ans = isSortedOptimal(arr);

        System.err.println(ans);;
    }


    // TC: O(N^2)
    // SC: O(1)
    public static boolean isSortedBrute(int[] arr) {

        // comparing each element with the rest of the elements in array.
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {
                if(arr[i] > arr[j]) { // If any of the picked elements is greater than its future elements means array is not sorted
                    return false;
                }
            }
        }
        return true;
    }


    // TC: O(N)
    // SC: O(1)
    public static boolean isSortedOptimal(int[] arr) {

        // comparing the current element with the next element
        for(int i = 0 ; i < arr.length - 1 ; i ++){
            if(arr[i] > arr[i + 1]) { // if at any point we get current element greater than next element means array is not sorted
                return false;
            }
        }

        return true;
    }
    
}
