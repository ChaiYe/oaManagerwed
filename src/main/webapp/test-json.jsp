<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/14/014
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.11.1.min.js"></script>
    <script>
        function fun(){
            $.ajax({
                url:"${pageContext.request.contextPath }/dept/testJson.action",
                type:"post",
                contentType:"application/json;charset=utf-8",
                //请求json数据,使用json表示商品信息

                success:function(data){

                    console.log(JSON.stringify(data));
                    alert(data.name);
                }


            });
        }

    </script>
</head>
<body>
<button type="button" onclick="fun()">按钮</button>
</body>
</html>
