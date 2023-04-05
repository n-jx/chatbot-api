package cn.njx.chatebot.api.domain.zsxq.domain.req;

/**
 * @author njx
 * @description 请求问答接口信息
 * @date 2023年4月5日19:51:05
 */
public class AnswerReq {

    private ReqData req_data;


    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }

}
