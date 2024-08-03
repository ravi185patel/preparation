package career.machineinterview.ratelimiter.factoryImpl;

import career.machineinterview.ratelimiter.ratelimiterI.RateLimiterI;
import career.machineinterview.ratelimiter.ratelimiterImpl.FixedWindowRateLimiterImpl;
import career.machineinterview.ratelimiter.ratelimiterImpl.SlidingWindowFixedCounterLimiterImpl;
import career.machineinterview.ratelimiter.ratelimiterImpl.SlidingWindowLogRateLimiterImpl;

public class RateLimiterFactory {

    public static RateLimiterI getRateLimiter(String rateLimiter){
        RateLimiterI rateLimiterI = null;
        switch (rateLimiter){
            case "fixedwindow"      : rateLimiterI = new FixedWindowRateLimiterImpl();
                break;
            case "slidingwindowlog" : rateLimiterI = new SlidingWindowLogRateLimiterImpl();
                break;
            case "slidingwindowfixed" : rateLimiterI = new SlidingWindowFixedCounterLimiterImpl();
                break;
            default : rateLimiterI = new FixedWindowRateLimiterImpl();
                break;
        }
        return rateLimiterI;
    }
}
