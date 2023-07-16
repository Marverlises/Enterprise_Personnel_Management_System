package com.by.coursedesign.utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSAUtils {
    
    public static String getDecryptData(String encryptedData) {
        // 获取私钥字符串（使用你提供的私钥）
        String privateKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJBkQTx5QU4OP94IhcexIN9GUmSSm3ayMGuLDpixxZD7cD4ivn1QXCeyI88Zxewg2Tt0uZa0Yu7RcI86SCksnSPGqvlBS7wM0zWQfZhyK7ISY6c5XsIEoLrs+ljShhnZoIu3Gwld4o8kfAHZ1R98wuHvOZaemRIWwacOHwe3ub5qk1wT7lkHkhrx546HGHarITh5DLhrUqWrdwiYxGbzXn84mgCSFLg4Tt988UDNDyqXuX8uhAuPfmXNBj5y+W51zmmsHa0IwHkx0pakaIe4eC21IUy3Vs2Ugz0zyL6/lKPkFntOLYxzpO3aMk4jawWSpcivZh/YFSdbpTbYyoNWjfAgMBAAECggEBAIfJZtM3Fx7tkeH3jQogh86+XzJmlGmISSiTA/Yb/hagBRxbGGlgZ5nHjjFYuT4KDYxRuyAo917vYsFjOKaQsmGdr5eZJka9FFyLIMVHLnrEqe6ItoxNnzMeo/WKAfdiR8/ZeRumGIb5OXM/pbQh3PvjfkGMfIJu3YMCVFSM+fyOTteE8v1TLHvxeVo4CKnBboy9BEi/EB0wB2KvUtl9mXD3QuJnzRw29u9VGJm0MHILxmqNOb7K3SJ9mwEuLhQAwZdwrv+kiBFUUYAXr2hk3z7dW5ecTjtWKrGFDqqqXC7VAMlWOxk1qOOjpznEITR+eXbdxr5Dg+aVIrMPyaXa4TECgYEAyk2s51tKE0LdvrghiZv7qDlVm1nacQgESXbvQA6b/DxwHaSiEHr2PkXawaQJlU4hYlKF7tJdJgxOoQjLsGkuq3XHI1GcHkNbP8s2Rn8eZtXOgThXmI+L/IWfuRY9qZpqQaqa93tDJSBypuGDlxA0EsrsNmgk3yqcwz/pO6InYokCgYEArWTy0qY1uZHt6Va2qYmhJz3fghOSrjT3SsScQDE/l+qGL4Miu4uvNttXSPCUQEgBRYxQ/YfN9Gp+5hIi2eX5ONEovJlIA0R+neA29V2mbkNuYaS6Ua1VJ8ury/VT+EbXcRFkfxkhc2GWkwcA6E1NKRzHxhLiLQVVP7cDRmjoticCgYBgwe/to7L1LWkDW+vmdi1SE0IHkF0y7IOoY3MgrxwZ8wook2JUAYue0yhCr2NJ/tlmvlOCmyikFwTQbnWFhXkl1qoNEW5a5xpQk6/83bW7t40SLtFujVGF0J9JFgMeCFXD3IZL0a+WpUNQ39FhQgW160o452yjNC1QEVRLCk5OCQKBgGReevDUpWS1xDG9sB6TPGWquyZgjhepMTxmcsv1tgoKB4KZNhG52cK3VN8z450SESpg/sDDS8SfoLUs6l+Xb5wj9qC1WaoKjAB3OVjuzNgm0E5VPETrQM+4Zcm6SND+sNcSaknoEiTn6HFTLINLu86AimNtqt7Ep4QqPAsVzx8hAoGAYd3EzzVuftYOQAk/BS0PZalviMN7r0FNTPrLpdaJxTV83jTVQtc0VPogr4eznY8oZqSedHZJ8QC3+X7N47TqP7SoSaOL6F7vFkGD8lLK8PuMbUpT2E8agSs8xRzn5mPK1bnylzEmwJkbhJZr2rzpoFFSBa+ANFi+iQIkIn36vlQ=";
        // 加密后的数据（从表单中获取或其他途径获取）
        byte[] decryptedBytes = new byte[0];
        try {
            // 解码私钥
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            
            // 创建Cipher对象并进行解密
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将解密后的字节数组转换为字符串
        String decryptedData = new String(decryptedBytes, StandardCharsets.UTF_8);
        // 输出解密后的数据
        return  decryptedData;
    }
    
}

