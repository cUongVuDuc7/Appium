package utilities;

public class TrackerTestResult {
    private static ThreadLocal<Integer> passedTCs = ThreadLocal.withInitial(() -> 0);
    private static ThreadLocal<Integer> skippedTCs = ThreadLocal.withInitial(() -> 0);
    private static ThreadLocal<Integer> failedTCs = ThreadLocal.withInitial(() -> 0);
    public static void incrementPassed() {
        passedTCs.set(passedTCs.get() + 1);
    }
    public static void incrementSkipped() {
        skippedTCs.set(skippedTCs.get() + 1);
    }
    public static void incrementFailed() {
        failedTCs.set(failedTCs.get() + 1);
    }
    public static int getPassed() {
        return passedTCs.get();
    }
    public static int getSkipped() {
        return skippedTCs.get();
    }
    public static int getFailed() {
        return failedTCs.get();
    }
    public static void setPassedTCs() {passedTCs.set(0);}
    public static void setSkipped() {skippedTCs.set(0);}
    public static void setFailed() {failedTCs.set(0);}
}

