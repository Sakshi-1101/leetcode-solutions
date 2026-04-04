// class Node {
//     int data;
//     Node next;

//     Node(int data, Node next) {
//         this.data = data;
//         this.next = next;
//     }
// }


public class ReverseLLRecursive {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(3, null);
        Node n3 = new Node(4, null);
        Node n4 = new Node(5, null);
        Node n5 = new Node(6, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Node revListRec = reverseRecursive(head);

        printList(revListRec);
    }

    // TC: O(n) -> Each node is visited exactly once during the recursive call,
    // SC: O(n) -> recursion stack space
    // Approach: In this approach, we will use recursion to reverse the linked list. We will start from the head of the list and 
    //           make a recursive call to reverse the rest of the list starting from the second node. Once we have reversed the 
    //           rest of the list, we will reverse the next pointer of the second node to point to the first node and break the 
    //           original forward link by setting the next pointer of the first node to null. Finally, we will return the new 
    //           head of the reversed list, which is the last node of the original list.
    public static Node reverseRecursive(Node head) {
        // Base case: If the list is empty or has only one node, return the head as it is already reversed
        if(head == null || head.next == null) {
            return head;
        }

        // Recursive call to reverse the rest of the list starting from the second node
        Node newHead = reverseRecursive(head.next);

        // store the next node of the current node (which is the second node in the original list) in temp
        Node temp = head.next;
        temp.next = head; // reverse the next pointer of the second node to point to the current node (first node)
        head.next = null; // break the original forward link

        // return the new head of the reversed list, which is the last node of the original list
        return newHead;
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }
    
}
