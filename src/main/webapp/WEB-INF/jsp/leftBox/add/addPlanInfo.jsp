<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/20/020
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/oaManagerwed/js/jquery-1.11.1.min.js"></script>
    <script src="/oaManagerwed/js/vue.js"></script>
    <link rel="stylesheet" href="/oaManagerwed/layui/css/layui.css">
    <script src="/oaManagerwed/layui/layui.js"></script>
    <link rel="stylesheet" href="/oaManagerwed/css/homepageGrid.css">
    <script src="/oaManagerwed/js/homepageGrid.js"></script>
    <script src="/oaManagerwed/js/popup.js"></script>
    <script src="/oaManagerwed/js/page.js"></script>
    <link rel="stylesheet" href="/oaManagerwed/css/leftBox/frame.css">
    <link rel="stylesheet" href="/oaManagerwed/css/leftBox/tableAndAdd.css">
    <link rel="stylesheet" href="/oaManagerwed/css/leftBox/add.css">
    <link rel="stylesheet" href="/oaManagerwed/css/leftBox/table.css">
    <link rel="stylesheet" href="/oaManagerwed/css/pageMenu.css">
    <script src="/oaManagerwed/ckeditor/ckeditor.js"></script>
    <script src="/oaManagerwed/ckeditor/config.js"></script>
    <script src="/oaManagerwed/js/modify.js"></script>
</head>
<body>
<div class="info">

    <div class="panel-title">
        <span class="title">公告管理</span>

        <span class="small-title">添加公告信息</span>
    </div>

    <div class="crumbs-nav">
                <span class="layui-breadcrumb">
                    <a href="">信息中心</a>
                    <span>&nbsp;/&nbsp;</span>
                    <a href="">公告管理</a>
                    <span>&nbsp;/&nbsp;</span>
                    <a><cite>添加</cite></a>
                </span>
    </div>

    <div>

        <div class="panel-window">
            <div class="panel-header">
                <span class="panel-header-left">公告管理</span>

            </div>
            <div class="content-page">

                <div>公告</div>
                <div class="add-content">
                    <form class="layui-form" action="${pageContext.request.contextPath}/announce/insert.action">
                        <div class="layui-form-item">
                            <label class="layui-form-label">描述</label>
                            <div class="layui-input-block">
                                <input type="text" name="descript" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">创建时间</label>
                            <div class="layui-input-block">
                                <input type="date" name="createtime" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">开始时间</label>
                            <div class="layui-input-block">
                                <input type="date" name="begintime" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">结束时间</label>
                            <div class="layui-input-block">
                                <input type="date" name="endtime" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
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

                            //监听提交
                           /* form.on('submit(formDemo)', function(data){
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
