package com.tkmybaitsdemo.demo.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author yb
 * @date 2023/05/110025
 **/
@Component
public class OpenAi {

    String apiKey = System.getenv("API_KEY");

    public  String chat(List<JSONObject> messages){
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Authorization", "Bearer " + apiKey);
            String requestBody = "{\n" +
                    "  \"model\": \"gpt-3.5-turbo-0301\",\n" +
                    "  \"messages\":"+  JSON.toJSONString(messages) +
                    "  }";
            StringEntity entity = new StringEntity(requestBody);
            request.setEntity(entity);

            // send API request
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            // transform jsonObject to JSON
            JSONObject jsonObject = JSON.parseObject(responseBody);

            if (jsonObject.containsKey("error")) {
                return "Error!";
            }
            // get choices
            JSONArray choices = jsonObject.getJSONArray("choices");

            // get first choices, because I did not set choice param so just 1 choice
            JSONObject firstChoice = choices.getJSONObject(0);

            // get message
            JSONObject message = firstChoice.getJSONObject("message");

            return message.getString("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
