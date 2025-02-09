package career.thirtydays.machine.ratelimiter.model;

public class LeakyBucket{

    private final int capacity;        // Maximum bucket capacity (maximum number of requests)
    private final int leakRate;        // The rate at which the bucket leaks (requests processed per second)
    private int waterLevel;            // Current water level in the bucket (current requests waiting to be processed)
    private long lastLeakTimestamp;    // Timestamp when the last leak occurred

    public LeakyBucket(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.waterLevel = 0;
        this.lastLeakTimestamp = System.currentTimeMillis();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLeakRate() {
        return leakRate;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public long getLastLeakTimestamp() {
        return lastLeakTimestamp;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setLastLeakTimestamp(long lastLeakTimestamp) {
        this.lastLeakTimestamp = lastLeakTimestamp;
    }

    public void incWaterLevel(){
        this.waterLevel++;
    }
}
