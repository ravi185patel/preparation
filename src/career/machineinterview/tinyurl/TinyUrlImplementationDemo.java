package career.machineinterview.tinyurl;

import career.machineinterview.tinyurl.tinyi.TinyUrlImplementation;

public class TinyUrlImplementationDemo {
    public static void main(String[] args) {
        TinyUrlImplementation tinyUrlImplementation = new TinyUrlImplementation();
        String tinyUrl = tinyUrlImplementation.getShortenUrl("https:\\\\google.com\\login.html");
        System.out.println(tinyUrl);
        String url = tinyUrlImplementation.getOriginalUrl(tinyUrl);
        System.out.println(url);
    }
}
