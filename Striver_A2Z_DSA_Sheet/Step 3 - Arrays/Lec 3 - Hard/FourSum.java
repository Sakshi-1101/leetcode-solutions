import java.util.*;

public class FourSum {

    public static void main(String[] args) {
        int[] arr = {1, 0, -1, 0, -2, 2};

        List<List<Integer>> ansBrute = fourSumBrute(arr);
        List<List<Integer>> ansBetter = fourSumBetter(arr);
        List<List<Integer>> ansOptimal = fourSumOptimal(arr);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^4 + log(no. of unique quadruplets)) -> N^4 for 4 loops + log() of adding quadruplets to set
           /* NOTE: TC of sorting is not considered since every time we are sorting only 4 elements. */
    // SC: O(2 * no. of the unique quadruplets) -> O(no. of unique quadruplets) for set + O(no. of unique quadruplets) for final list
    // Approach: In this approach, we will use four nested loops to find all quadruplets that sum to zero.
    public static List<List<Integer>> fourSumBrute(int[] arr) {
        Set<List<Integer>> set = new HashSet<>();

        // run four loops to traverse through the array to find quadruplets
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {
                for(int k = j + 1 ; k < arr.length ; k ++) {
                    for(int l = k + 1 ; l < arr.length ; l ++) {
                        int sum = arr[i] + arr[j] + arr[k] + arr[l];

                        // if sum is zero, create a list of the four elements and add it to the set
                        if(sum == 0) {
                            List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k], arr[l]);
                            Collections.sort(temp);
                            set.add(temp); // adding list to set ensures no duplicate quadruplets are added as set only stores unique values
                        }
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }
    
    // TC: O(N^3 + log(no. of unique quadruplets)) -> N^3 for 3 loops + log() of adding quadruplets to set
           /* NOTE: TC of sorting is not considered since every time we are sorting only 4 elements. */
    // SC: O(2 * no. of the quadruplets)+O(N) -> as we are using a set data structure and a list to store the quads. 
    //      This results in the first term. And the second space is taken by the set data structure we are using to store the 
    //      array elements. At most, the set can contain approximately all the array elements and so the space complexity is O(N).
    // Approach: In this approach, we will use three nested loops to find three elements and search for the fourth element or remaining sum in a set.
    public static List<List<Integer>> fourSumBetter(int[] arr) {
        Set<List<Integer>> set = new HashSet<>();

        // run three loops to traverse through the array to find first three elements
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {

                // create a temporary set to store elements for finding the fourth element for this pair for each (i, j)
                Set<Integer> tempSet = new HashSet<>();

                // loop for 3rd element
                for(int k = j + 1 ; k < arr.length ; k ++) {

                    int sum = arr[i] + arr[j] + arr[k];
                    int remSum = 0 - sum; // remaining sum is basically the fourth element we are looking for

                    // check if the remaining sum is present in the temp set
                    if(tempSet.contains(remSum)) {
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k], remSum);
                        Collections.sort(temp);
                        set.add(temp); // add list in set to avoid duplicates
                    }

                    // add the current element to the temp set
                    tempSet.add(arr[k]);
                }
            }
        }

        // convert set to arraylist
        return new ArrayList<>(set);

    }

    // TC: O(N^3) -> N^2 for two loops + N for two pointer approach
           /* NOTE: TC of sorting is O(N log N) but not considered here as it is dominated by O(N^3) */
    // SC: O(no. of the quadruplets) -> as we are using a list to store the quads.
    // Approach: In this approach, we will use two nested loops to find two elements and use the two-pointer technique to find the other two elements.
    public static List<List<Integer>> fourSumOptimal(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();

        // sort the array to apply two pointer approach
        Arrays.sort(arr);

        // run two loops to traverse through the array to find first two elements
        for(int i = 0 ; i < arr.length ; i ++) {

            // skip duplicates for the first element
            if(i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            // second loop for the second element
            for(int j = i + 1 ; j < arr.length ; j ++) {

                // skip duplicates for the second element
                if(j > i + 1 && arr[j] == arr[j - 1]){
                    continue;
                }

                // now apply two pointer approach for the remaining two elements
                int lo = j + 1;
                int hi = arr.length - 1;

                while(lo < hi) {
                    // calculate the sum of the four elements
                    int sum = arr[i] + arr[j] + arr[lo] + arr[hi];
                    
                    if(sum < 0){
                        lo++;
                    } else if(sum > 0){
                        hi--;
                    }else {
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[lo], arr[hi]);
                        Collections.sort(temp);
                        ans.add(temp);
                    
                        // skip duplicates for the third element
                        while(lo < hi && arr[lo] == arr[lo + 1]) {
                            lo++;
                        }

                        // skip duplicates for the fourth element
                        while(lo < hi && arr[hi] == arr[hi - 1]) {
                            hi--;
                        }

                        // we are doing this after the while loops skip duplicates bcoz if we move lo and hi before skipping duplicates. 
                        // That makes the duplicate-skip checks compare the wrong indices and allows the same quadruplet to be added multiple times. 
                        // Hence, to fix this When you find a matching quadruplet, skip duplicates for lo and hi first (comparing 
                        // current value to next/previous), then advance lo and hi.
                        /* 
                            Test case: [-2,-1,-1,1,1,2,2] where it failed when I added the below two line before while loops.
                                       Output when added before while loops: [[-2,-1,1,2]] ❌
                                       Output when added after while loops: [[-2,-1,1,2],[-1,-1,1,1]]  ✅
                        */ 
                        lo++;
                        hi--;
                    }
                }
            }
        }

        return ans;

    }
}
