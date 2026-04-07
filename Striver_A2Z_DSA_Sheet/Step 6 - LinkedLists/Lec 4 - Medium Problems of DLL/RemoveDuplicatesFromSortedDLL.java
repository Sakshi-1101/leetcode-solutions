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

public class RemoveDuplicatesFromSortedDLL {

     public static void main(String[] args) {
        Node head = new Node(1, null, null);
        Node n1 = new Node(1, null, head);
        Node n2 = new Node(3, null, n1);
        head.next = n1;
        n1.next = n2;

        Node newHead = removeDuplicates(head);

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
    // Approach: In this approach, we are using a single pointer temp to traverse the list. We will keep a fwd pointer that 
    //           starts from next of temp and we will skip and unlink all nodes with the same value as current temp node. After 
    //           that we will connect current temp node to the next distinct node and if fwd is not null then we will connect 
    //           fwd's prev to temp. In case it is null then we don't need to connect fwd's prev to temp because fwd is null 
    //           and it will not point to any node, so no need to connect fwd's prev to temp. Finally, we will move temp to the 
    //           next distinct node and repeat the same process until we reach the end of list.
    public static Node removeDuplicates(Node head) {
        // base case
        if(head == null) {
            return head;
        }

        Node temp = head;

        // traverse the LL until we reach the end of list
        while(temp != null && temp.next != null) {
            // keep a fwd pointer that starts from next of temp
            Node fwd = temp.next;
            
            // Skip and unlink all nodes with the same value as current
            while(fwd != null && temp.data == fwd.data) {
                fwd = fwd.next;
            }

            // Connect current node to the next distinct node
            temp.next = fwd;

            // if fwd is not null then we will connect fwd's prev to temp.
            // In case it is null then we don't need to connect fwd's prev to temp because fwd is null and it will not point to 
            // any node, so no need to connect fwd's prev to temp.
            if(fwd != null) {
                fwd.prev = temp;
            }

            //  Move to the next distinct node
            temp = fwd;
        }
        
        // return the head of deduplicated node
        return head;

    }
    
}
