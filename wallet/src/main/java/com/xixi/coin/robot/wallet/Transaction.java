package com.xixi.coin.robot.wallet;

import org.web3j.crypto.Credentials;

import com.xixi.coin.robot.wallet.eth.TransactionInfo;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
public interface Transaction {

    /**
     * 发送交易
     * 
     * @param transactionInfo 交易请求信息
     * @param credentials 证明
     * @return 交易hash
     */
    String sendTransaction(TransactionInfo transactionInfo, Credentials credentials);

    /**
     * 验证交易信息
     * 
     * @param transactionHash 验证交易
     * @return 交易是否成功
     */
    boolean checkTransaction(String transactionHash);


}
