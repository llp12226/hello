package com.example.feign;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Signature signature = new Signature();
        String secret = "98f13708210194c475687be6106a3b85";
        String timestamp = "1646899307";
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("param1","aaaa哈哈");
        map.put("param2","qweqwe");
        String MD5 = signature.buildSignature(secret,timestamp,map);
        System.out.println(MD5);

    }
}
