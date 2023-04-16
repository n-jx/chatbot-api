package cn.njx.chatebot.api.application.job;

import cn.njx.chatebot.api.domain.ai.IOpenAi;
import cn.njx.chatebot.api.domain.zsxq.IZsxqApi;
import cn.njx.chatebot.api.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;
import cn.njx.chatebot.api.domain.zsxq.domain.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @description 问题任务
 * @author njx
 * @date 2023年4月16日14:56:42
 */
@EnableScheduling
@Configuration
public class ChatBotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatBotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAi openAi;

    // 表达式：cron.qqe2.com
    @Scheduled(cron = "0/30 * * * * ?")
    public void run() {
        try {

            if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("AI回答休息中!");
                return;
            }

            // 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待回答问题");
                return;
            }

            // AI回答
            Topics topic = topics.get(0);
            String answer = openAi.doChatGpt(topic.getQuestion().getText());

            // 回答问题
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        }catch (Exception e) {
            logger.error("自动回复异常", e);
        }
    }

}
