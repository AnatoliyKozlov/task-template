package utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;

public abstract class LoadTest {
    private static final String PROCESS_START_TIME = Instant.now().truncatedTo(ChronoUnit.SECONDS).toString().replace("Z", "").replace("T", "_");
    private static long startTime = 0;
    private static long stopTime = 0;
    private static boolean isMeasureStarted;
    private static long limit = 0;
    private static long step = 0;
    private static long iteration = 0;
    private static DefaultCategoryDataset timeChartDataset = new DefaultCategoryDataset();
    private static DefaultCategoryDataset spaceChartDataset = new DefaultCategoryDataset();
    private static long startUsedMemory = 0;
    private static long stopUsedMemory = 0;
    private static boolean isFirstPrintMeasures = true;

    public static void startMeasure() {
        if (isMeasureStarted) {
            throw new IllegalStateException("Tried start measuring but, previous measure do not stopped");
        }

        stopTime = 0;
        stopUsedMemory = 0;

        startTime = System.currentTimeMillis();
        startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        isMeasureStarted = true;
    }

    public static void stopMeasure() {
        stopTime = System.currentTimeMillis();
        stopUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        isMeasureStarted = false;
    }

    public static void printMeasure() {
        if (isMeasureStarted) {
            throw new IllegalStateException("Tried print measuring but, measure in process");
        }
        if (isFirstPrintMeasures) {
            System.out.println("Step: " + step + " to Limit: " + limit);
            isFirstPrintMeasures = false;
        }
        System.out.println("step " + iteration / step + "  :  " + (stopTime - startTime) + " milliseconds  |  memory: " + (stopUsedMemory - startUsedMemory) + " bytes");
    }

    public static void chartMeasure() {
        if (isMeasureStarted) {
            throw new IllegalStateException("Tried chart measuring but, measure in process");
        }
        saveTimeChart(timeChartDataset);
        saveSpaceChart(spaceChartDataset);
    }

    public static void loadTest(long limit, long step, Consumer<Long> lambda) {
        LoadTest.limit = limit;
        LoadTest.step = step;

        timeChartDataset = new DefaultCategoryDataset();
        spaceChartDataset = new DefaultCategoryDataset();

        isFirstPrintMeasures = true;

        for (iteration = 0L; iteration <= limit; iteration += step) {
            lambda.accept(iteration);
            timeChartDataset.addValue(stopTime - startTime, "time", String.valueOf(iteration / step));
            spaceChartDataset.addValue(((double) (stopUsedMemory - startUsedMemory)) / 1048576, "space", String.valueOf(iteration / step));
        }
    }

    private static void saveTimeChart(DefaultCategoryDataset lineChartDataset) {
        var testName = Thread.currentThread().getStackTrace()[3].getMethodName();
        var timeChartObject = ChartFactory.createLineChart(testName + " time",
                "Load steps (step " + step + ")",
                "Time (milliseconds)",
                lineChartDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        saveChart(timeChartObject, testName + "-time");
    }

    private static void saveSpaceChart(DefaultCategoryDataset lineChartDataset) {
        var testName = Thread.currentThread().getStackTrace()[3].getMethodName();
        var timeChartObject = ChartFactory.createLineChart(testName + " space",
                "Load steps (step " + step + ")",
                "Space (mb)",
                lineChartDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        saveChart(timeChartObject, testName + "-space");
    }

    private static void saveChart(JFreeChart chartObject, String testName) {
        var path = new File("results/" + PROCESS_START_TIME);
        if (path.mkdirs()) {
            System.out.println("See run measures charts here: " + path.getAbsolutePath());
        }

        var lineChart = new File("results/" + PROCESS_START_TIME + "/" + testName + ".jpeg");
        try {
            ChartUtils.saveChartAsJPEG(lineChart, chartObject, 640, 480);
        } catch (IOException e) {
            System.out.println("Error while saving " + testName + " to file");
        }
    }
}
