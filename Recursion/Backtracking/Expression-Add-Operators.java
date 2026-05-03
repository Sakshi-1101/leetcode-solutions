1class Solution {
2    public List<String> addOperators(String num, int target) {
3        List<String> res = new ArrayList<>();
4        getExpressions(num, 0, "", 0, 0, target, res);
5        return res;
6    }
7
8    public static void getExpressions(String s, int idx, String exp, long valSoFar, long lastNum, int tar, List<String> res) {
9        // base case: If we've reached the end of the string
10        if(idx == s.length()) {
11            // If the expression evaluates to the target, add it to result
12            if(valSoFar == tar) {
13                res.add(exp);
14            }
15
16            return;
17        }
18
19        // Loop through all substrings starting from 'idx' index
20        // idx represents the level and i represent all the substring generation at each level 
21        for(int i = idx; i < s.length() ; i ++) {
22            // skip the leading zeros if string is "05" or "012", only allowed is "0"
23            if(i > idx && s.charAt(idx) == '0') {
24                return;
25            }
26
27            // generate the current number
28            long currNum = Long.parseLong(s.substring(idx, i + 1));
29
30            // if it is the first number
31            if(idx == 0) {
32                // go to the next number to start the operations
33                // valSoFar = currNum and lastNum = currNum 
34                getExpressions(s, i + 1, exp + currNum, currNum, currNum, tar, res);
35            } else {
36                // Add the current number with '+'
37                // valSoFar = add the currNum and lastNum = currNum 
38                getExpressions(s, i + 1, exp + "+" + currNum, valSoFar + currNum, currNum, tar, res);
39
40                // Add the current number with '-'
41                // valSoFar = subtract the currNum and lastNum = - currNum 
42                getExpressions(s, i + 1, exp + "-" + currNum, valSoFar - currNum, -currNum, tar, res);
43
44                // Add the current number with '*'
45                // valSoFar = valSoFar - lastNum + (lastNum * currNum) and lastNum = lastNum * currNum
46                getExpressions(s, i + 1, exp + "*" + currNum, valSoFar - lastNum + (lastNum * currNum), lastNum * currNum, tar, res);
47            }
48        }
49    }
50    
51}