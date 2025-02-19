package career.sixmonthssep.machine.ratelimiter.model;

public class Bucket {
    private final int capacity;  // Maximum tokens the bucket can hold
    private final int refillRate;  // Tokens added per second
    private int tokens;  // Current tokens in the bucket
    private long lastRefillTimestamp;  // Time of the last refill

    public Bucket(int capacity,int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;  // Initially, fill the bucket to the maximum capacity
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRefillRate() {
        return refillRate;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public long getLastRefillTimestamp() {
        return lastRefillTimestamp;
    }

    public void setLastRefillTimestamp(long lastRefillTimestamp) {
        this.lastRefillTimestamp = lastRefillTimestamp;
    }

    public void incUsedToken(){
        tokens--;
    }
}
