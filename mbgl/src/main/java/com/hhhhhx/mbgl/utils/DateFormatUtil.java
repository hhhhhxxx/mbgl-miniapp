package com.hhhhhx.mbgl.utils;

import com.hhhhhx.mbgl.entity.Record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DateFormatUtil {

    private static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sd2 = new SimpleDateFormat("MM-dd");

    public static Date strToDate(String strDate) {
        // SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMdd");

        try {
            Date date = sd.parse(strDate);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToStr(Date date) {
        return sd2.format(date);
    }

    public static void ListSort(List<Record> list) {
        Collections.sort(list, new Comparator<Record>() {
            @Override
            //定义一个比较器
            public int compare(Record o1, Record o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    // Date dt1 = o1.getTime();
                    // Date dt2 = o2.getTime();

                    LocalDateTime t1 = o1.getTime();
                    LocalDateTime t2 = o2.getTime();

                    if (t1.isAfter(t2)) {
                        return 1;
                    } else if (t1.isBefore(t2)) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
