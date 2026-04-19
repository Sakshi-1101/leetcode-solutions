1class Solution {
2    long mod = 1000000007;
3
4    public int countGoodNumbers(long n) {
5        long even = (n + 1) / 2;
6        long odd = n - even;
7        long ans = 1;
8
9        long evenPart = pow(5, even);
10        long oddPart = pow(4, odd);
11
12        ans = (evenPart * oddPart) % mod;
13
14        return (int)(ans);
15    }
16
17    private long pow(long x, long n) {
18         // base case
19        if(n == 0 || x == 1) {
20            return 1;
21        }
22
23        long half = pow(x, n / 2);
24
25        if (n % 2 == 0) {
26            return (half * half) % mod;
27        } else {
28            return (half * half * x) % mod;
29        }
30    }
31}