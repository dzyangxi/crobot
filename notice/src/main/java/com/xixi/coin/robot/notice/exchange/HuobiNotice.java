package com.xixi.coin.robot.notice.exchange;

import java.io.Serializable;
import java.util.List;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class HuobiNotice implements Serializable {

    private static final long serialVersionUID = -6166239053179402290L;

    private int id;

    /**
     * 标题
     */
    private String title;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 源
     */
    private String source ;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否置顶
     */
    private boolean topNotice;
    /**
     * 宽度
     */
    private int weight;
}
