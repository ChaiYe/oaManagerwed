<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>${sessionScope.employee.employeeInfo.name}</title>

    <link rel="stylesheet" type="text/css" href="../../layui/css/layui.css"/>
    <script src="../../js/jquery-1.11.1.min.js"></script>
    <script src="../../js/vue.js"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script src="../../layui/layui.js"></script>
    <script type="text/javascript">

        layui.use('laypage', function () {
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                , count: 70 //数据总数，从服务端得到
            });
        });

        /*function change(obj){
            console.log($(obj).parents("tr").children());
            $(obj).parents("tr").find("input").removeAttr("readonly").removeClass("inputs");
        }
        */

        function tab() {

            $(".right").toggle(3000).addClass("show");
        }
    </script>
    <style>
        /*图像*/
        .commentAvatarDiv{   width: 100px;   height: 100px;   padding:5px;   background: #ececec;   border-radius:100px;   box-shadow: 0px 0px 1px rgba(0,0,0,0.4);   -moz-border-radius: 100px;   -webkit-border-radius: 100px;}
        .commentAvatarImage{    width:100px;    height:100px;    line-height: 0;		/* remove line-height */     display: inline-block;	/* circle wraps image */   border-radius: 50%;	/* relative value */   -moz-border-radius: 50%;   -webkit-border-radius: 50%;   transition: linear 0.25s;}
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
            width: 40px;   height: 40px;   padding:12px;   background: #ececec;   border-radius:40px;   box-shadow: 0px 0px 1px rgba(0,0,0,0.4);   -moz-border-radius: 40px;   -webkit-border-radius: 40px;
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
            color: gainsboro;
            font-size: x-small;
            padding-left: 3px;
        }

    </style>
</head>
<body>
<div>
    <ul class="layui-nav">

        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}/employee/homePage.action" style="display: inline;padding-left: 0px" target="infoContent">首页</a>
        </li>

        <li class="layui-nav-item">
            <a href="" style="display: inline;padding-left: 0px">帮助</a>
        </li>

        <li class="layui-nav-item" style="float: right">
            <a href=""><img src="http://t.cn/RCzsdCq" class="layui-nav-img">${sessionScope.employee.account}</a>
            <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/employee/employeeHome.action">个人主页</a></dd>
                <c:if test="${sessionScope.job.authority > 1}"><dd><a href="${pageContext.request.contextPath}/employee/managePage.action" >管理</a></dd></c:if>
                <dd><a href="${pageContext.request.contextPath}/employee/logout.action" >注销</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item" style="float: right">
            <a href="">公司公告<span class="layui-badge-dot"></span></a>
        </li>
        <li class="layui-nav-item" style="float: right">
            <a href="" style="display: inline;padding-left: 0px">活动<span class="layui-badge-dot"></span></a>
        </li>
    </ul>
</div>

<div class="content">

    <div style="background: #AFB42B;height:120px;overflow: hidden;">
        <img src="../../img/152790724872243.png" style="float: right;height: 100%; padding-right:80px;" />
    </div>

    <div style="background:#CDDC39;height: 40px;">
        <a href="#" style="float: right;padding:10px 40px 0 0;color: white;">修改</a>
    </div>

    <div class="session1">
        <div class="commentAvatarDiv">
            <img src='../../img/31ca894c-2d67-4e35-854d-25b92aaff748.png' alt="头像" class="commentAvatarImage"/>
        </div>
    </div>

</div>

<div class="taskContent">

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
            </div>
        </div>
    </div>

    <div style="flex-grow: 1" class="item-panel">
        <!--最近操作列表-->
        <div style="margin: 20px;">
            <h3>信息</h3>
            <hr class="layui-bg-gray">
            <div style="margin: 10px;">
                <div class="item">
                    <div class="item-logo"><i class="layui-icon">&#xe629;</i></div>

                    <div class="item-content">
                        <div class="item-content-title">
                            分门别类
                        </div>
                        <div class="item-content-main">
                            这里是一大堆的内容，你喜不喜欢
                        </div>
                        <div class="item-content-operate">
                            <div><i class="layui-icon">&#xe63a;</i></div>
                            <div><i class="layui-icon">&#xe609;</i></div>
                            <div><i class="layui-icon">&#xe6c6;</i></div>
                            <div><i class="layui-icon">&#xe62c;</i></div>
                        </div>
                    </div>
                </div>
            </div>
            <hr class="layui-bg-gray">
            <div>end</div>
        </div>

    </div>

    <div style="width: 20%;margin-top: 0;">

        <div class="model-item">
            <div class="model-item-title">标题</div>

            <div class="model-item-subtitle">
                副标题
            </div>
            <div class="model-item-content">
                内容
            </div>

            <hr class="layui-bg-gray">

            <div class="model-item-subtitle">
                副标题
            </div>
            <div class="model-item-content">
                内容
            </div>
        </div>
        <script>
            var app = new Vue({
                el: '#comp_announce',
                data: {
                    items:""
                },
                beforeCreate:function(){
                    var url="${pageContext.request.contextPath}/";
                    var _self=this;
                    $.get(url,function(data){
                        _self.items=data;
                    })
                }
            })
        </script>
        <div id="#comp_announce"  v-for="item in items">
            <div class="model-item">
                <div class="model-item-title">{{item.title}}</div>
                <div class="model-item-subtitle">
                    {{item.createtime}}
                </div>
                <div class="model-item-content">
                    {{item.descript}}
                </div>
            </div>
        </div>
    </div>

</div>

</body>

<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;

        //…
    });
</script>

</html>
