import java.util.ArrayList;
import java.util.Collections;

public class LeadersInAnArray {
    
    /* A Leader is an element that is greater than all of the elements on its right side in the array. */
    public static void main(String args[]) {
        int[] arr = {10, 22, 12, 3, 0, 6};

        ArrayList<Integer> ansBrute = leadersBrute(arr);
        ArrayList<Integer> ansOptimal = leadersOptimal(arr);

        for(int key: ansBrute) {
            System.out.print(key + " ");
        }

        System.out.println();

        for(int key: ansOptimal) {
            System.out.print(key + " ");
        }
    }

    // TC: O(N^2)
    // SC: O(N)
    // Approach: For each element, we'll check if there is any element greater than it on its right side.
    public static ArrayList<Integer> leadersBrute(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i < arr.length ; i ++) {

            boolean leader = true; // assuming current element is leader

            for(int j = i + 1 ; j < arr.length ; j ++) {
                if(arr[j] > arr[i]) {
                    leader = false; // if at any point, we find an element greater than current element, then it is not a leader
                    break;
                }
            }

            // If current element is still a leader, add it to the ans arraylist
            if(leader) {
                ans.add(arr[i]);
            }
        }

        return ans;
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this we'll traverse the array from right to left, and keep track of the current leader so far.
    public static ArrayList<Integer> leadersOptimal(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();

        // last element or the rightmost element is always a leader, hence add it to the ans arraylist
        ans.add(arr[arr.length - 1]);

        //set the last element as the current leader
        int currLeader = arr[arr.length - 1];
        
        // traverse the array from right to left and check if the current element is greater than the current leader
        // if yes, then add it to the ans arraylist and update the current leader
        // if no, then move to the next element
       for(int i = arr.length - 2 ; i >= 0 ; i --) {
            if(arr[i] > currLeader) {
                ans.add(arr[i]);
                currLeader = arr[i];
            }
       }

       // OPTIONAL STEP: finally, sort the ans arraylist in descending order before returning it to match the expected output
       Collections.sort(ans, Collections.reverseOrder()); 
       return ans;
    }
}
