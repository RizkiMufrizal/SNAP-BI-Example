package org.rizki.mufrizal.snapbi.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rizki
 */
public class HashHelper {

    public static String LowerCaseMinify(String value) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(value, JsonNode.class);
        String requestBody = jsonNode.toString();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(requestBody.getBytes());
        byte[] hashedXArray = messageDigest.digest();
        String hexaSignature = StringHelper.toHexString(hashedXArray);
        return hexaSignature.toLowerCase();
    }

}
