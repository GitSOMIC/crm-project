<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <!--  JQUERY -->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <!--  BOOTSTRAP -->
    <link rel="stylesheet" type="text/css" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <%--    引入typehead--%>
    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
    <title>演示自动补全插件2</title>
    <script type="text/javascript">
        $(function () {
            //
            $("#customerName").typeahead({
                source: function (jquery, process) {//每次键盘弹起，都会触发这个函数，可以去后端查询，以[]json的形式返回
                    //process 把字符串赋值给 source
                    //jquery 用户在容器中输入的关键字
                    // var customerName = $("#customerName").val(); 不需要自己做插件已经做好

                    //发送查询请求
                    $.ajax({
                        url: 'workbench/transaction/queryCustomerByName.do',
                        data:{
                            // customerName:customerName 不需要自己做插件已经做好
                            customerName:jquery
                        },
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {//data=['xxx','xxxx']
                            process(data);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<input type="text" id="customerName">
</body>
</html>
