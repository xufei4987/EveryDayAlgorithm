import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test1 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<?> future = threadPool.submit(() -> {
            Object obj = null;
            System.out.println(obj.toString());
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        threadPool.execute(() -> {
//            Object obj = null;
//            System.out.println(obj.toString());
//        });
        threadPool.shutdown();
    }
}
