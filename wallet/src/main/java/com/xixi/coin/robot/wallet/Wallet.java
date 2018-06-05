package com.xixi.coin.robot.wallet;

import java.util.List;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
public interface Wallet {

    /**
     * 生成单个钱包
     * 
     * @param path 生成路径
     * @param password 生成密码
     * @return 钱包地址
     * @throws Exception
     */
    WalletInfo createWallet(String path, String password) throws Exception;

    /**
     * 生成多个钱包
     * 
     * @param path 生成路径
     * @param password 密码(牢记密码)
     * @param count 生成个数
     * @return 钱包地址
     * @throws Exception
     */
    List<WalletInfo> createMoreWallet(String path, String password, int count) throws Exception;

    /**
     * 加载钱包信息
     * 
     * @param walletFilePath 路径
     * @return 钱包信息
     */
    WalletInfo loadWallet(String walletFilePath);
}
