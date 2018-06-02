<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/25/025
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
        <span class="title">活动管理</span>
        <span class="small-title">添加活动信息</span>
    </div>

    <div class="crumbs-nav">
                <span class="layui-breadcrumb">
                    <a href="">信息中心</a>
                    <a href="">活动管理</a>
                    <a><cite>添加</cite></a>
                </span>
    </div>

    <div>

        <div class="panel-window">
            <div class="panel-header">
                <span class="panel-header-left">活动管理</span>

            </div>
            <div class="content-page">

                <div>公告</div>
                <div class="add-content">
                    <form class="layui-form" action="${pageContext.request.contextPath}/activity/insert.action">

                        <div class="layui-form-item">
                            <label class="layui-form-label">活动标题</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">活动单位</label>
                            <div class="layui-input-block">
                                <input type="text" name="dept" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">开始时间</label>
                            <div class="layui-input-block">
                                <input type="date" name="begintimes" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">结束时间</label>
                            <div class="layui-input-block">
                                <input type="date" name="endtimes" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>


                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">活动内容</label>
                            <div class="layui-input-block">
                                <textarea name="descript" id="editor" cols="30" rows="10"></textarea>
                                <script type="text/javascript">
                                    CKEDITOR.replace('editor');
                                </script>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>

                    <script>
                        //Demo
                        layui.use('form', function(){
                            var form = layui.form;

                         /*   //监听提交
                            form.on('submit(formDemo)', function(data){
                                layer.msg(JSON.stringify(data.field));
                                return false;
                            });*/
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
