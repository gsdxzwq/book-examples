package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        super();
        this.generator = generator;
        this.id = id;
    }

    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + "not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator intGenerator, int count) {
        System.out.println("press ctrl+c to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(intGenerator, count));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator intGenerator) {
        test(intGenerator, 10);
    }

}
