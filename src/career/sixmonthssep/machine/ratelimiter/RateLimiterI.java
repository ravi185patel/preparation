package career.sixmonthssep.machine.ratelimiter;

public interface RateLimiterI {
    public boolean isRequestAllowed(String userId);
}
