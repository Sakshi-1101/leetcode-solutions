class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int majEle = 0;

        //apply the moore's algo
        for(int i = 0 ; i < nums.length ; i ++) {
            if(count == 0) { // checking the new section of array, reset majEle to current element and count to 1
                count = 1;
                majEle = nums[i];
            } else if(majEle == nums[i]) { 
                count++;
            } else { // majEle != arr[i]
                count --;
            }
        }

        return majEle;
    }
}