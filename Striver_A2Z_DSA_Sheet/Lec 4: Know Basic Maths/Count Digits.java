class Main {
    public static void main(String[] args) {
       int n = 2;
       
       if(n < 10){
           System.out.print(1);
           return;
       }
       
       int count = 0;
       while(n > 0){
           n = n / 10;
           count++;
       }
       
       System.out.print(count);
    }
}
