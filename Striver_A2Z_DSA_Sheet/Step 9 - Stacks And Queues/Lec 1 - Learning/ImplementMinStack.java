import java.time.temporal.IsoFields;
import java.util.*;

class Pair {
    int val;
    int minSoFar;

    Pair(int val, int minSoFar) {
        this.val = val;
        this.minSoFar = minSoFar;
    }
}

public class ImplementMinStack {

    // TC: O(1)
    // SC: O(2*N) -> we are storing a N pairs in the stack with each pair having two values
    // Approach: In this approach, we use a stack of pairs where each pair stores the element and the minimum element so far in 
    //           the stack. Whenever we push a new element, we compare it with the minimum element so far and store the minimum 
    //           of the two in the pair. This way, we can get the minimum element in O(1) time by simply looking at the top of 
    //           the stack.
    public static class MinStackBrute{
        Stack<Pair> s;

        public MinStackBrute() {
            s = new Stack<>();
        }
        
        public void push(int val) {
            // if pushing first element
            if(s.isEmpty()) {
                s.push(new Pair(val, val));
                return;
            }

            // find the min of curr ele and minSofar and store the pair
            int min = Math.min(val, s.peek().minSoFar);
            s.push(new Pair(val, min));
        }
        
        public void pop() {
            if(s.isEmpty()) {
                return;
            }

            s.pop();
        }
        
        public int top() {
            if(s.isEmpty()) {
                return - 1;
            }

            Pair temp = s.peek();
            return temp.val;
        }
        
        public int getMin() {
            if(s.isEmpty()) {
                return -1;
            }

            return s.peek().minSoFar;
        }
    }

    // TC: O(1)
    // SC: O(N)
    // Approach: In this approach, we will use a stack to store the elements of the stack and a variable to keep track of the 
    //           minimum element so far in the stack. Whenever we push a new element, we will compare it with the minimum element 
    //           so far and if it is smaller than the minimum element, we will push a modified value in the stack which will help 
    //           us to retrieve the previous minimum element when we pop the current minimum element from the stack. This way, 
    //           we can get the minimum element in O(1) time by simply looking at the variable that keeps track of the minimum 
    //           element.
    public static class MinStackOptimal{
        Stack<Integer> s;
        int min;

         public MinStackOptimal() {
            s = new Stack<>();
            min = Integer.MAX_VALUE;
        }
        
        public void push(int val) {
            // pushing first element
            if(s.isEmpty()) {
                s.push(val);
                min = val;
                return;
            }

            if(val > min) {
                s.push(val);
            } else { // val < min -> insert the modified val in stack then update min
                int modifiedVal = 2 * val - min; // min = curr min
                s.push(modifiedVal);
                min = val;
            }

        }
        
        public void pop() {
            if(s.isEmpty()) {
                return;
            }

            // get the top of stack by popping the element
            int top = s.pop();

            // update min if modified value is present at top of stack otherwise don't do anything since you already popped the 
            // ele which will be orignal ele in case this condition doesn't run
            if(top < min) {
                int prevMin = 2 * min - top; // get the prev min
                min = prevMin;
            }
            
        }
        
        public int top() {
            if(s.isEmpty()) {
                return -1;
            }

            // if modified value present at top of stack, the min value will have the actual top of stack value
            if(s.peek() < min) {
                return min;
            }

            // if s.peek() > min, means original value is there in top of stack return that
            return s.peek(); 
            
        }
        
        public int getMin() {
            return min;
        }

    }

    public static void main(String[] args) {
         MinStackBrute st1 = new MinStackBrute();

        st1.push(12);
        st1.push(15);
        st1.push(10);
        System.out.println(st1.getMin());
        st1.pop();
        System.out.println(st1.getMin());
        System.out.println(st1.top());
        st1.push(10);
        System.out.println(st1.top());

        MinStackOptimal st2 = new MinStackOptimal();

        st2.push(12);
        st2.push(15);
        st2.push(10);
        System.out.println(st2.getMin());
        st2.pop();
        System.out.println(st2.getMin());
        System.out.println(st2.top());
        st2.push(10);
        System.out.println(st2.top());
        
    }
    
}
