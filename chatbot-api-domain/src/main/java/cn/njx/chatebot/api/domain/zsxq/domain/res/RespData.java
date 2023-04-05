package cn.njx.chatebot.api.domain.zsxq.domain.res;

import cn.njx.chatebot.api.domain.zsxq.domain.vo.Topics;

import java.util.List;

/**
 * @author njx
 * @description 结果数据
 * @date 2023年4月5日19:54:36
 */
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}
