package org.rizki.mufrizal.snapbi.helper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Rizki
 */
public class HMACSHA512 implements HashService {

    private static final String HMACSHA512 = "HmacSHA512";

    @Override
    public String hash(String data, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMACSHA512);
        Mac mac = Mac.getInstance(HMACSHA512);
        mac.init(secretKeySpec);
        return StringHelper.toHexString(mac.doFinal(data.getBytes()));
    }

    @Override
    public Boolean validate(String data, String key, String signature) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMACSHA512);
        Mac mac = Mac.getInstance(HMACSHA512);
        mac.init(secretKeySpec);
        return StringHelper.toHexString(mac.doFinal(data.getBytes())).equals(signature);
    }
}
