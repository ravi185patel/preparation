package career.thirtydays.machine.ratelimiter.slidingwindow;

import career.thirtydays.machine.ratelimiter.RateLimiterI;
import career.thirtydays.machine.ratelimiter.model.RequestInfo;
import com.sun.source.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class SlidingWindowRateLimiterLinkedList implements RateLimiterI {
    private final long WINDOW_SIZE = 60; // 60 seconds
    private final int MAX_REQUESTS = 5;
    private LinkedList<Long> requestTimestamps; //RequestInfo


    public SlidingWindowRateLimiterLinkedList(){
        requestTimestamps = new LinkedList<>();
    }


    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTimeStamp = System.currentTimeMillis()/1000; // convert into second
        long currentWindowStart = currentTimeStamp - WINDOW_SIZE;
        while(!requestTimestamps.isEmpty() && requestTimestamps.peekFirst() < currentWindowStart){
            requestTimestamps.remove();
        }

        // max allowed requests.
        if(requestTimestamps.size() < MAX_REQUESTS){
            requestTimestamps.add(currentTimeStamp);
            return true;
        }

        return false;
    }
}

class SlidingWindowRateLimiterQueue implements RateLimiterI {
    private final long WINDOW_SIZE = 60; // 60 seconds
    private final int MAX_REQUESTS = 5;
    private Queue<Long> requestTimestamps; //RequestInfo


    public SlidingWindowRateLimiterQueue(){
        requestTimestamps = new LinkedList<>();
    }


    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTimeStamp = System.currentTimeMillis()/1000; // convert into second
        long currentWindowStart = currentTimeStamp - WINDOW_SIZE;
        while(!requestTimestamps.isEmpty() && requestTimestamps.peek() < currentWindowStart){
            requestTimestamps.poll();
        }

        // max allowed requests.
        if(requestTimestamps.size() < MAX_REQUESTS){
            requestTimestamps.offer(currentTimeStamp);
            return true;
        }

        return false;
    }
}

class SlidingWindowRateLimiterTreeSet implements RateLimiterI {
    private final long WINDOW_SIZE = 60; // 60 seconds
    private final int MAX_REQUESTS = 5;
    private TreeSet<Long> requestTimestamps; //RequestInfo


    public SlidingWindowRateLimiterTreeSet(){
        requestTimestamps = new TreeSet<>();
    }


    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTimeStamp = System.currentTimeMillis()/1000; // convert into second
        long currentWindowStart = currentTimeStamp - WINDOW_SIZE;

        // Remove all requests older than the sliding window
        requestTimestamps.headSet(currentWindowStart).clear();

        // max allowed requests.
        if(requestTimestamps.size() < MAX_REQUESTS){
            requestTimestamps.add(currentTimeStamp);
            return true;
        }

        return false;
    }
}
/* if we have condition on specific url or domain
above all are just on current time stamp which means for every url it will go and check for current time stamp
1] login
2] submit will check login time stamp but if i don't won't to check login

ex
1] flipkart add
2] purchase both are different url for user.
 */
class SlidingWindowRateLimiterHashMap implements RateLimiterI {
    private final long WINDOW_SIZE = 60; // 60 seconds
    private final int MAX_REQUESTS = 5;
    private Map<String, List<Long>> requestTimestamps;


    public SlidingWindowRateLimiterHashMap(){
        requestTimestamps = new HashMap<>();
    }


    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTimeStamp = System.currentTimeMillis()/1000; // convert into second
        long currentWindowStart = currentTimeStamp - WINDOW_SIZE;

        List<Long> userRequests = requestTimestamps.computeIfAbsent(userId, k -> new ArrayList<>());

        userRequests.removeIf(timestamp -> timestamp < currentWindowStart);

        // max allowed requests.
        if(userRequests.size() < MAX_REQUESTS){
            userRequests.add(currentTimeStamp);
            return true;
        }

        return false;
    }
}

class SlidingWindowRateLimiterConcurrentHashMap implements RateLimiterI {
    private final long WINDOW_SIZE = 60; // 60 seconds
    private final int MAX_REQUESTS = 5;
    private ConcurrentHashMap<String, List<Long>> requestTimestamps;


