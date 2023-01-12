package org.rizki.mufrizal.snapbi.helper;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 *
 * @author Rizki
 */
public class SHA256WithRSA implements HashService {

    private static final String SHA256WITHRSA = "SHA256withRSA";

    @Override
    public String hash(String data, String key) throws Exception {
        key = key.replaceAll("\\n", "").replaceAll("-----BEGIN PRIVATE KEY-----", "").replaceAll("-----END PRIVATE KEY-----", "");
        key = key.replaceAll("\\n", "").replaceAll("-----BEGIN RSA PRIVATE KEY-----", "").replaceAll("-----END RSA PRIVATE KEY-----", "");

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance(SHA256WITHRSA);
        signature.initSign(privateKey);
        signature.update(data.getBytes(Charset.defaultCharset()));
        return StringHelper.toHexString(signature.sign());
    }

    @Override
    public Boolean validate(String data, String key, String signatureData) throws Exception {
        key = key.replaceAll("\\n", "").replaceAll("-----BEGIN PUBLIC KEY-----", "").replaceAll("-----END PUBLIC KEY-----", "");

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance(SHA256WITHRSA);
        signature.initVerify(publicKey);
        signature.update(data.getBytes(Charset.defaultCharset()));
        return signature.verify(StringHelper.hexStringToByteArray(signatureData));
    }
}
