class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
         System.out.println("testing github push");
        System.out.println("testing versioning");
        Arrays.sort(nums);
        
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i = 0 ; i < nums.length - 2 ; i++){
            
            if(i == 0 || (i > 0 && nums[i] != nums[i -1])){
                int lo = i + 1;
                int hi = nums.length - 1;
                
                int remSum = 0 - nums[i];
                
                while(lo < hi){
                    if(nums[lo] + nums[hi] == remSum){
                        List<Integer> al = new ArrayList<>();
                        al.add(nums[i]);
                        al.add(nums[lo]);
                        al.add(nums[hi]);
                        
                        ans.add(al);
                        
                        while(lo < hi && nums[lo] == nums[lo + 1]){
                            lo++ ;
                        }
                        
                        while(lo < hi && nums[hi] == nums[hi - 1]){
                            hi-- ;
                        }
                    
                        lo++ ;
                        hi-- ;
                    }
                    else if(nums[lo] + nums[hi] < remSum){
                        lo++ ;
                    }
                    else{
                        hi-- ;
                    }
                }
            }
            
        }
        
        return ans;
    }
}