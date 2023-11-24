import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.LoadTest;
import utils.Rand;

public class TaskLoadTest extends LoadTest {

    private static final Task task = new Task();

    /**
     * We should to call the class method many times for it to become precompiled.<br>
     * This is necessary so that JIT compilation does not occur directly during the test.
     */
    @BeforeAll
    public static void warming() {
        System.out.println("Warming started...");
        for (int i = 0; i < 1_000_000; i++) {
            var randIntArray = Rand.genIntArray(100, -100, 100);

            // Put here call for those method that should be compiled.
            task.findMaxElement(randIntArray);
            task.insetElementInCenter(randIntArray, 0);
        }
    }

    /**
     * This is example of load test with:
     * <br> - Time complexity O(n)
     * <br> - Space complexity O(1)
     */
    @Test
    void findLetterIndexInText() {
        loadTest(50_000_000L, 10_000_000L, (iteration) -> {
            // Here you can define data for every case. Use 'iteration' for counting.
            var randomIntArray = Rand.genIntArray(iteration, -1000, 1000);

            startMeasure(); // You should start measuring by call startMeasure()

            // Put here your interested code between start and stop measuring
            task.findMaxElement(randomIntArray);

            stopMeasure(); // You should stop measuring by call stopMeasure()
            printMeasure(); // Optionally you can print current iteration measures.
        });
        chartMeasure(); // Optionally you can save chart with measures. (See folder /results )
    }

    @Test
    void insetElementInCenter() {
        loadTest(50_000_000L, 10_000_000L, (iteration) -> {
            var randomIntArray = Rand.genIntArray(iteration, -100, 100);

            startMeasure();

            task.insetElementInCenter(randomIntArray, 0);

            stopMeasure();
            printMeasure();
        });
        chartMeasure();
    }
}
