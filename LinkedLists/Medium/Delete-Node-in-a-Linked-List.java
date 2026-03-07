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
11        * You are given a node that needs to be deleted.
12        * You do not have access to the head of the linked list.
13        * The usual approach (modifying the previous node’s next pointer) is not possible since we don’t have a reference to the 
14          previous node.
15        
16        To delete the given node, we copy the value of the next node to the current node and adjust the next pointer to skip 
17        the next node. Copy the value of the next node to the current node. Adjust the next pointer to skip the next node.
18        input = 4(A) -> 5(B) -> 1(C) -> 9(null)
19        node = 5
20        output = 4(A) -> 1(C) -> 9(null)
21    */
22    public void deleteNode(ListNode node) {
23        node.val = node.next.val;
24        node.next = node.next.next;
25        
26    }
27}