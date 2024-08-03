package career.machineinterview.ratelimiter.ratelimiterImpl;

import career.machineinterview.ratelimiter.pojo.SlidingWindowLogModel;
import career.machineinterview.ratelimiter.ratelimiterI.RateLimiterI;

import java.time.Clock;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

public class SlidingWindowLogRateLimiterImpl implements RateLimiterI {
    Map<String, SlidingWindowLogModel> domainSlidingWindowLogModelMap;
    Map<String,Deque<Long>> domainDequeMap;

    Clock clock;

    public SlidingWindowLogRateLimiterImpl(){
        domainSlidingWindowLogModelMap = new HashMap<>();
        domainDequeMap = new HashMap<>();
        clock =  Clock.systemUTC();;
    }

    public SlidingWindowLogRateLimiterImpl(Clock clock){
        this();
        this.clock = clock;
    }

    public void registerApi(String domain,Long time,int counter){
        domainSlidingWindowLogModelMap.put(domain,new SlidingWindowLogModel(domain,time,counter));
        domainDequeMap.put(domain,new LinkedBlockingDeque<>());
    }

    public boolean accessApi(String api){
        String domain = RateLimiterI.getDomain(api,domainSlidingWindowLogModelMap.keySet());
        if(!Objects.nonNull(domain)){
            System.out.println("Domain is not registered with rate limiter :- "+api);
            return false;
        }

        SlidingWindowLogModel slidingWindowLogModel = domainSlidingWindowLogModelMap.get(domain);
        Long windowLengthSecond = slidingWindowLogModel.getTime();

        int activeRequest = removeRequest(domain,windowLengthSecond);

        if(slidingWindowLogModel.getMaxCount() > activeRequest){
            System.out.println("Thread = "+Thread.currentThread().getName()+" Request allowed for api = "+api);
            addRequest(domain);
            return true;
        }else{
            System.out.println("Thread = "+Thread.currentThread().getName()+" Request get discarded due to bucket is empty for api = "+api);
            return false;
        }
    }

    public int removeRequest(String domain,Long windowLengthSecond){
        long now = clock.millis();
        Deque<Long> localDateTimeDeque = domainDequeMap.get(domain);
        if(!localDateTimeDeque.isEmpty())
            System.out.println(localDateTimeDeque.getFirst()+" "+windowLengthSecond+" "+now);
        while(!localDateTimeDeque.isEmpty()
                && localDateTimeDeque.getFirst() + windowLengthSecond < now){
            System.out.println("Remove out dated request : "+localDateTimeDeque.getFirst());
            localDateTimeDeque.removeFirst();
        }
        return localDateTimeDeque.size();
    }

    public void addRequest(String domain){

        domainDequeMap.get(domain).add(clock.millis());
        System.out.println(domainDequeMap.get(domain).size());
    }

}
