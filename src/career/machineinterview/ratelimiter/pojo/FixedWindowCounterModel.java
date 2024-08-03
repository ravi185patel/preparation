package career.machineinterview.ratelimiter.pojo;

public class FixedWindowCounterModel extends MetaData{

    private final Integer tokens;
    private Integer usedTokens;

    public FixedWindowCounterModel(String domain, Long time,Integer tokens) {
        super(domain, time);
        this.tokens = tokens;
    }

    public Integer getTokens() {
        return tokens;
    }

    public Boolean getToken() {
        if(usedTokens <= tokens){
            usedTokens++;
            return true;
        }else{
            return false;
        }
    }

    public void resetToken(){
        usedTokens =0;
    }

    public void setToken(){
        if(usedTokens == 0){
            return;
        }
        usedTokens--;
    }

    public int getActiveTokens(){
        return tokens - usedTokens;
    }

}
