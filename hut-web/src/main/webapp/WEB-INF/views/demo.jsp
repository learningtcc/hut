<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>demo</title>
</head>
<body>

<div>
    <span><input id="searchinput" type="text" value="keywords"/></span>
    <span id="searchButton" onclick="searchUser()">搜索用户</span>
</div>

<div>
    <div>登录</div>
    <form action="/user/login.html" method="get">
        <input type="text" name="userName"/>
        <input type="password" name="password"/>
        <input type="submit">提交</input>
    </form>
</div>

<div>
    <div>注册</div>
    <form action="/user/register.html" method="post">
        <input type="text" name="userName">用户名</input>
        <input type="password" name="password">密码</input>
        <input type="tel" name="phone">电话</input>
        <input type="email" name="email">邮箱</input>
        <input type="submit">提交</input>
    </form>
</div>

<div>
    <div>文件上传，需要先登录</div>
    <form action="/file/upload" method="post" enctype="multipart/form-data">
        <input type="file" value="file"/>
        <input type="submit">提交</input>
    </form>
</div>

<div>
    <div>发送邮件，需要先登录</div>
    <form action="/message" method="get">
        <input type="text" value="userName">
        <input type="text" value="userName">
        <input type="submit" value="userName">
    </form>
</div>


<div>
    <div>从Redis中获取所有后台用户，RabbitMq做后台删除用户，缓存也删除用户，后台添加用户，缓存重新查询并push进缓存</div>
    <div>
        <c:forEach items="userList" var="user">
            <ul>
                <%--${user.username}--%>
            </ul>
        </c:forEach>
    </div>
</div>

<script type="application/javascript" src="/rs/js/jquery-1.8.3.js"/>
<script>
    function queryUser() {

    }

    /*function searchUser() {
        var keywords = $(#"searchinput").val();

        if (!keywords.empty()&&keywords!=""){
            $.ajax(){}
        }
    }*/
</script>
</body>
</html>
