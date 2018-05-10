package com.xixi.coin.robot.notice.exchange;

import com.alibaba.fastjson.JSONObject;
import com.xixi.coin.robot.common.translate.Language;
import com.xixi.coin.robot.common.translate.TransApi;
import com.xixi.coin.robot.common.translate.TranslateResult;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Map;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class HuobiExchange extends AbstractBaseExchange {

    public HuobiExchange(){
        Site site = getSite();
        site.addHeader("accept","application/json, text/plain, */*");
        site.addHeader("accept-language","zh-CN");
        site.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        site.addHeader("cache-control","no-cache");
    }

    @Override
    public void process(Page page) {
        Json json = page.getJson();
        String success = json.jsonPath("success").get();
        if (Boolean.TRUE.toString().equals(success)){
            String totalCount = json.jsonPath("data.totalCount").get();
            String items = json.jsonPath("data.items").nodes().toString();
            System.out.println(items);
            List<HuobiNotice> notices = JSONObject.parseArray(items,HuobiNotice.class);
            //写入数据库
            //第二页
            String currentPage = json.jsonPath("data.currentPage").get();
            String pages = json.jsonPath("data.pages").get();
            //查询第二页
            if (!currentPage.equals(pages)){
                String url = page.getUrl().get();
                page.addTargetRequest(url.replace("limit=10","limit"+totalCount));
                System.out.println("url:"+url.replace("limit=10","limit="+totalCount));
            }
        }
        System.out.println(json);
    }
}
