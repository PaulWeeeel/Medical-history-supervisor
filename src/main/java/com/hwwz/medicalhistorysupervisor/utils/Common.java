package com.hwwz.medicalhistorysupervisor.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static String getCurTimeString()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(d);
    }
    public static String getCurDateString()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static Double nullToZero(Object obj)
    {
        if(obj==null)
        {
            return 0d;
        }
        return (Double) obj;
    }
}
