# 登陆保护 check端示例demo（java）

check端即 接入方业务后台，该demo仅提供login.do接口，需要结合sdk demo一起使用

# demo运行步骤

* 修改LoginServlet.java

```

    /** 产品密钥ID */
    private static final String SECRET_ID = "YOUR_SECRET_ID";
    
    /** 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露 */
    private static final String SECRET_KEY = "YOUR_SECRET_KEY";
    
    /** 业务ID，易盾根据产品业务特点分配 */
    private static final String BUSINESS_ID = "YOUR_BUSINESS_ID";

``


* 运行demo
mvn tomcat7:run


* 浏览器访问 http://localhost:8182/login.do，如果返回json串说明成功

* 结合sdk demo，查看登陆保护检测效果
