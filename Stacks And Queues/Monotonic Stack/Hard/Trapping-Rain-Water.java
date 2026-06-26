1class Solution {
2    public int trap(int[] height) {
3        int totalWater = 0;
4
5        int left = 0;
6        int right = height.length - 1;
7
8        int leftMax = 0;
9        int rightMax = 0;
10
11        while(left < right) {
12            // always pick the smaller one
13            if(height[left] <= height[right]) {
14                if(height[left] < leftMax) {
15                    totalWater += leftMax - height[left];
16                }else {
17                    leftMax = height[left];
18                }
19
20                left++;
21            } else { 
22                if(height[right] < rightMax) {
23                    totalWater += rightMax - height[right];
24                } else {
25                    rightMax = height[right]; 
26                }
27
28                right--;
29            }
30        }
31        
32        return totalWater;
33    }
34}