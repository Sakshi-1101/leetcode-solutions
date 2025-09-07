import java.util.*;

public class TwoSum {

    public static void main(String[] args) {
        int[] arr = {2, 6, 5, 8, 11};
        int targetSum = 14;

        //variant 1 -> Return YES if there exist two numbers such that their sum is equal to the target. Otherwise, return NO.
        String ansV1Brute = twoSumV1Brute(arr, targetSum);
        String ansv1Better = twoSumV1Better(arr, targetSum);
        String ansV1Optimal = twoSumV1Optimal(arr, targetSum);

        System.out.println(ansV1Brute);
        System.out.println(ansv1Better);
        System.out.println(ansV1Optimal);

        //variant 2 -> Return indices of the two numbers such that their sum is equal to the target. Otherwise, we will return {-1, -1}.
        int[] ansV2Brute = twoSumV2Brute(arr, targetSum);

        for(int val: ansV2Brute) {
            System.out.print(val);
        }

        System.out.println();

        int[] ansv2Better = twoSumV2Better(arr, targetSum);
        
        for(int val: ansv2Better) {
            System.out.print(val);
        }

        System.out.println();

        int[] ansV2Optimal = twoSumV2Optimal(arr, targetSum);

        for(int val: ansV2Optimal) {
            System.out.print(val);
        }

        System.out.println();
        
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: for every element i, check that we have another element in the array such that the sum of both the elements is equal to the target.
    public static String twoSumV1Brute(int[] arr, int tar) {

        for(int i = 0 ; i < arr.length ; i ++){
            for(int j = i + 1 ; j < arr.length ; j ++) {
                int sum = arr[i] + arr[j];

                if(sum == tar){
                    return "YES";
                }
            }
        }

        return "NO";
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: Using Hashmap, we'll store each element in the map and check if the there an element equal to the remSum required to achieve the target.
    public static String twoSumV1Better(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i ++) {
            int sum = arr[i];
            int remSum = tar - sum;

            if(map.containsKey(remSum)) {
                return "YES";
            }

            map.put(arr[i], i);
        }

        return "NO";

    }

    // TC: O(N*logN) + O(N) -> nlogn for sorting the array and n for traversing the array
    // SC: 0(1) ->  as we are not using any extra space.
        /* 
        * Note: Here we are distorting the given array. So, if we need to consider this change, the space complexity will be O(N).
            * This implies that Sorting changes the input array (i.e., “distorts” it).
            * In algorithm analysis, we don’t normally count modifying the input as extra space — that’s just the algorithm working.
            * But if the problem statement requires the original order to be preserved, then you must make a copy first.
            * Copying the array would cost O(n) space.
            * Therefore, 
                * If modifying input is okay → O(1) space.
                * If not (must preserve array) → need an extra copy → O(n) space.
        */
    // Approach: sort the array and use two pointer technique to find the elements who sum to target.
    public static String twoSumV1Optimal(int[] arr, int tar) {
        Arrays.sort(arr);

        int i = 0;
        int j = arr.length - 1;

        while(i < j) {
            int sum = arr[i] + arr[j];

            if(sum < tar) {
                i++;
            } else if (sum > tar) {
                j--;
            } else {
                return "YES";
            }
        }

        return "NO";
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: for every element i, check that we have another element in the array such that the sum of both the elements is equal to the target.
    public static int[] twoSumV2Brute(int[] arr, int tar) {

        for(int i = 0 ; i < arr.length ; i ++){
            for(int j = i + 1 ; j < arr.length ; j ++) {
                int sum = arr[i] + arr[j];

                if(sum == tar){
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: Using Hashmap, we'll store each element in the map and check if the there an element equal to the remSum required to achieve the target.
    public static int[] twoSumV2Better(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i ++) {
            int sum = arr[i];
            int remSum = tar - sum;

            if(map.containsKey(remSum)) {
                return new int[]{i, map.get(remSum)};
            }

            map.put(arr[i], i);
        }

        return new int[]{-1, -1};

    }

    // TC: O(N*logN) + O(N)
    // SC: 0(1)
    // Approach: sort the array and use two pointer technique to find the elements who sum to target.
    public static int[] twoSumV2Optimal(int[] arr, int tar) {
        Arrays.sort(arr);

        int i = 0;
        int j = arr.length - 1;

        while(i < j) {
            int sum = arr[i] + arr[j];

            if(sum < tar) {
                i++;
            } else if (sum > tar) {
                j--;
            } else {
                return new int[]{i, j};
            }
        }

        return new int[]{-1, -1};
    }
    
    
}
