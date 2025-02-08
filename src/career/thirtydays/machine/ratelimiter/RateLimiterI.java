package career.thirtydays.machine.ratelimiter;

public interface RateLimiterI {
    public boolean isRequestAllowed(String userId);
}
