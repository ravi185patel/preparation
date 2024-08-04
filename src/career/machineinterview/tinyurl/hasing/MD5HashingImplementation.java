package career.machineinterview.tinyurl.hasing;

import career.machineinterview.tinyurl.hasing.hashingi.HashingI;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingImplementation implements HashingI {

    MessageDigest md;

    public MD5HashingImplementation(){
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception in getInstance method of MessageDigest : "+e);
        }
    }

    @Override
    public String getHash(String intput) {
        byte[] messageDigest = md.digest(intput.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < messageDigest.length; ++i) {
            sb.append(Integer.toHexString((messageDigest[i] & 0xFF) | 0x100).substring(1,3));
        }
        String hashText = sb.toString();

//        BigInteger no = new BigInteger(1, messageDigest);
//        String hashText = no.toString(16);
//        while (hashText.length() < 6) {
//            hashText = "0" + hashText;
//        }
        return hashText.substring(0,3);
    }
}
