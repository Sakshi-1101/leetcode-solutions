import java.util.Stack;

public class RemoveOutermostParentheses {

    public static void main(String[] args) {
        String str = "(()())(())";

        String ansBrute = removeParanthesesBrute(str);
        String ansOptimal = removeParenthesesOptimal(str);

        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }  
    
    // TC: O(n)
    // SC: O(1) 
    // Approach: In this approach we'll be traversing the string and we'll be maintaining a variable level which will keep track 
    //           of the level of parentheses. Whenever we encounter an opening parenthesis we will check if the level is greater 
    //           than 0 then only we will add it to our answer string and then we will increase the level by 1. Whenever we 
    //           encounter a closing parenthesis we will decrease the level by 1 and then check if the level is greater than 0 
    //           then only we will add it to our answer string. In this way we can remove the outermost parentheses from the 
    //           string.
    public static String removeParanthesesBrute(String s) {
        // this will keep track of the level of parentheses
        int level = 0; // 0 denotes the outermost level of parentheses
        StringBuilder sb = new StringBuilder("");

        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);

            if(ch == '(') {
                // if the level is > 0 then only we will add it to our ans since we have to remove the outermost ()
                if(level > 0) {
                    sb.append(ch);
                }

                // increment the level by 1 since we have encountered an opening (
                level++;

            } else {
                // decrement the level by 1 since we have encountered a closing )
                level--;

                // we are decrementing first and then we are checking if the level is > 0 bcoz if we find the ) bracket of the
                // outermost level then we have to remove it and we should not add it to our ans string.
                if(level > 0) {
                    sb.append(ch);
                }
            }
        }

        // return final string after removing the outermost parentheses
        return sb.toString();
    }

    // TC: O(n)
    // SC: O(n) - for the stack
    // Approach: In this approach we'll be using a stack to keep track of the parentheses. Whenever we encounter an opening 
    //           ( we will check if the stack is not empty then only we will add it to our answer string and then we will push it 
    //           to the stack. Whenever we encounter a closing ) we will pop from the stack and then check if the stack is not 
    //           empty then only we will add it to our answer string. In this way we can remove the outermost parentheses from 
    //           the string.
    public static String removeParenthesesOptimal(String s) {
        // stack to keep track of the parentheses
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder("");

        // traverse the string
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '('){
                // if the stack is not empty means we have outer () in stack so we will add the current ( to our ans string
                if(st.size() > 0){
                    sb.append(s.charAt(i));
                }
                
                // push the current ( to the stack
                st.push(s.charAt(i));
            }else{
                // we are popping first and then we are checking if the stack is not empty bcoz if we find the ) bracket of the 
                // outermost level then we have to remove it and we should not add it to our ans string.
                st.pop(); // pop from the stack since we have encountered a closing )

                // if the stack is not empty means we have outer () in stack so we will add the current ) to our ans string
                if(st.size() > 0){
                    sb.append(s.charAt(i));
                }
            }
        }

        // return final string after removing the outermost parentheses
        return sb.toString();
    }
    
}
