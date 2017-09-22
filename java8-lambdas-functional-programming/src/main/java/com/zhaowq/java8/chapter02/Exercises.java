package com.zhaowq.java8.chapter02;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;

/**
 * Created by zhaowq on 2017/9/11.
 */
public class Exercises {
    public final static ThreadLocal<DateFormatter> dateFormatter =
            ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));
}
