package career.machineinterview.tinyurl.encoding;

import career.machineinterview.tinyurl.encoding.encodingi.EncodingI;

import java.util.Base64;

public class Base64Implementation implements EncodingI {

    @Override
    public String idToShortURL(String hashing) {
        return Base64.getUrlEncoder().encodeToString(hashing.getBytes());
//        StringBuffer shortUrl = new StringBuffer();
//        while (key > 0)
//        {
//            shortUrl.append(charMap62[key % 62]);
//            key = key / 62;
//        }
//        return shortUrl.reverse().toString();
    }

    @Override
    public String shortURLToId(String hashValue) {
        byte[] decodeHashValue =  Base64.getUrlDecoder().decode(hashValue.getBytes());
        return String.valueOf(decodeHashValue);

//        int id = 0;
//
//        for (int i = 0; i < shortURL.length(); i++)
//        {
//            if ('a' <= shortURL.charAt(i) &&
//                    shortURL.charAt(i) <= 'z')
//                id = id * 62 + shortURL.charAt(i) - 'a';
//            if ('A' <= shortURL.charAt(i) &&
//                    shortURL.charAt(i) <= 'Z')
//                id = id * 62 + shortURL.charAt(i) - 'A' + 26;
//            if ('0' <= shortURL.charAt(i) &&
//                    shortURL.charAt(i) <= '9')
//                id = id * 62 + shortURL.charAt(i) - '0' + 52;
//        }
//        return id;
    }
}
