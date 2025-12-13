class Solution {
    public int[] searchRange(int[] nums, int target) {
        return searchRangeHelperBinary(nums, target);
    }

    public static int[] searchRangeHelperBinary(int[] nums, int tar) {
        //To find first occurrence, move left direction and discard right
        int fidx = -1;
        int lo = 0;
        int hi = nums.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] < tar) {
                lo = mid + 1;
            } else if(nums[mid] > tar) {
                hi = mid - 1;
            } else {
                fidx = mid;
                hi = mid - 1;
            }
        }
        
        //To find last occurrence, move right direction and discard left
        int lidx = -1;
        lo = 0;
        hi = nums.length - 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] < tar) {
                lo = mid + 1;
            } else if(nums[mid] > tar) {
                hi = mid - 1;
            } else {
                lidx = mid;
                lo = mid + 1;
            }
        }

        return new int[]{fidx, lidx};
    }  

    //linear search
    public static int[] searchRangeHelperLinear(int[] nums, int tar) {
        int fidx = -1;
        int lidx = -1;

        for(int i = 0 ; i < nums.length ; i ++) {
            if(nums[i] == tar) {
                if(fidx == -1) {
                    fidx = i;
                }

                lidx = i;
            }
        }

        return new int[]{fidx, lidx};
    }
}