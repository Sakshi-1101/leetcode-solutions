1/**
2 * Definition for singly-linked list.
3 * public class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode(int x) { val = x; }
7 * }
8 */
9class Solution {
10    /*
11        To delete the given node, we copy the value of the next node to the current node and adjust the next pointer to skip the next node. Copy the value of the next node to the current node. Adjust the next pointer to skip the next node.
12    */
13    public void deleteNode(ListNode node) {
14        node.val = node.next.val;
15        node.next = node.next.next;
16        
17    }
18}