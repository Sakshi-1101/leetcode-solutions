import java.util.*;

public class ReverseWordsInString {

    public static void main(String[] args) {
        String s = "the sky is blue";

        String ansMyApproach = reverseBruteMyApproach(s);
        String ansBrute = reverseBrute(s);
        String ansOptimal = reverseOptimal(s);

        System.out.println(ansMyApproach);
        System.out.println(ansBrute);
        System.out.println(ansOptimal);
    }

    // TC: o(N^2) -> bcoz insert(0, ...) operations in StringBuilder require shifting existing characters (O(k) per insertion). 
    //               For m words, this results in O(1 + 2 + ... + m) = O(m²) time.
    // SC: O(N)
    // Approach: In this approach, we iterate through the input string character by character. We use a temporary StringBuilder 
    //           to build each word. When we encounter a space, we check if the temporary StringBuilder has a word. If it does, 
    //           we insert that word at the beginning of the main StringBuilder (which holds the final result). This way, we 
    //           effectively reverse the order of the words as we build the final string. After processing all characters, we 
    //           also handle any remaining word in the temporary StringBuilder and insert it at the beginning of the main 
    //           StringBuilder. Finally, we convert the main StringBuilder to a string and return it as the result.
    public static String reverseBruteMyApproach(String s) {
        StringBuilder sb = new StringBuilder(); // to store the final result
        StringBuilder temp = new StringBuilder(); // to build each word

        // Iterate through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If the character is a space, it indicates the end of a word
            if (ch == ' ') {
                // Check if the temporary StringBuilder has a word
                // This is being so that we don't have any leading or trailing spaces anywhere. Only single spaces between words.
                if (temp.length() > 0) {
                    // If the main StringBuilder already has content, add a space before inserting the new word
                    if (sb.length() > 0) {
                        sb.insert(0, " ");
                    }
                    // Insert the current word at the beginning of the main StringBuilder
                    sb.insert(0, temp);
                    temp.setLength(0); // Clear the temporary StringBuilder for the next word
                }
            } else { // If the character is not a space, append it to the temporary StringBuilder to build the current word
                temp.append(ch);
            }
        }

        // handle last word
        if (temp.length() > 0) {
            // If the main StringBuilder already has content, add a space before inserting the last word.
            if (sb.length() > 0) sb.insert(0, " ");
            sb.insert(0, temp);
        }

        return sb.toString();
    }

    // TC: O(N)
    // SC: O(N)
    // Approach: In this approach, we build each word and store it in an ArrayList. After processing the entire string, we 
    //           reverse the ArrayList to get the words in the correct order. Finally, we join the reversed list of words with 
    //           spaces to form the final result string. 
    public static String reverseBrute(String str) {
        ArrayList<String> al = new ArrayList<>(); // to store the all the words
        StringBuilder sb = new StringBuilder(); // to build each word

        // iterate through the string and build words
        for(int i = 0 ; i < str.length() ; i ++) {
            char ch = str.charAt(i);

            // if the character is not a space, append it to the StringBuilder to build the current word
            if(ch != ' '){
                sb.append(ch);
            } else { // if the character is a space, it indicates the end of a word
                // word is formed
                al.add(sb.toString()); // add the formed word to the ArrayList
                sb.setLength(0); // clear the StringBuilder for the next word
            }
        }

        // add the last word
        al.add(sb.toString());

        // reverse the arraylist to get the words in the reverse order
        Collections.reverse(al);

        // join the reversed list of words with spaces to form the final result string
        return String.join(" ", al);
        
    }

    // TC: O(N)
    // SC: O(1) -> Ignoring the output string, no additional data structures proportional to input size are used.
    // Approach: In this approach, we traverse the input string from the end to the beginning. We skip any trailing spaces and 
    //           then identify each word by finding the indices of the start and end of the word. We append each identified word 
    //           to a StringBuilder that holds the final result. If the final StringBuilder already has content, we add a space 
    //           before appending the new word to ensure that there are only single spaces between words and no leading or 
    //           trailing spaces.
    public static String reverseOptimal(String str) {
        StringBuilder sb = new StringBuilder();

        int i = str.length() - 1;

        // traverse the string from the end to the beginning
        while(i >= 0) {

            // skip trailing spaces. Eg: "hello world   "
            while(i >= 0 && str.charAt(i) == ' ') {
                i-- ;
            }

            // if pointer goes out of bound, break
            if(i < 0) {
                break;
            }

            int end = i; //  store idx of last char of the current word

            // move backward to find the start of the current word
            while(i >= 0 && str.charAt(i) != ' ') {
                i-- ;
            }

            // word is formed
            String word = str.substring(i + 1, end + 1); // (end + 1) bcoz substring takes range: [x, y)

            // if the final string already has content, add a space before appending the new word. This is being so that we don't 
            // have any leading or trailing spaces anywhere. Only single spaces between words.
            if(sb.length() > 0) {
                sb.append(" ");
            }

            // append the current word to the final string
            sb.append(word);
        }

        // convert the StringBuilder to a string and return it as the result
        return sb.toString();
    }
    
}
