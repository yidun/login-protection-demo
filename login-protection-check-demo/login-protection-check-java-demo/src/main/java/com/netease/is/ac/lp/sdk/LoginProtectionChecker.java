package com.netease.is.ac.lp.sdk;

public class LoginProtectionChecker extends AbstProtectionChecker {

    /**
     * 易盾登录保护检测接口地址
     */
    private static final String API_URL = "https://ac.dun.163yun.com/v1/login/check";

    /**
     * 
     * @param secretId 产品密钥ID
     * @param secretKey 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     * @param businessId 业务ID，易盾根据产品业务特点分配
     */
    public LoginProtectionChecker(String secretId, String secretKey, String businessId) {
        super(API_URL, secretId, secretKey, businessId);
    }
}
