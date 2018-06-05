package com.xixi.coin.robot.common.etherscan;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 账户爬虫，用来爬取账户信息，及账户余额和账户token余额信息
 * 
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/3.
 */
public class AccountSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {}

    @Override
    public Site getSite() {
        return site;
    }
}
