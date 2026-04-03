
import java.util.Stack;

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}

public class ReverseDLL {

    public static void main(String[] args) {
        Node head = new Node(0, null, null);
        Node n1 = new Node(1, null, head);
        Node n2 = new Node(2, null, n1);
        head.next = n1;
        n1.next = n2;


        // reverse list
        Node newHeadBrute = reverseBrute(head);
        Node newHeadOptimal = reverseOptimal(head);

        printList(newHeadBrute);
        printList(newHeadOptimal);
    }

    // TC: O(2 * n) -> traversing LL twice
    // SC: O(n) -> using stack to store the values
    // Approach: In this approach involves replacing data in a doubly linked list. First, we traverse the list and store node 
    //           data in a stack. Then, in a second pass, we assign elements from the stack to nodes, ensuring a reverse order 
    //           replacement since stacks follow the Last-In-First-Out (LIFO) principle.
    private static Node reverseBrute(Node head) {
        // If list is empty or  only one node present
        if (head == null || head.next == null) {
            return head;
        }

        Stack<Integer> st = new Stack<>();

        Node temp = head;

        while(temp != null) {
            st.push(temp.data);
            temp = temp.next;
        }

        temp = head;

        while(temp != null) {
            temp.data = st.pop();
            temp = temp.next;
        }

        return head;
    }

    // TC: O(n) -> traversing the list only once.
    // SC: O(1) -> reversal done in place.
    // Approach: In this approach we'll reverse the list in place by swapping the next and prev pointers of each node. We'll also 
    //           keep track of the new head of the reversed list as we go along. 
    private static Node reverseOptimal(Node head) {
        // If list is empty or  only one node present
        if (head == null || head.next == null) {
            return head;
        }

        // Pointer to traverse the list
        Node curr = head;

        // Variable to eventually store new head after reversal
        Node newHead = head;

        while(curr != null) {
            Node temp = curr.next;
            curr.next = curr.prev;
            curr.prev = temp;

            // move the newHead to curr pointer position before moving the curr pointer fwd in next step to keep newHead one step
            // back so that when curr becomes null newHead is pointing to the last node of original list(or first node of reverse list)
            newHead = curr;

            // after above steps curr.next has the curr.prev value so we won't be able to move fwd to correct node.
            // But we stored the original value of curr.next in temp so we'll move our curr pointer to temp
            curr = temp;
        }

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
