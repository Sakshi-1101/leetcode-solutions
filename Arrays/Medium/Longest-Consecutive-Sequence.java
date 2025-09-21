class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length <= 1) {
            return nums.length;
        }

        HashSet<Integer> set = new HashSet<>();

        // adding all the elements to the hashset
        for(int key: nums) {
            set.add(key);
        }

        int len = 1;

        for(int ele: set) {
            // check if the ele is the starting number i.e. ele - 1 doesn't exist
            if(!set.contains(ele - 1)) {
                int count = 1; // count the first element i.e. starting number of potential subsequence
                int startEle = ele;

                // check for the next consecutive elements in the hashset and loop till we don't find the next element
                // if found, increment the count and update the startEle to the next element
                while(set.contains(startEle + 1)) {
                    startEle += 1;
                    count++;
                }

                len = Math.max(len, count);
            }
        }

        return len;
        
    }
}