package com.xixi.coin.robot.wallet.eth;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import com.xixi.coin.robot.wallet.WalletInfo;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/3.
 */
public class TestMyEthTransaction extends BaseTest {

    private String filePath;

    private MyEthTransaction myEthTransaction;

    private EthWallet ethWallet;

    private String transactionHex;

    @Before
    public void before() {
        File file = new File("testWallet");
        File[] files = file.listFiles();
        File firstFile = files[0];
        filePath = firstFile.getAbsolutePath();
        System.out.println(firstFile.getAbsolutePath());

        myEthTransaction = new MyEthTransaction();
        myEthTransaction.setWeb3JClient(getWeb3jClient());

        ethWallet = new EthWallet();
    }

    @Test
    public void testSendTransaction() throws IOException, CipherException {
        WalletInfo walletInfo = ethWallet.loadWallet(filePath);
        TransactionInfo transactionInfo = new TransactionInfo(walletInfo.getAddress(), walletInfo.getAddress(),
                new BigDecimal(0), BigInteger.valueOf(15L), BigInteger.valueOf(80000L));
        Credentials credentials = WalletUtils.loadCredentials("123456", filePath);
        transactionHex = myEthTransaction.sendTransaction(transactionInfo, credentials);
        System.out.println(transactionHex);
    }

    @Test
    public void testCheckTransaction() {
        boolean hex = myEthTransaction.checkTransaction(transactionHex);
    }
}
