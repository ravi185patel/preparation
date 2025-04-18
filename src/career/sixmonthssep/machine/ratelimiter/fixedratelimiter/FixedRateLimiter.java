package career.sixmonthssep.machine.ratelimiter.fixedratelimiter;


import career.sixmonthssep.machine.ratelimiter.RateLimiterI;
import career.sixmonthssep.machine.ratelimiter.model.RequestInfo;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
copied from chat gpt
 */

class FixedRateLimiterUsingHashMap implements RateLimiterI {
    private static final int FIXED_WINDOW_SIZE = 60000;
    private static final int MAX_REQUEST = 5;

    private HashMap<String, RequestInfo> requestInfoHashMap;

    public FixedRateLimiterUsingHashMap(){
        requestInfoHashMap = new HashMap<>();
    }

    public boolean isRequestAllowed(String userId){
        long currentTime = System.currentTimeMillis();
        RequestInfo requestInfo = requestInfoHashMap.getOrDefault(userId,new RequestInfo(0,currentTime));

        if(currentTime -  requestInfo.getTimestamp() > FIXED_WINDOW_SIZE){
            requestInfo = new RequestInfo(0,currentTime);
            System.out.println(" request got reset ");
        }

        if(requestInfo.getRequestCount() < MAX_REQUEST){
            requestInfo.setRequestCount(requestInfo.getRequestCount()+1);
            requestInfoHashMap.put(userId,requestInfo);
            return true;
        }
        return false;
    }
}

class FixedRateLimiterUsingConcurrentHashMap implements  RateLimiterI{
    private static final int FIXED_WINDOW_SIZE = 600;
    private static final int MAX_REQUEST = 5;

    private ConcurrentHashMap<String, RequestInfo> requestInfoHashMap;

    public FixedRateLimiterUsingConcurrentHashMap(){
        requestInfoHashMap = new ConcurrentHashMap<>();
    }

    public boolean isRequestAllowed(String userId){
        long currentTime = System.currentTimeMillis();
        RequestInfo requestInfo = requestInfoHashMap.getOrDefault(userId,new RequestInfo(0,currentTime));

        if(currentTime -  requestInfo.getTimestamp() > FIXED_WINDOW_SIZE){
            requestInfo = new RequestInfo(0,currentTime);
            System.out.println(" request got reset ");
        }

        if(requestInfo.getRequestCount() < MAX_REQUEST){
            requestInfo.setRequestCount(requestInfo.getRequestCount()+1);
            requestInfoHashMap.put(userId,requestInfo);
            return true;
        }
        System.out.println(" max request par");
        return false;
    }
}


class FixedRateLimiterUsingObjectLevelLock implements  RateLimiterI{
    private static final int FIXED_WINDOW_SIZE = 600;
    private static final int MAX_REQUEST = 5;

    private HashMap<String, RequestInfo> requestInfoHashMap;
    private final Object lock = new Object();

    public FixedRateLimiterUsingObjectLevelLock(){
        requestInfoHashMap = new HashMap<>();
    }

    public boolean isRequestAllowed(String userId){

        synchronized(lock) {

            long currentTime = System.currentTimeMillis();
            RequestInfo requestInfo = requestInfoHashMap.getOrDefault(userId, new RequestInfo(0, currentTime));

            if (currentTime - requestInfo.getTimestamp() > FIXED_WINDOW_SIZE) {
                requestInfo = new RequestInfo(0, currentTime);
                System.out.println(" request got reset ");
            }

            if (requestInfo.getRequestCount() < MAX_REQUEST) {
                requestInfo.setRequestCount(requestInfo.getRequestCount() + 1);
                requestInfoHashMap.put(userId, requestInfo);
                return true;
            }
            System.out.println(" max request par");
            return false;
        }
    }
}

class FixedRateLimiterUsingScheduleExecutor implements  RateLimiterI{
    private static final int FIXED_WINDOW_SIZE = 600;
    private static final int MAX_REQUEST = 5;

    private ConcurrentHashMap<String, RequestInfo> requestInfoHashMap;

    private final ScheduledExecutorService scheduler;

    public FixedRateLimiterUsingScheduleExecutor(){
        requestInfoHashMap = new ConcurrentHashMap<>();
        scheduler = Executors.newScheduledThreadPool(1);
        startWindowResetTask();
    }

    private void startWindowResetTask() {
        // Schedule a task to reset request counts every WINDOW_SIZE
        scheduler.scheduleAtFixedRate(this::resetRequestCounts, FIXED_WINDOW_SIZE, FIXED_WINDOW_SIZE, TimeUnit.MILLISECONDS);
    }

    private void resetRequestCounts() {
        // Reset request counts for all users at the end of each window
        requestInfoHashMap.clear();
        System.out.println("Window reset! All request counts cleared.");
    }

    public boolean isRequestAllowed(String userId){
            long currentTime = System.currentTimeMillis();
            RequestInfo requestInfo = requestInfoHashMap.getOrDefault(userId, new RequestInfo(0, currentTime));

            if (requestInfo.getRequestCount() < MAX_REQUEST) {
                requestInfo.setRequestCount(requestInfo.getRequestCount() + 1);
                requestInfoHashMap.put(userId, requestInfo);
                return true;
            }
            System.out.println(" max request par");
            return false;
    }
}

public class FixedRateLimiter {

    public static void main(String[] args) {

        RateLimiterI rateLimiter = null;
/*

        RateLimiterI rateLimiter = new FixedRateLimiterUsingHashMap();
        singleThreadEnv(rateLimiter); // 100 user worked
        System.out.println(" In multi thread env");
        multiThreadEnv(rateLimiter); // not worked for 100 user

*/

/*

        rateLimiter = new FixedRateLimiterUsingConcurrentHashMap();
        singleThreadEnv(rateLimiter); // 100 user worked
        System.out.println(" In multi thread env");
        multiThreadEnv(rateLimiter); // not worked for 100 user

*/

/*

        rateLimiter = new FixedRateLimiterUsingObjectLevelLock();
        singleThreadEnv(rateLimiter); // 100 user worked
        System.out.println(" In multi thread env");
        multiThreadEnv(rateLimiter); // not worked for 100 user
*/

        rateLimiter = new FixedRateLimiterUsingScheduleExecutor();
//        singleThreadEnv(rateLimiter); // 100 user worked
//        System.out.println(" In multi thread env");
        multiThreadEnv(rateLimiter); // not worked for 100 user
        // Sleep for a while to let window reset (you can adjust the duration as needed)
        try {
            Thread.sleep(1000 * 2); // Let the window reset twice
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        multiThreadEnv(rateLimiter); // not worked for 100 user
    }

    public static void singleThreadEnv(RateLimiterI RateLimiterI){
        String userId = "user1";

        for (int i = 0; i < 10; i++) {
            if (RateLimiterI.isRequestAllowed(userId)) {
                System.out.println("Request " + (i + 1) + " allowed");
            } else {
                System.out.println("Request " + (i + 1) + " denied");
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
        }
    }
}
