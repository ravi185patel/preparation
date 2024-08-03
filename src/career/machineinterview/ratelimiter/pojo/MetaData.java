package career.machineinterview.ratelimiter.pojo;

public class MetaData {
    private final String domain;
    private final Long time;

    public MetaData(String domain, Long time) {
        this.domain = domain;
        this.time = time*1000;
    }

    public String getDomain() {
        return domain;
    }

    public Long getTime() {
        return time;
    }

}
