package com.xixi.coin.robot.wallet;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 钱包信息
 * 
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/1.
 */
@Data
public class WalletInfo implements Serializable {

    private static final long serialVersionUID = 4976971808464319435L;

    /**
     * 钱包名称
     */
    private String name;

    /**
     * 钱包地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 币种
     */
    private CurrencyEnum currency;

    /**
     * 钱包文件
     */
    private String walletFile;
}
