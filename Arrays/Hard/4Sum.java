class Solution {

    // TEST CASE: arr[] = [1000000000,1000000000,1000000000,1000000000] , target = -294967296
    // That's why we used long otherwise the code will fail because of integer overflow when computing:
    // int sum = arr[i] + arr[j] + arr[lo] + arr[hi];
    public List<List<Integer>> fourSum(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(arr);

        for(int i = 0 ; i < arr.length ; i ++) {

            if(i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            for(int j = i + 1 ; j < arr.length ; j ++) {

                if(j > i + 1 && arr[j] == arr[j - 1]){
                    continue;
                }

                int lo = j + 1;
                int hi = arr.length - 1;

                while(lo < hi) {
                    long sum = (long) arr[i] + arr[j] + arr[lo] + arr[hi];
                    
                    if(sum < target){
                        lo++;
                    } else if(sum > target){
                        hi--;
                    }else {
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[lo], arr[hi]);
                        Collections.sort(temp);
                        ans.add(temp); // add list in set to avoid duplicates

                        while(lo < hi && arr[lo] == arr[lo + 1]) {
                            lo++;
                        }

                        while(lo < hi && arr[hi] == arr[hi - 1]) {
                            hi--;
                        }

                        lo++;
                        hi--;
                    }
                }
            }
        }

        return ans;
        
    }
}