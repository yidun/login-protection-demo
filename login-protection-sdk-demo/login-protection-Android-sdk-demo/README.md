登陆保护 Android sdk 示例 demo

demo 运行步骤

1、运行模拟业务后端：check demo，运行方法见login-protection-check-demo目录
2、修改 LoginActivity.java 的 onCreate，填入您的 bussinessId，如下：
RjsbHandler.setBusinessId(getApplicationContext(), "YOUR_BUSINESS_ID");


String url = "http://localhost:8182/login.do";
// 例如，替换如下：
String url = "http://10.240.132.43:8182/login.do";
至此，配置和修改完成，编译运行即可