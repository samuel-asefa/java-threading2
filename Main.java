import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        ArrayList<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Callable<Long> task = () -> {
                long count = 0;

                for (int n = 1; n <= 1_000_000; n++) {
                    count++;
                }

                return count;
            };

            Future<Long> future = executor.submit(task);
            futures.add(future);
        }

        executor.shutdown();

        System.out.println("Project complete");
    }
}