class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        // initialising counts and elements
        int count1 = 0;
        int count2 = 0;
        int ele1 = Integer.MIN_VALUE;
        int ele2 = Integer.MIN_VALUE;

        // finding the two potential candidates
        for(int i = 0 ; i < nums.length ; i ++) {
            // assigning ele1 if count1 is 0 and nums[i] is not equal to ele2
            if(count1 == 0 && nums[i] != ele2) {
                count1 = 1;
                ele1 = nums[i];
            } else if(count2 == 0 && nums[i] != ele1) { // assigning ele2 if count2 is 0 and nums[i] is not equal to ele1
                count2 = 1;
                ele2 = nums[i];
            } else if(nums[i] == ele1) { // incrementing count1 if nums[i] is equal to ele1
                count1++;
            } else if(nums[i] == ele2) { // incrementing count2 if nums[i] is equal to ele2
                count2++;
            } else { // decrementing both counts if nums[i] is not equal to either ele1 or ele2
                count1--;
                count2--;
            }
        }

        // verifying the candidates if they are actually majority elements.
        // resetting counts
        count1 = 0;
        count2 = 0;

        // counting occurrences of ele1 and ele2
        for(int i = 0 ; i < nums.length ; i ++) {
            if(ele1 == nums[i]){
                count1++;
            }

            if(ele2 == nums[i]) {
                count2++;
            }
        }

        // checking if counts are greater than n/3 + 1
        if(count1 >= (nums.length / 3) + 1) {
            ans.add(ele1);
        }

        if(count2 >= (nums.length / 3) + 1 && ele1 != ele2) {
            ans.add(ele2);
        }

        return ans;
        
    }
}