package org.rizki.mufrizal.snapbi.helper;

/**
 *
 * @author Rizki
 */
public interface HashService {

    public String hash(String data, String key) throws Exception;

    public Boolean validate(String data, String key, String signature) throws Exception;
}
