<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <script src="../../js/axios.min.js" type="text/javascript" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="../../layui/css/layui.css"/>
</head>
<body>
<div style="width: 800px;margin: 80px auto;">
    <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/vote/addVote.action">

        <input type="hidden" name="activity" value="${activity}">

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">文本域</label>
            <div class="layui-input-block">
                <textarea name="descript" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选项A</label>
            <div class="layui-input-block">
                <input type="text" name="optionA"  autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选项B</label>
            <div class="layui-input-block">
                <input type="text" name="optionB"  autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选项C</label>
            <div class="layui-input-block">
                <input type="text" name="optionC"  autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选项D</label>
            <div class="layui-input-block">
                <input type="text" name="optionD"  autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">开始时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="begintime" id="date1"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">结束时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="endtime" id="date2"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <script>
            layui.use(['form', 'layedit', 'laydate'], function(){
                var form = layui.form
                    ,layer = layui.layer
                    ,layedit = layui.layedit
                    ,laydate = layui.laydate;

                //日期
                laydate.render({
                    elem: '#date1'
                });
                laydate.render({
                    elem: '#date2'
                });
            });

        </script>
        <button class="layui-btn" lay-submit="">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>


    </form>
</div>

</body>
</html>
