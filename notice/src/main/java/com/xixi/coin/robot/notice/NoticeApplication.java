package com.xixi.coin.robot.notice;

import javax.management.JMException;

import com.xixi.coin.robot.notice.exchange.HuobiExchange;

import com.xixi.coin.robot.notice.exchange.OkexExchange;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class NoticeApplication {

    /**
     * 火币公告
     */
    private static final String HUOBI_NOTICE = "https://www.huobipro.com/-/x/hb/p/api/contents/pro/list_notice?r=am5aluwb4bs&limit=10&language=zh-cn";

    /**
     * OKEX公告
     */
    private static final String OKEX_NOTICE = "https://support.okex.com/hc/zh-cn/sections/115000447632-%E5%85%AC%E5%91%8A%E4%B8%AD%E5%BF%83";

    public static void main(String[] args) {

        //设置代理ip池
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(
                SimpleProxyProvider.from(new Proxy("127.0.0.1", 50791)));

        Spider huobiSpider = Spider.create(new HuobiExchange()).addUrl(HUOBI_NOTICE)
                .addPipeline(new JsonFilePipeline()).thread(5).setDownloader(httpClientDownloader);

        Spider okexSpider = Spider.create(new OkexExchange()).addUrl(OKEX_NOTICE).addPipeline(new JsonFilePipeline()).thread(2).setDownloader(httpClientDownloader);
        try {
            // 注册监控
            SpiderMonitor.instance().register(huobiSpider,okexSpider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        okexSpider.start();
        huobiSpider.start();
    }
}
