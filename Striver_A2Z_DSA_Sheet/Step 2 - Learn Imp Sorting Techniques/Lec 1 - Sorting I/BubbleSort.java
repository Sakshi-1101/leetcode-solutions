public class BubbleSort {

    public static void main(String[] args){
        int[] arr = {7, 5, 9, 2, 8};

        bubbleSort(arr);
        bubbleSortOptimisation(arr);
    }
    

    // TC: O(N^2)
    // SC: O(1)
    public static void bubbleSort(int[] arr) {
        
        // outer loop to ensure that it runs uptil the unsorted part
        // in every iteration the last idx will get sorted so it should run till before that.
        for(int i = arr.length - 1 ; i >= 0 ; i --){

            // inner loop to compare adjacent elements
            for(int j = 0 ; j < i ; j ++){
              if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }


    //best case scenario -> array already sorted
    // TC: O(N)
    // SC: O(1)
    public static void bubbleSortOptimisation(int[] arr) {
        
        //logic: if in the first iteration only no swap happened that means array is already sorted so no need to perform next iterations just break out fo the loop
        for(int i = arr.length - 1 ; i >= 0 ; i --){

            int flag = 0; // flag to check if any swap happened

            for(int j = 0 ; j < i ; j ++){
              if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = 1; // swap happened
                }
            }

            if(flag == 0) {// no swap happened, array is sorted
                break;
            }
        }

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }

}
