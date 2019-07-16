package com.aohashi.demo.utils;

import java.util.UUID;

public class UuidUtil {

    public static String get32UUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-","");
        return uuid;

    }

    public static void main(String[] args) {


    }


}
