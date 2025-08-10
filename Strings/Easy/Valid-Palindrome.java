class Solution {
    public boolean isPalindrome(String s) {

        String str = s.toLowerCase();

        int i = 0;
        int j = str.length() - 1;

        //two pointer approach
        while(i <= j){
            char ch1 = str.charAt(i);
            char ch2 = str.charAt(j);

            //move fwd if ith char is not alphanumeric
            if(!Character.isDigit(ch1) && !Character.isAlphabetic(ch1)){
                i++;
            }else if(!Character.isDigit(ch2) && !Character.isAlphabetic(ch2)){ // move fwd if jth char is not alphanumeric
                j--;
            }else if (ch1 == ch2){
                i++;
                j--;
            }else{
                return false;
            }
        }

        return true;
        
    }
}