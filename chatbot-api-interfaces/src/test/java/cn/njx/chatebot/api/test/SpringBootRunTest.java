package cn.njx.chatebot.api.test;

import cn.njx.chatebot.api.domain.ai.IOpenAi;
import cn.njx.chatebot.api.domain.zsxq.IZsxqApi;
import cn.njx.chatebot.api.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;
import cn.njx.chatebot.api.domain.zsxq.domain.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author njx
 * @description
 * @date 2023年4月5日20:36:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAi openAi;

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topic_id = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topic_id: {} text: {}", topic_id, text);

            // 回答问题
            boolean flag = zsxqApi.answer(groupId, cookie, topic_id, "很好用", false);
            logger.info("回答状态：{}" , flag ? "成功" : "失败");
        }

    }

    @Test
    public void test_openai() throws IOException {
         String response = openAi.doChatGpt("写一个java反射代码");
        logger.info("测试结果：{}", response);
    }

}
