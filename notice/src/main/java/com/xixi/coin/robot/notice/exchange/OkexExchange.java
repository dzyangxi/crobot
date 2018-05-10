package com.xixi.coin.robot.notice.exchange;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class OkexExchange extends AbstractBaseExchange{

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String data = html.$(".article-list").get();
        System.out.println(data);
        System.out.println(html);
    }
}
