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

public class DeleteAllOccurrencesOfKey {

    public static void main(String[] args) {
        Node head = new Node(1, null, null);
        Node n1 = new Node(2, null, head);
        Node n2 = new Node(3, null, n1);
        head.next = n1;
        n1.next = n2;

        int tar = 1;

        Node newHead = deleteOccurence(head, tar);

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
    // Approach: In this approach, we'll traverse the list and whenever we find a node whose data is equal to the target, 
    //           we'll delete that node by updating the next and previous pointers of the adjacent nodes. We'll also handle 
    //           the case when the node to be deleted is the head of the list. Finally, we'll return the head of the modified 
    //           list.
    public static Node deleteOccurence(Node head, int tar) {
        Node temp = head;

        // traverse the list
        while(temp != null) {
            // if the current node's data is equal to the target, delete it
            if(temp.data == tar) {

                // if the current node is the head and is equal to the target, update the head to the next node to delete 
                // the current head node
                if(temp == head) {
                    head = head.next;
                }

                // store the next and previous nodes of the current node
                Node nextNode = temp.next;
                Node prevNode = temp.prev;

                /*
                    We'll have 3 cases:
                    1. If nextNode is null, in that case we'll only update the previous node's next pointer to null
                    2. If prevNode is null, in that case we'll only update the next node's previous pointer to null
                    3. If both nextNode and prevNode are not null, in that case we'll update both the pointers to skip the current node
                 */

                // update the next node's previous pointer to skip the current node
                if(nextNode != null) {
                    nextNode.prev = prevNode;
                }

                // update the previous node's next pointer to skip the current node
                if(prevNode != null) {
                    prevNode.next = nextNode;
                }

                // move the temp pointer to the next node after deleting the current node
                temp = nextNode;

            } else { // if the current node's data is not equal to the target, move the temp pointer to the next node
                temp = temp.next;
            }
           
        }

        // return the head of the modified list
        return head;
       
    }
    
}
