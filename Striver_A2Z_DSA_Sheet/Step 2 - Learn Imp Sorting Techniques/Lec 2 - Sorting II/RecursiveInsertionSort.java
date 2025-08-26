public class RecursiveInsertionSort {

    public static void main(String[] args){
        int[] arr = {7, 5, 9, 2, 8};

        recursiveInsertionSort(arr, 0);

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }

    // TC: O(N^2)
    // SC: O(N)
    public static void recursiveInsertionSort(int[] arr, int i) {
        if(i == arr.length - 1){ // base case -> if the array is of length 1 or less, it's already sorted
            return;
        }

        // inner loop is to find the correct position for the picked element
        // need to move from ith idx to 0th idx backward to place the element in sorted part
        for(int j = i ; j > 0 ; j --){
            if(arr[j] < arr[j - 1]){
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }

        // this call will pick elements from unsorted part and insert them into sorted part
        recursiveInsertionSort(arr, i + 1);
    }
    
}
