public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int elementToSearch = 4;

        linearSearch(arr, elementToSearch);
    }

    // TC: O(n)
    // SC: O(1)
    public static void linearSearch(int[] arr, int element) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == element) {
                System.out.println("Element found at index: " + i);
                return;
            }
        }
        System.out.println("Element not found");
    }

}
