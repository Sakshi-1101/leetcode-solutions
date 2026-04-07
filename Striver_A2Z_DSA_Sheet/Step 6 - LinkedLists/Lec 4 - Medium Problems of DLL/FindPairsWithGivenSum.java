import java.util.ArrayList;
import java.util.List;

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

public class FindPairsWithGivenSum {

     public static void main(String[] args) {
        Node head = new Node(1, null, null);
        Node n1 = new Node(3, null, head);
        Node n2 = new Node(4, null, n1);
        head.next = n1;
        n1.next = n2;

        int tar = 6;

        List<List<Integer>> alBrute = findPairsBrute(head, tar);
        List<List<Integer>> alOptimal = findPairsOptimal(head, tar);

        System.out.println(alBrute);
        System.out.println(alOptimal);
    }

    private static void printList(Node head) {
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    // TC: ~ O(n^2), nearly n^2 not exactly n^2.
    // SC: O(1) -> in order to solve problem we didn't use any space, just store the ans in list depending on no. of pairs
    // Approach: In this approach, we are using two loops to check for all the pairs. If at any point, sum is equal to target 
    //           then we will add the pair in the ans list.
    public static List<List<Integer>> findPairsBrute(Node head, int tar) { 
        List<List<Integer>> ans = new ArrayList<>();

        Node temp1 = head;

        // we are using two loops to check for all the pairs
        while(temp1 != null) {
            Node temp2 = temp1.next;

            while(temp2 != null) {
                int sum = temp1.data + temp2.data;

                // if sum is greater than target then we can break the inner loop because list is sorted in increasing order 
                // and all the next values to the right will be greater than target so no point of checking them
                if(sum > tar) {
                    break;
                }

                // if sum is equal to target then we will add the pair in the ans list
                if(sum == tar) {
                    List<Integer> al = new ArrayList<>();
                    al.add(temp1.data);
                    al.add(temp2.data);
                    ans.add(al);
                }

                temp2 = temp2.next;
            }

            temp1 = temp1.next;
        }

        // return all pairs list
        return ans;
    }

    // TC: O(N) + O(N) = O(2*N)
    // SC: O(1)
    // Approach: In this approach, we are using two pointers, left and right. Left pointer will be at the start of list and 
    //           right pointer will be at the end of list. We will check the sum of left and right pointer, if sum is equal to 
    //           target then we will add the pair in the ans list and move both pointers towards each other else we'll move the 
    //           pointer which is having greater value towards the other pointer because we need to decrease the sum if sum is 
    //           greater than target and we need to increase the sum if sum is less than target.
    public static List<List<Integer>> findPairsOptimal(Node head, int tar) {
        List<List<Integer>> ans = new ArrayList<>();

        // base case: if head is null then we will return empty list
        if(head == null) {
            return ans;
        }

        Node temp = head;

        // traverse till end of list to get the right pointer at the end of list
        while(temp.next != null) {
            temp = temp.next;
        }

        // implement two pointer approach, left pointer at the start of list and right pointer at the end of list
        Node left = head;
        Node right = temp;

        // checking both conditions bcoz if left and right are at the same node then we can't form a pair and if left has 
        // crossed right then also we can't form a pair, so we will stop the loop when any of these conditions is true
        while(left != right && left.prev != right) { // OR while(left.data < right.data) -> bcoz LL is sorted
            int sum = left.data + right.data;

            // if sum is equal to target then we will add the pair in the ans list and move both pointers towards each other
            if(sum == tar) {
                List<Integer> al = new ArrayList<>();
                al.add(left.data);
                al.add(right.data);
                ans.add(al);

                right = right.prev;
                left = left.next;
            } else if(sum > tar) { // move right pointer towards left because we need to decrease the sum
                right = right.prev;
            } else { // move left pointer towards right because we need to increase the sum
                left = left.next;
            }
        }

        // return all pairs list
        return ans;
    } 

}
