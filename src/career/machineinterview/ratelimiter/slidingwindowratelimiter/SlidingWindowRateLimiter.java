package career.machineinterview.ratelimiter.slidingwindowratelimiter;

import career.machineinterview.ratelimiter.common.Metadata;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SlidingWindowRateLimiter {
    Map<String, Metadata> urlMetadataMap;
    Map<String,PriorityQueue<LocalDateTime>> urlMetadataPqMap; // you can use deque/doubly linked list

    private LocalDateTime startTime;

    public SlidingWindowRateLimiter(){
        urlMetadataMap = new HashMap<>();
        urlMetadataPqMap = new HashMap<>();
        startTime = LocalDateTime.now();
    }

    public void registerApi(String api,int size,int time){
        urlMetadataMap.put(api,new Metadata(api,size,time));
        urlMetadataPqMap.put(api,new PriorityQueue<>((ld1,ld2) -> {
            return ld1.compareTo(ld2);
        }));
    }

    public boolean accessApi(String api){
        System.out.println(api+" "+urlMetadataMap.get(api).getTime()+" "+urlMetadataMap.get(api).getSize());
        System.out.println(urlMetadataPqMap);
        String threadName = Thread.currentThread().getName();
        removeOutDatedRequest(api);
        if(requestEligibleForProcessOrNot(api)){
            urlMetadataPqMap.get(api).add(LocalDateTime.now());
            System.out.println("Thread = "+threadName+" Request allowed for api = "+api);
        }else{
            System.out.println("Thread = "+threadName+" Request get discarded due to bucket is empty for api = "+api);
        }

        try{
            Thread.sleep(1000*5);
        }catch (Exception e){

        }
//        removeProcessedRequest(api);
        return false;
    }

    private boolean requestEligibleForProcessOrNot(String api){
        return urlMetadataPqMap.get(api).size() < urlMetadataMap.get(api).getSize();
    }

    private void removeProcessedRequest(String api){
        System.out.println("remove localdatetime:- "+urlMetadataPqMap.get(api).poll());
    }

    private void removeOutDatedRequest(String api){
        PriorityQueue<LocalDateTime> localDateTimes = urlMetadataPqMap.get(api);
        Metadata metadata = urlMetadataMap.get(api);
        while(!localDateTimes.isEmpty()){
              LocalDateTime localDateTime = localDateTimes.peek();
              LocalDateTime now = LocalDateTime.now().minusSeconds((long)metadata.getTime());
              if(localDateTime.isBefore(now)){
                  System.out.println(LocalDateTime.now()+" <> "+now+" remove localdatetime fun :- "+localDateTime);
                  localDateTimes.remove(localDateTime);
              }else{
                  break;
              }
        }
    }

}

