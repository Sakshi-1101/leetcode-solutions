class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class DeletionOfTheHeadOfLL {
    
     public static void main(String[] args) {
        Node head = new Node(0,null);
        Node n1 = new Node(1,null);
        Node n2 = new Node(2, null);
        
        head.next = n1;
        n1.next = n2;

        printList(head);

        // delete last node of list
        Node newHead = deleteTailNode(head);

        printList(newHead);

    }

    // TC: O(n) ->  we traverse the entire linked list once to delete the tail of the list.
    // SC: O(1)
    private static Node deleteTailNode(Node head) {
        if(head == null || head.next == null) {
            return null;
        }

        Node temp = head;

        // traverse till second last node
        while(temp.next.next != null) {
            temp = temp.next;
        }

        // delete last node
        temp.next = null;

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
