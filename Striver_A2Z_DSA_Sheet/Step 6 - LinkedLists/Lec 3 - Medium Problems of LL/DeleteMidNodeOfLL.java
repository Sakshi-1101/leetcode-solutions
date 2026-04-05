class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class DeleteMidNodeOfLL {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(3, null);
        Node n3 = new Node(4, null);
        Node n4 = new Node(5, null);
        // Node n5 = new Node(6, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        // n4.next = n5;

        Node headBrute = deleteMidNodeBrute(head);
        Node headOptimal1 = deleteMidNodeOptimal1(head);
        Node headOptimal2 = deleteMidNodeOptimal2(head);

        printList(headBrute);
        printList(headOptimal1);
        printList(headOptimal2);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(N + N/2) -> we traverse the entire LL once to count the no. of nodes and then traverse again to delete the middle node.
    // SC: O(1)
    // Approach: In this approach, we first count the number of nodes in the LL to find the index of the middle node. Then, we 
    //           traverse the LL again to reach the node just before the middle node and update its next pointer to skip the 
    //           middle node, effectively deleting it from the list.
    public static Node deleteMidNodeBrute(Node head) {
        // base case: if the list is empty or has only one node, return null as there is no middle node to delete.
        if (head == null || head.next == null) {
            return null; 
        }

        Node temp = head;
        int countNodes = 0;

        // Count the number of nodes in the linked list
        while(temp != null) {
            countNodes++;
            temp = temp.next;
        }

        // Calculate the index of the middle node (0-based index)
        int mid = countNodes / 2;

        temp = head;

        // Traverse the LL again to reach the node just before the middle node and update its next pointer to skip the middle node.
        while(temp != null) {
            mid--;

            //  this will stop at one node before mid bcoz we are considering 0-based indexing.
            if(mid == 0) {
                temp.next = temp.next.next; // skip the middle node
                break;
            }

            temp = temp.next;
        }

        // return the head of the modified LL after deleting the middle node.
        return head;
    }

    // TC: O(N/2)
    // SC: O(1)
    // Approach: In this approach, we use the three-pointers to find the middle node in a single traversal. The slow pointer 
    //           moves one step at a time, while the fast pointer moves two steps at a time. When the fast pointer reaches the 
    //           end of the list, the slow pointer will be at the middle node. We also keep track of the previous node to 
    //           update its next pointer to skip the middle node, effectively deleting it from the list.
    public static Node deleteMidNodeOptimal1(Node head) {
        // base case: if the list is empty or has only one node, return null as there is no middle node to delete.
        if (head == null || head.next == null) {
            return null;
        }

        
        Node slow = head;
        Node fast = head;
        Node prev = null; //to keep track of the previous node before the mid node.

        // The fast pointer moves twice as fast as the slow pointer. When the fast pointer reaches the end of the list, the slow pointer will be at the middle node.
        // And the prev node will be at the node just before the middle node, which we will use to update its next pointer to skip the middle node.
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // At this point, the slow pointer is at the middle node, and the prev pointer is at the node just before the middle 
        // node. We can update the next pointer of the prev node to skip the middle node, effectively deleting it from the list.
        prev.next = slow.next;

        // return the head of the modified LL after deleting the middle node.
        return head;
    }

    // TC: O(N/2)
    // SC: O(1)
    // Approach: In this approach, we use the two-pointers technique to find the middle node in a single traversal. The slow 
    //           pointer moves one step at a time, while the fast pointer moves two steps at a time. When the fast pointer 
    //           reaches the end of the list, the slow pointer will be at the middle node. We can then update the next pointer 
    //           of the slow node to skip the middle node, effectively deleting it from the list.
    public static Node deleteMidNodeOptimal2(Node head) {
        // base case: if the list is empty or has only one node, return null as there is no middle node to delete.
        if (head == null || head.next == null) {
            return null;
        }

        // initialize the slow pointer at the head of the list 
        Node slow = head;

        // Initialize fast pointer two steps ahead bcoz we want the slow pointer to stop at the node just before the middle 
        // node when the fast pointer reaches the end of the list.
        Node fast = head.next.next; 

        // traverse the list
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // At this point, the slow pointer is at the node just before the middle node. We can update the next pointer of the 
        // slow node to skip the middle node, effectively deleting it from the list.
        slow.next = slow.next.next;

        // return the head of the modified LL after deleting the middle node.
        return head;
    }


    
}
