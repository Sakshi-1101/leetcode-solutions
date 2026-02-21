import java.util.*;

// Helper class to store a character and its frequency
class Pair {
    int freq;
    char ch;

    Pair(int freq, char ch) {
        this.freq = freq;
        this.ch = ch;
    }
}

public class SortCharactersByFrequency {

    public static void main(String[] args) {
        String s= "raaaajj";

        ArrayList<Character> ans = sortCharsFreqApproach1(s);
        String res = sortCharsFreqApproach2(s);

        System.out.println(ans);
        System.out.println(res);

    }
 
    // TC: O(n) + O(k log (base 2) k) -> traversal + sorting
    // SC: O(k) -> k = 256 chars
    // Approach: In this approach, we create an array of Pair objects to store the frequency and character. We then sort this 
    //           array based on frequency and character. Finally, we traverse the sorted array and add characters to the answer 
    //           list based on their frequency.
    public static ArrayList<Character> sortCharsFreqApproach1(String s) { 
        // array to store frequency of each character
        Pair[] arr = new Pair[256];

        // initialize the array with default values. Without this, arr[i] would be null → NullPointerException when we try to 
        // access arr[ch].freq++
        for(int i = 0 ; i < arr.length ; i ++) {
            arr[i] = new Pair(0, (char)i);
        }

        // count frequency of each character in the input string
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            arr[ch].freq++;
        }

        // sort the array based on frequency (descending) and then by character (ascending)
        Arrays.sort(arr, (a,b) -> {
            if(a.freq != b.freq) {
                return b.freq - a.freq; // higher frequency first
            }
            
            return a.ch - b.ch; // tie-breaker: smaller char first
        });

        ArrayList<Character> ans = new ArrayList<>();

        // traverse the sorted array and add chars to the answer list based on their frequency
        for(int i = 0 ; i < arr.length ; i ++) {
            while(arr[i].freq != 0) {
                ans.add(arr[i].ch);
                arr[i].freq--;
            }
        }

        return ans;
    }

    // TC: O(n) + O(k log (base 2) k) -> traversal + sorting
    // SC: O(n + k) -> k for map and list, n for StringBuilder
    // Approach: In this approach, we use a HashMap to count the frequency of each character. We then store the characters in a 
    //           list and sort the list based on frequency using the map. Finally, we traverse the sorted list and add characters 
    //           to the StringBuilder based on their frequency in the map.
    public static String sortCharsFreqApproach2(String s) {
        // Convert the input string to a char array
        char str[] = s.toCharArray();

        // map to store frequency of each character
        HashMap<Character,Integer> map = new HashMap<>();

        // traverse the char array and update the frequency of each character in the map
        for(char ch: str){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // from the map, store all the chars in a list 
        ArrayList<Character> list = new ArrayList<>(map.keySet());

        // sort the list based on freq of each char in descending order. We can use the map to get the frequency of each char while sorting.
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));

        StringBuilder sb = new StringBuilder();

        // traverse the sorted list and add chars to the StringBuilder based on their frequency in the map
        for(char ch: list){
            // for each char, we append it to the StringBuilder as many times as its frequency in the map
            for(int i = 0; i < map.get(ch); i++){
                sb.append(ch);
            }
        }

        // convert the StringBuilder to a String 
        return sb.toString();
    }
    
}
