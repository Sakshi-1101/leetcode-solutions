import java.util.ArrayList;

public class RearrangeArrayElementsBySign {

    public static void main(String[] args) {

        version1(); // array with equal number of positive and negative elements.
        version2(); // array with unequal number of positive and negative elements.
    }

    public static void version2() {
        int[] arrV2 = {1,2,-4,-5,3,4};

        int[] ansV2 = rearrangeElementsV2(arrV2);

        for(int i = 0 ; i < ansV2.length ; i ++){
            System.out.print(ansV2[i] + " ");
        }

        System.out.println();
    }

    public static void version1() {
        // int[] arrV1 = {1,2,-3,-1,-2,3};
        int[] arrV1 = {28,-41,22,-8,-37,46,35,-9,18,-6,19,-26,-37,-10,-9,15,14,31};

        int[] ansBruteV1 = rearrangeElementsBruteV1(arrV1);
        int[] ansMyApproachV1 = rearrangeElementsMyApproachV1(arrV1);
        int[] ansOptimalV1 = rearrangeElementsOptimalV1(arrV1);

        for(int i = 0 ; i < ansBruteV1.length ; i ++){
            System.out.print(ansBruteV1[i] + " ");
        }

        System.out.println();

        for(int i = 0 ; i < ansMyApproachV1.length ; i ++){
            System.out.print(ansMyApproachV1[i] + " ");
        }

        System.out.println();

        for(int i = 0 ; i < ansOptimalV1.length ; i ++){
            System.out.print(ansOptimalV1[i] + " ");
        }

        System.out.println();

    }

    // TC: O(N + N/2) -> O(N) for traversing the array once for segregating (+)ves and (-)ves and O(N/2) for adding those elements alternatively to the array}
    // SC: O(N/2 + N/2) -> O(N) for storing the (+)ves and (-)ves in separate arraylists
    // Approach: Store positive and negative numbers in separate lists, then merge them back into the original array in alternating order.
    public static int[] rearrangeElementsBruteV1(int[] arr) {
       ArrayList<Integer> pos = new ArrayList<>();
       ArrayList<Integer> neg = new ArrayList<>();

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] < 0) {
                neg.add(arr[i]);
            } else {
                pos.add(arr[i]);
            }
        }

        // add the positive elements on even indices, negative elements on odd indices
        for(int i = 0; i < arr.length / 2; i ++){
            arr[2 * i] = pos.get(i);
            arr[2 * i + 1] = neg.get(i);
        }

        return arr;
    }

    // TC: O(N^2)
    // SC: 0(1)
    // Approach: We'll maintain two pointers i and j. The pointer i will traverse the array, and j will look for the next element to swap when the sign condition is not met.
    public static int[] rearrangeElementsMyApproachV1(int[] arr) {
       int n = arr.length;
        int i = 0;

        while (i < n) {
            // Even index → need positive
            if (i % 2 == 0 && arr[i] < 0) {
                int j = i + 1;
                while (j < n && arr[j] < 0) { // find next positive
                    j++;
                }

                if (j == n) { // no positive left
                    break; 
                }

                rotateRight(arr, i, j); // bring nums[j] to position i
            }

            // Odd index → need negative
            if (i % 2 == 1 && arr[i] > 0) {
                int j = i + 1;
                while (j < n && arr[j] > 0) {  // find next negative
                    j++;
                }

                if (j == n) {  // no negative left
                    break;
                }

                rotateRight(arr, i, j); // bring nums[j] to position i
            }

            i++;
        }

        return arr;
    }

    // Rotate the subarray [i...j] right by one
    /*
     *  rotate() is used to fix a misplaced element (wrong sign at index i) by bringing the next correctly-signed element (at index j) forward. 
     * Instead of just swapping i and j, we rotate the subarray [i..j] to the right by 1 position. 
     * This preserves the original relative order of all elements between i and j (important because
     * the problem requires maintaining order).
     * Without rotation and just swapping, order breaks (later positives/negatives jump ahead).
     */
    public static void rotateRight(int[] arr, int i, int j) {
        int temp = arr[j];
        for (int k = j; k > i; k--) {
            arr[k] = arr[k - 1];
        }

        arr[i] = temp;
    }


    // TC: O(N)
    // SC: O(N)
    // Approach: In this we'll create a new array and place positive elements at even indices and negative elements at odd indices in a single traversal of the input array.
    public static int[] rearrangeElementsOptimalV1(int[] arr) {
        int[] ans = new int[arr.length];

        // resultant array must start from a positive element so we initialize the positive index as 0 and negative index as 1
        int posIdx = 0;
        int negIdx = 1;

        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] > 0) {
                ans[posIdx] =  arr[i];
                posIdx += 2;
            } else {
                ans[negIdx] = arr[i];
                negIdx += 2;
            }
        }

        return ans;

    }
    


     public static int[] rearrangeElementsV2(int[] arr) {

        
     }
}
