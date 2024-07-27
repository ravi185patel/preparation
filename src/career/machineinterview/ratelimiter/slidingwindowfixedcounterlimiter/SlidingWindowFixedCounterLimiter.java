package career.machineinterview.ratelimiter.slidingwindowfixedcounterlimiter;

import career.machineinterview.ratelimiter.common.Metadata;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SlidingWindowFixedCounterLimiter {

    Map<String, Metadata> urlMetadataMap;
    Map<String, Map<LocalDateTime,Integer>> urlLocalDateCounterMap;
    Map<String,Integer> urlCounterMap;

    private LocalDateTime startTime;

    public SlidingWindowFixedCounterLimiter(int size,int time){
        urlLocalDateCounterMap = new TreeMap<>();
        urlCounterMap = new HashMap<>();
        startTime = LocalDateTime.now();
        urlMetadataMap = new HashMap<>();
    }

    public void registerApi(String api,int size,int time){
        urlMetadataMap.put(api,new Metadata(api,size,time));
        urlLocalDateCounterMap.put(api,new HashMap<>());
        urlCounterMap.put(api,0);
    }

    public boolean accessApi(String api){
        System.out.println(api+" "+urlMetadataMap.get(api).getTime()+" "+urlMetadataMap.get(api).getSize());
        System.out.println(urlLocalDateCounterMap);
        String threadName = Thread.currentThread().getName();
        removeOutDatedRequest(api);
        if(requestEligibleForProcessOrNot(api)){
            increaseCounterOfTimeOfHitApi(api);
            System.out.println("Thread = "+threadName+" Request allowed for api = "+api);
        }else{
            System.out.println("Thread = "+threadName+" Request get discarded due to bucket is empty for api = "+api);
        }

        try{
            Thread.sleep(1000*5);
        }catch (Exception e){

        }
        removeProcessedRequest(api);
        return false;
    }


    private synchronized boolean requestEligibleForProcessOrNot(String api){
        return urlCounterMap.get(api) < urlMetadataMap.get(api).getSize();
    }

    private void increaseCounterOfTimeOfHitApi(String api){
        Map<LocalDateTime,Integer> dateTimeIntegerMap = urlLocalDateCounterMap.get(api);
        LocalDateTime localDateTime = LocalDateTime.now();
        dateTimeIntegerMap.put(localDateTime,dateTimeIntegerMap.getOrDefault(localDateTime,0)+1);
        urlCounterMap.put(api,urlCounterMap.getOrDefault(api,0)+1);
    }

    private void removeProcessedRequest(String api){
        removeRequest(api);
    }
    private void removeOutDatedRequest(String api){
        removeRequest(api);
    }

    private void removeRequest(String api){
        Map<LocalDateTime,Integer> dateTimeIntegerMap = urlLocalDateCounterMap.get(api);
        Metadata metadata = urlMetadataMap.get(api);
        for(LocalDateTime localDateTime:dateTimeIntegerMap.keySet()) {
            LocalDateTime now = LocalDateTime.now().minusSeconds((long)metadata.getTime());
            if (localDateTime.isBefore(now)) {
                System.out.println(LocalDateTime.now() + " <> " + now + " remove localdatetime fun :- " + localDateTime);
                Integer count = dateTimeIntegerMap.get(localDateTime);
                dateTimeIntegerMap.remove(localDateTime);
                Integer counter =  count - urlCounterMap.getOrDefault(api, 0);
                if (counter >= 0) {
                    urlCounterMap.put(api, counter);
                }
            } else {
                break;
            }
        }
    }

}
