public class RecursiveBubbleSort {

    public static void main(String[] args){
        int[] arr = {7, 5, 9, 2, 8};

        recursiveBubbleSort(arr, arr.length - 1);
        recursiveBubbleSortOptimisation(arr, arr.length - 1);

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }

    // TC: O(n^2)
    // SC: O(n) -> for the recursive stack space
    public static void recursiveBubbleSort(int[] arr, int idx) {
        if(idx == 1){ //base case -> if the array is of length 1 or less, it's already sorted
            return;
        } 

        // perform bubble sort for the current pass
        for(int j = 0 ; j < idx ; j ++){
            if(arr[j] > arr[j + 1]){
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        // this call will handle the size of the unsorted array by decrementing each time so that the last element is always in place
        recursiveBubbleSort(arr, idx - 1);
    }


    // TC: O(N2) for the worst and average cases and O(N) for the best case.
    // SC: O(N)
    //The best case occurs if the given array is already sorted. We can reduce the time complexity to O(N) by just adding a small check inside the recursive function. 
    public static void recursiveBubbleSortOptimisation(int[] arr, int idx) {
         if(idx == 1){ 
            return;
        } 

        boolean swapped = false;

        // perform bubble sort for the current pass
        for(int j = 0 ; j < idx ; j ++){
            if(arr[j] > arr[j + 1]){
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = true;
            }
        }

        if (swapped == false) { // If the array is already sorted no swap will occur and we will return from the recursion call. 
            return;
        }


        // this call will handle the size of the unsorted array by decrementing each time so that the last element is always in place
        recursiveBubbleSort(arr, idx - 1);
    }
    
}
