<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>${sessionScope.employee.employeeInfo.name}</title>

    <script src="../../js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.form.js"></script>
    <script src="../../js/vue.js"></script>
    <script src="../../layui/layui.js"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css">

    <script type="text/javascript">
        layui.use('laypage', function () {
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                , count: 70 //数据总数，从服务端得到
            });
        });
        function tab() {

            $(".right").toggle(3000).addClass("show");
        }
    </script>
    <script>
        //注意：导航 依赖 element 模块，否则无法进行功能性操作
        layui.use('element', function () {
            var element = layui.element;
            //…
        });
        layui.use('layer', function(){ //独立版的layer无需执行这一句
            var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

            //触发事件
            var active = {
                offset: function(othis){
                    var type = othis.data('type')
                        ,text = othis.text();
                    layer.open({
                        type: 1
                        ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        ,id: 'layerDemo'+type //防止重复弹出
                        ,content: '<div style="padding: 20px 100px;">'+ text +'</div>'
                        ,btn: '关闭全部'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            layer.closeAll();
                        }
                    });
                }
            };

            $('#layerDemo .layui-btn').on('click', function(){
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            });
        });
    </script>

    <style>
        /*禁止显示滚动条*/
        ::-webkit-scrollbar {display:none}

        /*图像*/
        .commentAvatarDiv{
            width: 100px;
            height: 100px;
            padding:5px;
            background: #ececec;
            border-radius:100px;
            box-shadow: 0px 0px 1px rgba(0,0,0,0.4);
            -moz-border-radius: 100px;
            -webkit-border-radius: 100px;
        }
        .commentAvatarImage{
            width:100px;
            height:100px;
            line-height: 0;
            /* remove line-height */
            display: inline-block;
            /* circle wraps image */
            border-radius: 50%;
            /* relative value */
            -moz-border-radius: 50%;
            -webkit-border-radius: 50%;
            transition: linear 0.25s;
        }
        /*个人信息模块布局*/
        .content{
            height:180px;
        }
        .session1{
            /*align-self: center;*/
            position:absolute;
            top:50px;
            left:40px;
            margin:20px;
        }

        /*任务列表布局*/
        .taskContent{
            display:flex;
            flex-wrap: wrap;
            align-items: flex-start;
            align-content: flex-start;
            height: 150px;
        }
        .taskContent>div{
            margin:20px;
        }

        /*个人信息展示*/
        /*布局*/
        .self_info{
            margin:0 20px 20px 20px;
        }
        .self-info-name{
            padding-bottom: 10px;
        }
        .self-info-message>div>div{
            display: inline;
        }

        /*最近操作列表下 子项*/
        /*布局*/
        .item{
            display: flex;
        }
        .item-logo{
            margin:10px;
            align-self:center;

        }
        .item-content>div{
            padding:8px;
        }

        .item-content-operate>div{
            display:inline;
            margin-right:50px;
        }

        /*装饰*/
        .item-content-title{
            font-size: medium;
            font-weight:bold;
        }
        .item-logo>i{
            font-size: 40px;
            margin: 0 auto;
        }
        .item-logo{
            width: 40px;
            height: 40px;
            padding:12px;
            background: #ececec;
            border-radius:40px;
            box-shadow: 0px 0px 1px rgba(0,0,0,0.4);
            -moz-border-radius: 40px;
            -webkit-border-radius: 40px;
        }
        .item-panel{
            background: #f5f5f5;
            border-radius:5px;
            box-shadow: 0px 0px 5px rgba(50,50,50,0.4);
        }

        /*model列表*/
        .model-item{
            margin:20px 10px;
            padding:10px;
            border-radius:5px;
            background: #f5f5f5;
        }
        .model-item>div{
            margin: 5px;
        }
        .model-item-title{
            font-size: 21px;
            font-weight: bold;
        }
        .model-item-subtitle{
            color: #919191;
            font-size: x-small;
            padding-left: 3px;
        }
    </style>
    <%--弹出动画--%>
    <style>
        /*activity-vote*/
        .activity-vote{
            /*height:18px;*/
            /*overflow: hidden;*/
            /*transition: height 0.5s;*/
            overflow: hidden;
            max-height: 20px;
            -webkit-transition: max-height .75s;
            transition: max-height .75s;
        }
        .activity-vote:hover {
            /*height:auto;*/
            /*height: 400px;*/
            max-height:600px;
        }
    </style>
    <%--文件上传框样式--%>
    <style>
        /*文件上传样式*/
        .file {
            position: relative;
            display: inline-block;
            background: rgba(0,0,0,0);
            border: 1px solid rgba(0,0,0,0);
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #1E88C7;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
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
            background-color: #02a67d;
            border-radius: 50%;
            transition: .3s;
            cursor: pointer;
            vertical-align: middle;
            box-shadow: 0px 0px 5px rgba(0,0,0,0.4);
            text-align:center;
        }
        .btn-floating>i{
            size: 50px;
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
    <!--头像、装饰图片-->
    <div class="content">

        <div style="background: #AFB42B;height:120px;overflow: hidden;">
            <img src="../../img/152790724872243.png" style="float: right;height: 100%; padding-right:80px;" />
        </div>

        <!--图片上传模块-->
        <div style="background:#CDDC39;height: 40px;padding-left: 75px">
            <form method="POST" enctype="multipart/form-data" id="imgForm">
                <a href="javascript:;" class="file">更改头像
                    <input type="file" name="imgFile" id="imgFile" accept="image/png, image/jpeg, image/gif, image/jpg">
                </a>
            </form>
            <%--异步上传图片，不刷新页面--%>
            <script>
                //数据检验
                function checkData(fileInput){
                    var fileDir = fileInput.val();
                    if("" === fileDir){
                        alert("请选择图片！");
                        return false;
                    }
                    return true;
                }
                //异步上传操作
                function fileUpload(){
                    $('#imgForm').ajaxSubmit({
                        url:'${pageContext.request.contextPath}/employee/imgUpload.action?' + new Date().getMilliseconds(),
                        dataType: 'json',
                        success: function(data){
                            alert("成功上传");
                            $('#imgFile').val("");
                            //设定img的src，加时间戳以防浏览器缓存不加载
                            var src = "'" + "${pageContext.request.contextPath}/employee/showPic/" + data +".action?" + new Date().getMilliseconds() + "'";
                            var img = "<img src=" + src + " alt='头像' class='commentAvatarImage'/>";
                            $("#avatarDiv").html(img);
                            var smallImg = "<img src="+ src + " class='layui-nav-img'>";
                            $("#smallAvatar").html(smallImg);
                        },
                        error: function () {
                            alert("error")
                        }
                    });
                }
                $(document).ready(function () {
                    $('#imgFile').bind('input propertychange', function() {
                        // alert("ssdasdsad");
                        if (checkData($('#imgFile'))) {
                            fileUpload();
                        }
                    });
                })
            </script>
        </div>

        <div class="session1">
            <div class="commentAvatarDiv" id="avatarDiv">
                <c:choose>
                    <c:when test="${sessionScope.employee.employeeInfo.image != null && sessionScope.employee.employeeInfo.image != ''}">
                        <img src="/employee/showPic/${sessionScope.employee.employeeInfo.image}.action" alt="头像" class="commentAvatarImage"/>
                    </c:when>
                    <c:otherwise>
                        <img src="../../img/icons_02.gif" alt="头像" class="commentAvatarImage" id="imgAvatar"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <!--信息部分-->
    <div class="taskContent">
        <!--个人信息模块-->
        <div style="width:20%;">
            <div class="self_info">
                <div class="self-info-name">
                    <h3>${sessionScope.employee.account}</h3>
                    <p>${sessionScope.employee.employeeInfo.name}</p>
                </div>
                <div class="self-info-message">
                    <div>
                        <div><i class="layui-icon">&#xe62e;</i></div>
                        <div>joined <fmt:formatDate value="${sessionScope.employee.createtime}" type="date" /></div>
                    </div>
                    <div>
                        <div><i class="layui-icon">&#xe637;</i></div>
                        <div>age ${sessionScope.employee.employeeInfo.age}</div>
                    </div>
                    <hr class="layui-bg-gray">
                    <div>
                        <c:if test="${!empty sessionScope.employee.jobs}">
                            <c:forEach var="job" items="${sessionScope.employee.jobs}">
                                <div class="model-item-subtitle" style="padding-left: 0px">
                                    部门：${job.depart.name}<br/>
                                    职位：${job.name}<br/>
                                    入职时间：<fmt:formatDate value="${job.worktime}" type="date" />
                                    <hr class="layui-bg-gray">
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>


        <!--活动模块-->
        <div style="flex-grow: 1">
            <div>
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>近期活动</legend>
                </fieldset>
            </div>
            <div id="activity">
                <div style="margin: 12px; padding-bottom: 5px" class="item-panel" v-for="item in items">
                    <hr class="layui-bg-gray">
                    <div style="margin: 20px;">
                        <div style="margin: 10px;">
                            <div class="item">
                                <a :href="'${pageContext.request.contextPath}/activity/activityDetailPage/'+item.uuid +'.action'"><div class="item-logo"><i class="layui-icon">&#xe629;</i></div></a>

                                <div class="item-content">
                                    <div class="item-content-title">
                                        {{item.name}}
                                    </div>
                                    <div class="item-content-main" v-html="item.descript"></div>
                                    <div class="item-content-operate">
                                        <div><i class="layui-icon" style="color: green">&#xe637;</i>{{item.begintime|dateFormat}}</div>
                                        <div><i class="layui-icon" style="color: red">&#xe637;</i>{{item.endtime|dateFormat}}</div>
                                        <div><i class="layui-icon">&#xe613;</i>{{item.depart.name}}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="layui-bg-gray">
                        <div class="activity-vote">
                            <div style="text-align:center">查看活动大事记</div>
                            <div>
                                <fieldset class="layui-elem-field">
                                    <legend>里程碑</legend>
                                    <div style="padding:20px">
                                        <ul class="layui-timeline">
                                            <li class="layui-timeline-item" v-for="i in item.markers">
                                                <i class="layui-icon layui-timeline-axis"></i>
                                                <div class="layui-timeline-content layui-text">
                                                    <div class="layui-timeline-title">{{i.createtime|timeStampFormat}}，{{i.descript}}</div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div >
            </div>
            <script>
                var activity = new Vue({
                    el: '#activity',
                    data: {
                        items:[]
                    },
                    created () {
                        $.ajax({
                            url:"${pageContext.request.contextPath}/activity/recentActivity.action",
                            type:"post",
                            dataType:"json",
                            success:function(result){
                                activity.items = result;
                            },
                            error:function(){
                                alert("请求失败");
                            }
                        });
                    },
                    filters:{
                        dateFormat:function(val){
                            var d = new Date(val);
                            var year = d.getFullYear();
                            var month = d.getMonth() + 1;
                            var day = d.getDate() <10 ? '0' + d.getDate() : '' + d.getDate();
                            var hour = d.getHours();
                            var minutes = d.getMinutes();
                            var seconds = d.getSeconds();
                            return  year+ '年' + month + '月' + day + '日';
                        }
                        ,timeStampFormat:function(val){
                            var d = new Date(val);
                            var year = d.getFullYear();
                            var month = d.getMonth() + 1;
                            var day = d.getDate() <10 ? '0' + d.getDate() : '' + d.getDate();
                            var hour = d.getHours();
                            var minutes = d.getMinutes();
                            var seconds = d.getSeconds();
                            return  year+ '年' + month + '月' + day + '日' + ' '
                                + hour + ':' + minutes + ':' + seconds;
                        }
                    }
                })
            </script>
        </div>

        <!-- 公告模块-->
        <div style="width: 20%;margin-top: 0;">
            <!-- 公司公告-->
            <div id="comp_announce">
                <%--<h2 v-show="items.length">公司公告</h2>--%>
                <fieldset class="layui-elem-field layui-field-title" v-show="items.length">
                    <legend>公司公告</legend>
                </fieldset>
                <div class="model-item" v-for="item in items">
                    <div class="model-item-title">
                        {{item.title}}
                    </div>

                    <div class="model-item-subtitle">
                       {{item.createtime|dateFormat}}
                        <span style="float: right">公司</span>
                    </div>
                    <div class="model-item-content" v-html="item.descript"></div>
                </div>
            </div>
            <script>
                var app = new Vue({
                    el: '#comp_announce',
                    data: {
                        items:[]
                    },
                    created () {
                        $.ajax({
                            url:"${pageContext.request.contextPath}/announce/recentCompAjax.action",
                            type:"post",
                            dataType:"json",
                            success:function(result){
                                app.items = result;
                            },
                            error:function(){
                                alert("请求失败");
                            }
                        });
                    },
                    filters:{
                        dateFormat:function(val){
                            var d = new Date(val);
                            var year = d.getFullYear();
                            var month = d.getMonth() + 1;
                            var day = d.getDate() <10 ? '0' + d.getDate() : '' + d.getDate();
                            var hour = d.getHours();
                            var minutes = d.getMinutes();
                            var seconds = d.getSeconds();
                            return  year+ '年' + month + '月' + day + '日';
                        }
                    }
                })
            </script>

            <div id="allDept">
            <!-- 部门公告-->
                <div id="dept_announce">
                    <%--<h2 v-show="items.length">部门公告</h2>--%>
                    <fieldset class="layui-elem-field layui-field-title" v-show="items.length">
                        <legend>部门公告</legend>
                    </fieldset>
                    <div class="model-item" v-for="item in items">
                        <div class="model-item-title">
                            {{item.title}}
                        </div>

                        <div class="model-item-subtitle">
                            {{item.createtime|dateFormat}}
                            <span style="float: right">{{item.depart.name}}</span>
                        </div>
                        <div class="model-item-content" v-html="item.descript"></div>
                    </div>
                </div>
            </div>
            <script>
                var dept = new Vue({
                    el: '#dept_announce',
                    data: {
                        items:[]
                    },
                    created () {
                        $.ajax({
                            url:"${pageContext.request.contextPath}/announce/recentDeptAjax.action",
                            type:"post",
                            dataType:"json",
                            success:function(result){
                                dept.items = result;
                            },
                            error:function(){
                                alert("请求失败");
                            }
                        });
                    },
                    filters:{
                        dateFormat:function(val){
                            var d = new Date(val);
                            var year = d.getFullYear();
                            var month = d.getMonth() + 1;
                            var day = d.getDate() <10 ? '0' + d.getDate() : '' + d.getDate();
                            var hour = d.getHours();
                            var minutes = d.getMinutes();
                            var seconds = d.getSeconds();
                            return  year+ '年' + month + '月' + day + '日';
                        }
                    }
                })
            </script>
        </div>
    </div>

    <!-- 右下角-->
    <c:if test="${sessionScope.job.authority > 1}">
        <div class="more" >
        <div class="fixed-action-btn" >
            <a class="btn-floating btn-large teal"  style="transform: translateY(100px) translateX(0px);"><i class="layui-icon">&#xe653;</i> </a>

            <ul class="more-item">
                <li>
                    <a class="btn-floating orange modal-trigger" href="${pageContext.request.contextPath}/announce/addAnnouncePage.action" title="公告" style="transform: scaleY(1) scaleX(1) translateY(-60px) translateX(0px); opacity: 0;"><span style="font-size: 10px">发布公告</span></a>
                </li>
                <li>
                    <a class="btn-floating blue modal-trigger" href="${pageContext.request.contextPath}/activity/addActivityPage.action" title="修改" style="transform: scaleY(1) scaleX(1) translateY(-55px) translateX(0px); opacity: 0;"><span style="font-size: 10px">发布活动</span></a>
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
    </c:if>
</body>
</html>
