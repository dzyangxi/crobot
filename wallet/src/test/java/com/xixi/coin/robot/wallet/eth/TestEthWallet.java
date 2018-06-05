package com.xixi.coin.robot.wallet.eth;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.xixi.coin.robot.wallet.WalletInfo;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
public class TestEthWallet extends BaseTest {

    private String path = "testWallet/";

    private String password = "123456";

    @Test
    public void testCreateMoreWallet() throws Exception {
        File file = new File(path);
        File[] files = file.listFiles();
        List<WalletInfo> walletInfos = null;
        if (files.length == 0) {
            EthWallet ethWallet = new EthWallet();
            walletInfos = ethWallet.createMoreWallet(path, password, 1);
            Assert.assertNotNull(walletInfos);
        }
    }
}
