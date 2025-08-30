
import java.util.ArrayList;
import java.util.HashSet;

public class UnionOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4};
        int[] arr2 = {2, 3, 5, 6};

        ArrayList<Integer> resultBrute = unionOfTwoSortedArraysBruteApproach(arr1, arr2);
        ArrayList<Integer> resultOptimal = unionOfTwoSortedArraysOptimalApproach(arr1, arr2);

        System.out.println(resultBrute);
        System.out.println(resultOptimal);
    }

    // TC: O(N + M)log(N + M)
    // SC: O(N + M)
    public static ArrayList<Integer> unionOfTwoSortedArraysBruteApproach(int[] arr1, int[] arr2) {
        ArrayList<Integer> ans = new ArrayList<>();

        /*
         * HashSet time complexity behaviour:
         * Average Case: add(), remove(), contains() are O(1) in HashSet.
         * Worst Case (Java 8+): contains() can be O(log n) due to treeification of buckets in case of high collisions.
         * Worst Case (Java 7-): contains() could be O(n) due to linked list traversal in case of high collisions.
         */
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0 ; i < arr1.length ; i ++){
            set.add(arr1[i]);
        }

        for(int i = 0 ; i < arr2.length ; i ++){
            set.add(arr2[i]);
        }

        for(int key: set) {
            ans.add(key);
        }

        return ans;
    }


    // TC: O(N + M)
    // SC: O(N + M)
    public static ArrayList<Integer> unionOfTwoSortedArraysOptimalApproach(int[] arr1, int[] arr2) {
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < arr1.length && j < arr2.length) {
            //arr1[i] < arr2[j] -> we need to insert arr1[i] in ans.If last element in ans is not equal to arr1[i],then insert in ans else don’t insert. After checking Increment i.
           // arr1[i] = arr2[j] -> when we found a common element, so insert only one element in the ans. insert arr1[i] in ans and increment i.
            if(arr1[i] <= arr2[j]){
                // insert only when last element in ans is not equal to arr1[i] and if ans is empty then also insert
                if(ans.size() == 0 || ans.get(ans.size() - 1) != arr1[i]){
                    ans.add(arr1[i]);
                }
                i++;
            } else { // (arr1[i] > arr2[j]) -> we need to insert arr2[j] in ans.If the last element in the ans is not equal to arr2[j], then insert in the ans, else don’t insert. After checking Increment j
                // insert only when last element in ans is not equal to arr2[j] and if ans is empty then also insert
                if(ans.size() == 0 || ans.get(ans.size() - 1) != arr2[j]){
                    ans.add(arr2[j]);
                }

                j++;
            }
        }

        // If there are remaining elements in arr1
        while(i < arr1.length) {
            // insert only when last element in ans is not equal to arr1[i] and if ans is empty then also insert
            if(ans.size() == 0 || ans.get(ans.size() - 1) != arr1[i]){
                ans.add(arr1[i]);
            }

            i++;
        }

        // If there are remaining elements in arr2
        while(j < arr2.length) {
            // insert only when last element in ans is not equal to arr1[i] and if ans is empty then also insert
            if(ans.size() == 0 || ans.get(ans.size() - 1) != arr2[j]){
                ans.add(arr2[j]);
            }

            j++;
        }

        return ans;
    }
}
