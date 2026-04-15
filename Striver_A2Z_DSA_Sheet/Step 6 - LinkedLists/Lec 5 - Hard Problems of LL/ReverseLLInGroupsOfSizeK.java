class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class ReverseLLInGroupsOfSizeK {

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

        int k = 4;

        Node rev = kReverse(head, k);
        printList(rev);

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
    // Approach: In this approach, we will reverse the linked list in groups of size k. We will use three pointers to keep 
    //           track of the current node, the previous node and the next node. We will also use a helper function to find 
    //           the kth node from the current node and another helper function to reverse the list. We will traverse the list 
    //           and reverse the nodes in groups of size k until we reach the end of the list. If the number of nodes in the 
    //           last group is less than k, we will not reverse that group and connect it to the previous group directly.
    public static Node kReverse(Node head, int k) {
        // base case
        if(head == null || head.next == null) {
            return head;
        }

        Node temp = head; // node to traverse the list
        Node nextNode = null; // to keep track of the starting node of the next group of k nodes
        Node prevNode = null; // to keep track of the ending node of the last reversed group

        // traverse the list
        while(temp != null) {
            // find the kthNode
            Node kthNode = getKthNode(temp, k);

            // if no. of nodes in group < k, kthNode will be null
            if(kthNode == null) {
                // connect the prevNode to the next group directly without reversing it
                if(prevNode != null) {
                    prevNode.next = temp;
                }

                // if it's the first group of the list, then we won't have any prevNode so just break the loop
                break;
            }

            // store the pointer to the first node of the next group
            nextNode = kthNode.next;
            // break the link of the current group so that it can be treated as a separate list
            kthNode.next = null;

            // reverse the curr group of k nodes list
            /*
                Note:
                reverseList(temp) works because:
                1. it reverses the nodes between temp and kthNode,
                2. kthNode remains the same object reference,
                3. after reversal, that object is now the head of the reversed group,
                4. the caller uses kthNode to connect the reversed group into the overall list.
             */
            reverseList(temp);

            // if it's the firt group, then temp = head
            if(temp == head) {
                head = kthNode; // set the head to the kthNode since kthNode will be the head after reversal
            } else {
                prevNode.next = kthNode; // connect the prevNode to the kthNode
            }

            // store the pointer to the end node of the previous group
            prevNode = temp;
            // move the temp to the start node of the next group that will be up for reversal
            temp = nextNode;
        }

        // finally return head of the update list
        return head;
    }

    // helper function to find the kthNode
    private static Node getKthNode(Node head, int k) {
        Node temp = head;

        while(temp != null && k > 0) {
            k--;
            if(k == 0) {
                return temp;
            }

            temp = temp.next;
        }

        return null;
    }

    // helper function to reverse the list
    private static void reverseList(Node head) {
        Node curr = head;
        Node prev = null;

        while(curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
    }

    
    // public static Node kReverse(Node head, int k) {
    //     if(head == null || head.next == null) {
    //         return head;
    //     }

    //     int len = findLen(head);

    //     Node dummyHead = new Node(-1, null);
    //     dummyHead.next = head;

    //     Node prev = dummyHead;

    //     while(len >= k) {
    //         Node curr = prev.next;
    //         Node nex = curr.next;

    //         for(int i = 1; i <= k ; i ++) {
    //             curr.next = nex.next;
    //             nex.next = prev.next;
    //             prev.next = nex;
    //             nex = curr.next;
    //         }

    //         prev = curr;
    //         len -= k;
    //     }

    //     return dummyHead.next;
    // }

    // private static int findLen(Node head)  {
    //     Node temp = head;
    //     int len = 0;

    //     while(temp != null) {
    //         len++;
    //         temp = temp.next;
    //     }

    //     return len;
    // }
}
    
