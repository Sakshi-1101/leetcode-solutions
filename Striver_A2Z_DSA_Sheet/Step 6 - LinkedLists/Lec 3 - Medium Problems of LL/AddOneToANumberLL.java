class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class AddOneToANumberLL {

    public static void main(String[] args) {

        Node head = new Node(4, null);
        Node n1 = new Node(5, null);
        Node n2 = new Node(9, null);

        head.next = n1;
        n1.next = n2;

        /*
            PROS AND CONS FOR BOTH METHODS:
            1. Iterative:
            => Pros - no extra space used.
               Cons - Tampering data and more time taking
               
            2. Recursive:
            => Pros - No tampering of data, addition is done in-place and in faster time.
               Cons - Extra recursion space
         */
        Node headIter = addNumIterative(head);
        Node headRec = addNumRecursive(head);

        printList(headIter);
        printList(headRec);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: O(3 * n) -> Two reversals and one pass for addition
    // SC: O(1)
    // Approach: In this approach, we first reverse the LL to facilitate addition from the least significant digit (the end of 
    //           the list). We then traverse the reversed list, adding 1 to the last node and handling any carry that may occur.
    //           If we reach the end of the list and still have a carry, we add a new node with the carry value. Finally, we 
    //           reverse the list again to restore the original order and return the head of the modified list. This method 
    //           allows us to perform the addition in-place without using extra space.
    public static Node addNumIterative(Node head) {
        // reverse the list so we can traverse the list from the end to the beginning and add 1 to the last node.
        Node revHead = reverseList(head);

        // carry variable to keep track of the carry while adding 1 to the last node and propagating it through the list.
        int c = 1; // at first addition it will be 1 bcoz we need to add 1 to the number represented by the LL.

        Node temp = revHead;

        // traverse the list and add the carry to the current node's data.
        while(temp != null && c > 0) {
            int sum = c + temp.data;
            temp.data = sum % 10;
            c = sum / 10; //Update the carry for the next iteration.

            //If we reach the end of the list and still have a carry, we need to add a new node with the carry value.
            if(temp.next == null && c > 0) {
                Node carry = new Node(c, null);
                temp.next = carry;
                c = 0;
            }

            temp = temp.next;
        }

        // reverse the list again to restore the original order and return the head of the modified list.
        Node revertHead = reverseList(revHead);
        return revertHead;
    }

    // helper function to reverse the list.
    private static Node reverseList(Node head) {
        Node curr = head;
        Node prev = null;

        while(curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    // TC: O(n)
    // SC: O(n) -> recursive stack space
    // Approach: In this approach, we use recursion to traverse to the end of the list first. Once we reach the last node, we 
    //           add 1 to it and propagate the carry backward. The recursive function processes the right side of the list 
    //           first, returning the carry to the left side. If there is a carry left after processing the entire list, we 
    //           add a new node at the beginning of the list with the carry value. This method allows us to perform the 
    //           addition without explicitly reversing the list, as the recursion naturally handles the right-to-left traversal 
    //           needed for addition.
    public static Node addNumRecursive(Node head) {

        //Instead of reversing, we go to the last node first (using recursion) and propagate the carry backward.
        // With only recursion you can move backwards so we'll use backtracking.
        /*
            When using recursion, you go to the end first. While coming back, you process nodes. 
            That exactly mimics right → left traversal

            Now the critical question:
            When you're at a node during the return phase, what information do you need from the right side?
            Only one thing: CARRY
            That’s it.
            So naturally, your recursive function should:
            - Process the right side
            - Return carry
            - Use it to update current node
         */
       
        // this is final carry value that we will get after processing the entire list. 
        int carry = helper(head);

        // If after processing the entire list, we still have a carry, it means we need to add a new node at the beginning of 
        // the list with the carry value.
        if(carry != 0) {
            Node newNode = new Node(carry, null);
            newNode.next = head;
            return newNode;
        }

        // If there is no carry left after processing the entire list, we can simply return the head of the modified list.
        return head;
    }

    // this function will return the carry after processing the entire list. It will add 1 to the last node and propagate the 
    // carry backward through the list.
    private static int helper(Node head) {
        // base case: If we reach the end of the list, we return 1 to indicate that we need to add 1 to the last node.
        if(head == null) {
            return 1;
        }

        // call recursion for the next node and get the carry from the right side.
        int carry = helper(head.next);

        // add the carry to the current node's data. 
        head.data = head.data + carry;

        // if the curr node value is less than 10, it means we can simply return 0 as there is no carry to propagate further.
        if(head.data < 10) {
           return 0;
        } else { // else we need to set the current node's data to 0 and return 1 as carry to propagate to the left side.
            head.data = 0;
            return 1;
        }
    }
}