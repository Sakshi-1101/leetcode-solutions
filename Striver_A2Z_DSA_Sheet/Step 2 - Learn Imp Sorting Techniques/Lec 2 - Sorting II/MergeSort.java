public class MergeSort {
    
    // TC: O(nlogn)
    // SC: O(n)
    public static void main(String[] args){
        int[] arr = {7, 5, 9, 2, 8};

        mergeSort(arr, 0 , arr.length - 1);

        for(int i = 0 ; i < arr.length ; i ++){
            System.out.println(arr[i]);
        }
    }
    

    public static void mergeSort(int[] arr, int i , int j) {
        if(i >= j) {
            return;
        }

        int mid = (i + j) / 2;

        mergeSort(arr, i, mid);
        mergeSort(arr, mid + 1, j);
        mergeArray(arr, i , mid, j);

    }

    public static void mergeArray(int[] arr, int i , int mid, int j) {
        int[] ans = new int[j - i + 1]; // size of current subarray being merged 

        int fhalf = i;
        int shalf = mid + 1;
        int k = 0;

        while(fhalf <= mid && shalf <= j) {
            if(arr[fhalf] <= arr[shalf]) {
                ans[k] = arr[fhalf];
                fhalf++;
            } else {
                ans[k] = arr[shalf];
                shalf++;
            }
            k++;
        }

        while(fhalf <= mid){
            ans[k] = arr[fhalf];
            fhalf++;
            k++;
        }

        while(shalf <= j){
            ans[k] = arr[shalf];
            shalf++;
            k++;
        }


        // copying back the sorted elements into original array
        // After you merge into ans, the sorted part exists only in ans.
        // But you still need the main arr to be sorted for the next merges.
        for(int l = i ; l < j; l ++){
           arr[l] = ans[l - i]; // example: l = 1 -> arr[1] = ans[1 - 0] where i = 0 bcoz original array i = 0 and j = arr.length - 1
        }

    }
}
