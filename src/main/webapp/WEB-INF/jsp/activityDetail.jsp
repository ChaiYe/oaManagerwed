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
        layui.use(['form', 'layedit', 'laydate', 'element', 'upload'], function() {
            <%--注意：导航 依赖 element 模块，否则无法进行功能性操作--%>
            var $ = layui.$
                ,form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate
                ,element = layui.element
                ,upload = layui.upload;

            //日期
            laydate.render({
                elem: '#begintime'
                ,calendar: true         //开启公历节日
                //限定可选日期
                // ,min:
                // ,max: '2080-10-14'
                //前后若干天可选，这里以7天为例
                ,min: 0
                ,max: 30
                //自定义颜色
                ,theme: 'molv'
                //底部按钮,内置可识别的值有：clear、now、confirm
                ,btns: ['now', 'clear']
                //初始赋值
                ,value: new Date()
                ,isInitValue: true
                //选中后的回调
                ,done: function(value, date){
                    // layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
                    $("#endtimeDiv").html('<input type="text" class="layui-input" id="endtime" name="endtime" placeholder="HH:mm:ss" lay-verify="required">');
                    $("#endtimetip").html('请选择活动结束时间');

                    var end = laydate.render({
                        elem: '#endtime'
                        ,calendar: true         //开启公历节日
                        //自定义重要日
                        ,mark: {
                            // '0-10-14': '生日'
                            // ,'0-12-31': '跨年' //每年的日期
                            // ,'0-0-10': '工资' //每月某天
                            // ,'0-0-15': '月中'
                            // ,'2017-8-15': '' //如果为空字符，则默认显示数字+徽章
                            // ,'2099-10-14': '呵呵'
                        }

                        //限定可选日期
                        ,min: value
                        ,max: '2080-10-14'
                        //前后若干天可选，这里以7天为例
                        // ,min: -7
                        // ,max: 7

                        //自定义颜色
                        ,theme: 'molv'
                        // ,theme: 'grid'
                        // ,theme: '#393D49'

                        //不出现底部栏
                        // ,showBottom: false

                        //底部按钮,内置可识别的值有：clear、now、confirm
                        ,btns: ['now', 'clear']

                        //初始赋值
                        ,value: value
                        ,isInitValue: true

                        //选中后的回调
                        // ,done: function(value, date){
                        //     layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
                        // }
                        //日期切换的回调
                        // ,change: function(value, date){
                        //     layer.msg('你选择的日期是：' + value + '<br><br>获得的对象是' + JSON.stringify(date));
                        // }
                    });
                }
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

            //拖拽上传
            upload.render({
                elem: '#upload'
                ,url: '/upload/'
                ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                    layer.load(); //上传loading
                }
                ,done: function(res, index, upload){
                    layer.closeAll('loading'); //关闭loading
                }
                ,error: function(index, upload){
                    layer.closeAll('loading'); //关闭loading
                }
                ,accept: 'file' //允许上传的文件类型
                ,size: 50 //设置文件最大可允许上传的大小，单位 KB
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
                        return '内容太短';
                    }
                    if (content.length > 500) {
                        return '内容不能大于500个字符'
                    }
                }
            });
            //监听指定开关
            form.on('switch(vote)', function(data){
                $("#voteOption").toggle();
            });
            //监听 lay-filter="add" 按钮
            form.on('submit(add)', function(data){
                //表单所有有name属性的input框的值，存放在data.field中，格式为 name : value
                // layer.alert(JSON.stringify(data.field), {
                //     title: '最终的提交信息'
                // });
                <%--$.ajax({--%>
                    <%--type : "post",--%>
                    <%--dataType: 'json',--%>
                    <%--url : "${pageContext.request.contextPath}/activity/addActivityAjax.action",--%>
                    <%--contentType : "application/json",--%>
                    <%--data : JSON.stringify(data.field),--%>
                    <%--async:false,--%>
                    <%--error:function(data){--%>
                        <%--alert("Error");--%>
                    <%--},--%>
                    <%--success:function(data){--%>
                        <%--if(data.isSuccess){--%>
                            <%--layer.alert(data.message, {title: '提示'});--%>
                            <%--location.href = "${pageContext.request.contextPath}/employee/employeeHome.action";--%>
                        <%--}--%>
                        <%--else{--%>
                            <%--// layer.alert(data.message, {title: '提示'});--%>
                        <%--}--%>
                    <%--}--%>
                <%--});--%>
                return false;
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
            height:18px;
            overflow: hidden;
            transition: height 0.5s;
        }
        .activity-vote:hover {
            /*height:auto;*/
            height: 400px;
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
            <a href="${pageContext.request.contextPath}/announce/addAnnouncePage.action">公告<span class="layui-badge-dot"></span></a>
        </li>
        <li class="layui-nav-item" style="float: right">
            <a href="" style="display: inline;padding-left: 0px">活动<span class="layui-badge-dot"></span></a>
        </li>
    </ul>
</div>

<!--页面主体-->
<div class="layui-row layui-col-space30">

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>活动详细情况</legend>
    </fieldset>

    <div class="layui-row  layui-col-space20">
        <div class="layui-col-xs7 layui-col-lg-offset1">
            <!--活动模块-->
            <div style="flex-grow: 1" class="item-panel">
                <div style="margin: 20px;">
                    <h3>信息</h3>
                    <hr class="layui-bg-gray">
                    <div style="margin: 10px;">
                        <div class="item">
                            <div class="item-logo"><i class="layui-icon">&#xe629;</i></div>

                            <div class="item-content">
                                <div class="item-content-title">
                                    活动标题
                                </div>
                                <div class="item-content-main">
                                    <%--${fn:escapeXml(后台传值)} 解决html解析问题--%>
                                    活动描述，长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长
                                </div>
                                <%--//小图标--%>
                                <div class="item-content-operate">
                                    <div><i class="layui-icon" style="color: green">&#xe637;</i>2018-8-2</div>
                                    <div><i class="layui-icon" style="color: red">&#xe637;</i>2018-8-6</div>
                                    <div><i class="layui-icon" style="color: darkgreen">&#x1005;</i>正在进行</div>
                                    <div><i class="layui-icon">&#xe613;</i>部门</div>
                                    <div><i class="layui-icon">&#xe612;</i>发布者</div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                                <legend>活动时间线：大事记</legend>
                            </fieldset>
                            <ul class="layui-timeline">
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis"></i>
                                    <div class="layui-timeline-content layui-text">
                                        <div class="layui-timeline-title">2018年，layui 5.0 发布。并发展成为中国最受欢迎的前端UI框架（期望）</div>
                                    </div>
                                </li>
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis"></i>
                                    <div class="layui-timeline-content layui-text">
                                        <div class="layui-timeline-title">2017年，layui 里程碑版本 2.0 发布</div>
                                    </div>
                                </li>
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis"></i>
                                    <div class="layui-timeline-content layui-text">
                                        <div class="layui-timeline-title">2016年，layui 首个版本发布</div>
                                    </div>
                                </li>
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis"></i>
                                    <div class="layui-timeline-content layui-text">
                                        <div class="layui-timeline-title">2015年，layui 孵化</div>
                                    </div>
                                </li>
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>
                                    <div class="layui-timeline-content layui-text">
                                        <div class="layui-timeline-title">更久前，轮子时代。维护几个独立组件：layer等</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <hr class="layui-bg-gray">
                    <div class="activity-vote">
                        <div style="text-align:center;">查看活动投票</div>
                        <div>
                            <fieldset class="layui-elem-field">
                                <legend>投票 截止时间：2018-11-11 11:11:11</legend>
                                <div><i class="layui-icon" style="font-size: 30px; float: right; color: forestgreen ">&#xe61f;</i></div>
                                <div style="padding:20px">
                                    <form action="" method="post" class="layui-form">
                                        <input type="radio" name="vote" value="" title="A.选项Aasbd"/>
                                        <input type="radio" name="vote" value="" title="B.选项Bdasds"/>
                                        <input type="radio" name="vote" value="" title="C.选项Casdas"/>
                                        <input type="radio" name="vote" value="" title="D.选项Ddsadsa"/>
                                        <button class="layui-btn layui-btn-primary" lay-submit="" style="width: 50%;" lay-filter="add">投票</button>
                                    </form>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-xs3">
            <div style="flex-grow: 1" class="item-panel">
                <div style="margin: 20px;">
                    <h3>文件列表</h3>
                    <hr class="layui-bg-gray">
                    <div style="margin: 10px;">
                        <div class="item">
                            <div class="item-content">
                                <div class="layui-upload-drag" id="upload">
                                    <i class="layui-icon"></i>
                                    <p>点击上传，或将文件拖拽到此处</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr class="layui-bg-green">

                    <div class="item">
                        <div class="item-content">
                            <div class="item-content">
                                <div class="item-content-title">
                                    文件名长长长长长长长长长长长长长长长长长
                                </div>
                                <div class="model-item-subtitle">
                                    <%--{{item.createtime|dateFormat}}--%>
                                    时间：2018-8-9
                                    <div style="float: right">
                                        <span>上传者：</span>
                                        <span>部门,</span>
                                        <span>张三</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                       <i class="layui-icon" style="font-size: 50px; color: #1E9FFF;">&#xe601;</i>
                    </div>

                    <hr class="layui-bg-green">

                    <div class="item">
                        <div class="item-content">
                            <div class="item-content">
                                <div class="item-content-title">
                                    文件名长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长
                                </div>
                                <div class="model-item-subtitle">
                                    <%--{{item.createtime|dateFormat}}--%>
                                    时间：2018-8-9<br>
                                    大小：500M
                                    <div>
                                        <span>公司</span>
                                        <span>张三</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <i class="layui-icon" style="font-size: 50px; color: #1E9FFF;">&#xe601;</i>
                    </div>
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
