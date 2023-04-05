package cn.njx.chatebot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @description 单元测试
 * @author njx
 * @date 2023年4月3日
 */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15552512115812/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie", "zsxqsessionid=5b50078197d14527e7a6854500755d7e; abtest_env=product; UM_distinctid=1875044da396b9-0e3ea6dc127c94-26031851-384000-1875044da3a6f8; zsxq_access_token=52BBF1ED-8603-CF72-1CC5-14C6A6FD9019_25B6491775BE0CE6; sensorsdata2015jssdkcross={\"distinct_id\":\"418412128448558\",\"first_id\":\"184b1dc25b0541-08ae759203cae78-26021e51-3686400-184b1dc25b157c\",\"props\":{\"$latest_traffic_source_type\":\"社交网站流量\",\"$latest_search_keyword\":\"未取到值\",\"$latest_referrer\":\"https://open.weixin.qq.com/\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg0YjFkYzI1YjA1NDEtMDhhZTc1OTIwM2NhZTc4LTI2MDIxZTUxLTM2ODY0MDAtMTg0YjFkYzI1YjE1N2MiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTg0MTIxMjg0NDg1NTgifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"418412128448558\"},\"$device_id\":\"184b1dc25b0541-08ae759203cae78-26021e51-3686400-184b1dc25b157c\"}");
        get.addHeader("Content_Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/814282484215212/answer");
        post.addHeader("cookie", "zsxqsessionid=5b50078197d14527e7a6854500755d7e; abtest_env=product; UM_distinctid=1875044da396b9-0e3ea6dc127c94-26031851-384000-1875044da3a6f8; zsxq_access_token=52BBF1ED-8603-CF72-1CC5-14C6A6FD9019_25B6491775BE0CE6; sensorsdata2015jssdkcross={\"distinct_id\":\"418412128448558\",\"first_id\":\"184b1dc25b0541-08ae759203cae78-26021e51-3686400-184b1dc25b157c\",\"props\":{\"$latest_traffic_source_type\":\"社交网站流量\",\"$latest_search_keyword\":\"未取到值\",\"$latest_referrer\":\"https://open.weixin.qq.com/\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg0YjFkYzI1YjA1NDEtMDhhZTc1OTIwM2NhZTc4LTI2MDIxZTUxLTM2ODY0MDAtMTg0YjFkYzI1YjE1N2MiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTg0MTIxMjg0NDg1NTgifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"418412128448558\"},\"$device_id\":\"184b1dc25b0541-08ae759203cae78-26021e51-3686400-184b1dc25b157c\"}");
        post.addHeader("Content_Type", "application/json; charset=UTF-8");

        String paramJson = "{\"req_data\":{\"text\":\"好好学习\\n\",\"image_ids\":[],\"silenced\":false}}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }


    }


}
