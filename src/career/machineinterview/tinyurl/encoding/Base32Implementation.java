package career.machineinterview.tinyurl.encoding;

import career.machineinterview.tinyurl.encoding.encodingi.EncodingI;

public class Base32Implementation implements EncodingI {

    @Override
    public String idToShortURL(String hashing) {
//        return Base32.getEncoder().encodeToString(hashing.getBytes());
//        StringBuffer shortUrl = new StringBuffer();
//        while (key > 0)
//        {
//            shortUrl.append(charMap62[key % 62]);
//            key = key / 62;
//        }
//        return shortUrl.reverse().toString();
        return null;
    }

    @Override
    public String shortURLToId(String hashValue) {
//        byte[] decodeHashValue =  Base32.getDecoder().decode(hashValue.getBytes());
//        return String.valueOf(decodeHashValue);

        return null;

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
    
//    @Override
//    public String idToShortURL(int key) {
//        StringBuffer shorturl = new StringBuffer();
//        while (key > 0)
//        {
//            shorturl.append(charMap32[key % 32]);
//            key = key / 32;
//        }
//        return shorturl.reverse().toString();
//    }
//
//    @Override
//    public String shortURLToId(String shortURL) {
//        int id = 0;
//
//        for (int i = 0; i < shortURL.length(); i++)
//        {
//            if ('A' <= shortURL.charAt(i) &&
//                    shortURL.charAt(i) <= 'Z')
//                id = id * 32 + shortURL.charAt(i) - 'A';
//            if ('2' <= shortURL.charAt(i) &&
//                    shortURL.charAt(i) <= '9' && shortURL.charAt(i) != '8')
//                id = id * 32 + shortURL.charAt(i) - '0' + 26;
//        }
//        return id;
//    }
}
