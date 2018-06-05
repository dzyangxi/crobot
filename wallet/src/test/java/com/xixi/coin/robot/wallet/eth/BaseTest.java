package com.xixi.coin.robot.wallet.eth;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
public class BaseTest {

    private Web3jClient web3jClient;

    @Before
    public void setup() throws IOException {
        web3jClient = new Web3jClient(InfuraInfo.KOVAN + "/" + InfuraInfo.getToken());
    }

    public Web3jClient getWeb3jClient() {
        return web3jClient;
    }

    @After
    public void tearDown() {}
}
