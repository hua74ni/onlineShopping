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
        var myjson = {"userCode":"test123","userPassword":"123"};
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

    function shopSubmit(){

        var form = $("#form1");
        form.submit();

    }

    function deleteShop(){

        var myurl = "${pageContext.request.contextPath}/shop/deleteShop.do";
        var myjson = {"shopId":"f93b4535168b419899c36809da6da256"};
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

    function goodsSubmit(){

        var form = $("#form2");
        form.submit();

    }

    function deleteGoods(){

        var myurl = "${pageContext.request.contextPath}/goods/deleteGoods.do";
        var myjson = {"goodsId":"4cf2305f317044fcbb4833f8ef5dcee9"};
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

    function checkUserCode(){
        var myurl = "${pageContext.request.contextPath}/user/checkUserCode.do";
        var myjson = {"userCode":"test123"};
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

    <form id="form1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/shop/addShop.do" >
        商家图片上传<input type="file" id="shopImage" name="shopImage" /><br/>
        商家名称<input type="text" id="shopName" name="shopName" value="test123" />
    </form><br/>

    <form id="form2" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/goods/addGoods.do" >
        商品图片上传<input type="file" id="goodsImage" name="goodsImage" /><br/>
        商品名称<input type="text" id="goodsName" name="goodsName" value="goodsName123" />
        商家编码<input type="text" id="goodsShopId" name="goodsShopId" value="test123" />
        商品类型<input type="text" id="goodsType" name="goodsType" value="char" />
        商品价格<input type="text" id="goodsPrice" name="goodsPrice" value="32.00" />
    </form><br/>

    <p>商家图片</p>
    <img src="${pageContext.request.contextPath}/shop/loadImage.do?type=shop&shopLogoPath=1513499222-avatar.jpg" height="200" width="200">

    <p>商品图片</p>
    <img src="${pageContext.request.contextPath}/shop/loadImage.do?type=goods&shopLogoPath=1513500279-favicon.ico" height="200" width="200">

    <button onclick="shopSubmit();">
        商家注册
    </button>

    <button onclick="deleteShop();">
        删除商家
    </button><br/><br/>

    <button onclick="goodsSubmit();">
        商家上传商品
    </button>

    <button onclick="deleteGoods();">
        删除商品
    </button><br/><br/>

    <button onclick="checkUserCode();">
        测试userCode是否已经存在
    </button><br/>

</body>
</html>
