<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>活动详情</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <script src="../../js/vue.js"></script>
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

            var success=0;
            var fail=0;
            //拖拽上传
            upload.render({
                elem: '#upload',    //指向容器选择器，如：elem: '#id'。也可以是DOM对象
                url: '${pageContext.request.contextPath}/activity/uploadFile/${activity.uuid}.action',    //服务端上传接口
                // method: 'post',     //上传接口的 HTTP 类型,默认post
                multiple: true,
                size:10240,         //上传的单个图片大小,单位为kb
                number:10,          //最多上传的数量
                accept: 'file',        //指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
                //acceptMime:       //规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表。
                                    // 如：acceptMime: 'image/*'（只显示图片文件）acceptMime: 'image/jpg, image/png'（只显示 jpg 和 png 文件）
                field:'file',       //MultipartFile file 对应，layui默认就是file,要改动则相应改动
                auto:true,          //自动上传
                // bindAction: '#test9',   //指向一个按钮触发上传，一般配合 auto: false 来使用。值为选择器或DOM对象
                before: function(obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(index, file, result) {

                    });
                },
                done: function(res, index, upload) {
                    //每个图片上传结束的回调，成功的话，就把新图片的名字保存起来，作为数据提交
                    if(res.code === 1){
                        layer.msg(res.resultMsg);
                        success++;
                    }
                    else if(res.code === 0){
                        layer.msg(res.resultMsg);
                        fail++;
                    }
                },
                allDone:function(obj){
                    layer.msg("总共要上传文件总数为："+(fail+success)+"\n"
                        +"其中上传成功文件数为："+success+"\n"
                        +"其中上传失败文件数为："+fail
                    );
                    files.getFiles();
                }
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
<div class="layui-row layui-col-space30">

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>活动详细情况</legend>
    </fieldset>

    <div class="layui-row  layui-col-space20">
        <div class="layui-col-xs7 layui-col-lg-offset1">
            <!--活动模块-->
            <div style="flex-grow: 1; padding:12px;" class="item-panel">
                <div style="margin: 20px;">
                    <h3>信息</h3>
                    <hr class="layui-bg-gray">
                    <div style="margin: 10px;">
                        <%--主题信息--%>
                        <div class="item">
                            <div class="item-logo"><i class="layui-icon">&#xe629;</i></div>
                            <div class="item-content">
                                <div class="item-content-title">
                                    ${activity.name}
                                </div>
                                <div class="item-content-main">
                                    <%--${fn:escapeXml(后台传值)} 解决html解析问题--%>
                                    <%--${fn:escapeXml(activity.descript)}--%>
                                    <c:out value="${activity.descript}" escapeXml="false"/>
                                </div>
                                <%--//小图标--%>
                                <div class="item-content-operate">
                                    <div><i class="layui-icon" style="color: green">&#xe637;</i>2018-8-2</div>
                                    <div><i class="layui-icon" style="color: red">&#xe637;</i>2018-8-6</div>
                                    <c:choose>
                                        <c:when test="${activity.state == 0}">
                                            <div><i class="layui-icon" style="color: red">&#xe63f;</i>待审批</div>
                                        </c:when>
                                        <c:when test="${activity.state == 1}">
                                            <div><i class="layui-icon" style="color: darkgreen">&#xe643;</i>未开始</div>
                                        </c:when>
                                        <c:when test="${activity.state == 2}">
                                            <div><i class="layui-icon" style="color: darkgreen">&#x1005;</i>正在进行</div>
                                        </c:when>
                                        <c:otherwise>
                                            <div><i class="layui-icon" style="color: red">&#x1007;</i>已结束</div>
                                        </c:otherwise>
                                    </c:choose>
                                    <div><i class="layui-icon">&#xe613;</i>${activity.depart.name}</div>
                                </div>
                            </div>
                        </div>
                        <%--里程碑--%>
                        <div>
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                                <legend>活动时间线：里程碑</legend>
                            </fieldset>
                            <div id="markers">
                                <ul class="layui-timeline">
                                    <li class="layui-timeline-item" v-for="i in items">
                                        <i class="layui-icon layui-timeline-axis"></i>
                                        <div class="layui-timeline-content layui-text">
                                            <div class="layui-timeline-title">{{i.createtime|timeStampFormat}}，{{i.descript}}</div>
                                        </div>
                                    </li>
                                </ul>
                                <button id="test" data-method="addMarker" v-if="show" class="layui-btn">
                                    <i class="layui-icon">&#xe608;</i> 添加
                                </button>
                            </div>
                            <script>
                                var markers = new Vue({
                                    el: '#markers',
                                    data: {
                                        items : [],
                                        show : false
                                    },
                                    created () {
                                        this.getMarkers();
                                        this.showBtn();
                                    },
                                    methods:{
                                        getMarkers: function () {
                                            $.ajax({
                                                url:"${pageContext.request.contextPath}/activity/getActMarkersAjax/${activity.uuid}.action",
                                                type:"post",
                                                dataType:"json",
                                                success:function(result){
                                                    markers.items = result;
                                                },
                                                error:function(){
                                                    alert("请求失败");
                                                }
                                            });
                                        },
                                        showBtn: function () {
                                            $.ajax({
                                                url:"${pageContext.request.contextPath}/activity/showAddMarker/${activity.employee}/${activity.dept}.action",
                                                type:"post",
                                                dataType:"json",
                                                success:function(result){
                                                    markers.show = result;
                                                },
                                                error:function(){
                                                    alert("请求失败");
                                                }
                                            });
                                        }
                                    },
                                    filters:{
                                        timeStampFormat:function(val){
                                            var d = new Date(val);
                                            var year = d.getFullYear();
                                            var month = d.getMonth() + 1;
                                            var day = d.getDate() <10 ? '0' + d.getDate() : '' + d.getDate();
                                            var hour = d.getHours() <10 ? '0' + d.getHours() : '' + d.getHours();
                                            var minutes = d.getMinutes() <10 ? '0' + d.getMinutes() : '' + d.getMinutes();
                                            var seconds = d.getSeconds() <10 ? '0' + d.getSeconds() : '' + d.getSeconds();
                                            return  year+ '年' + month + '月' + day + '日' + ' '
                                                + hour + ':' + minutes + ':' + seconds;
                                        }
                                    }
                                });
                                layui.use('layer', function(){ //独立版的layer无需执行这一句
                                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                                    //触发事件
                                    var active = {
                                        addMarker: function(){
                                            layer.prompt({title: '随便写点啥，并确认', formType: 2}, function(text, index){
                                                if(text.length < 2){
                                                    layer.alert("描述信息不应少于两个字符");
                                                }
                                                else if(text.length > 50)
                                                    layer.msg("描述信息不应超过50个字符");
                                                else{
                                                    layer.close(index);
                                                    var param = { actid : '${activity.uuid}' , marker : text};
                                                    $.ajax({
                                                        url:"${pageContext.request.contextPath}/activity/addMarkerAjax.action",
                                                        type : "post",
                                                        dataType: 'json',
                                                        contentType : "application/json",
                                                        data : JSON.stringify(param),
                                                        async:false,
                                                        error:function(data){
                                                            alert("Error");
                                                        },
                                                        success:function(data){
                                                            if(data){
                                                                layer.msg('添加成功'+text);
                                                                markers.getMarkers();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    };
                                    $('#test').on('click', function(){
                                        var othis = $(this), method = othis.data('method');
                                        active[method] ? active[method].call(this, othis) : '';
                                    });
                                });
                            </script>
                        </div>
                    </div>
                </div>
                <%--活动投票--%>
                <%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                    <legend>活动投票</legend>
                </fieldset>
                <div class="layui-collapse">
                    <c:forEach items="${activity.markers}" var="i">
                        <div id="vote${i.uuid}">
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">为什么JS社区大量采用未发布或者未广泛支持的语言特性？</h2>
                                <div class="layui-colla-content">
                                    <form class="layui-form" action="" >

                                        <input type="radio" name="sex" value="男" title="男" checked="">
                                        <input type="radio" name="sex" value="女" title="女">

                                        <div class="layui-form-item">
                                            <button class="layui-btn" lay-submit="" lay-filter="demo2">跳转式提交</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>--%>
            </div>
        </div>
        <%--文件模块--%>
        <div class="layui-col-xs3">
            <div style="flex-grow: 1" class="item-panel">
                <div style="margin: 20px;">
                    <h3 style="padding-top: 10px">文件列表</h3>

                    <hr class="layui-bg-gray">

                    <div style="margin: 10px;">
                        <div class="item">
                            <div class="item-content" style="width:100%">
                                <div class="layui-upload-drag" id="upload" style="width:100%">
                                    <i class="layui-icon"></i>
                                    <p>点击上传，或将文件拖拽到此处</p>
                                    <p>多文件上传，最多不超过10个<br>
                                        每个文件最大为10M</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--文件展示列表--%>
                    <div id="files">
                        <div v-for="i in items">
                            <hr class="layui-bg-green">
                            <div class="item">
                                <a :href="'${pageContext.request.contextPath}/activity/download/'+ i.uuid +'.action'"><i class="layui-icon" style="font-size: 50px;">&#xe655;</i></a>
                                <div class="item-content">
                                    <span class="model-item-subtitle">
                                        {{i.name}}<br>
                                        上传时间：{{i.createtime|dateFormat}}<br>
                                        大小：{{i.size|sizeChange}}<br>
                                        上传者: {{i.info.name}}
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        var files = new Vue({
                            el: '#files',
                            data: {
                                items : []
                            },
                            created () {
                                this.getFiles();
                            },
                            methods:{
                                getFiles: function () {
                                    $.ajax({
                                        url:"${pageContext.request.contextPath}/activity/getActFiles/${activity.uuid}.action",
                                        type:"post",
                                        dataType:"json",
                                        success:function(result){
                                            files.items = result;
                                        },
                                        error:function(){
                                            alert("请求失败");
                                        }
                                    });
                                },
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
                                ,sizeChange:function (val) {
                                    if(val > 1048576)
                                        return (val/1048576).toFixed(2) + 'MB';
                                    else if(val > 1024)
                                        return (val/1024).toFixed(2) + 'KB';
                                    else return val + 'B';
                                }
                            }
                        })
                    </script>
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
