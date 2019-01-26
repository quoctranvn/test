package com.qtr.core.utils;

import java.text.Normalizer;

public class StringHelper {

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        temp = temp.replace('đ','d');
        return temp.replaceAll("\\p{M}", "");
    }
}
