package com.yyc;

import org.junit.Test;

import java.time.ZonedDateTime;

public class _1获取时间格式 {

    @Test
    public void 获取时间格式() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//默认时区
        System.out.println(zonedDateTime);
    }

}
