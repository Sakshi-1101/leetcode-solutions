1/**
2 * Definition for singly-linked list.
3 * public class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode() {}
7 *     ListNode(int val) { this.val = val; }
8 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
9 * }
10 */
11class Solution {
12    public ListNode oddEvenList(ListNode head) {
13        ListNode oddHead = null;
14        ListNode evenHead = null;
15        ListNode oddTail = null;
16        ListNode evenTail = null;
17
18        ListNode temp = head;
19        /*
20            It is given that first node will be considered odd and second node even.
21
22            EXAMPLE:
23            nodes = 2 -> 1 -> 3 -> 5 -> 6 -> 4 -> 7
24            pos   = 0    1    2    3    4    5    6
25
26            odd Nodes = 2 -> 3 -> 6 -> 7
27            pos       = 0    2    4    6 (even idx values)
28
29            even nodes = 1 -> 5 -> 4
30            pos        = 1    3    5 (odd idx values)
31
32            output = 2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4
33                    |----------------|  |----------|
34                        odd nodes        even nodes
35        */
36        int pos = 0; // assign pos to each node
37
38        while(temp != null) {
39            // odd pos -> part of even list
40            if(pos % 2 != 0) {
41
42                if(evenHead == null) {
43                    evenHead = temp;
44                    evenTail = temp;
45                } else {    
46                    evenTail.next = temp;
47                    evenTail = temp;
48                }
49            // even pos -> part of odd list
50            } else {
51
52                if(oddHead == null) {
53                    oddHead = temp;
54                    oddTail = temp;
55                } else {
56                    oddTail.next = temp;
57                    oddTail = temp;
58                }
59            }
60
61            temp = temp.next;
62            pos++;
63        }
64
65        if(oddHead == null) {
66            return evenHead;
67        }
68
69        if(evenHead == null) {
70            return oddHead;
71        }
72
73        // oddlist should be before even list
74        oddTail.next = evenHead;
75        evenTail.next = null;
76
77        return oddHead;
78        
79    }
80}