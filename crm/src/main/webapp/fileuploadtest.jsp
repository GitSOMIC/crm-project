<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%--
    文件上传的表单 三个条件
    1.表单组件标签只能使用：file
    <input type="text|password|radio|checkbox|hidden|button|submit|reset|file">
    <select >,<textarea>等
    2.请求方式只能post
    3.表单编码格式只能用 multipart/form-data
    根据http协议规定，浏览器每次向后台提交参数，都会对参数进行统一编码，默认采用的编码格式是urlencoded，
    这种编码格式只能对文本数据进行编码
    浏览器每次向后台提交参数，都会首先把所有的参数转换成为字符串，然后对这些数据统一进行urlencoded编码；
    文件上传的表单编码格式只能用multipart/form-data， enctype="multipart/form-data"
--%>
<form action="workbench/activity/fileUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="myFile">
    <input type="text" name="userName">
    <input type="submit" value="上传">
</form>
</body>
</html>
