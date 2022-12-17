package com.example.feign;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Signature {

    public String buildSignature(String secret, String timestamp, Map<String,String> params){

        String signature = "";
        try {
            params.put("timestamp",timestamp);
            //进行ascii排序
            List<String> signatureParamsList = sortMapForKey(params);
            //生成url参数字符串
            String signatureStr = httpBuildStr(signatureParamsList);

            //将密钥拼接在后面
            signatureStr += "&cl_secret="+secret;
            //进行MD5加密
            signature = getMD5(signatureStr);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;

    }

    private List<String> sortMapForKey(Map<String,String> params) throws UnsupportedEncodingException {
        String[] sortKey = params.keySet().toArray(new String[]{});
        Arrays.sort(sortKey);
        List<String> list = new ArrayList<String>();
        for (int i=0;i<sortKey.length;i++){
            String value = (params.get(sortKey[i]));
            String valueEncode = URLEncoder.encode(value,"UTF-8");
            String temp = sortKey[i]+"="+valueEncode;
            list.add(temp);
        }
        return list;
    }

    private String httpBuildStr(List<String> list) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private String getMD5(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(msg.getBytes());
        String MD5 = new BigInteger(1,md.digest()).toString(16);
        return MD5.toUpperCase();
    }

}
