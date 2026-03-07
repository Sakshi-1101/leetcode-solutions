class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class SearchInALL {

    
    // TC: O(1)-> creating a new node and updating the head takes constant time.
    // SC: O(1)-> only one extra node is created to insert at the head of the linked list.
    public static void main(String[] args) {
        Node n1 = new Node(0,null);
        Node n2 = new Node(1,null);
        Node n3 = new Node(2, null);
        
        n1.next = n2;
        n2.next = n3;

        printList(n1);

        Node newHead = new Node(5, null);
        newHead.next = n1;

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
    
}
