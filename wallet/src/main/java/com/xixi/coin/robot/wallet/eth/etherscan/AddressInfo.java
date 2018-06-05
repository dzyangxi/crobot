package com.xixi.coin.robot.wallet.eth.etherscan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/3.
 */
@Data
public class AddressInfo implements Serializable {

    private static final long serialVersionUID = 4740575941689908008L;

    /**
     * 以太坊地址
     */
    private String address;

    /**
     * 交易数据
     */
    private int countTxs;

    /**
     * 以太坊账户余额
     */
    private BigDecimal balance;

    /**
     * 代币信息
     */
    private List<TokenInfo> tokens;
}


/**
 * token信息及账户余额
 */
@Data
class TokenInfo implements Serializable {

    private static final long serialVersionUID = -2093801381455613086L;
    /**
     * token地址
     */
    private String address;

    /**
     * 币种
     */
    private String symbol;

    /**
     * 余额
     */
    private BigDecimal balance;
}
