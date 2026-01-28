package com.deepseek.impl;


import com.alibaba.fastjson.JSON;
import com.deepseek.IDeepSeek;
import com.deepseek.dto.ChatCompletionRequestDTO;
import com.deepseek.dto.ChatCompletionResponseDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeepSeek implements IDeepSeek {

    private final String apiHost="https://api.deepseek.ai/v1/chat/completions";
    private final String apiKeySecret="sk-xxxxxxxxxxxxxxxxxxxxx";

    @Override
    public String completions(String userQuestion) throws Exception {

        // 1. 创建HTTP连接到AI API端点
        // AI的聊天补全API地址
        URL url = new URL(apiHost);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 2. 设置HTTP请求头
        connection.setRequestMethod("POST");  // 使用POST方法
        connection.setRequestProperty("Authorization", "Bearer " + apiKeySecret);
        connection.setRequestProperty("Content-Type", "application/json");  // 请求体为JSON格式
        connection.setDoOutput(true);  // 允许向服务器发送数据

        // 构建请求体
        ChatCompletionRequestDTO request = new ChatCompletionRequestDTO();
        // 深度思考
        Map<String, Object> extraBody = new HashMap<>();
        extraBody.put("enable_thinking", true);
        // 消息内容
        List<ChatCompletionRequestDTO.Prompt> messages = new ArrayList<>();
        // to do
        messages.add(new ChatCompletionRequestDTO.Prompt("system",
                "你是一个宠物健康咨询系统的AI客服，精通各类宠物健康场景咨询，" +
                        "包括宠物饮食、行为、健康护理、疾病预防等方面。请以专业、友好的态度回答用户的宠物健康相关问题。"));
        messages.add(new ChatCompletionRequestDTO.Prompt("user",
                "请根据以下用户问题进行回复：\n + " + userQuestion));
        //设置请求体
        request.setMessages(messages);
        request.setModel("deepseek-v3.2");
        request.setExtraBody(extraBody);


        // 3. 发送请求（将请求体写入输出流）
        try (OutputStream os = connection.getOutputStream()) {
            String jsonInput = JSON.toJSONString(request);
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        // 4. 读取响应数据（成功响应）
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);  // 逐行读取响应内容
        }

        // 5. 关闭资源
        in.close();
        connection.disconnect();


        // 6. 返回解析的响应数据
        ChatCompletionResponseDTO responseDTO = JSON.parseObject(content.toString(), ChatCompletionResponseDTO.class);

        // 获取结果
        ChatCompletionResponseDTO.Message message = responseDTO.getChoices().get(0).getMessage();
        return message.getContent();


    }
}
