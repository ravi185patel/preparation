package career.machineinterview.ratelimiter.ratelimiterImpl;

import career.machineinterview.ratelimiter.pojo.FixedWindowCounterModel;
import career.machineinterview.ratelimiter.ratelimiterI.RateLimiterI;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FixedWindowRateLimiterImpl implements RateLimiterI {
    Map<String, FixedWindowCounterModel> domainFixedWindowCounterModelMap;
    Clock clock;

    Long reset;

    public FixedWindowRateLimiterImpl() {
        this.domainFixedWindowCounterModelMap = new HashMap<>();
        this.clock = Clock.systemUTC();
        reset=0l;
    }

    public FixedWindowRateLimiterImpl(Clock clock){
        this();
        this.clock = Clock.systemUTC();
    }

    @Override
    public void registerApi(String domain, Long time, int counter) {
        domainFixedWindowCounterModelMap.put(domain,new FixedWindowCounterModel(domain,time,counter));
    }

    @Override
    public boolean accessApi(String api) {
        String domain = RateLimiterI.getDomain(api,domainFixedWindowCounterModelMap.keySet());
        if(!Objects.nonNull(domain)){
            System.out.println("Domain is not registered with rate limiter :- "+api);
            return false;
        }
        FixedWindowCounterModel fixedWindowCounterModel = domainFixedWindowCounterModelMap.get(domain);
        Long windowLengthSecond = fixedWindowCounterModel.getTime();
        int activeTokens = removeRequest(domain,windowLengthSecond);

        if(activeTokens <= fixedWindowCounterModel.getTokens()){
            System.out.println("Thread = "+Thread.currentThread().getName()+" Request allowed for api = "+api);
            addRequest(domain);
            return true;
        }else{
            System.out.println("Thread = "+Thread.currentThread().getName()+" Request get discarded due to bucket is empty for api = "+api);
            return false;
        }
    }

    @Override
    public void addRequest(String api) {
        domainFixedWindowCounterModelMap.get(api).getToken();
    }

    @Override
    public int removeRequest(String domain,Long windowLengthSecond){
        long now = clock.millis();
        FixedWindowCounterModel fixedWindowCounterModel = domainFixedWindowCounterModelMap.get(domain);
        System.out.println((now/windowLengthSecond)+" "+now);
        if(reset != (now/windowLengthSecond)){
            reset = (now/windowLengthSecond);
            System.out.println("Reset tokens..");
            fixedWindowCounterModel.resetToken();
        }
        return fixedWindowCounterModel.getActiveTokens();
    }

}
