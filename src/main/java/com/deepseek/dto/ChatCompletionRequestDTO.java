package com.deepseek.dto;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChatCompletionRequestDTO {
    private String model;
    private List<Prompt> messages;
    private Map<String, Object> extraBody; // 阿里云特殊参数

    @Data
    public static class Prompt {
        private String role;
        private String content;

        public Prompt(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}