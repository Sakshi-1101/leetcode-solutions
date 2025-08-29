public class LeftRotatetheArraybyOne {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        leftRotateByOne(arr);

        for(int i = 0 ; i < arr.length ; i ++) {
            System.out.println(arr[i]);
        }
    }

    // TC: O(n)
    // SC: O(1)
    public static void leftRotateByOne(int[] arr) {
        //edge case
        if(arr.length == 1){
            return;
        }

        int temp =arr[0];

        for(int i = 1 ; i < arr.length ; i ++){
            arr[i - 1] = arr[i];
        }

        arr[arr.length - 1] = temp;
    }
    
}
