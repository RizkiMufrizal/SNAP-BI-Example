package org.rizki.mufrizal.snapbi.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author rizki
 */
public class DateHelper {

    public static String toDateIso8601Gmt7() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }
}
