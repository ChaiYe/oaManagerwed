<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${sessionScope.employee.employeeInfo.name}账号设置</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css" />

    <%--表单js--%>
    <script>
        $(function () {
            layui.use(['form', 'layedit', 'laydate', 'element'], function(){
                var $ = layui.$
                    ,form = layui.form
                    ,layer = layui.layer
                    ,layedit = layui.layedit
                    ,laydate = layui.laydate
                    ,element = layui.element;

                //创建一个编辑器
                var editIndex = layedit.build('LAY_demo_editor');

                //自定义验证规则
                form.verify({
                    account : function(value){
                        if(value.length < 1){
                            return '账号不能少于1个字符';
                        }
                        if(value.length > 200){
                            return '账号不能大于200个字符'
                        }
                    }
                    ,password : function (value) {
                        if(value.length > 200){
                            return '密码不能超过200位';
                        }
                        if(value.length < 3){
                            return '密码不能小于3位';
                        }
                    }
                    ,pass : function (value) {
                        if (value !== $('#newPW').val()){
                            return '两次输入的密码不一致';
                        }
                    }
                });

                form.on('submit(acc)', function(data){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "${pageContext.request.contextPath}/employee/accountUpdate.action",
                        contentType : "application/json",
                        data : JSON.stringify(data.field),
                        async:false,
                        error:function(data){
                            alert("Error");
                        },
                        success:function(data){
                            if(data){
                                layer.alert(data, {title: '提示'});
                            }
                        }
                    });
                    return false;
                });

                form.on('submit(PW)', function(data){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "${pageContext.request.contextPath}/employee/passwordUpdate.action",
                        contentType : "application/json",
                        data : JSON.stringify(data.field),
                        async:false,
                        error:function(data){
                            alert("Error");
                        },
                        success:function(data){
                            if(data){
                                layer.alert(data, {title: '提示'});
                                $("#oldPW").val("");
                                $("#newPW").val("");
                                $("#newPWAgain").val("");
                            }
                        }
                    });
                    return false;
                });
            });
        })
    </script>
    <style>
        table td{
            padding:12px;
        }
        .info-content{
            background: white;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(150,130,150,0.5);
            margin-bottom:70px;

        }
        .title{
            font-size: 36px;
            font-weight: bold;
            padding-top: 64px;
            padding-bottom: 32px;
            line-height: 42px;
        }
        .subtitle{
            color: rgba(0,0,0,0.65);
            font-size: 17px;
            font-weight: 400;
            line-height: 24px;
            padding-bottom: 12px;
        }
        .head-portrait{
            overflow: hidden;
            height:200px;
            width: auto;
        }
        .img{
            width: 100%;
        }
        .edit-panel{
            padding: 12px;
        }
        .layui-form-item,.layui-inline{
            padding: 10px;
        }
        #submit-btn{
            padding-top: 36px;
            width: 70%;
        }
    </style>
    <%--右下角按钮样式--%>
    <style>
        /*more*/
        .fixed-action-btn {
            position: fixed;
            right: 23px;
            bottom: 23px;
            padding-top: 15px;
            margin-bottom: 0;
            z-index: 998;

        }
        .btn-floating {
            display: inline-block;
            color: #fff;
            position: relative;
            overflow: hidden;
            z-index: 1;
            width: 50px;
            height: 50px;
            line-height: 50px;
            padding: 0;
            background-color: #26a69a;
            border-radius: 50%;
            transition: .3s;
            cursor: pointer;
            vertical-align: middle;
            box-shadow: 0px 0px 5px rgba(0,0,0,0.4);
            text-align:center;
        }
        .btn-floating>i{
            size: 36px;
        }
    </style>
</head>
<body>

