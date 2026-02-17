class Solution {
    public String longestCommonPrefix(String[] strs) {
        // To store the longest common prefix
        StringBuilder ans = new StringBuilder();

        // sort the strings in lexicographical order bcoz the common prefix of the first and last string in the sorted array 
        // will be the longest common prefix for the entire array.
        Arrays.sort(strs);

        //find the smallest(first) and largest(last) string
        String first = strs[0];
        String last = strs[strs.length - 1];

        // we'll traverse till the min length of the first and last string because the common prefix can't be longer than the 
        // smallest string
        int min = Math.min(first.length() , last.length());

        // Compare characters of the first and last strings until a mismatch is found
        for(int i = 0 ; i < min ; i ++) {
            // Stop if characters are different
            if(first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }

            // Add matching character to result
            ans.append(first.charAt(i));
        }

        // Return the final common prefix
        return ans.toString();
        
    }

    // horizotal scanning
     public String longestCommonPrefixH(String[] strs) {
      
       int n=strs.length;
       String prefix=strs[0]; // compare these with next values

       for(int i=1;i<n;i++){
           while(!strs[i].startsWith(prefix)){
            if(prefix.isEmpty()){
                return "";
            }
            prefix=prefix.substring(0,prefix.length()-1);
           }
       }
       return prefix;

    }

    // vertical scanning
    public String longestCommonPrefixV(String[] strs) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<strs[0].length();i++){
            char s=strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(i>=strs[j].length()){
                    return sb.toString();
                }
                if(s!=strs[j].charAt(i)){
                    return sb.toString();
                }
            }
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }
33}