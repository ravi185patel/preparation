package career.machineinterview.ratelimiter.ratelimiterImpl;

import career.machineinterview.ratelimiter.pojo.FixedWindowCounterModel;
import career.machineinterview.ratelimiter.pojo.SlidingWindowLogFixedCounterModel;
import career.machineinterview.ratelimiter.pojo.SlidingWindowLogModel;
import career.machineinterview.ratelimiter.ratelimiterI.RateLimiterI;

import java.time.Clock;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

class Node{
    Long time;
    Integer counter;

    public Node(Long time, Integer counter) {
        this.time = time;
        this.counter = counter;
    }

    public Long getTime() {
        return time;
    }

    public Integer getCounter() {
        return counter;
    }
}
public class SlidingWindowFixedCounterLimiterImpl implements RateLimiterI {

    Map<String, SlidingWindowLogFixedCounterModel> domainSlidingWindowLogFixedCounterModelMap;
    Map<String, Deque<Node>> domainDequeMap;

    Map<String,Integer> domainCounterMap;
    Clock clock;

    public SlidingWindowFixedCounterLimiterImpl(){
        domainSlidingWindowLogFixedCounterModelMap = new HashMap<>();
        domainDequeMap = new HashMap<>();
        domainCounterMap = new HashMap<>();
        clock =  Clock.systemUTC();;
    }

    public SlidingWindowFixedCounterLimiterImpl(Clock clock){
        this();
        this.clock = clock;
    }

    public void registerApi(String api,Long time,int counter){
        domainSlidingWindowLogFixedCounterModelMap.put(api,new SlidingWindowLogFixedCounterModel(api,time,counter));
        domainDequeMap.put(api,new LinkedBlockingDeque<>());
        domainCounterMap.put(api,0);
    }

    public boolean accessApi(String api){
        SlidingWindowLogModel slidingWindowLogModel = domainSlidingWindowLogFixedCounterModelMap.get(api);
        Long windowLengthSecond = slidingWindowLogModel.getTime()*1000;

        int activeRequest = removeRequest(api,windowLengthSecond);

        if(slidingWindowLogModel.getMaxCount() >= activeRequest){
            System.out.println("Thread = "+Thread.currentThread().getName()+" Request allowed for api = "+api);
            addRequest(api);
            return true;
        }else{
            System.out.println("Thread = "+Thread.currentThread().getName()+" Request get discarded due to bucket is empty for api = "+api);
            return false;
        }
    }

    public int removeRequest(String api,Long windowLengthSecond){
        long now = clock.millis();
        Deque<Node> deque = domainDequeMap.get(api);
        while(!deque.isEmpty() && deque.getFirst().time + windowLengthSecond < now ){
            int count = deque.getFirst().counter;
            deque.removeFirst();
            domainCounterMap.put(api,
                    domainCounterMap.getOrDefault(api,count)-count);
        }
        return domainCounterMap.getOrDefault(api,0);
    }

    public void addRequest(String api){
        long now = clock.millis();
        Deque<Node> deque = domainDequeMap.get(api);
        for(Node node:deque){
            if(node.time == now){
                node.counter++;
                domainCounterMap.put(api,domainCounterMap.getOrDefault(api,0)+1);
                break;
            }
        }
    }

}
