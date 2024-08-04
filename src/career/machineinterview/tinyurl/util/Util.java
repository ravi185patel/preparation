package career.machineinterview.tinyurl.util;

public class Util {

    public static String removeDomain(String url){
        return url.substring(url.indexOf("\\"));
    }
}
