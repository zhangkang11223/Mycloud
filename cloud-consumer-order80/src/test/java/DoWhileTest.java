public class DoWhileTest {

    public static void main(String[] args) {
        int i =0;
        do {
            System.out.println(i++);   // 先打印i=0，然后i++
            System.out.println(i==5); //false
        }while(i<=5); //false跳出循环
        System.out.println("end");
    }
}
