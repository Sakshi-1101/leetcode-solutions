class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class SegregateOddAndEvenNodes {

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

        Node newHead = segregateOddAndEven(head);

        printList(newHead);
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
    // SC: O(1)
    // Approach: In this approach, we will maintain two separate lists for odd and even nodes and then we will connect the odd 
    //           list with the even list at the end. We will traverse the original list and for each node, we will check if it 
    //           is odd or even and then we will add it to the respective list. After traversing the original list, we will 
    //           connect the odd list with the even list and return the head of the new list.
    public static Node segregateOddAndEven(Node head) {
        // Edge case: If list is empty or has only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize the heads and tails of the odd and even lists
        Node oddHead = null;
        Node evenHead = null;
        Node oddTail = null;
        Node evenTail = null;

        // temp node to traverse the original list
        Node temp = head;

        // traverse the LL and separate the odd and even nodes into two lists
        while(temp != null) {
            // even node
            if(temp.data % 2 == 0) {
                // if it is the first even node, then we will initialize the even head and tail with this node
                if(evenHead == null) {
                    evenHead = temp;
                    evenTail = temp;
                } else { // if it is not the first even node, then we will add it to the even list and update the even tail
                    evenTail.next = temp;
                    evenTail = temp;
                }
            } else { // odd node
                // if it is the first odd node, then we will initialize the odd head and tail with this node
                if(oddHead == null) {
                    oddHead = temp;
                    oddTail = temp;
                } else { // if it is not the first odd node, then we will add it to the odd list and update the odd tail
                    oddTail.next = temp;
                    oddTail = temp;
                }
            }

            // move to the next node in the original list
            temp = temp.next;
        }

        // if there are no odd nodes, then we will return the even head as the new head of the list
        if(oddHead == null) {
            return evenHead;
        }

        // if there are no even nodes, then we will return the odd head as the new head of the list
        if(evenHead == null) {
            return oddHead;
        }

        // connect the odd list with the even list and return the head of the new list
        evenTail.next = oddHead;
        oddTail.next = null;

        return evenHead;

    }
    
}
