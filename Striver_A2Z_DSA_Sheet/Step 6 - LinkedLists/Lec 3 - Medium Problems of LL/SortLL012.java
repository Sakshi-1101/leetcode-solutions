class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class SortLL012 {

    public static void main(String[] args) {

        Node head = new Node(1, null);
        Node n1 = new Node(2, null);
        Node n2 = new Node(0, null);
        Node n3 = new Node(1, null);
        Node n4 = new Node(0, null);
        Node n5 = new Node(2, null);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        Node newheadBrute = sortLLBrute(head);
        Node newheadOptimal = sortLLOptimal(head);

        printList(newheadBrute);
        printList(newheadOptimal);
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
    // Approach: In this approach, we will maintain three separate LL for 0s, 1s and 2s, and we will maintain 2 pointers for 
    //           each LL, one for head and one for tail. We will traverse the given LL and add the nodes to their respective LL 
    //           based on their values. After traversing the given LL, we will connect the three LLs and return the head of 
    //           sorted LL.
    public static Node sortLLBrute(Node head) {
        // base case: if head is null or head has only one node, then it's already sorted, so we will return head.
        if(head == null || head.next == null) {
            return head;
        }

        // we will maintain three separate LL for 0s, 1s and 2s, and we will maintain 2 pointers for each LL
        Node head0 = null;
        Node head1 = null;
        Node head2 = null;
        Node tail0 = null;
        Node tail1 = null;
        Node tail2 = null;

        Node temp = head;

        // traverse the LL and add the nodes to their respective LL based on their values
        while(temp != null) {
            if(temp.data == 0) {
                // if first node of 0s LL
                if(head0 == null) {
                    head0 = temp;
                    tail0 = temp;
                } else {
                    tail0.next = temp;
                    tail0 = temp;
                }
            } else if(temp.data == 1) {
                // if first node of 1s LL
                if(head1 == null) {
                    head1 = temp;
                    tail1 = temp;
                } else {
                    tail1.next = temp;
                    tail1 = temp;
                }
            } else {
                // if first node of 2s LL
                if(head2 == null) {
                    head2 = temp;
                    tail2 = temp;
                } else {
                    tail2.next = temp;
                    tail2 = temp;
                }
            }

            temp = temp.next;
        }

        
        if(head0 == null) {
            if(head1 == null) { // if head0 and head1 both are null, then we will return head2 as head of sorted LL.
                return head2;
            } else { // if head0 is null, connect head1 and head2 and return head1 as head of sorted LL.
                tail1.next = head2;
                return head1;
            }

        } else if(head1 == null) {
            if(head2 == null) { // if head1 and head2 both are null, then we will return head0 as head of sorted LL.
                return head0;
            } else { // if head1 is null, connect head0 and head2 and return head0 as head of sorted LL.
                tail0.next = head2;
                return head0;
            }
        } else { // if head2 is null, connect head0 and head1 and return head0 as head of sorted LL.
            tail0.next = head1;
            tail1.next = head2;
            return head0;
        }
    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will maintain three separate LL for 0s, 1s and 2s, and we will create dummy nodes for 
    //           each LL to keep track of starting point of each LL, and we will maintain tail pointers for each LL to add 
    //           nodes to their respective LL. We will traverse the given LL and add the nodes to their respective LL based on 
    //           their values. After traversing the given LL, we will connect the three LLs and return the head of sorted LL.
    public static Node sortLLOptimal(Node head) {
        // dummy node is just a fake node that gurantees that list is never empty even in the starting (from pointer perspective)
        // With dummy node you don't have to handle the first node (head == null) edge case 
        /*
            Example:
            Node dummy = new Node(-1) => LL = -1 -> NULL
            Add 0 to list => LL = -1 -> 0 -> NULL
        */

        // dummy nodes to keep track of starting point of 0s, 1s and 2s LL
        Node zeroHead = new Node(-1, null);
        Node oneHead = new Node(-1, null);
        Node twoHead = new Node(-1, null);

        // tail pointers starting from dummy nodes, these will be used to add nodes to their respective LL
        Node zeroTail = zeroHead;
        Node oneTail = oneHead;
        Node twoTail = twoHead;

        Node temp = head;

        // traverse the LL and add the nodes to their respective LL based on their values
        while(temp != null) {
            if(temp.data == 0) {
                zeroTail.next = temp;
                zeroTail = temp;
            } else if(temp.data == 1) {
                oneTail.next = temp;
                oneTail = temp;
            } else {
                twoTail.next = temp;
                twoTail = temp;
            }

            temp = temp.next;
        }

        /*
            Explanation of the below logic to connect the three LLs:
            1. zeroTail.next = (oneHead.next != null) ? oneHead.next : twoHead.next; 
            -> we will connect 0s LL to 1s LL if 1s LL is not empty, otherwise we will connect 0s LL to 2s LL.
            2. oneTail.next = twoHead.next; 
            -> we will connect 1s LL to 2s LL, if 1s LL is empty then oneTail will be pointing to oneHead dummy node and 
               oneHead.next will be null, so it will connect 0s LL to 2s LL.
            3. twoTail.next = null; 
            -> we will make next of last node of 2s LL as null, because it will be the last node of sorted LL.

         */
        zeroTail.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        oneTail.next = twoHead.next;
        twoTail.next = null;

        // return the head of sorted LL, which will be next of zeroHead dummy node, because zeroHead is the dummy node for 0s 
        // LL and 0s LL will be the first LL in sorted order.
        return zeroHead.next;

    }
}
