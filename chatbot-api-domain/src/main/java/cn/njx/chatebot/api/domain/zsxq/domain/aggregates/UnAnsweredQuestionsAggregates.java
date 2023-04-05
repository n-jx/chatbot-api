package cn.njx.chatebot.api.domain.zsxq.domain.aggregates;


import cn.njx.chatebot.api.domain.zsxq.domain.res.RespData;

/**
 * @author njx
 * @description 未回答问题的聚合信息
 * @date 2023年4月5日19:59:34
 */
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;

    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

}