<!--导航条-->
<div>
    <ul class="layui-nav">

        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/employee/employeeHome.action" style="display: inline;padding-left: 0px" target="infoContent">首页</a>
        </li>

        <li class="layui-nav-item">
            <a href="" style="display: inline;padding-left: 0px">帮助</a>
        </li>

        <li class="layui-nav-item" style="float: right">
            <a href="" id="smallAvatar"><img src="/employee/showPic/${sessionScope.employee.employeeInfo.image}.action" class="layui-nav-img"></a>
            <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/employee/employeeHome.action">个人主页</a></dd>
                <dd><a href="${pageContext.request.contextPath}/employee/infoEditPage.action">个人信息</a></dd>
                <c:if test="${sessionScope.job.authority > 1}"><dd><a href="${pageContext.request.contextPath}/employee/managePage.action" >管理</a></dd></c:if>
                <dd><a href="${pageContext.request.contextPath}/employee/logout.action" >注销</a></dd>
            </dl>
        </li>

        <li class="layui-nav-item" style="float: right">
            <a href="">公告<span class="layui-badge-dot"></span></a>
        </li>
        <li class="layui-nav-item" style="float: right">
            <a href="" style="display: inline;padding-left: 0px">活动<span class="layui-badge-dot"></span></a>
        </li>
    </ul>
</div>

<!--页面主体-->
<div class="layui-row">
    <div class="layui-col-md6 layui-col-md-offset3">

        <div class="description">
            <div class="title">您的账号设置</div>
            <div class="subtitle">您可以更改您的账号和密码</div>
        </div>

        <div class="info-content">
            <div class="head-portrait" >
                <img class="img" src="../../img/14ab8759-d897-445d-8156-ea9fe1ca20cd.jpg" />
            </div>
            <!--表单-->
            <div class="edit-panel">
                <div>
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>更改登录账号</legend>
                    </fieldset>
                    <form class="layui-form layui-form-pane" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">账号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="account" autocomplete="off" lay-verify="required|account" placeholder="请输入账号" class="layui-input" value="${sessionScope.employee.account}">
                            </div>
                            <div class="layui-form-mid layui-word-aux">请输入新的账号</div>
                        </div>

                        <div class="layui-form-item">
                            <button class="layui-btn" lay-submit="" lay-filter="acc">更改</button>
                        </div>
                    </form>

                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                        <legend>更换密码</legend>
                    </fieldset>
                    <form class="layui-form layui-form-pane" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">旧密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="oldPW" name="oldPassword" lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">请填写旧密码</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="newPW" name="newPassword" lay-verify="required|password" placeholder="请输入新密码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">请输入安全的密码</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">再次输入</label>
                            <div class="layui-input-inline">
                                <input type="password" id="newPWAgain" name="newPassWordAgain" lay-verify="required|pass" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">请确保两次输入的密码一致</div>
                        </div>

                        <div class="layui-form-item">
                            <button class="layui-btn" lay-submit="" lay-filter="PW">修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 右下角-->
<div class="more" >
    <div class="fixed-action-btn" >
        <a class="btn-floating btn-large teal"  style="transform: translateY(100px) translateX(0px);"><i class="layui-icon">&#xe653;</i> </a>

        <ul class="more-item">
            <li>
                <a class="btn-floating orange modal-trigger" href="#" title="修改" style="transform: scaleY(0.7) scaleX(0.7) translateY(-80px) translateX(0px); opacity: 0;"><i class="layui-icon">&#xe654;</i> </a>
            </li>
            <li>
                <a class="btn-floating blue modal-trigger" href="#" title="修改" style="transform: scaleY(0.7) scaleX(0.7) translateY(-80px) translateX(0px); opacity: 0;"><i class="layui-icon">&#xe670;</i> </a>
            </li>
        </ul>
    </div>
    <script type="text/javascript">
        $(function(){
            $('.more').mouseover(
                function(){
                    $(".modal-trigger").css("opacity",'1');
                }
            );
            $('.more').mouseout(
                function(){
                    $(".modal-trigger").css("opacity",'0');
                }
            );
        })
    </script>
</div>
</body>
</html>
