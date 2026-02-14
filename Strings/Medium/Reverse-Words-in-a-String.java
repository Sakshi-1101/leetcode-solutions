1class Solution {
2    public String reverseWords(String str) {
3       StringBuilder sb = new StringBuilder();
4
5        int i = str.length() - 1;
6
7        // traverse the string from the end to the beginning
8        while(i >= 0) {
9
10            // skip trailing spaces. Eg: "hello world   "
11            while(i >= 0 && str.charAt(i) == ' ') {
12                i-- ;
13            }
14
15            // if pointer goes out of bound, break
16            if(i < 0) {
17                break;
18            }
19
20            int end = i; //  store idx of last char of the current word
21
22            // move backward to find the start of the current word
23            while(i >= 0 && str.charAt(i) != ' ') {
24                i-- ;
25            }
26
27            // word is formed
28            String word = str.substring(i + 1, end + 1); // (end + 1) bcoz substring takes range: [x, y)
29
30            // if the final string already has content, add a space before appending the new word. This is being so that we don't 
31            // have any leading or trailing spaces anywhere. Only single spaces between words.
32            if(sb.length() > 0) {
33                sb.append(" ");
34            }
35
36            // append the current word to the final string
37            sb.append(word);
38        }
39
40        // convert the StringBuilder to a string and return it as the result
41        return sb.toString();
42    }
43}
44