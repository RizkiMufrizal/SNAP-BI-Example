package org.rizki.mufrizal.snapbi;

import org.rizki.mufrizal.snapbi.helper.DateHelper;
import org.rizki.mufrizal.snapbi.helper.HMACSHA512;
import org.rizki.mufrizal.snapbi.helper.HashHelper;
import org.rizki.mufrizal.snapbi.helper.HashService;
import org.rizki.mufrizal.snapbi.helper.SHA256WithRSA;

/**
 *
 * @author Rizki
 */
public class App {

    public static void main(String[] args) throws Exception {
        var message = "Hello Hash";
        System.out.println("Data Hash : " + message);

        HashService hashSHA256WithRSAService = new SHA256WithRSA();
        var privateKey = """
                         -----BEGIN PRIVATE KEY-----
                         MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC58/08J1aOdvKE
                         6NpSDYQWhr8jAt+dHhzAb7MB7zE36ZbhS9TUoV3CbfxpGZkZlucILepnm4HAtw26
                         bCt3TIvK5fLswMs6smhIPnNkgXLtrAE0HjD3TGvZo53jCqOOt+kbEtDDeymyiYDa
                         A7/9Y4Uz+mG863ZkU3IcYvVUiClppsR2SG5AZ2zWNm0N5/p+0pZlI/EuBIX6ger7
                         ztRNQC4uZ2b6oU77RBhHMkSKiLzSGs9w1iEzvPoAW/FvBPpH/rj9349poDRLftIa
                         0P1JhFTkChCpzj0tf+2YaugNpf5E7l2Uo2QmkbneDV6F8lWXQ8UCPV0a4FQjsJN5
                         EXyPpFUFAgMBAAECggEAFq6WQxIhUrtbla65gqy3YfAJvIGLOSUFKMv3L9M3U7Sg
                         fNa9HTqZry+vTDqdHCv1/l9rajvjtdgeSpELNEAYBSXLXemeYIiGg9lqReYsWKVU
                         eED+hAvEVAXLKniPvsbcHjtLT6zkRyzSlMVEU+ohw1dVOLAX/uDW2HORrT/2nTZP
                         Gfrs6ZD4SkXzSo9tPQtUY3O21OYFULdgYBKHINOUd543Xa4EW7JRoHR29+u8zEGp
                         CeKf+XCb5p+sUj3ofpmS+cmmyQvMdcf88vKvQJtSUoXx9k/E8TfejqlaAlsy4Hri
                         liI3HBHAThJ97rUZp4iaewdy9+gAYN2PTZbH/mfrAQKBgQDh8BzE1OsyQdtLuCEf
                         bl30C5gwu9oNpw/m7zphPQnViPryoFzWxqLa7ke1T5lE6KHdwRibASbvjRDK8lm9
                         OE7wTnSUdriPugT8LeiGthiOtDXNOpQ6KdQrejzkPjgka30zw9krLM3fBZCIpxeg
                         7385A8wJTCg1XJIBsFJWIvIx4QKBgQDSseocsMn83IfJH+OvDFadIwmpPa7qHZ90
                         eJmZUhhUkqK8WI7T/t8yHTh12tc8OCw8s4eqD/tCASxw6d0LV/6kAZJJk9a//okO
                         Qfm/7v3GILSLScRhIiG98BFtkBxoC9sAJthF9+FOEt+5twwjoVrNl0N4FE7xuWpE
                         02A+iF4PpQKBgQCS1rvLzNli21xL1MpqJA+ZyWNK4Puxfc8e9oGJquLWTeVShJhx
                         2b0D7nLaSnZBS7PPbGyyaMhGTfcI/wMFsC/VjBfGWXZPqsUMpEWIL5a8QDDrRdQK
                         DR9gdlxWtn1EZjuDxhhBMChIdtRdi8MWjDCx/F7B+CX1sEkQIekRJDsl4QKBgCHZ
                         hZRAbuMaBxKv9eNspwmt1GKFT3G7ep+6tGqmftQvjseVrrSucOtbOHPuyb+aArlp
                         2nTfBm3vRFWLrQCijX2J5xe9EbZczWYmAVrtqYXIQVlTa6JzWlpqlFI24elxZijb
                         FMoA7gieDaWHnAKpbK41DBjhomFBeao/D2pcfEglAoGBAJ8HFBGcC4GibpL3iAlp
                         o6f3HnWNcbkaGusaXVl/IsHDR887ubm+JKhOsfxrAauMxzk0Yn2nR4qtIkYyvUid
                         Stf/PhcDcFRcj6xcGhGxy2Pef6tOCwJY67PN+uwVCEDwXN4cuK+V6RtH+Nd/RlWd
                         /Rf/ar0HZ3+XEwL2QxWji1Pa
                         -----END PRIVATE KEY-----""";

        var publicKey = """
                           -----BEGIN PUBLIC KEY-----
                           MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAufP9PCdWjnbyhOjaUg2E
                           Foa/IwLfnR4cwG+zAe8xN+mW4UvU1KFdwm38aRmZGZbnCC3qZ5uBwLcNumwrd0yL
                           yuXy7MDLOrJoSD5zZIFy7awBNB4w90xr2aOd4wqjjrfpGxLQw3spsomA2gO//WOF
                           M/phvOt2ZFNyHGL1VIgpaabEdkhuQGds1jZtDef6ftKWZSPxLgSF+oHq+87UTUAu
                           Lmdm+qFO+0QYRzJEioi80hrPcNYhM7z6AFvxbwT6R/64/d+PaaA0S37SGtD9SYRU
                           5AoQqc49LX/tmGroDaX+RO5dlKNkJpG53g1ehfJVl0PFAj1dGuBUI7CTeRF8j6RV
                           BQIDAQAB
                           -----END PUBLIC KEY-----""";

        var resultHashSHA256WithRSA = hashSHA256WithRSAService.hash(message, privateKey);
        System.out.println("Result Hash SHA256WithRSA : " + resultHashSHA256WithRSA);
        Boolean validateHashSHA256WithRSA = hashSHA256WithRSAService.validate(message, publicKey, resultHashSHA256WithRSA);
        System.out.println("Validate Hash SHA256WithRSA : " + validateHashSHA256WithRSA);

        //======================================================================================
        var secretKey = "8aea0053-6f31-48f9-acfd-4640b6c5dd54";
        var url = "/api/v1/sample";
        var accessToken = "36247fd1-bccb-4743-a359-776472b83f68";
        var timestamp = DateHelper.toDateIso8601Gmt7();
        var requestBody = """
                          {
                            "message": "hello message"
                          }""";
        var methodName = "POST";

        var componentSignature = new StringBuilder();
        componentSignature.append(methodName).append(":")
                .append(url).append(":")
                .append(accessToken).append(":")
                .append(HashHelper.LowerCaseMinify(requestBody)).append(":")
                .append(timestamp);
        System.out.println("component signature " + componentSignature.toString());

        HashService hashHMACSHA512Service = new HMACSHA512();

        var resultHMACSHA512 = hashHMACSHA512Service.hash(componentSignature.toString(), secretKey);
        System.out.println("Result Hash HMACSHA512 : " + resultHMACSHA512);
        Boolean validateHashHMACSHA512 = hashHMACSHA512Service.validate(componentSignature.toString(), secretKey, resultHMACSHA512);
        System.out.println("Validate Hash HMACSHA512 : " + validateHashHMACSHA512);
    }
}
