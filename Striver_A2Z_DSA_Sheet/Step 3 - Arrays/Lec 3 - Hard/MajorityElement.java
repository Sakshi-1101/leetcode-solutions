import java.util.*;

public class MajorityElement {

    public static void main(String[] args) {
        
        // int[] arr = {1, 2, 1, 1, 3, 2, 2};
        int[] arr = {1,1,1,1,3,2,2,2};

        // MAJORITY ELEMENT - N/3 TIMES
        List<Integer> ansBrute = findMajElementBrute(arr);
        List<Integer> ansBetter = findMajElementBetter(arr);
        List<Integer> ansOptimal = findMajElementOptimal(arr);

        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^2)
    // SC: O(1) -> as we are using a constant amount of space for the result array, which can hold at most 2 elements.
    // Approach: We will use two nested loops to count the occurrences of each element in the array.
    public static List<Integer> findMajElementBrute(int[] arr) {
        List<Integer> ans = new ArrayList<>();

        // traverse through the array
        for(int i = 0 ; i < arr.length ; i ++){
            int count =  0; // count for each element

            for(int j = 0 ; j < arr.length ; j ++) {
                // count occurrences of arr[i] only if it is not already in the ans list
                // or  if arr[i] is not equal to the first element in ans
                // or if ans list has less than 2 elements and arr[i] is not equal to the second element in ans
                if(ans.size() == 0 || ans.get(0) != arr[i]  && (ans.size() < 2 || ans.get(1) != arr[i])) {
                    if(arr[i] == arr[j]) {
                        count++;
                    }
                }
            }

            // check if count is greater than n/3
            if(count > (arr.length / 3)) {
                ans.add(arr[i]);
            }

            // if we have found two majority elements, no need to check further as there can be at most two such elements
           if(ans.size() == 2) {
                break;
           }
        }

        return ans;
    }

    // TC: O(N * log N) -> N for traversing the array and log N for map operations
    // SC: O(N) + O(1) ~ O(N) -> O(N) for map and O(1) for list 
    // Approach: Using HashMap to store the frequency of each element and then checking which elements have frequency greater than n/3
    public static List<Integer> findMajElementBetter(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        // traverse through the array
        for(int i = 0 ; i < arr.length ; i ++) {
            
            // update the frequency of each element in the map
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            // check if the frequency of the element is equal to (n/3) + 1
            /*
               NOTE: We are checking for (n/3) + 1 instead of > n/3 bcoz we want to add the ele to the list as soon as its frequency crosses n/3.
                     For eg: if arr[] = {1,1,1,1,3,2,2,2}, n = 8, so we want to add elements that have freq > 2. 
                             Now here i can see 1,2 have freq 4 and 3 respectively.
                             So when we are at index 2, the freq of 1 becomes 3 which is > 2, so we add it to the list at that point itself. 
                             But when we go to index 3, the freq of 1 becomes 4, due to which we would be adding 1 again to the list if we were checking for > n/3.
                             And we'll miss out on adding 2 to the list as we already have 2 elements in the list and we are breaking out of the loop on that condition.
             */
            if(map.get(arr[i]) == (arr.length / 3) + 1) {
                ans.add(arr[i]);
            }

            // if we have found two majority elements, no need to check further as there can be at most two such elements
            if(ans.size() == 2) {
                break;
            }
        }

        return ans;
    }

    // TC: O(2N)
    // SC: O(1)
    // Approach: Using Boyer–Moore majority-voting algorithm
    //           In this we will maintain two candidate elements and their counts. We will first find the two potential candidates 
    //           and then verify them. This works bcoz there can be at most two elements with frequency greater than n/3. So even
    //           if we eliminate pairs of different elements, we will still be left with the potential candidates.
    //           Finally we will verify these candidates by counting their occurrences in the array.
    public static List<Integer> findMajElementOptimal(int[] arr) {
        List<Integer> ans = new ArrayList<>();

        // initialising counts and elements
        int count1 = 0;
        int count2 = 0;
        int ele1 = Integer.MIN_VALUE;
        int ele2 = Integer.MIN_VALUE;

        // finding the two potential candidates
        for(int i = 0 ; i < arr.length ; i ++) {
            // assigning ele1 if count1 is 0 and arr[i] is not equal to ele2
            if(count1 == 0 && arr[i] != ele2) {
                count1 = 1;
                ele1 = arr[i];
            } else if(count2 == 0 && arr[i] != ele1) { // assigning ele2 if count2 is 0 and arr[i] is not equal to ele1
                count2 = 1;
                ele2 = arr[i];
            } else if(arr[i] == ele1) { // incrementing count1 if arr[i] is equal to ele1
                count1++;
            } else if(arr[i] == ele2) { // incrementing count2 if arr[i] is equal to ele2
                count2++;
            } else { // decrementing both counts if arr[i] is not equal to either ele1 or ele2
                count1--;
                count2--;
            }
        }

        // verifying the candidates if they are actually majority elements.
        // resetting counts
        count1 = 0;
        count2 = 0;

        // counting occurrences of ele1 and ele2
        for(int i = 0 ; i < arr.length ; i ++) {
            if(ele1 == arr[i]){
                count1++;
            }

            if(ele2 == arr[i]) {
                count2++;
            }
        }

        // checking if counts are greater than n/3 + 1. Added +1 bcoz we want to include the element as soon as its count crosses n/3
        if(count1 >= (arr.length / 3) + 1) {
            ans.add(ele1);
        }

        if(count2 >= (arr.length / 3) + 1 && ele1 != ele2) {
            ans.add(ele2);
        }

        return ans;

    }
    
}