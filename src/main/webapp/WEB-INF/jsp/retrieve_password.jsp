<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <script src="../../js/axios.min.js" type="text/javascript" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="../../layui/css/layui.css"/>
</head>
<body>
<div style="width: 500px;margin: 120px auto; ">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="phoneNum" lay-verify="phoneNum" autocomplete="off" placeholder="请输入关联的手机号" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="newPassword" lay-verify="newPassword" autocomplete="off" placeholder="请输入新的密码" class="layui-input" id="newPassword">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" lay-verify="confirmPassword" autocomplete="off" placeholder="请输入新的密码" class="layui-input" id="confirmPassword">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>



    layui.use(['form', 'layedit', 'laydate', 'element'], function(){

            var $ = layui.$
                ,form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate
                ,element = layui.element;

            form.verify({
                phoneNum:  [/(.+){6,11}$/, '手机号必须6到11位']
                ,newPassword: [/(.+){6,12}$/, '密码必须6到12位']
                ,confirmPassword: function(value){
                    if($("#newPassword").val()!=$("#confirmPassword").val()){
                        return "两次密码不一致";
                    }
                }
            });


            form.on('submit(demo1)', function(data){
                var newpassword=$("input[name='newPassword']").val();
                var phoneNum=$("input[name='phoneNum']").val();

                $.ajax({
                    url:"${pageContext.request.contextPath}/employee/forgetPassword.action?phone="+phoneNum+"&password="+newpassword+"",
                    type:"get",

                    success:function(result){
                        console.log(result);
                        alert(result.toString());

                    },
                    error:function(e){
                        console.log(e.data);
                        alert(e);

                    }
                });

            });

        }
    )


</script>

</body>
</html>

