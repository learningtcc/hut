<%--
  Created by IntelliJ IDEA.
  User: Jared
  Date: 2016/12/10
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%
    String ctx = request.getContextPath();
    request.setAttribute("ctx",ctx);
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hut in the forest</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/common.css">
</head>
<body>
<h1>hut in the forest</h1>
<P>欢迎来到 hut in the forest ，在这里可以做很多你喜欢的事情。</P>

<div><a target="_blank" href="">注册</a></div>
<div><a target="_blank" href="">登陆</a></div>

<h2>把你的相片留下这里</h2>
<ul>
    <li>照片1</li>
    <li>照片2</li>
    <li>照片3</li>
</ul>
<h2>把你的心情留在这里</h2>
<ul>
    <li>2015/12/21  开始我的java征程</li>
    <li>2015/06/25  四年大学结束了，今天正式步入社会</li>
    <li>2015/11/28  进入黑马开始我的4个月IT培训</li>
    <li>2016/04/18  黑马的培训在今天结束了</li>
</ul>
<h2>把你的故事留在这里</h2>
<ul>
    <li>今天妈妈生日，一家人过得很愉快</li>
    <li>快要过年了，好高兴</li>
    <li>学习不能停，你不是一个“人”在战斗，你是一匹野马</li>
</ul>

<h2>这是你的备忘录</h2>
<ol>
    <li>还有一屁股债啊，赶紧还了得</li>
    <li>老爸是什么时候生日，记着问问老妈去</li>
    <li>到过年前的学习计划是HTML/CSS、Spring Framework</li>
</ol>

</body>
</html>
