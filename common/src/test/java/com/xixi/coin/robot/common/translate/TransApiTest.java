package com.xixi.coin.robot.common.translate;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class TransApiTest {

    @Test
    public void testTranslate() {
        TransApi transApi = new TransApi();
        String query = "我是中国人";
        TranslateResult result = transApi.getTransResult(query, Language.ZH, Language.EN);
        Assert.assertEquals("I am Chinese,", result.getResults().get(0).getDst());
    }
}
