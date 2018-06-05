package com.xixi.coin.robot.wallet.eth;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.lang3.StringUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
@Slf4j
public final class Web3jClient {

    private static final long pollingInterval = 100;
    private static final int threadSize = 2;
    private Web3j web3j;

    public Web3jClient(String url) throws IOException {
        this(url, pollingInterval, Executors.newScheduledThreadPool(threadSize));
    }

    public Web3jClient(String url, long pollingInterval, ScheduledExecutorService scheduledExecutorService)
            throws IOException {
        if (StringUtils.isBlank(url) || scheduledExecutorService == null) {
            throw new IllegalArgumentException();
        }
        web3j = Web3j.build(new HttpService(url), pollingInterval, scheduledExecutorService);
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        log.info("http url : %s,version : %s", url, web3ClientVersion.getWeb3ClientVersion());
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public void showdown() {
        if (web3j != null) {
            web3j.shutdown();
        }
    }
}
