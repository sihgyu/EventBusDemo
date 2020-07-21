package com.sihgyu.eventbustest.service;

import com.sihgyu.eventbustest.util.Base64Util;
import com.sihgyu.eventbustest.util.FileUtil;
import com.sihgyu.eventbustest.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;


@Service
public class OcrService {
    @Autowired
    AuthService authService;
    public String generalBasic() {
        String token = authService.getAuth();
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        try {
            // 本地文件路径
            String filePath = "/Users/jbkj/Documents/ocr.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            String result = HttpUtil.post(url, token, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
