<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布公告</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css" />

    <%--表单js--%>
    <script>
        layui.use(['form', 'layedit', 'laydate', 'element'], function() {
            <%--注意：导航 依赖 element 模块，否则无法进行功能性操作--%>
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate
                , element = layui.element;

            //日期
            laydate.render({
                elem: '#date'
            });
            laydate.render({
                elem: '#date1'
            });

            //创建一个编辑器
            var editor = layedit.build('editor', {
                tool: [
                    'strong' //加粗
                    , 'italic' //斜体
                    , 'underline' //下划线
                    , 'del' //删除线
                    , '|' //分割线
                    , 'left' //左对齐
                    , 'center' //居中对齐
                    , 'right' //右对齐
                    , 'link' //超链接
                    , 'unlink' //清除链接
                    , 'face' //表情
                    // ,'image' //插入图片
                    // , 'help' //帮助
                ]
            });
            //自定义验证规则
            form.verify({
                title: function (value) {
                    if (value.length < 1) {
                        return '标题不能少于1个字符';
                    }
                    if (value.length > 50) {
                        return '标题不能大于200个字符'
                    }
                }
                , descript: function (value) {
                    var content = layedit.getText(editor);
                    if (content.length < 10) {
                        return '内容不能少于10个字符';
                    }
                    if (content.length > 500) {
                        return '内容不能大于500个字符'
                    }
                }
            });
            //监听指定开关
            form.on('switch(switchTest)', function(data){
                layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                    offset: '6px'
                });
                layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
            });
        });
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
            <a href="${pageContext.request.contextPath}/employee/employeeHome.action" style="display: inline;padding-left: 0px" >首页</a>
        </li>
        <li class="layui-nav-item">
            <a href="" style="display: inline;padding-left: 0px">帮助</a>
        </li>
        <li class="layui-nav-item" style="float: right">
            <a href="" id="smallAvatar">
                <c:choose>
                    <c:when test="${sessionScope.employee.employeeInfo.image != null && sessionScope.employee.employeeInfo.image != ''}">
                        <img src="${pageContext.request.contextPath}/employee/showPic/${sessionScope.employee.employeeInfo.image}.action" class="layui-nav-img">
                    </c:when>
                    <c:otherwise>
                        <img src="../../img/icons_02.gif" class="layui-nav-img">
                    </c:otherwise>
                </c:choose>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/employee/employeeHome.action">个人主页</a></dd>
                <dd><a href="${pageContext.request.contextPath}/employee/accountSetPage.action">账号设置</a></dd>
                <dd><a href="${pageContext.request.contextPath}/employee/infoEditPage.action">个人信息</a></dd>
                <c:if test="${sessionScope.job.authority > 1}"><dd><a href="${pageContext.request.contextPath}/employee/managePage.action" >管理</a></dd></c:if>
                <dd><a href="${pageContext.request.contextPath}/employee/logout.action" >注销</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item" style="float: right">
            <a href="${pageContext.request.contextPath}/announce/jumpToAllAnnounce.action">公告<span class="layui-badge-dot"></span></a>
        </li>
        <%--<li class="layui-nav-item" style="float: right">--%>
        <%--<a href="${pageContext.request.contextPath}/activity/addActivityPage.action" style="display: inline;padding-left: 0px">活动<span class="layui-badge-dot"></span></a>--%>
        <%--</li>--%>
    </ul>
    </div>

    <!--页面主体-->
    <div class="layui-row">
        <div class="layui-col-md6 layui-col-md-offset3">

            <div class="description">
                <div class="title">发布公告</div>
                <div class="subtitle">请填写这些信息，以便于其他员工查看</div>
            </div>

            <div class="info-content">
                <div class="head-portrait" >
                    <img class="img" src="../../img/14ab8759-d897-445d-8156-ea9fe1ca20cd.jpg" />
                </div>
                <!--表单-->
                <div class="edit-panel">
                    <div>
                        <form action="${pageContext.request.contextPath}/announce/addAnnounce.action" method="post" class="layui-form">

                            <div class="layui-form-item">
                                <label class="layui-form-label">标题</label>
                                <div class="layui-input-block">
                                    <input type="text" name="title" lay-verify="required|title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">部门</label>
                                <div class="layui-input-block">
                                    <select name="dept" lay-verify="required">
                                        <option value=""></option>
                                        <c:forEach var="item" items="${jobs}">
                                            <option value="${item.dept}">${item.depart.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">内容</label>
                                <div class="layui-input-block">
                                    <textarea id="editor" name="descript" lay-verify="descript"></textarea>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label bug">&nbsp;</label>
                                <div class="layui-input-block" id="submit-btn">
                                    <button class="layui-btn layui-btn-primary" lay-submit="" style="width: 100%;">发布</button>
                                </div>
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
