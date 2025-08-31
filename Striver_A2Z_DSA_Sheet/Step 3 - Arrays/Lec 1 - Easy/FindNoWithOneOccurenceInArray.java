
import java.util.*;

public class FindNoWithOneOccurenceInArray {
    
    public static void main(String[] args) {
        int[] arr = {4,1,2,1,2};

        int resBrute = findOccurenceBrute(arr);
        int resBetter1 = findOccurenceBetter1(arr);
        int resBetter2 = findOccurenceBetter2(arr);
        int resOptimal = findOccurenceOptimal(arr);

        System.out.println(resBrute);
        System.out.println(resBetter1);
        System.out.println(resBetter2);
        System.out.println(resOptimal);
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: For every element in the array, we will do a linear search & count the occurrence. If for any element, the count is 1, we will return it.
    public static int findOccurenceBrute(int[] arr) {

        for(int i = 0 ; i < arr.length ; i ++) {
            int count = 1;
            for(int j = i + 1 ; j < arr.length ; j ++){
                if(arr[j] == arr[i]){
                    count++;
                }
            }

            if(count == 1){
                return arr[i];
            }
        }

        return -1;

    }

    // TC: O(N) + O(N) + O(N) ~ O(N)
    // SC: O(maxNum + 1)
    // Approach: in this we'll add the fre  q of each element in the freq array on it's corresponding index value and then check for element with freq = 1.
    public static int findOccurenceBetter1(int[] arr) {
        int maxNum = Integer.MIN_VALUE;

        for(int i = 0 ; i < arr.length ; i ++) {
            maxNum = Math.max(maxNum, arr[i]);
        }

        int[] freq = new int[maxNum + 1];

        for(int i = 0 ; i < arr.length ; i ++) {
            freq[arr[i]]++;
        }
        

        for(int i = 0 ; i < freq.length ; i ++) {
            if(freq[i] == 1){
                return i;
            }

        }

        return -1;
    }

    // TC: O(N) + O(M) -> M = size of map, N = size of array
    // SC: O(M) -> M = (N/2)+1 (because there is one element with single occurence and rest elements with double occurence)
    // Approach: putting count of each element in map and the traversing the map to find element with 1 count.
    public static int findOccurenceBetter2(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i ++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for(int key: map.keySet()){
            if(map.get(key) == 1) {
                return key;
            }
        }

        return -1;
    }


    // TC: O(N)
    // SC: O(1)
    // Approach: Using XOR operator. (a ^ a = 0) and (0 ^ a = a). So all the duplicate elements will xor to 0 which will then xor with distinct element to give the itself as output. 
    public static int findOccurenceOptimal(int[] arr) {

        int xor = 0;

        for(int i = 0 ; i < arr.length ; i ++){
            xor ^= arr[i];
        }

        return xor;
        
    }

}
