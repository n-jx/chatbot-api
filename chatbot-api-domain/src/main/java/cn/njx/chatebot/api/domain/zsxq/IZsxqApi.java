package cn.njx.chatebot.api.domain.zsxq;

import cn.njx.chatebot.api.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @author njx
 * @description 知识星球 API 接口
 * @date 2023年4月5日20:12:09
 */
public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer (String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;

}
