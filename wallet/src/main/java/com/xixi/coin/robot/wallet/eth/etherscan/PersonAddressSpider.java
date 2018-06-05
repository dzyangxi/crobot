package com.xixi.coin.robot.wallet.eth.etherscan;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 个人账户爬虫，用来爬取账户信息，及账户余额和账户token余额信息
 * 
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/3.
 */
public class PersonAddressSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private String queryUrl = "https://etherscan.io/address/";

    private AddressInfo addressInfo;

    public PersonAddressSpider(String address) {
        queryUrl = queryUrl + address;
    }

    public static AddressInfo start(PersonAddressSpider personAddressSpider) {
        Spider spider = Spider.create(personAddressSpider).addUrl(personAddressSpider.getQueryUrl())
                .addPipeline(new JsonFilePipeline()).thread(1);
        spider.start();
        return personAddressSpider.getAddressInfo();
    }

    public static void main(String[] args) {
        PersonAddressSpider spider = new PersonAddressSpider("0xcc1ac58603c23f609f3f9212a0932284def9deb7");
        PersonAddressSpider.start(spider);
    }

    @Override
    public void process(Page page) {
        addressInfo = new AddressInfo();
        Html html = page.getHtml();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public String getQueryUrl() {
        return queryUrl;
    }
}
