public class InsertionSort {

    public static void main(String[] args){
        int[] arr = {7, 5, 9, 2, 8};

        insertionSort(arr);
    }
    

    // TC: O(N^2)
    // SC: O(1)
    public static void insertionSort(int[] arr) {

        // outer loop is to pick elements from unsorted part and insert them into sorted part
        for(int i = 0 ; i < arr.length ; i ++){
            // inner loop is to find the correct position for the picked element
            // need to move from ith idx to 0th idx backward to place the element in the correct position
            for(int j = i ; j > 0 ; j --){
                if(arr[j] < arr[j - 1]){
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }
    
}
