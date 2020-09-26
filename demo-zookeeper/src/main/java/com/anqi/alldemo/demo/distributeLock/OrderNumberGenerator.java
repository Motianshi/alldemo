package com.anqi.alldemo.demo.distributeLock;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class OrderNumberGenerator {
    private int count = 0;

    public String getNumber() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        return format.format(new Date()) + ++count;
    }


}
