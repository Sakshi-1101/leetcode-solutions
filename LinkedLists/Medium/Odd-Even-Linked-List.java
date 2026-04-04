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
19        int pos = 0;
20
21        while(temp != null) {
22            // 
23            if(pos % 2 != 0) {
24
25                if(evenHead == null) {
26                    evenHead = temp;
27                    evenTail = temp;
28                } else {    
29                    evenTail.next = temp;
30                    evenTail = temp;
31                }
32
33            } else {
34
35                if(oddHead == null) {
36                    oddHead = temp;
37                    oddTail = temp;
38                } else {
39                    oddTail.next = temp;
40                    oddTail = temp;
41                }
42            }
43
44            temp = temp.next;
45            pos++;
46        }
47
48        if(oddHead == null) {
49            return evenHead;
50        }
51
52        if(evenHead == null) {
53            return oddHead;
54        }
55
56        oddTail.next = evenHead;
57        evenTail.next = null;
58
59        return oddHead;
60        
61    }
62}