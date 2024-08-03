package career.machineinterview.ratelimiter.pojo;

public class SlidingWindowLogModel extends MetaData{
    private final int maxCount;

    public SlidingWindowLogModel(String domain, Long time,Integer maxCount) {
        super(domain, time);
        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
