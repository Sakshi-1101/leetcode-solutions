1class Solution {
2    public int maxProduct(int[] nums) {
3        int maxProd = Integer.MIN_VALUE;
4        int prefixProd = 1;
5        int suffixProd = 1;
6
7        for(int i = 0 ;  i < nums.length ; i ++) {
8
9            if(prefixProd == 0) {
10                prefixProd = 1;
11            }
12
13            if(suffixProd == 0) {
14                suffixProd = 1;
15            }
16
17            prefixProd *= nums[i];
18            suffixProd *= nums[nums.length - i - 1];
19
20            maxProd = Math.max(maxProd, Math.max(prefixProd, suffixProd));
21        }
22
23        return maxProd;
24    }
25}