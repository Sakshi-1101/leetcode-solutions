1class Pair {
2    int freq;
3    char ch;
4
5    Pair(int freq, char ch) {
6        this.freq = freq;
7        this.ch = ch;
8    }
9}
10
11class Solution {
12    public String frequencySort(String s) {
13        Pair[] arr = new Pair[256];
14
15        for(int i = 0 ; i < arr.length ; i ++) {
16            arr[i] = new Pair(0, (char)i);
17        }
18
19        for(int i = 0 ; i < s.length() ; i ++) {
20            char ch = s.charAt(i);
21            arr[ch].freq++;
22        }
23
24        Arrays.sort(arr, (a,b) -> {
25            if(a.freq != b.freq) {
26                return b.freq - a.freq;
27            }
28            
29            return a.ch - b.ch;
30        });
31
32        StringBuilder ans = new StringBuilder();
33
34        for(int i = 0 ; i < arr.length ; i ++) {
35            while(arr[i].freq != 0) {
36                ans.append(arr[i].ch);
37                arr[i].freq--;
38            }
39        }
40
41        return ans.toString();
42        
43    }
44}