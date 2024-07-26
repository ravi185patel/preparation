package career.machineinterview.ratelimiter.common;

import java.time.LocalDateTime;

public class Metadata {

    private Integer totalToken;
    private Integer time;
    private String api;
    private Integer token;
    private Integer resetTimer;

    private LocalDateTime insertTime;

    private Integer size;

    public Metadata(Integer token, Integer time, String api) {
        this.totalToken = token;
        this.time = time;
        this.api = api;
        this.token = token;
    }

    public void resetToken() {
        token = this.totalToken;
    }

    public boolean getToken() {
        if (token > 0) {
            token--;
            return true;
        }
        return false;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getResetTimer() {
        return resetTimer;
    }

    public void setResetTimer(Integer resetTimer) {
        this.resetTimer = resetTimer;
    }

    public Metadata(String api, int size, int time) {
        this.size = size;
        this.api = api;
        this.time = time;
    }

    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "totalToken=" + totalToken +
                ", time=" + time +
                ", api='" + api + '\'' +
                ", token=" + token +
                ", resetTimer=" + resetTimer +
                ", insertTime=" + insertTime +
                ", size=" + size +
                '}';
    }
}
