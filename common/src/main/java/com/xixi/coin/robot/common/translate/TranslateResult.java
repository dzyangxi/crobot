package com.xixi.coin.robot.common.translate;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author noname
 * @version V1.0.0
 * @since 2018/5/10.
 */
public class TranslateResult implements Serializable {

    private static final long serialVersionUID = -752242180978359063L;
    private String from;

    private String to;

    @JSONField(name = "trans_result")
    private List<Result> results;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}


