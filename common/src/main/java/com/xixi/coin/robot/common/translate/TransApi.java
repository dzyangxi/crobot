package com.xixi.coin.robot.common.translate;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid = "20180510000156416";
    private String securityKey = "YLsvZKActjPSiUlU2GBv";

    public TransApi() {}

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public TranslateResult getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return JSON.parseObject(HttpGet.get(TRANS_API_HOST, params), TranslateResult.class);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
