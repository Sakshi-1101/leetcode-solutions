import java.util.Stack;

class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class CheckIfLLisPalinOrNot {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(3, null);
        Node n3 = new Node(2, null);
        Node n4 = new Node(1, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        boolean headBrute = checkIfPalinBrute(head);
        boolean headOptimal = checkIfPalinOptimal(head);

        System.out.println(headBrute);
        System.out.println(headOptimal);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we will use a stack to store the nodes of the list and then we will traverse the list again 
    //          and compare the nodes with the nodes in the stack. The idea is stack is LIFO so it will store the nodes in reverse 
    //          order so we can compare the first and the last nodes of the list and then the second and the second last nodes and 
    //          so on. If all the nodes match with the nodes in the stack, then it is a palindrome and we will return true. 
    public static boolean checkIfPalinBrute(Node head) {
        // If the head is null or there is only one node in the list, then it is a palindrome
        if(head == null || head.next == null) {
            return true;
        }

        // stack to store the nodes of the list
        Stack<Node> st = new Stack<>();

        Node temp = head;

        // Traverse the list and push the nodes into the stack
        while(temp != null) {
            st.push(temp);
            temp = temp.next;
        }

        temp = head;

        // Traverse the list again and compare the nodes with the nodes in the stack
        while(temp != null) {
            Node s = st.pop();
            
            // If the data of the current node is not equal to the data of the node in the stack, then it is not a palindrome and we will return false
            if(temp.data != s.data) {
                return false;
            }

            temp = temp.next;
        }

        // if all nodes match with the nodes in the stack, then it is a palindrome and we will return true
        return true;
                
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will use the Tortoise and Hare algorithm to find the mid of the list and then we will 
    //           reverse the second half of the list after the mid node and then we will compare the first half of the list with 
    //           the reversed second half of the list. If all the nodes match, then it is a palindrome and we will return true.
    public static boolean checkIfPalinOptimal(Node head) {
        // If the head is null or there is only one node in the list, then it is a palindrome
        if(head == null || head.next == null) {
            return true;
        }

        // Find the mid of the list using the Tortoise and Hare algorithm
        Node slow = head;
        Node fast = head;

        // find the mid of the list
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // store the mid node
        Node mid = slow;

        // Reverse the second half of the list after the mid node and store the head of the reversed list in newHead
        Node newHead = reverseList(mid.next);

        Node temp1 = head;
        Node temp2 = newHead;

        // Traverse the first half of the list and the reversed second half of the list and compare the nodes. If any node is not 
        // equal, then it is not a palindrome and we will return false
        while(temp2 != null) {
            if(temp1.data != temp2.data) {
                return false;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // if all nodes match, then it is a palindrome and we will return true
        return true;
    }

    // Helper function to reverse the list
    private static Node reverseList(Node head) {
        Node curr = head;
        Node prev = null;

        while(curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }


    
}
