public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        for(int i = 0 ; i < n ; i ++){
            System.out.print(arr[i] + " ");
        }
    }

    // TC: O(n log n) on average and O(n^2) in worst case
    // SC: O(1) + O(n) for auxiliary recursive stack space
    public static void quickSort(int[] arr, int low, int high) {
        if(low >= high){
            return;
        }

        int pi = partitionAlgo(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }

    public static int partitionAlgo(int[] arr, int low , int high) {
        int pivot = arr[low]; // select the first element of the array as pivot
        int i = low;
        int j = high;

        // loop till i and j cross each other
        while(i < j) {

            // increment i till we find an element greater than pivot and it should not cross the array boundary
            while (arr[i] <= pivot && i < high) {
                i++;
            }

            // decrement j till we find an element smaller than pivot and it should not cross the array boundary
            while(arr[j] > pivot && j > low) {
                j--;
            }

            // if i and j have not crossed each other,that means at this point we have found an element greater than
            // pivot at index i and an element smaller than pivot at index j
            if( i < j) {
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap pivot element at 0th index with element at j to place pivot at its correct position
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j; // return j because it will cross i which means it will be at the correct position of pivot
    }


}
