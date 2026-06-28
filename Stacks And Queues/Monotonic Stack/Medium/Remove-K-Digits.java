1class Solution {
2    public String removeKdigits(String num, int k) {
3        StringBuilder ans = new StringBuilder();
4        Stack<Character> st = new Stack<>();
5
6        // if k == length of string means remove all the digits
7        if(k == num.length()) {
8            return "0";
9        }
10
11        // traverse the string
12        for(int i = 0 ; i < num.length() ; i ++) {
13            
14            // for each char check if the stack is not empty and k is still remaining and the top of the stack is greater than the current char, then we can pop the top of the stack to remove that digit and decrease k by 1
15            while(!st.isEmpty() && k > 0 && st.peek() > num.charAt(i)) {
16                st.pop();
17                k--;
18            }
19
20            // push the current char to stack
21            st.push(num.charAt(i));
22        }
23
24        // if K is still remaining
25        while(k > 0) {
26            st.pop();
27            k--;
28        }
29
30        // check if stack is empty, means we have removed all the digits, so we can return "0" as the smallest number possible
31        if(st.isEmpty()) {
32            return "0";
33        }
34
35        // pop all elements in the stack and append into the string
36        while(!st.isEmpty()) {
37            ans.append(st.pop());
38        }
39
40        // remove the leading zeroes
41        while(ans.length() > 0 && ans.charAt(ans.length() - 1) == '0') {
42            ans.deleteCharAt(ans.length() - 1);
43        }
44
45        // reverse the string to get the correct number since stack had the digits in rev order so string will also have like that 
46        ans.reverse();
47
48        // what if the stack had all 0s, and after removing leading 0s our string will become empty 
49        if(ans.length() == 0) {
50            return "0";
51        }
52
53        // return final string with smallest number after removing k digits
54        return ans.toString();
55        
56    }
57}