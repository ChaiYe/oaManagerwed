<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/17/017
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../../../css/pageMenu.css">
    <link rel="stylesheet" href="../../../../css/leftBox/frame.css">
    <script src="../../../../js/jquery-1.11.1.min.js"></script>
    <script src="../../../../js/vue.js"></script>
    <link rel="stylesheet" href="../../../../layui/css/layui.css">
    <script src="../../../../layui/layui.js"></script>
    <link rel="stylesheet" href="../../../../css/homepageGrid.css">
    <script src="../../../../js/homepageGrid.js"></script>
    <script src="../../../../js/popup.js"></script>
    <script src="../../../../js/page.js"></script>
    <link rel="stylesheet" href="../../../../css/leftBox/frame.css">
    <link rel="stylesheet" href="../../../../css/leftBox/tableAndAdd.css">
    <link rel="stylesheet" href="../../../../css/leftBox/add.css">
    <link rel="stylesheet" href="../../../../css/leftBox/table.css">
    <link rel="stylesheet" href="../../../../css/pageMenu.css">
    <script src="../../../../ckeditor/ckeditor.js"></script>
    <script src="../../../../ckeditor/config.js"></script>
    <script src="../../../../js/modify.js"></script>
</head>
<body>
<div class="info">

    <div class="panel-title">
        <span class="title">员工管理</span>
        <span class="small-title">添加员工信息</span>
    </div>

    <div class="crumbs-nav">
                <span class="layui-breadcrumb">
                    <a href="">人事管理</a>
                    <span>&nbsp;/&nbsp;</span>
                    <a href="">员工管理</a>
                    <span>&nbsp;/&nbsp;</span>
                    <a><cite>添加</cite></a>
                </span>
    </div>

    <div>

        <div class="panel-window">
            <div class="panel-header">
                <span class="panel-header-left">员工管理</span>

            </div>
            <div class="content-page">

                <div>员工管理</div>
                <div class="add-content">
                    <form class="layui-form" action="${pageContext.request.contextPath}/employee/add.action">

                        <div class="layui-form-item">
                            <label class="layui-form-label">账号</label>
                            <div class="layui-input-block">
                                <input type="text" name="account" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input" value="A004">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input" value="王城">
                            </div>
                        </div>



                        <div class="layui-form-item">
                            <label class="layui-form-label">密码框</label>
                            <div class="layui-input-inline">
                                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="123">
                            </div>
                            <div class="layui-form-mid layui-word-aux">辅助文字</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input type="radio" name="sex" value="男" title="男" checked>
                                <input type="radio" name="sex" value="女" title="女" >
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">图片</label>
                            <input type="file" class="upload"></input>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">生日</label>
                            <div class="layui-inline">
                                <input type="text" class="layui-input" id="test1">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">电话</label>
                            <div class="layui-input-block">
                                <input type="text" name="phone" required  lay-verify="required" placeholder="请输入电话号码" autocomplete="off" class="layui-input" value="1883423544">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">住址</label>
                            <div class="layui-input-block">
                                <input type="text" name="address" required  lay-verify="required" placeholder="请输入住址" autocomplete="off" class="layui-input" value="广东省肇庆市">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮件</label>
                            <div class="layui-input-block">
                                <input type="text" name="email" required  lay-verify="required" placeholder="请输入邮件" autocomplete="off" class="layui-input" value="2344341214@qq.com">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>

                    <%--<script>
                        //Demo
                        layui.use('form', function(){
                            var form = layui.form;

                            //监听提交
                            form.on('submit(formDemo)', function(data){
                                layer.msg(JSON.stringify(data.field));
                                return false;
                            });
                        });

                    </script>--%>
                </div>
            </div>
        </div>
    </div>



</div>
</body>
</html>
