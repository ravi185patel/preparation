package career.machineinterview.ratelimiter.ratelimiterI;

import java.util.Set;

public interface RateLimiterI {
 public void registerApi(String api,Long time,int counter);
 public boolean accessApi(String api);
 public void addRequest(String api);
 public int removeRequest(String api,Long windowLengthSecond);

  public static String getDomain(String api, Set<String> domains){
    for(String domain:domains){
      if(api.contains(domain)){
        return domain;
      }
    }
    return null;
  }
}
