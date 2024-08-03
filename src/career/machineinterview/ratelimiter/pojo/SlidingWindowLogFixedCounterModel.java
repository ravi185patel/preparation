package career.machineinterview.ratelimiter.pojo;

public class SlidingWindowLogFixedCounterModel extends SlidingWindowLogModel{

    public SlidingWindowLogFixedCounterModel(String domain, Long time,Integer maxCount) {
        super(domain, time, maxCount);
    }
}
