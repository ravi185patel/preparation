package career.machineinterview.tinyurl.tinyi;

import career.machineinterview.tinyurl.encoding.Base64Implementation;
import career.machineinterview.tinyurl.encoding.encodingi.EncodingI;
import career.machineinterview.tinyurl.hasing.MD5HashingImplementation;
import career.machineinterview.tinyurl.hasing.hashingi.HashingI;
import career.machineinterview.tinyurl.pojo.UrlMetaData;
import career.machineinterview.tinyurl.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TinyUrlImplementation {

    private Map<String,UrlMetaData> urlMetaDataMap;

    private Map<String,String> urlShortenUrlMap;
    private EncodingI encodingI;
    private HashingI hashingI;

    public TinyUrlImplementation() {
        this.urlShortenUrlMap = new HashMap<>();
        this.urlMetaDataMap = new HashMap<>();
        this.encodingI = new Base64Implementation();
        this.hashingI = new MD5HashingImplementation();
    }

    public TinyUrlImplementation(EncodingI encodingI,HashingI hashingI) {
        this.urlShortenUrlMap = new HashMap<>();
        this.urlMetaDataMap = new HashMap<>();
        this.encodingI = encodingI;
        this.hashingI = hashingI;
    }

    public String getShortenUrl(String url){
        UrlMetaData urlMetaData = getShortenUrlExists(url);
        if(Objects.nonNull(urlMetaData)){
            return urlMetaData.tinyUrl;
        }
        String pageName= Util.removeDomain(url);
        String hashing = url;
        if(Objects.nonNull(hashingI)) {
             hashing = hashingI.getHash(pageName);
        }
        String encodedHashing = hashing;
        if(Objects.nonNull(encodingI)){
            encodedHashing = encodingI.idToShortURL(hashing);
        }
        String shortenUrl = "https:\\tinyurl.com\\"+encodedHashing;
        urlMetaData = new UrlMetaData(new Date(),new Date(),url,shortenUrl);
        urlShortenUrlMap.put(shortenUrl,url);
        urlMetaDataMap.put(url,urlMetaData);
        return shortenUrl;
    }

    public String getOriginalUrl(String shortenUrl){
        return urlShortenUrlMap.getOrDefault(shortenUrl,shortenUrl);
    }

    public void removeExpiredUrl(String url){

    }

    public UrlMetaData getShortenUrlExists(String url){
        return urlMetaDataMap.getOrDefault(url,null);
    }

}
