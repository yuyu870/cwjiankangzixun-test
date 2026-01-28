curl -X POST "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions" \
  -H "Authorization: Bearer sk-5e217a3788c44d259537cd3143db5817" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "deepseek-v3.2",
    "messages": [{"role": "user", "content": "hello"}],
    "extra_body": {"enable_thinking": true}
  }'