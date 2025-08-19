public class SelectionSort {

    public static void main(String[] args){
        int[] arr = {7, 5, 9, 2, 8};

        selectionSort(arr);
    }
    
    // TC: O(N^2)
    // SC: O(1)
    public static void selectionSort(int[] arr) {
        // outer loop to iterate through the array
        for(int i = 0 ; i < arr.length ; i ++){
            int minIdx = i; // assume the first element is the minimum

            // inner loop to find the minimum element in the unsorted part of the array
            for(int j = i + 1 ; j < arr.length ; j ++){
                if(arr[j] < arr[minIdx]){ // we are comparing with minIdx because we assume the first element is the minimum
                    minIdx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

        }

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }
}
