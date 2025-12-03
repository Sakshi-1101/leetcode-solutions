import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> ansBrute = threeSumBrute(arr);
        List<List<Integer>> ansBetter = threeSumBetter(arr);
        List<List<Integer>> ansOptimal = threeSumOptimal(arr);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^3 + log(no. of unique triplets)) -> N^3 for 3 loops + log() of adding triplets to set
           /* NOTE: TC of sorting is not considered since every time we are sorting only 3 elements. */
    // SC: O(2 * no. of the unique triplets) -> O(no. of unique triplets) for set + O(no. of unique triplets) for final list
    // Approach: In this approach, we will use three nested loops to find all triplets that sum to zero.
    public static List<List<Integer>> threeSumBrute(int[] arr) {
        Set<List<Integer>> set = new HashSet<>();

        // run three loops to traverse through the array to find triplets
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i + 1 ; j < arr.length ; j ++) {
                for(int k = j + 1 ; k < arr.length ; k ++) {
                    // calculate sum
                    int sum = arr[i] + arr[j] + arr[k];

                    if(sum == 0) {
                        List<Integer> temp = new ArrayList<>();
                        Collections.addAll(temp, arr[i], arr[j], arr[k]);
                        Collections.sort(temp); // sort the elements in the list

                        // add the list to the set.
                        /*
                            NOTE: If we get two lists A and B that are equal and list A is already present in the set.
                                  Then the set will not add the list B because it will recognise list B as duplicate and will not add it.
                                  This is why we used set so handle that the final list doesn't contain any duplicates.
                         */
                        set.add(temp);
                    }
                }
            }
        }

        // convert set to arraylist
        return new ArrayList<>(set);

    }

    // TC: O(N^2 + log(no. of unique triplets)) -> N^ 2for 2 loops + log() of adding triplets to set
           /* NOTE: TC of sorting is not considered since every time we are sorting only 3 elements. */
    // SC: O(2 * no. of the unique triplets) -> O(no. of unique triplets) for set + O(no. of unique triplets) for final list
    // Approach: In this approach, we will use two loops to find the two elements and search for the third element or remaining sum in the set.
    public static List<List<Integer>> threeSumBetter(int[] arr) {
        // created a set to store non duplicate triplets since set only stores unique values
        Set<List<Integer>> ans = new HashSet<>();

        // First loop for first element
        for(int i = 0 ; i < arr.length ; i ++) {
            Set<Integer> set = new HashSet<>(); // created a set to store elements seen so far for the current first element

             // Second loop for second element
            for(int j = i + 1 ; j < arr.length ; j ++) {
                int sum = arr[i] + arr[j];
                int remSum = 0 - sum; // Calculate third element needed

                // check if the third element already present in the set, if yes found the triplet
                if(set.contains(remSum)) {
                    List<Integer> temp = new ArrayList<>();
                    Collections.addAll(temp, arr[i], arr[j], remSum);
                    Collections.sort(temp);
                    ans.add(temp); // add the triplet set to the final set
                }

                // Add current element to set
                set.add(arr[j]);

            }
        }
        
        // Convert set to list
        return new ArrayList<>(ans);

    }
    
    // TC: O(NlogN)+O(N^2) -> NlogN for sorting + N^2 for two loops with two pointers
    // SC: O(no. of triplets) -> for the final answer list
    // Approach: In this approach, we will sort the array and use two pointers to find the triplets. To handle duplicates, we will skip the duplicate elements.
    public static List<List<Integer>> threeSumOptimal(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();

        // sort the array
        Arrays.sort(arr);

        // First loop for first element
        for(int i = 0 ; i < arr.length ; i ++) {

            // if the current elements are equal to the next elements, skip them to avoid duplicates
            // Skip duplicates for first element
            if(i== 0 || (i > 0 && arr[i] != arr[i - 1])) {

                // Two pointers
                int j = i + 1;
                int k = arr.length - 1;

                // Find pairs for current arr[i] to get the triplets
                while(j < k) {
                    int sum = arr[i] + arr[j] + arr[k];

                    if(sum < 0) {
                        j++;
                    } else if (sum > 0) {
                        k--;
                    } else {
                        List<Integer> list = Arrays.asList(arr[i], arr[j], arr[k]);
                        ans.add(list);

                        j++;
                        k--;

                        // if the current elements are equal to the next elements, skip them to avoid duplicates
                        // Skip duplicates for left
                        while(j < k && arr[j] == arr[j + 1]) {
                            j++;
                        }
                        
                        // if the current elements are equal to the next elements, skip them to avoid duplicates
                        // Skip duplicates for left
                        while(j < k && arr[k] == arr[k - 1]) {
                            k--;
                        }
                    }
                }
            }
        }

        return ans;

    }
        
}
