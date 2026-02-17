class Solution {
    public String reverseWords(String str) {
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
