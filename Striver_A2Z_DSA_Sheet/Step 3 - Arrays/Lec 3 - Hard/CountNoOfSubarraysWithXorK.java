import java.util.HashMap;

public class CountNoOfSubarraysWithXorK {

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        int k = 6;

        int ansBrute = countSubarraysBrute(arr, k);
        int ansBetter = countSubarraysBetter(arr, k);
        int ansOptimal = countSubarraysOptimal(arr, k);


        System.out.println(ansBrute);
        System.out.println(ansBetter);
        System.out.println(ansOptimal);
    }

    // TC: O(N^3)
    // SC: O(1)
    // Approach: In this we'll generate all subarrays using three nested loops and for each subarray,we'll calculate the count of subarrays whose xor is k.
    public static int countSubarraysBrute(int[] arr, int target) {
        int count = 0;

        // generate all subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            for(int j = i ; j < arr.length ; j ++) {
                int finalXor = 0; // initialize the xor value to 0 because 0 ^ a = a
                for(int k = i ; k <= j ; k ++) {
                    finalXor ^= arr[k]; // calculate xor for the subarray
                }
                
                // check if finalXor is equal to target, increment count
                if(finalXor == target) {
                    count++ ;
                }

            }
        }

        return count;
    }

    // TC: O(N^2)
    // SC: O(1)
    // Approach: In this we'll generate all subarrays using two nested loops and for each subarray,we'll maintain a running xor to calculate the count of subarrays whose xor is k.
    public static int countSubarraysBetter(int[] arr, int target) {
        int count = 0;

        // generate all subarrays
        for(int i = 0 ; i < arr.length ; i ++) {
            int finalXor = 0; // initialize the xor value to 0 because 0 ^ a = a
            for(int j = i ; j < arr.length ; j ++) {

                finalXor ^= arr[j]; // running xor

                // check if finalXor is equal to target, increment count
                if(finalXor == target) {
                    count++ ;
                }

            }
        }

        return count;
    }
    
    // TC: O(N * logN) -> due to hashmap operations
    // SC: O(N) -> we are using a hashmap to store prefix xors and their counts
    // Approach: In this approach, we'll maintain a hashmap in which we'll store the prefix xor and it's count of occurrences.
    //           At each index, we'll calculate the prefix xor. Then, we'll calculate the required remaining xor (remXor) 
    //           needed to achieve the target by performing XOR between the current prefix xor and the target.
    //           If remXor exists in the hashmap, it means there are subarrays ending at the current index that have the 
    //           desired xor of k. We'll add the count of such subarrays to our total count.
    /*  XOR Properties Used: 
        1. a ^ a = 0
        2. a ^ 0 = a
        3. a ^ b = c implies a = b ^ c and b = a ^ c
    */
    public static int countSubarraysOptimal(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // initialize prefixXor and count
        int prefixXor = 0;
        int count = 0;

        // initially put prefixXor 0 with count 1 in map bcoz 0 ^ a = a
        // this will also help in a base case where the prefixXor itself is equal to target
        map.put(prefixXor, 1);

        // traverse the array
        for(int i = 0 ; i < arr.length ; i ++) {
            prefixXor ^= arr[i]; // calculate prefix xor

            // calculate the required remaining xor to achieve target
            int remXor = prefixXor ^ target;

            // if remXor is present in map, add its count to total count
            if(map.containsKey(remXor)) {
                count += map.get(remXor);
            }

            // add or update the count of current prefixXor in map
            map.put(prefixXor, map.getOrDefault(prefixXor, 0) + 1);
        }

        return count;

    }
}
