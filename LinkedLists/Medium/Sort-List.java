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
12    public ListNode sortList(ListNode head) {
13         // base case 
14        if(head == null || head.next == null) {
15            return head;
16        }
17
18        ListNode mid = findMid(head);
19
20        ListNode leftHalfHead = head;
21        ListNode rightHalfHead = mid.next;
22        mid.next = null; // break the connection;
23
24        // 
25        leftHalfHead = sortList(leftHalfHead);
26        rightHalfHead = sortList(rightHalfHead);
27
28        ListNode newHead = mergeLeftAndRrightHalfList(leftHalfHead, rightHalfHead);
29        return newHead;
30    }
31
32    private static ListNode findMid(ListNode head) {
33        ListNode slow = head;
34
35        // In even nodes LL, we have two mids, now for merge sort implementation we need first mid. 
36        // That's we have kept fast pointer one place ahead so that slow pointer stops one place before.
37        ListNode fast = head.next;
38
39        while(fast != null && fast.next != null) {
40            slow = slow.next;
41            fast = fast.next.next;
42        }
43
44        return slow;
45    }
46
47    private static ListNode mergeLeftAndRrightHalfList(ListNode left, ListNode right) {
48        ListNode temp = null;
49        ListNode newHead = null;
50
51        while(left != null && right != null) {
52            if(left.val < right.val) {
53
54                if(temp == null) {
55                    temp = left;
56                    newHead = temp;
57                } else {
58                    temp.next = left;
59                    temp = left;
60                }
61
62                left = left.next;
63            } else {
64                if(temp == null) {
65                    temp = right;
66                    newHead = temp;
67                } else {
68                    temp.next = right;
69                    temp = right;
70                }
71
72                right = right.next;
73            }
74        }
75
76        if(left != null) {
77            temp.next = left;
78        }
79
80        if(right != null) {
81            temp.next = right;
82        }
83
84        return newHead;
85
86    }
87}