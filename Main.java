import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();

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

        long grandTotal = 0;

        for (Future<Long> future : futures) {
            grandTotal += future.get();
        }

        long endTime = System.nanoTime();
        double milliseconds = (endTime - startTime) / 1_000_000.0;

        System.out.println("Grand total: " + grandTotal);
        System.out.println("Time: " + milliseconds + " ms");

        System.out.println("Main branch complete");
        System.out.println("Timer branch complete");
    }
}