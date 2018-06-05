package com.xixi.coin.robot.wallet.eth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2.
 */
@Data
@AllArgsConstructor
public class TransactionInfo implements Serializable {

    private static final long serialVersionUID = -6740664746949862912L;

    /**
     * 来自那个地址
     */
    private String fromAddress;

    /**
     * 要转到那个地址
     */
    private String toAddress;

    /**
     * 转账个数
     */
    private BigDecimal value;

    /**
     * price
     */
    private BigInteger gasPrice;

    /**
     * limit
     */
    private BigInteger gasLimit;
}
