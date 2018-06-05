package com.xixi.coin.robot.wallet.eth;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.xixi.coin.robot.wallet.Transaction;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
@Slf4j
public class MyEthTransaction implements Transaction {

    private Web3jClient web3JClient;

    private Web3j web3j;

    @Override
    public String sendTransaction(TransactionInfo transactionInfo, Credentials credentials) {
        if (transactionInfo == null) {
            throw new NullPointerException("transactionInfo is null");
        }
        if (credentials == null) {
            throw new NullPointerException("credentials is null");
        }
        try {
            EthGetTransactionCount ethGetTransactionCount =
                    web3j.ethGetTransactionCount(transactionInfo.getToAddress(), DefaultBlockParameterName.LATEST)
                            .sendAsync().get();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
            // 创建交易，这里是转0.5个以太币
            BigInteger value = Convert.toWei(transactionInfo.getValue(), Convert.Unit.ETHER).toBigInteger();
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, transactionInfo.getGasPrice(),
                    transactionInfo.getGasLimit(), transactionInfo.getToAddress(), value);

            // 签名Transaction，这里要对交易做签名
            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);

            // 发送交易
            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println(ethSendTransaction);
            if (transactionHash == null) {
                // TODO 处理错误
                System.out.println(ethSendTransaction.getError().getMessage());
            }
            return transactionHash;
        } catch (Exception e) {
            log.info("交易失败:", e);
            throw new RuntimeException("交易失败：", e);
        }
    }

    @Override
    public boolean checkTransaction(String transactionHash) {
        if (StringUtils.isBlank(transactionHash)) {
            throw new IllegalArgumentException("transactionHash is null");
        }
        try {
            EthTransaction et = web3j.ethGetTransactionByHash(transactionHash).send();
            if (et != null) {
                return true;
            }
        } catch (IOException e) {
            log.info("get transaction by hash : %s error", transactionHash, e);
        }
        return false;
    }

    public void setWeb3JClient(Web3jClient web3JClient) {
        this.web3JClient = web3JClient;
        this.web3j = web3JClient.getWeb3j();
    }
}
