class Solution {
   public String removeOuterParentheses(String s) {
       int level = 0;
       StringBuilder sb = new StringBuilder("");
       for(int i = 0 ; i < s.length() ; i ++) {
           char ch = s.charAt(i);
           if(ch == '(') {
                if(level > 0) {
                    sb.append(ch);
                }

                level++;

            } else {
                level--;

                if(level > 0) {
                    sb.append(ch);
                }

            }
        }

        return sb.toString();
        
    }
}