public class RotateArraybyKelements {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 2;

        rotateArrayBrute(arr, k);
        rotateArrayOptimal(arr, k);
    }

    // TC: O(n)
    // SC: O(n)
    public static void rotateArrayBrute(int[] arr, int k) {
        k = k % arr.length; // in case k is greater than array length 
        
        if(arr.length == 0 || k <= 0 || k > arr.length) {
            return;
        }

        // left rotation
        int[] leftTemp = new int[k];

        for(int i = 0 ; i < k ; i ++){
            leftTemp[i] = arr[i]; // storing first k elements in temp array
        }

        for(int i = 0 ; i < arr.length - k ; i ++){
            arr[i] = arr[i + k]; // shifting elements to left by k positions
        }

        for(int i = arr.length - k ; i < arr.length ; i ++){
            arr[i] = leftTemp[i - (arr.length - k)]; // copying elements from temp array to the last k positions of original array
        }

        System.out.println("After Left Rotation:");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        // right rotation
        int[] rightTemp = new int[k];

        for(int i = arr.length - 1; i >= arr.length - k ; i --){
            rightTemp[i - (arr.length - k)] = arr[i]; // storing last k elements in temp array
        }

        for(int i = arr.length - k - 1 ; i >= 0 ; i --){
            arr[i + k] = arr[i]; // shifting elements to right by k positions
        }

        for(int i = 0 ; i < k ; i ++){
            arr[i] = rightTemp[i]; // copying elements from temp array to the first k positions of original array
        }

        System.out.println("\nAfter Right Rotation:");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    // TC: O(n)
    // SC: O(1)
    public static void rotateArrayOptimal(int[] arr, int k) {
        k = k % arr.length; // in case k is greater than array length 
        
        if(arr.length == 0 || k <= 0 || k > arr.length) {
            return;
        }

        rotateArrayByleft(arr, k);
        rotateArrayByRight(arr, k);
    }


    public static void rotateArrayByleft(int[] arr, int k) {

        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);

        System.err.println("Left Rotation:");

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void rotateArrayByRight(int[] arr, int k) {

        reverse(arr, 0, arr.length - k - 1);
        reverse(arr, arr.length - k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);

        System.err.println("\nRight Rotation:");

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void reverse(int[] arr, int i, int j) {

        while(i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}