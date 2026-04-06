class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class AddTwoNumbersLL {

    public static void main(String[] args) {

        /*
            We are given already reversed lists. Original numbers: 342 + 465 = 807
            list1 = 2 -> 4 -> 3
            list2 = 5 -> 6 -> 4

            output = 7 -> 0 -> 8
         */
        Node head1 = new Node(2, null);
        Node n1 = new Node(4, null);
        Node n2 = new Node(3, null);

        head1.next = n1;
        n1.next = n2;

        Node head2 = new Node(5, null);
        Node n3 = new Node(6, null);
        Node n4 = new Node(4, null);

        head2.next = n3;
        n3.next = n4;

        Node head = addNum(head1, head2);

        printList(head);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(max(m,n)) -> it iterates at most max(m,n) times.
    // SC: O(max(m,n)) -> The length of the new list is at most max(m,n)+1.
    // Approach: In this approach, we traverse both linked lists simultaneously, adding corresponding digits along with any 
    //           carry from the previous addition. We create a new LL to store the result of the addition. We continue this 
    //           process until we have traversed both lists and there is no carry left. If one list is shorter than the other, 
    //           we treat the missing digits as 0. Finally, we return the head of the new LL that represents the sum of the 
    //           two numbers.
    public static Node addNum(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        // carry variable to keep track of the carry while adding the digits. 
        int carry = 0;

        // dummy node to help in constructing the new list without worrying about edge cases for the head of the new list.
        Node head = new Node(-1, null);
        Node dummy = head;

        // traverse both lists until we reach the end of both lists and there is no carry left. 
        while(temp1 != null || temp2 != null || carry != 0) {
            int sum = 0;

            // if temp1 is not null, we add its data to the sum and move temp1 to the next node.
            if(temp1 != null) {
                sum += temp1.data;
                temp1 = temp1.next;
            }

            // if temp2 is not null, we add its data to the sum and move temp2 to the next node.
            if(temp2 != null) {
                sum += temp2.data;
                temp2 = temp2.next;
            }

            // add the carry to the sum. The new digit will be sum % 10 and the new carry will be sum / 10.
            sum += carry;
            int digit = sum % 10;
            carry = sum / 10;

            // create a new node with the digit and add it to the new list. Move the dummy pointer to the new node.
            Node temp = new Node(digit, null);
            dummy.next = temp;
            dummy = temp;
        }

        // return the head of the new list, which is the next node of the dummy node.
        return head.next;
        
    }
}
