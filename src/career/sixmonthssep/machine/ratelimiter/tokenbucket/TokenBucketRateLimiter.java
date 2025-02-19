package career.sixmonthssep.machine.ratelimiter.tokenbucket;


import career.sixmonthssep.machine.ratelimiter.RateLimiterI;
import career.sixmonthssep.machine.ratelimiter.model.Bucket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

work for each request.
No dependent on user, url , domain.
 */
class TokenBucketNormalRateLimiter implements RateLimiterI {
    private final Bucket bucket;

    public TokenBucketNormalRateLimiter(Bucket bucket) {
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


class TokenBucketConcurrentHashMapRateLimiter implements RateLimiterI {

    private final int capacity;
    private final int refillRate;
    private ConcurrentHashMap<String,Bucket> userRequestBucketMap; // user vs bucket (can be same for that particular user)


    public TokenBucketConcurrentHashMapRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.userRequestBucketMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean isRequestAllowed(String userId) {
        userRequestBucketMap.putIfAbsent(userId,new Bucket(capacity,refillRate));
        Bucket bucket = userRequestBucketMap.get(userId);

        long currentTimeStamp = System.currentTimeMillis();
        int timeSinceLastRefill = (int) (currentTimeStamp - bucket.getLastRefillTimestamp())/1000;

        // Refill tokens based on elapsed time
        int tokensToAdd = timeSinceLastRefill * refillRate;
        if (timeSinceLastRefill > refillRate) {
            bucket.setTokens(Math.min(bucket.getCapacity(),bucket.getTokens() + tokensToAdd));
            bucket.setLastRefillTimestamp(currentTimeStamp);
        }

        if(bucket.getTokens() > 0){
            bucket.incUsedToken();
            return true;
        }
        return false;
    }
}


class TokenBucketRateLM {
    private final int capacity;         // Maximum tokens a bucket can hold
    private final int refillRate;       // Tokens added per second
    private final ConcurrentHashMap<String, TokenBucket> buckets; // Holds the token buckets for each client
    private final ConcurrentHashMap<String, Lock> locks; // Holds locks for each client

    // Constructor
    public TokenBucketRateLM(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.buckets = new ConcurrentHashMap<>();
        this.locks = new ConcurrentHashMap<>();
    }

    // Get or create a token bucket for a client
    private TokenBucket getTokenBucket(String clientId) {
        // If bucket doesn't exist, create and add a new one
        buckets.putIfAbsent(clientId, new TokenBucket(capacity, refillRate));
//        locks.putIfAbsent(clientId, new ReentrantLock());
        return buckets.get(clientId);
    }

    // Try to consume a token for a specific client
    public boolean tryConsume(String clientId) {
        locks.putIfAbsent(clientId, new ReentrantLock());
        Lock lock = locks.get(clientId);
        lock.lock(); // Locking for thread safety

        try {
            TokenBucket bucket = getTokenBucket(clientId);
            return bucket.tryConsume();
        } finally {
            lock.unlock(); // Always unlock after using the bucket
        }
    }

    // Inner class representing a token bucket for a client
    private static class TokenBucket {
        private final int capacity;
        private final int refillRate;
        private int tokens;
        private long lastRefillTimestamp;

        public TokenBucket(int capacity, int refillRate) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.tokens = capacity;  // Initially fill the bucket
            this.lastRefillTimestamp = System.currentTimeMillis();
        }

        // Try to consume one token (request)
        public boolean tryConsume() {
            long currentTime = System.currentTimeMillis();
            int timeSinceLastRefill = (int) (currentTime - lastRefillTimestamp)/1000;

            // Refill tokens based on elapsed time
            int tokensToAdd = timeSinceLastRefill * refillRate;
            System.out.println(timeSinceLastRefill +" "+ refillRate+" "+tokensToAdd+" "+tokens);
            if (timeSinceLastRefill > refillRate) {
                System.out.println(" refill ");
                tokens = Math.min(capacity, tokens + tokensToAdd);
                lastRefillTimestamp = currentTime;
            }

            // If tokens are available, consume one token
            if (tokens > 0) {
                tokens--;
                return true;
            } else {
                return false; // Rate-limited, no tokens available
            }
        }
    }
}
public class TokenBucketRateLimiter {
    public static void main(String[] args) throws InterruptedException {

        RateLimiterI rateLimiterI = new TokenBucketConcurrentHashMapRateLimiter(5,6);

        TokenBucketRateLM tokenBucketRateLM = new TokenBucketRateLM(5,6);

        // Simulate multiple clients and requests
        Runnable clientRequests = () -> {
            String clientId = Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                if (
//                        tokenBucketRateLM.tryConsume(clientId)
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
