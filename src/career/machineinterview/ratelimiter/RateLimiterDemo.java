package career.machineinterview.ratelimiter;

import career.machineinterview.ratelimiter.factoryImpl.RateLimiterFactory;
import career.machineinterview.ratelimiter.ratelimiterI.RateLimiterI;
public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {

//        testSlidingWindowLogRateLimiter();
        testFixedWindowCounterRateLimiter();

    }


    public static void testSlidingWindowLogRateLimiter() throws InterruptedException {
        RateLimiterI rateLimiterI = RateLimiterFactory.getRateLimiter("slidingwindowlog");
        rateLimiterI.registerApi("atlassian",20l,5);

        rateLimiterI.accessApi("https://atlassian.com");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/search");
        Thread.sleep(1000*10);
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/jobapi");
        Thread.sleep(1000*10);
        rateLimiterI.accessApi("https://atlassian.com/submit");

        Thread.sleep(1000*20);
        rateLimiterI.accessApi("https://atlassian.com");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/jobapi");
        rateLimiterI.accessApi("https://atlassian.com/submit");
    }

    public static void testFixedWindowCounterRateLimiter() throws InterruptedException {
        RateLimiterI rateLimiterI = RateLimiterFactory.getRateLimiter("fixedwindow");
        rateLimiterI.registerApi("atlassian",20l,5);

        rateLimiterI.accessApi("https://atlassian.com");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/search");
        Thread.sleep(1000*10);
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/jobapi");
        Thread.sleep(1000*10);
        rateLimiterI.accessApi("https://atlassian.com/submit");

        Thread.sleep(1000*20);
        rateLimiterI.accessApi("https://atlassian.com");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/search");
        rateLimiterI.accessApi("https://atlassian.com/jobapi");
        rateLimiterI.accessApi("https://atlassian.com/submit");
    }
}
