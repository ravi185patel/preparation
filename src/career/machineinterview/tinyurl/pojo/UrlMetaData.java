package career.machineinterview.tinyurl.pojo;

import java.util.Date;

public class UrlMetaData {
    public Date creation;
    public Date expiration;
    public String url;
    public String tinyUrl;

    public UrlMetaData(Date creation, Date expiration, String url, String tinyUrl) {
        this.creation = creation;
        this.expiration = expiration;
        this.url = url;
        this.tinyUrl = tinyUrl;
    }
}
