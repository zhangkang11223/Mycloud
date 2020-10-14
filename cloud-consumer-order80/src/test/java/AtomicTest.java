import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        boolean b = atomicInteger.compareAndSet(0, 1);
        System.out.println(b);
        System.out.println(Integer.MAX_VALUE);
    }
}
