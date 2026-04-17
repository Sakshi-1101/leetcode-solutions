class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class RotateLL {

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

        int k = 2;

        // Rotate to right by K places
        Node rotBrute = rotateLLBrute(head, k);
        Node rotOptimal1 = rotateLLOptimal1(head, k);
        Node rotOptimal2 = rotateLLOptimal2(head, k);

        printList(rotBrute);
        printList(rotOptimal1);
        printList(rotOptimal2);

    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(n * k) -> we are performing `k` rotations. In each rotation, We traverse the list n times
    // SC: O(1)
    // Approach: In this we will perform `k` rotations. In each rotation, we will traverse the list to find the last node 
    //           and break the connection to the second last node and connect the last node to head and move head to the last 
    //           node. We will repeat this process `k` times to get the final rotated list.
    public static Node rotateLLBrute(Node head, int k) {
        // base case: If list is empty or has one node or no rotation needed
        if(head == null || head.next == null || k == 0) {
            return head;
        }

         // Repeat rotation k times
        for(int i = 1; i <= k ; i ++) {
            // Initialize pointers for traversal
            Node curr = head;
            Node prev = null;

            // Traverse to the last node
            while(curr.next != null) {
                prev = curr;
                curr = curr.next;
            }

            // break the second last node connection
            prev.next = null;
            
            // connect the last node to head
            curr.next = head;
            head = curr; // move head to the curr node (last node in original list)
        }

        return head;

    }

    // TC: O(N)
    // SC: O(1)
    // Approach: In this approach, we will first calculate the length of the list and then calculate the effective rotation 
    //           by taking k % len. Then we will traverse the list to find the node at position len - k and break the connection
    //           to divide the list into two halves. We will reverse both the halves and then connect them together and reverse 
    //           the entire list again to get the final rotated list.
    public static Node rotateLLOptimal1(Node head, int k) {
        // base case: If list is empty or has one node or no rotation needed
        if(head == null || head.next == null || k == 0) {
            return head;
        }

        int len = 0;
        Node temp = head;

        // calculate the len of LL
        while(temp != null) {
            len++;
            temp = temp.next;
        }

        // no rotation needed if len = k
        if(len == k) {
            return head;
        } 

        // if k > len, no. of rotation will be equal to k % len
        k = k % len;

        // if k < 0
        if(k < 0) {
            k = k + len;  // or k = (k % len + len) % len
        }

        // calculate the remaining nodes from head that needs to be traversed
        int remNodes = len - k;

        temp = head;

        // traverse the nodes till we reach remNodes
        while(temp != null && remNodes > 0) {
            remNodes--;

            if(remNodes == 0) {
                break;
            }

            temp = temp.next;
        }

        // store the nextNode of the remNode
        // break the connection to divide the LL into two halves
        // first half LL = head -> temp , second half LL = nextNode -> end (null)
        Node nextNode = temp.next;
        temp.next = null;

        // reverse both the halves
        Node h1 = reverse(head);
        Node h2 = reverse(nextNode);

        Node t1 = h1;

        // traverse the 1st half of rev LL to reach end node
        while(t1.next != null) {
            t1 = t1.next;
        }

        // connect the 1st and 2nd half of the LL
        t1.next = h2;

        // reverse the entire list again to get the final rotated LL
        Node newHead = reverse(h1);
        return newHead;
    }

    // helper function to reverse the list
    private static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;

        while(curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
    }

    // TC: O(n)
    // SC: O(1)
    // Approach: In this approach, we will first connect the tail of the list to head to make it circular. Then we will move 
    //           the head and tail pointers to the new positions after rotation and break the connection to make it linear again.
    public static Node rotateLLOptimal2(Node head, int k) {
        // base case: If list is empty or has one node or no rotation needed
        if(head == null || head.next == null || k == 0) {
            return head;
        }

        int len = 1; // count the head node in len
        Node tail = head;

        // calculate the len of LL and move tail to the last node
        while(tail.next != null) {
            len++;
            tail = tail.next;
        }

        // Calculate effective rotation: if k > len, no. of rotation will be equal to k % len
        k = k % len;

        // if k < 0
        if(k < 0) {
            k = k + len;  // or k = (k % len + len) % len
        }

        // calculate the remaining nodes from head that needs to be traversed
        int remNodes = len - k;

        Node temp = head;

        // traverse till we reach the remNodes node
        while(temp != null && remNodes > 0) {
            remNodes--;

            if(remNodes == 0) {
                break;
            }

            temp = temp.next;
        }

        tail.next = head; // connect the tail to head to make it circular
        head = temp.next; // move head to the next node of temp (remNodes node) which will be the new head after rotation
        temp.next = null; // break the connection to make the list linear again

        // return the head of the rotated LL
        return head;

    }
    
}
