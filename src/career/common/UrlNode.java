package career.common;

public class UrlNode {
    public UrlNode next, prev;
    public String key;
    public String value;
    public int freq=0;

    public UrlNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
