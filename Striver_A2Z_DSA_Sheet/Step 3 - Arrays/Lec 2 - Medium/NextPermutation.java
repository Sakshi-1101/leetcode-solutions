import java.util.ArrayList;
import java.util.Comparator;

public class NextPermutation {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2};

        // ArrayList<Integer> ansBrute = nextPermutationBrute(arr);
        int[] ansOptimal = nextPermutationOptimal(arr);

        // System.out.println(ansBrute);
        
        for(int i = 0 ; i < ansOptimal.length ; i ++) {
            System.out.print(ansOptimal[i] + " ");
        }
    }

    // TC: O(N! * N)
    // SC: O(N!)
    // Approach: In this we'll generate all ther permutations and then match it with the input array and return the next permutation
    public static ArrayList<Integer> nextPermutationBrute(int[] arr) {
        ArrayList<Integer> input = new ArrayList<>();

        for(int key: arr){
            input.add(key);
        }

        //generate all the permutations
        /*
         * NOTE: These functions will return permutations in random order, so we need to sort the permutations in lexographical order to 
         *       get correct next permutation.
         */
        ArrayList<ArrayList<Integer>> permutationsBrute = findAllPermutationsBrute(arr);
        ArrayList<ArrayList<Integer>> permutationsOptimal = findAllPermutationsOptimal(arr);

        // sorting the arraylist
         permutationsBrute.sort((a, b) -> {
            int n = a.size();
            for (int i = 0; i < n; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });

        // sorting the arraylist
        permutationsOptimal.sort((a, b) -> {
            int n = a.size();
            for (int i = 0; i < n; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });


        for(int i = 0 ; i < permutationsBrute.size() ; i ++) {
            if(permutationsBrute.get(i).equals(input)){
                return permutationsBrute.get(i + 1);
            }
        }

        for(int i = 0 ; i < permutationsOptimal.size() ; i ++) {
            if(permutationsOptimal.get(i).equals(input)){
                return permutationsOptimal.get(i + 1);
            }
        }

        return new ArrayList<>();

    }

    // TC: O(N! * N) -> O(N) for traversing the array and O(N!) is the no. of permutations generated
    // SC: O(N!) + O(N) -> visited array + final ans array (Auxiliary stack space used by Recursion = O(N))
    // Approach: Using recursion, we'll traverse each element of the array and generate all the permutations by including each element in the current permutation if it is not already included
    public static ArrayList<ArrayList<Integer>> findAllPermutationsBrute(int[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(); // this will store all the permutations
        boolean[] visited = new boolean[arr.length]; // this will keep track of the elements which are already included in the current permutation
        ArrayList<Integer> permutation = new ArrayList<>(); // this will store the current permutation

        permuteBrute(arr, permutation, visited, ans);

        return ans;
    }

    public static void permuteBrute(int[] arr, ArrayList<Integer> permutation, boolean[] visited, ArrayList<ArrayList<Integer>> ans) {
        // base case: if the size of permutation is equal to the size of arr
        if(permutation.size() == arr.length) {
            ans.add(new ArrayList<>(permutation)); // add a new copy of permutation to ans where permutation array will have the current permutation
            return;
        }

        // we'll traverse the array and for each element, if it is not visited, we'll mark it as visited and add it to the permutation array
        // then we'll call the function recursively after that, we'll backtrack by marking the element as unvisited and removing it from the permutation array
        for(int i = 0 ; i < arr.length ; i ++) {
            int val = arr[i];

            // check if the element is not visited
            if(!visited[i]){
                visited[i] = true;
                permutation.add(arr[i]); // add the element to the permutation array

                // call recursion to generate the next element of the permutation
                permuteBrute(arr, permutation, visited, ans); 

                // once we have generated the permutation, we need to backtrack by marking the element as unvisited and removing it from the permutation array so that we can generate the next permutation
                visited[i] = false;
                permutation.remove(permutation.size() - 1); // remove the last element from the permutation array

            }
        }
    }

    // TC: O(N! * N)
    // SC: O(N!) -> for ans araylist (Auxiliary stack space used by Recursion = O(N))
    // Approach: in this approach, we'll swap the elements in the array itself to generate the permutations.
    public static ArrayList<ArrayList<Integer>> findAllPermutationsOptimal(int[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        permuteOptimal(arr, 0, ans);

        return ans;
    }

    public static void permuteOptimal(int[] arr, int idx, ArrayList<ArrayList<Integer>> ans) {
        // base case: if the pointer idx goes out of bound, we'll store the current permutation
        if(idx == arr.length) {
            ArrayList<Integer> permutation = new ArrayList<>();
            
            for(int i = 0 ; i < arr.length ; i ++) {
                permutation.add(arr[i]);
            }

            ans.add(new ArrayList<>(permutation));
            return;
        }

        // we'll swap each element with the element at the pointer idx and call the function recursively with idx + 1
        // after that, we'll backtrack by swapping the elements again to revert the swap
        for(int i = idx ; i < arr.length ; i ++) {
            swap(arr, i, idx); // idx is the pointer on the current element for which we are finding permutations
            permuteOptimal(arr, idx + 1, ans);
            swap(arr, i, idx); // reverting the swap
        }
    }

    public static void swap(int[] arr, int i, int idx) {
        int temp = arr[i];
        arr[i] = arr[idx];
        arr[idx] = temp;
    }

    public static int[] nextPermutationOptimal(int[] arr) {
        int idx = -1; // breakpoint

        // find the breakpoint -> a point till where there value is inreasing ang post this it drops.
        for(int i = arr.length - 2 ; i >= 0 ; i --) {
            if(arr[i] < arr[i + 1]){
                idx = i;
                break;
            }
        }

        // edge case: [5,4,3,2,1] next permutation is [1,2,3,4,5]
        if(idx == -1) {
            reverse(arr, 0, arr.length - 1);
            return arr;
        }

        // find the next largest element for the breakpoint element
        for(int i = arr.length - 1 ; i > idx ; i --) {
            if(arr[i] > arr[idx]) {
                swap(arr, i, idx);
                break;
            }
        }

        // reverse the array betwee [idx + 1 .... end] to get the smallest rearrangement. 
        // Also as we know all the elements after breakpoint idx are in the desc order starting from idx we can reverse it to make it in asc order and get the samllest rearrangement.
        reverse(arr, idx + 1, arr.length - 1);

        return arr;
        
    }

    public static void reverse(int[] arr, int i , int idx) {
        int j = i;
        int k = idx;

        while(j < k) {
            int temp = arr[j];
            arr[j] = arr[k];
            arr[k] = temp;

            j++;
            k--;
        }
    }
    
}
