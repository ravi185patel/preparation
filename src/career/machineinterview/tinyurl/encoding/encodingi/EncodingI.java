package career.machineinterview.tinyurl.encoding.encodingi;

public interface EncodingI {
    final char charMap62[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    final char charMap32[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ2345679".toCharArray();

    public String idToShortURL(String input);
    public String shortURLToId(String input);
}
