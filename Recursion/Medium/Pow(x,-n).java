1class Solution {
2    public double myPow(double x, int n) {
3        long temp = n; // to avoid integer overflow
4
5        if(n < 0) {
6            return 1.0 / helper(x, temp * -1);
7        }
8
9        return helper(x, temp);
10    }
11
12    //
13    private static double helper(double x, long n) {
14        // base case:
15        if(n == 0 || x == 1.0) {
16            return 1.0;
17        }
18
19       double half = helper(x, n / 2);
20
21        if (n % 2 == 0) {
22            return half * half;
23        } else {
24            return half * half * x;
25        }
26        
27    }
28}