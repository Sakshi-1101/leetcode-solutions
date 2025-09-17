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

                rotateRight(arr, i, j); // bring arr[j] to position i
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

                rotateRight(arr, i, j); // bring arr[j] to position i
            }

            i++;
        }

        return arr;
    }

    // Rotate the subarray [i...j] right by one
    /*
     * rotate() is used to fix a misplaced element (wrong sign at index i) by bringing the next correctly-signed element (at index j) forward. 
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
    

    // TC: O(2N) -> This is the worst case complexity which is a combination of O(N) of traversing the array to segregate into neg and pos array and O(N) for adding the elements alternatively to the main array
    // SC: O(N/2 + N/2) = O(N) -> N/2 space required for each of the positive and negative element arrays, where N = size of the array A
    // Approach: In this approach, we first segregate the positive and negative elements into two separate lists. 
                // Then, we fill the original array by alternating between elements from the positive and negative lists. 
                // If one list is exhausted before the other, we append the remaining elements from the longer list to the end of the array.
    public static int[] rearrangeElementsV2(int[] arr) {
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        // segregate positive and negative elements
        for(int i = 0 ; i < arr.length ; i ++) {
            if(arr[i] > 0) {
                pos.add(arr[i]);
            } else {
                neg.add(arr[i]);
            }
        }

        // if the number of positive elements is less than the number of negative elements
        if(pos.size() < neg.size()){

            // first add equal number of positive and negative elements alternatively until one of the lists is exhausted
            for(int i = 0 ; i < pos.size() ; i ++) {
                arr[2 * i] = pos.get(i);
                arr[2 * i + 1] = neg.get(i);
            }

            /*
             * At this point, all positives have been paired with negatives alternately.
             * Example: pos.size() = 2 → placed like [pos0, neg0, pos1, neg1].
             * So we've already filled 2 * pos.size() slots in the array. Hence we'll start filling from index 2 * pos.size()
             */
            int idx = 2 * pos.size();

            /*
             * Start from pos.size() since the first 'pos.size()' negatives are already used in alternation.  
             * Continue until neg.size() to place all remaining negatives sequentially.  
             */
            for(int i = pos.size() ; i < neg.size() ; i ++) { // add the remaining negative elements
                arr[idx] = neg.get(i);
                idx++;
            }

        } else { // if the number of negative elements is less than the number of positive elements

            // first add equal number of positive and negative elements alternatively until one of the lists is exhausted
            for(int i = 0 ; i < neg.size() ; i ++) {
                arr[2 * i] = pos.get(i);
                arr[2 * i + 1] = neg.get(i);
            }

            /*
             * At this point, all negatives have been paired with positives alternately.
             * Example: neg.size() = 2 → placed like [pos0, neg0, pos1, neg1].
             * So we've already filled 2 * neg.size() slots in the array. Hence we'll start filling from index 2 * neg.size()
             */
            int idx = 2 * neg.size();

            /*
             * Start from neg.size() since the first 'neg.size()' positives are already used in alternation.  
             * Continue until pos.size() to place all remaining positives sequentially.  
             */
            for(int i = neg.size() ; i < pos.size() ; i ++) { // add the remaining positive elements
                arr[idx] = pos.get(i);
                idx++;
            }
        }
        
        return arr;
        
     }
}
