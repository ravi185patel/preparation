package career.thirtydays.machine.ratelimiter.leakybucket;


import career.thirtydays.machine.ratelimiter.RateLimiterI;
import career.thirtydays.machine.ratelimiter.model.Bucket;
import career.thirtydays.machine.ratelimiter.model.LeakyBucket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

work for each request.
No dependent on user, url , domain.
 */
class LeakyBucketNormalRateLimiter implements RateLimiterI {
    private final Bucket bucket;

    public LeakyBucketNormalRateLimiter(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public boolean isRequestAllowed(String userId) {
          long currentTimeStamp = System.currentTimeMillis();
          long timeSinceLastRefill = currentTimeStamp - bucket.getLastRefillTimestamp(); // it is time for refill or not.

          int tokensToAdd = (int) (timeSinceLastRefill / 1000)*bucket.getRefillRate();
          if(tokensToAdd > 0){
              bucket.setTokens(Math.min(bucket.getCapacity(),bucket.getTokens() + tokensToAdd));
              bucket.setLastRefillTimestamp(currentTimeStamp);
          }

          if(bucket.getTokens() > 0){
              bucket.incUsedToken();
              return true;
          }else{
              return false;
          }
    }
}


class LeakyBucketConcurrentHashMapRateLimiter implements RateLimiterI {

    private final int capacity;
    private final int leakRate;
    private ConcurrentHashMap<String, LeakyBucket> userRequestBucketMap; // user vs bucket (can be same for that particular user)


    public LeakyBucketConcurrentHashMapRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.userRequestBucketMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(String userId) {
        userRequestBucketMap.putIfAbsent(userId,new LeakyBucket(capacity,leakRate));
        LeakyBucket bucket = userRequestBucketMap.get(userId);

        long currentTimeStamp = System.currentTimeMillis();
        int elapsedTime = (int) (currentTimeStamp - bucket.getLastLeakTimestamp())/1000;


        System.out.println(bucket.getWaterLevel()+" "+ bucket.getCapacity());
        int leakedRequests = elapsedTime * bucket.getLeakRate(); // no of tokens got leaked in given time elapsedTime
        bucket.setWaterLevel(Math.max(bucket.getWaterLevel()-leakedRequests,0)); // set tokens up to 0 to capacity - leakedRequests
        bucket.setLastLeakTimestamp(currentTimeStamp);

        System.out.println(bucket.getWaterLevel()+" "+ bucket.getCapacity()+" "+leakedRequests);
        if(bucket.getWaterLevel() < bucket.getCapacity()){
            System.out.println("in if");
            bucket.incWaterLevel();
            return true;
        }
        return false;
    }
}
public class LeakyBucketRateLimiter { // using deque implement it , still not understand this implementation
    public static void main(String[] args) throws InterruptedException {

        RateLimiterI rateLimiterI = new LeakyBucketConcurrentHashMapRateLimiter(5,6);


        // Simulate multiple clients and requests
        Runnable clientRequests = () -> {
            String clientId = Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                if (
                    rateLimiterI.isRequestAllowed(clientId)
                ) {
                    System.out.println(clientId + ": Request " + i + " allowed.");
                } else {
                    System.out.println(clientId + ": Request " + i + " denied (rate-limited).");
                }
                try {
                    TimeUnit.SECONDS.sleep(1); // Simulate 1 second between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Simulate two clients (two threads)
        Thread client1 = new Thread(clientRequests, "Client-1");
        Thread client2 = new Thread(clientRequests, "Client-2");

        client1.start();
        client2.start();

        client1.join();
        client2.join();

    }
}
