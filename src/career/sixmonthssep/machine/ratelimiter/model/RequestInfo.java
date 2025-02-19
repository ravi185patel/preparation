package career.sixmonthssep.machine.ratelimiter.model;


public class RequestInfo {
    int requestCount;
    long timestamp;

    public RequestInfo(int requestCount, long timestamp) {
        this.requestCount = requestCount;
        this.timestamp = timestamp;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}