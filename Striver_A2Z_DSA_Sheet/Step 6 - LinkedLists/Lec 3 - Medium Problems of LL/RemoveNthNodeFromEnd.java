class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class RemoveNthNodeFromEnd {

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

        int n = 2; // nth no0de from the end to be removed

        Node headBrute = removeNthNodeBrute(head, n);
        Node headOptimal = removeNthNodeOptimal(head, n);

        printList(headBrute);
        printList(headOptimal);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(L)+O(L-N) -> where L = length of LL
    // SC: O(1)
    // Approach: In this approach, we first calculate the length of the LL and then calculate the position of the node to be 
    //           removed from the start of the LL. Then we traverse the LL again until we reach the node just before the node 
    //           to be removed and remove it by skipping it and connecting the current node to the next of the next node.
    public static Node removeNthNodeBrute(Node head, int n) {
        // If list is empty
        if (head == null) {
            return null;
        }

        Node temp = head;
        int count = 0; // to store the len of the LL

        // Count the number of nodes in the LL
        while(temp != null) {
            count++;
            temp = temp.next;
        }

        // if n is equal to the len of the LL, then we have to remove the head node
        if(count == n) {
            return head.next; // return the next node as the new head of the LL
        }

        // calculate the position of the node to be removed from the start of the LL
        int pos = count - n;

        temp = head;

        // traverse the LL until we reach the node just before the node to be removed
        while(temp != null) {
            pos--;

            if(pos == 0) {
                break;
            }

            temp = temp.next;
        }

        // remove the nth node from the end by skipping it and connecting the current node to the next of the next node
        temp.next = temp.next.next;
        return head;
    }
    
    // TC: O(n)
    // SC: O(1)
    // Approach: In this approach, we set two pointers, prev and curr, to the head of the LL. We move the curr pointer n nodes 
    //           ahead starting from the head of the LL. If n is equal to the len of the LL, then curr will point to null after 
    //           the above loop, which means we have to remove the head node of the LL. Otherwise, we move both the pointers, 
    //           prev and curr, one node at a time until curr reaches the end of the LL. At this point, prev will be pointing 
    //           to the node just before the node to be removed. We remove the nth node from the end by skipping it and 
    //           connecting the current node to the next of the next node.
    public static Node removeNthNodeOptimal(Node head, int n) {
        // set two pointers, prev and curr, to the head of the LL
        Node prev = head;
        Node curr = head;

        // move the curr pointer n nodes ahead starting from the head of the LL
        for(int i = 1 ; i <= n ; i ++) {
            curr = curr.next;
        }

        // In case, n is equal to the len of the LL, then curr will point to null after the above loop, which means we have to 
        // remove the head node of the LL. 
        if(curr == null) {
            return head.next; // return the next node as the new head of the LL
        }

        // move both the pointers, prev and curr, one node at a time until curr reaches the end of the LL. At this point, prev 
        // will be pointing to the node just before the node to be removed.
        while(curr.next != null) {
            prev = prev.next;
            curr = curr.next;
        }

        // remove the nth node from the end by skipping it and connecting the current node to the next of the next node
        prev.next = prev.next.next;

        return head;
    }
}
