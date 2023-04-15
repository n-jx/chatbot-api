package cn.njx.chatebot.api.domain.ai.Controller;

import cn.njx.chatebot.api.domain.ai.IOpenAi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private  IOpenAi openAi;

    @GetMapping("/openAi")
    public String getOpenAi() throws IOException {
        return openAi.doChatGpt("写一个java反射");
    }
}
