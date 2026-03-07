class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class InsertionAtTheHeadOfLL {

    public static void main(String[] args) {
        Node head = new Node(0,null);
        Node n1 = new Node(1,null);
        Node n2 = new Node(2, null);
        
        head.next = n1;
        n1.next = n2;

        int newNode = 6;

        printList(head);

        // add new node at head of list
        Node newHead = insertNodeAtHead(head, newNode);

        printList(newHead);

    }

    // TC: O(1)
    // SC: O(1)
    private static Node insertNodeAtHead(Node head, int val) {
        if(head == null) {
            Node newNode = new Node(val, null);
            return newNode;
        }

        // Create a new node whose next points to current head
        Node newNode = new Node(val, head);
        // Return the newNode as the new head
        return newNode;
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
