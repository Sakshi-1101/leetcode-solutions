
import java.util.Arrays;

public class LargestElementInArray {

    public static void main(String[] args){
        int[] arr = {2, 5, 1, 3, 0};
       
        System.out.println(largestElementApproach1(arr));
        
        System.out.println(largestElementApproach2(arr));
    }

    // TC: O(N)
    // SC: O(1)
    public static int largestElementApproach1(int[] arr){
        int max = Integer.MIN_VALUE;

        for(int i = 0 ; i < arr.length ; i ++){
            if(arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    // TC: O(N log N)
    // SC: O(1)
    public static int largestElementApproach2(int[] arr){
       Arrays.sort(arr);
       return arr[arr.length - 1];
    }
}