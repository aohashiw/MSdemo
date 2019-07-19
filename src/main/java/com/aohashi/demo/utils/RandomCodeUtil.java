package com.aohashi.demo.utils;

import java.util.Random;

public class RandomCodeUtil {
    public static  String GetCode(int length) {
        Random random = new Random();
        random.nextInt();
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                     'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                     'j', 'k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        char[] res = new char[length];
        for (int i = 0;i<length;i++){
            char num = ch [random.nextInt(ch.length)];
            res[i]=num;
        }
        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(GetCode(9));
    }
}
