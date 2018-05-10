package com.xixi.coin.robot.common.translate;

import java.io.Serializable;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 4180507138481700052L;
    private String str;
    private String dst;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
