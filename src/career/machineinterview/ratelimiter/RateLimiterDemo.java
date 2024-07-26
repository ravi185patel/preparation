package career.machineinterview.ratelimiter;

import career.machineinterview.ratelimiter.slidingwindowratelimiter.SlidingWindowRateLimiter;

public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter();
        slidingWindowRateLimiter.registerApi("xyz",3,20);

        slidingWindowRateLimiter.accessApi("xyz");
        slidingWindowRateLimiter.accessApi("xyz");
        Thread.sleep(1000*10);
        slidingWindowRateLimiter.accessApi("xyz");
        slidingWindowRateLimiter.accessApi("xyz"); // dropped

//        Thread.sleep(1000*20);

        slidingWindowRateLimiter.accessApi("xyz");
        Thread.sleep(100);
        slidingWindowRateLimiter.accessApi("xyz");
        Thread.sleep(100);
        slidingWindowRateLimiter.accessApi("xyz");
        Thread.sleep(100);
        slidingWindowRateLimiter.accessApi("xyz"); // dropped

    }
}
