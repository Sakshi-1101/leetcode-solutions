class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class LengthOfLL {

    public static void main(String[] args) {
        Node head = new Node(0,null);
        Node n1 = new Node(1,null);
        Node n2 = new Node(2, null);
        
        head.next = n1;
        n1.next = n2;

        printList(head);

        // find length of list
        int len = lengthofList(head);

        System.out.println(len);

    }

    // TC: O(n) ->  we traverse the entire linked list to find total no. of nodes.
    // SC: O(1)
    private static int lengthofList(Node head) {
        if(head == null) {
            return 0;
        }

        if(head.next == null) {
            return 1;
        }

        Node temp = head;
        int len = 0; // to keep track of no. of nodes.

        // traverse till last node
        while(temp != null) {
            len++;
            temp = temp.next;
        }

        return len;
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

