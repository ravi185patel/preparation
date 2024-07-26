package career.machineinterview.ratelimiter.fixedwindowlimiter;

import career.machineinterview.ratelimiter.common.Metadata;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter {

    Map<String, Metadata> urlMetadataMap;

    private LocalDateTime startTime;

    public FixedWindowRateLimiter(){
        urlMetadataMap = new HashMap<>();
        startTime = LocalDateTime.now();
    }

    public void registerApi(String api,int time,int token){
        urlMetadataMap.put(api,new Metadata(time,token,api));
    }

    public boolean accessApi(String api){
        String threadName = Thread.currentThread().getName();
        resetTokenBasedOnTimeInterval(api);
        if(reduceCounterOfApi(api)){
           System.out.println("Thread = "+threadName+" Request allowed for api = "+api);
           return true;
        }else{
           System.out.println("Thread = "+threadName+" Request get discarded due to bucket is empty for api = "+api);
        }
        return false;
    }

    public void resetTokenBasedOnTimeInterval(String api){
        Metadata metadata = urlMetadataMap.get(api);
        Duration duration = Duration.between(LocalDateTime.now(),startTime);
        long reminder = duration.getSeconds()/metadata.getTime();

        if(metadata.getResetTimer() != reminder){
            metadata.resetToken();
            metadata.setResetTimer((int)reminder);
        }
    }

    public synchronized boolean reduceCounterOfApi(String api){ // atomic counter
        return  urlMetadataMap.get(api).getToken();
    }
}
