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


public class InsertNodeAtEndOfDLL {

    public static void main(String[] args) {
        Node head = new Node(0, null, null);
        Node n1 = new Node(1, null, head);
        Node n2 = new Node(2, null, n1);
        head.next = n1;
        n1.next = n2;

        int k = 3; // new node to be inserted

        // add new node at end of list
        Node newHead = insertNode(head, k);

        printList(newHead);
    }

    // TC: O(n) -> traversing till end of list
    // SC: O(1)
    private static Node insertNode(Node head, int k) {
        Node n3 = new Node(k, null, null);

        // If the list is empty, return the new node as the head
        if (head == null) {
            return n3;
        }

        Node temp = head;

        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = n3;
        n3.prev = temp;

        return head;
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
