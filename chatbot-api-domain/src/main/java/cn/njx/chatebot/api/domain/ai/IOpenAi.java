package cn.njx.chatebot.api.domain.ai;

import java.io.IOException;

/**
 * @description ChatGPT openAI 接口
 * @author njx
 */
public interface IOpenAi {

    /**
     * 调用openAi 接口
     * @param question 请求
     * @return 结果
     * @throws IOException 异常
     */
    String doChatGpt(String question) throws IOException;

}
