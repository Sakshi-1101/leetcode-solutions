class Solution {
    public int subarraySum(int[] arr, int k) {

         //Map to store frequency of prefix sums
        HashMap<Integer, Integer> map = new HashMap<>();

        // base case: prefix sum=0 has occurred once before starting the array traversal bcoz empty subarray is also a subarray for every array
        map.put(0, 1);

        // Initialize prefix sum and count of subarrays
        int prefixSum = 0;
        int count = 0;
        
        for(int i = 0 ; i < arr.length ; i ++) {
            prefixSum += arr[i];  // Add current element to prefix sum
            
            // Calculate the remaining sum bcoz if there exists a prefix sum equal to remSum, then the subarray between that prefix sum and current prefix sum will have sum equal to k
            int remSum = prefixSum - k; 

            // If remSum exists in map, that means there exists a subarray with sum equal to k
            if(map.containsKey(remSum)){
                count += map.get(remSum); // Increment count by the frequency of remSum
            }

            // Add/updatpe the current prefix sum in the map
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
        
    }
}
