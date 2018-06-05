package com.xixi.coin.robot.wallet.eth;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.WalletUtils;

import com.xixi.coin.robot.wallet.CurrencyEnum;
import com.xixi.coin.robot.wallet.Wallet;
import com.xixi.coin.robot.wallet.WalletInfo;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
public class EthWallet implements Wallet {

    private static final String WALLET_ADDRESS_SPLIT = "--";

    private static final String OX = "0x";

    @Override
    public WalletInfo createWallet(String path, String password) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("wallet file path is null");
        }

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("wallet password is null");
        }

        List<WalletInfo> walletInfos = createWallet(path, password, 1);
        return walletInfos.get(0);
    }

    @Override
    public List<WalletInfo> createMoreWallet(String path, String password, int count) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("wallet file path is null");
        }

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("wallet password is null");
        }
        return createWallet(path, password, count);
    }

    @Override
    public WalletInfo loadWallet(String walletFilePath) {
        if (StringUtils.isBlank(walletFilePath)) {
            throw new IllegalArgumentException("wallet file path is null");
        }
        File file = new File(walletFilePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("wallet file not exists");
        }
        String utc = file.getName();
        return generateWalletInfo(file, utc);
    }

    /**
     * 创建钱包
     * 
     * @param path
     * @param password
     * @param count
     * @return
     * @throws Exception 异常处理，创建失败
     */
    private List<WalletInfo> createWallet(String path, String password, int count) throws Exception {
        if (count <= 0) {
            count = 1;
        }
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        List<WalletInfo> walletInfos = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String utc = WalletUtils.generateFullNewWalletFile(password, directory);
            walletInfos.add(generateWalletInfo(directory, utc));
        }
        return walletInfos;
    }

    /**
     * 生成钱包信息
     * 
     * @param directory 磁盘
     * @param utc 文件
     * @return 钱包包装信息
     */
    private WalletInfo generateWalletInfo(File directory, String utc) {
        WalletInfo walletInfo = new WalletInfo();
        String[] utcs = utc.split(WALLET_ADDRESS_SPLIT);
        String address = OX + utcs[2].substring(0, utcs[2].length() - 5);
        // 名称使用当前地址
        walletInfo.setName(address);
        walletInfo.setAddress(address);
        walletInfo.setCreateTime(new Date());
        walletInfo.setCurrency(CurrencyEnum.ETH);
        walletInfo.setWalletFile(directory + utc);
        return walletInfo;
    }
}
