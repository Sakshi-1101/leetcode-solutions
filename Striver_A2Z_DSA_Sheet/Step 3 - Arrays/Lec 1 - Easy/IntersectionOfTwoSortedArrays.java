import java.util.ArrayList;

public class IntersectionOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4};
        int[] arr2 = {2, 3, 5, 6};

        ArrayList<Integer> resultBrute = intersectionofTwoSortedArraysBruteApproach(arr1, arr2);
        ArrayList<Integer> resultBetter = intersectionofTwoSortedArraysBetterApproach(arr1, arr2);
        ArrayList<Integer> resultOptimal = intersectionofTwoSortedArraysOptimalApproach(arr1, arr2);

        System.out.println(resultBrute);
        System.out.println(resultBrute);
        System.out.println(resultBrute);
        System.out.println(resultBrute);
        System.out.println(resultBrute);
        System.out.println(resultBetter);
        System.out.println(resultOptimal);
    }

    // TC: O(N * M)
    // SC: O(N + M)
    // Approach: In this approach we will maintain a visited array to keep a track of the element we have already included in the output array.
    public static ArrayList<Integer> intersectionofTwoSortedArraysBruteApproach(int[] arr1, int[] arr2) {
        ArrayList<Integer> ans = new ArrayList<>();

        int[] visited = new int[Math.max(arr1[arr1.length - 1], arr2[arr2.length - 1]) + 1];

        // for each element in arr1, check if it exists in arr2
        for(int i = 0 ; i < arr1.length ; i ++){
            for(int j = 0 ; j < arr2.length ; j ++){
                if(arr1[i] == arr2[j] && visited[arr1[i]] == 0){
                    ans.add(arr1[i]);
                    visited[arr1[i]] = 1;
                }

                if (arr2[j] > arr1[i]) {
                    break; // Since arrays are sorted, no need to check further
                }
            }
        }

        return ans;
    }

    // TC: O(N + M)
    // SC: O(N + M)
    // Approach: In this approach, we use a boolean array to keep track of visited elements.
    public static ArrayList<Integer> intersectionofTwoSortedArraysBetterApproach(int[] arr1, int[] arr2) {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[Math.max(arr1[arr1.length - 1], arr2[arr2.length - 1]) + 1];

        int i = 0;
        int j = 0;

        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] == arr2[j] && visited[arr1[i]] == false) {
                ans.add(arr1[i]);
                visited[arr1[i]] = true;
                i++;
                j++;
            } else if(arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return ans;
    }

    // TC: O(N + M)
    // SC: O(N + M)
    // Approach: In this approach, we use two pointers to find the intersection.
    public static ArrayList<Integer> intersectionofTwoSortedArraysOptimalApproach(int[] arr1, int[] arr2) {
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < arr1.length && j < arr2.length) {
            if(arr1[i] < arr2[j]) {
                i++;
            } else if(arr1[i] > arr2[j]) {
                j++;
            } else {
                if(ans.size() == 0 || ans.get(ans.size() - 1) != arr1[i]) {
                    ans.add(arr1[i]);
                }

                i++;
            }
        }

        return ans;
    }

}

