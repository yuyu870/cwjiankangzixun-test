import com.deepseek.IDeepSeek;
import com.deepseek.impl.DeepSeek;

public class AItest {
    public static void main(String[] args) {
        try {
            // 创建DeepSeek实例
            IDeepSeek deepSeek = new DeepSeek();

            // 测试问题
            String userQuestion = "我的狗狗最近有点拉肚子，应该怎么办？";
            System.out.println("用户问题：" + userQuestion);
            System.out.println("================================");

            // 调用AI并获取回答
            String aiResponse = deepSeek.completions(userQuestion);

            System.out.println("AI回答：");
            System.out.println(aiResponse);
            System.out.println("================================");

            // 再测试一个例子
            System.out.println("\n\n第二个测试：");
            String secondQuestion = "猫咪一天应该吃几顿饭？";
            System.out.println("用户问题：" + secondQuestion);
            System.out.println("================================");

            String secondResponse = deepSeek.completions(secondQuestion);
            System.out.println("AI回答：");
            System.out.println(secondResponse);

        } catch (Exception e) {
            System.err.println("调用AI服务时出现错误：");
            e.printStackTrace();
        }
    }
}