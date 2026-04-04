import java.util.Stack;

// class Node {
//     int data;
//     Node next;

//     Node(int data, Node next) {
//         this.data = data;
//         this.next = next;
//     }
// }

public class ReverseLLIterative {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(3, null);
        Node n3 = new Node(4, null);
        Node n4 = new Node(5, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Node revListBrute = reverseBrute(head);
        Node revListOptimal = reverseOptimal(head);

        printList(revListBrute);
        printList(revListOptimal);
    }

    // TC: O(2 * N) -> traversed LL twice
    // SC: O(N) -> stack space 
    // Approach: In this approach, we will first traverse the LL and store the data of each node in a stack. Then, we will traverse
    //           the LL again and replace the data of each node with the data popped from the stack. This way, we will reverse the 
    //           LL by replacing the data of each node with the data of the corresponding node from the end of the LL.
    public static Node reverseBrute(Node head) {
        Stack<Integer> st = new Stack<>(); // Stack to store the data of each node in the LL

        Node temp = head;

        // Traverse the LL and store the data of each node in the stack
        while(temp != null) {
            st.push(temp.data);
            temp = temp.next;
        }

        // reset temp to head to traverse the LL again 
        temp = head;

        // Traverse the LL again and replace the data of each node with the data popped from the stack
        while(temp != null) {
            temp.data = st.pop();
            temp = temp.next;
        }

        // return new head of the reversed LL
        return head;

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will use three pointers - prev, curr and temp. We will initialize prev to null and curr to 
    //           head. Then, we will traverse the LL and for each node, we will store the next node in temp, reverse the next 
    //           pointer of the current node to point to the previous node, update prev to the current node and move curr to the 
    //           next node (which is stored in temp). This way, we will reverse the LL in place without using any extra space.
    public static Node reverseOptimal(Node head) {
        Node curr = head;
        Node prev = null;

        // Traverse the LL and reverse the next pointer of each node to point to the previous node
        while(curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // at the end of the loop, prev will be pointing to the new head of the reversed LL
        return prev;

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
