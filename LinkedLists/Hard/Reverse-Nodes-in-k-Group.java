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
12    // RECURSIVE APPROACH
13    public ListNode reverseKGroup(ListNode head, int k) {
14        // Pointer to traverse and count k nodes
15        ListNode curr = head;
16        int count = 0;
17        
18        // Step 1: Check if there are at least k nodes ahead
19        // Move 'curr' k steps forward
20        while (curr != null && count < k) {
21            curr = curr.next;
22            count++;
23        }
24        
25        // If we found k nodes, we can reverse this group
26        if (count == k) {
27            
28            // Step 2: Recursively process the remaining list
29            // 'curr' now points to the start of next group
30            // This returns the head of the already processed (reversed) remaining list
31            ListNode prev = reverseKGroup(curr, k);
32            
33            // Step 3: Reverse current k nodes
34            // We attach nodes one by one in front of 'prev'
35            // So current group gets reversed and linked to rest
36            
37            while (count-- > 0) {
38                ListNode next = head.next; // store next node
39                
40                // Reverse the link:
41                // make current node point to already processed part
42                head.next = prev;
43                
44                // Move 'prev' forward (it becomes new head of reversed part)
45                prev = head;
46                
47                // Move 'head' forward to continue reversal
48                head = next;
49            }
50            
51            // Step 4: Update head to new head of this reversed group
52            head = prev;
53        }
54        
55        // If less than k nodes, return head as it is (no reversal)
56        return head;
57    }
58}