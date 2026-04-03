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

public class DeleteLastNodOfDLL {

    public static void main(String[] args) {
        Node head = new Node(0, null, null);
        Node n1 = new Node(1, null, head);
        Node n2 = new Node(2, null, n1);
        head.next = n1;
        n1.next = n2;

        // delete last node at end of list
        Node newHead = deleteNode(head);

        printList(newHead);
    }

    // TC: O(n) -> traversing till end of list to delete last node
    // SC: O(1)
    private static Node deleteNode(Node head) {
        // If list is empty
        if (head == null) {
            return null;
        }

        // If only one node present
        if (head.next == null) {
            return null;
        }

        Node temp = head;

        // reach the last node
        while(temp.next != null) {
            temp = temp.next;
        }

        // set the next pointer of second last node to null
        temp.prev.next = null;

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
