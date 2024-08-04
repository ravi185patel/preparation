package career.machineinterview.tinyurl.hasing;

import career.machineinterview.tinyurl.hasing.hashingi.HashingI;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1HashingImplementation implements HashingI {

    MessageDigest md;

    public SHA1HashingImplementation(){
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception in getInstance method of MessageDigest : "+e);
        }
    }
    @Override
    public String getHash(String url) {

        byte[] messageDigest = md.digest(url.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashText = no.toString(16);

        while (hashText.length() < 40) {
            hashText = "0" + hashText;
        }

        return hashText;
    }
}
