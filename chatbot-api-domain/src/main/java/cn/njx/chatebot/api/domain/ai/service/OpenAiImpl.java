package cn.njx.chatebot.api.domain.ai.service;

import cn.njx.chatebot.api.domain.ai.IOpenAi;
import cn.njx.chatebot.api.domain.ai.model.aggregates.AiAnswer;
import cn.njx.chatebot.api.domain.ai.model.vo.Choices;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @description 接口实现
 * @author njx
 * @date 2023年4月15日21:54:53
 */
@Service
public class OpenAiImpl implements IOpenAi {

    private Logger logger = LoggerFactory.getLogger(OpenAiImpl.class);

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Override
    public String doChatGpt(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1", 33210)).build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAiKey);

        String paramModel = "{\"model\": \"text-davinci-003\", \"prompt\": \" " + question + " \", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramModel, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AiAnswer aiAnswer = JSON.parseObject(jsonStr, AiAnswer.class);
            StringBuilder sb = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                sb.append(choice.getText());
            }
            return sb.toString();
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

        return null;
    }
}
