package com.fei.store.common.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;


public class ShortMessageUtils {

    public static void code(String code, String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("", "", "");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "必购商城");
        request.putQueryParameter("TemplateCode", "SMS_168587317");
        request.putQueryParameter("TemplateParam", "{code:" + code.toString() + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String random() {
        char[] ch = "123456789".toCharArray();
        //随机获取里面的内容
        Random random = new Random();
        int len = ch.length, index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(len);
            sb.append(ch[index]);
        }
        return sb.toString();
    }
}
