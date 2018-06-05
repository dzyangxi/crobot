package com.xixi.coin.robot.wallet.eth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;
import lombok.Getter;

/**
 * @author yangxx
 * @version V1.0.0
 * @since 2018/6/2. https://infura.io/setup?key=hSMtDlmnP70JDCbRoqa4
 */
@Getter
@Data
public class InfuraInfo {

    /**
     * 主网(正式使用)
     */
    public static final String MAIN_NET = "https://mainnet.infura.io";
    /**
     * 测试使用(Ropsten)
     */
    public static final String ROPSTEN = "https://ropsten.infura.io";

    /**
     * 测试网络(INFURANET)
     */
    public static final String INFURANET = "https://infuranet.infura.io";
    /**
     * 测试网络(KOVAN)
     */
    public static final String KOVAN = "https://kovan.infura.io";
    /**
     * 测试网络(RINKEBY)
     */
    public static final String RINKEBY = "https://rinkeby.infura.io";

    /**
     * Infura Tocken 通过邮件申请
     */
    private static final List<String> tokens = new ArrayList<>(10);

    static {
        tokens.add("yJ7CBQH2vLpcyoQgqE9y");
        tokens.add("cv3gik59mKWbmKHX7iUz");
        tokens.add("hSMtDlmnP70JDCbRoqa4");
    }

    private InfuraInfo() {}

    public static String getToken() {
        return tokens.get(new Random().nextInt(2));
    }
}
