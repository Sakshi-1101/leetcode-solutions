/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /*
        * You are given a node that needs to be deleted.
        * You do not have access to the head of the linked list.
        * The usual approach (modifying the previous node’s next pointer) is not possible since we don’t have a reference to the 
          previous node.
        
        To delete the given node, we copy the value of the next node to the current node and adjust the next pointer to skip 
        the next node. Copy the value of the next node to the current node. Adjust the next pointer to skip the next node.
        input = 4(A) -> 5(B) -> 1(C) -> 9(null)
        node = 5
        output = 4(A) -> 1(C) -> 9(null)
    */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
        
    }
}