    public SlidingWindowRateLimiterConcurrentHashMap(){
        requestTimestamps = new ConcurrentHashMap<>();
    }


    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTimeStamp = System.currentTimeMillis()/1000; // convert into second
        long currentWindowStart = currentTimeStamp - WINDOW_SIZE;

        List<Long> userRequests = requestTimestamps.computeIfAbsent(userId, k -> new ArrayList<>());

        userRequests.removeIf(timestamp -> timestamp < currentWindowStart);

        // max allowed requests.
        if(userRequests.size() < MAX_REQUESTS){
            userRequests.add(currentTimeStamp);
            return true;
        }

        return false;
    }
}



class SlidingWindowRateLimiterObject implements RateLimiterI {
    private final long WINDOW_SIZE = 60; // 60 seconds
    private final int MAX_REQUESTS = 5;
    private final Map<String, Deque<Long>> requestTimestamps;
    /*
    Feature 	Deque	                                            List
    -------------------------------------------------------------------------------------------------------------
    Operations	Efficient at both ends (add/remove)	            Efficient at random access (index)
    -------------------------------------------------------------------------------------------------------------
    Performance for Access	Slower for random access(O(n))	    Faster for random access (O(1))
    Insertions
    -------------------------------------------------------------------------------------------------------------
    Insertions/Removals	Efficient at both ends (O(1))	                Inserting/removing in middle is slow
    Use Case	Sliding window, double-ended queue, stack,
                queue	                                        Random access, ordered collection, dynamic size
    ;-------------------------------------------------------------------------------------------------------------
    Thread
    Safety	    Not thread-safe by default	                    Not thread-safe by default
    -------------------------------------------------------------------------------------------------------------
     */
    private final Object lock;


    public SlidingWindowRateLimiterObject(){
        requestTimestamps = new HashMap<>();
        this.lock = new Object();
    }


    @Override
    public boolean isRequestAllowed(String userId) {
        long currentTimestamp = System.currentTimeMillis() / 1000; // Get current time in seconds
        long windowStart = currentTimestamp - WINDOW_SIZE;

        synchronized (lock) {
            // Fetch the user's request list or initialize it if it doesn't exist
            Deque<Long> userRequests = requestTimestamps.computeIfAbsent(userId, k -> new ArrayDeque<>());

            // Remove timestamps that are older than the sliding window
//            userRequests.remove(timestamp -> timestamp < windowStart);

            // Clean up old timestamps that are outside the time window
            while (!userRequests.isEmpty() && userRequests.peek() <= windowStart) {
                userRequests.poll(); // Remove the oldest timestamp
            }

            // Check if the request count is below the allowed limit
            if (userRequests.size() < MAX_REQUESTS) {
                userRequests.add(currentTimestamp); // Add the new timestamp
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        SlidingWindowRateLimiterObject rateLimiter = new SlidingWindowRateLimiterObject();

    }
}

public class SlidingWindowRateLimiter {
    public static void main(String[] args) {
        RateLimiterI rateLimiter = new SlidingWindowRateLimiterLinkedList();

//        singleThreadEnv(rateLimiter);
        multiThreadEnv(rateLimiter);
    }



    public static void singleThreadEnv(RateLimiterI RateLimiterI){
        String userId = "user1";

        for (int i = 0; i < 10; i++) {
            if (RateLimiterI.isRequestAllowed(userId)) {
                System.out.println("Request " + (i + 1) + " allowed");
            } else {
                System.out.println("Request " + (i + 1) + " denied");
            }
            try {
                Thread.sleep(1000); // Simulate a delay of 10 seconds between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void multiThreadEnv(RateLimiterI RateLimiterI){
        String userId = "user2";

        // Simulate requests from different threads
        for (int i = 0; i < 10; i++) {
            final int requestNumber = i + 1;
            new Thread(() -> {
                if (RateLimiterI.isRequestAllowed(userId)) {
                    System.out.println("Request " + requestNumber + " allowed");
                } else {
                    System.out.println("Request " + requestNumber + " denied");
                }
            }).start();

            try {
                Thread.sleep(1000); // Simulate a delay of 10 seconds between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
