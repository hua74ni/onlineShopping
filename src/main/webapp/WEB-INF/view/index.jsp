<%--
  Created by IntelliJ IDEA.
  User: huangdonghua
  Date: 2017/12/14
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello界面</title>
</head>
<script src="${pageContext.request.contextPath}/resouces/js/jquery.min.js"></script>
<script>

    //登录
    function login() {
        var myurl = "${pageContext.request.contextPath}/login.do";
        var myjson = {"userCode":"test222","userPassword":"123"};
        $.ajax({
            type: "POST",
            url: myurl,
            dataType: "json",
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(myjson),
            success: function(data) {
                console.log(data);
            }
        });
    }

    //通过用户ID获取用户信息
    function getUserByUserId() {
        var myurl = "${pageContext.request.contextPath}/goods/getGoodsByUserId.do";
        var myjson = {"pageNum":0,"pageSize":1};
        $.ajax({
            type: "POST",
            url: myurl,
            dataType: "json",
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(myjson),
            success: function(data) {
                console.log(data);
            }
        });
    }

</script>
<body>

    <p>hello页面</p><br/>
    <br/>
    <button onclick="login();">
        登录
    </button>

    <button onclick="getUserByUserId();">
        测试
    </button>

</body>
</html>
