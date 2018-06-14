<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>申请活动</title>
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

            //多文件列表示例
            var demoListView = $('#demoList')
                ,uploadListIns = upload.render({
                elem: '#testList'
                ,url: '/upload/'
                ,accept: 'file'
                ,multiple: true
                ,auto: false
                ,bindAction: '#testListAction'
                ,choose: function(obj){
                    var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                    //读取本地文件
                    obj.preview(function(index, file, result){
                        var tr = $(['<tr id="upload-'+ index +'">'
                            ,'<td>'+ file.name +'</td>'
                            ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                            ,'<td>等待上传</td>'
                            ,'<td>'
                            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                            ,'</td>'
                            ,'</tr>'].join(''));

                        //单个重传
                        tr.find('.demo-reload').on('click', function(){
                            obj.upload(index, file);
                        });

                        //删除
                        tr.find('.demo-delete').on('click', function(){
                            delete files[index]; //删除对应的文件
                            tr.remove();
                            uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        });

                        demoListView.append(tr);
                    });
                }
                ,done: function(res, index, upload){
                    if(res.code == 0){ //上传成功
                        var tr = demoListView.find('tr#upload-'+ index)
                            ,tds = tr.children();
                        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                        tds.eq(3).html(''); //清空操作
                        return delete this.files[index]; //删除文件队列已经上传成功的文件
                    }
                    this.error(index, upload);
                }
                ,error: function(index, upload){
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                    tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
                }
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
                $.ajax({
                    type : "post",
                    dataType: 'json',
                    url : "${pageContext.request.contextPath}/activity/addActivityAjax.action",
                    contentType : "application/json",
                    data : JSON.stringify(data.field),
                    async:false,
                    error:function(data){
                        alert("Error");
                    },
                    success:function(data){
                        if(data.isSuccess){
                            layer.alert(data.message, {title: '提示'});
                        }
                        else{
                            // layer.alert(data.message, {title: '提示'});
                        }
                    }
                });
                return false;
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
        <li class="layui-nav-item" style="float: right">
            <a href="${pageContext.request.contextPath}/activity/applyActivityPage.action" style="display: inline;padding-left: 0px">申请活动<span class="layui-badge-dot"></span></a>
        </li>
    </ul>
</div>

<!--页面主体-->
<div class="layui-row">
    <div class="layui-col-md8 layui-col-md-offset2">

        <div class="description">
            <div class="title">创建活动</div>
            <div class="subtitle">请填写这些信息，以便于其他员工查看</div>
        </div>

        <div class="info-content">
            <div class="head-portrait" >
                <img class="img" src="../../img/14ab8759-d897-445d-8156-ea9fe1ca20cd.jpg" />
            </div>
            <!--表单-->
            <div class="edit-panel">
                <div>
                    <form action="" method="post" class="layui-form">

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
                                    <c:forEach var="item" items="${depts}">
                                        <option value="${item.dept}">${item.depart.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">开始时间</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="begintime" class="layui-input" id="begintime" placeholder="yyyy-MM-dd" lay-verify="required">
                                </div>
                                <div class="layui-form-mid layui-word-aux">
                                    请选择活动开始的时间
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">结束时间</label>
                                <div class="layui-input-inline" id="endtimeDiv" >

                                </div>
                                <div class="layui-form-mid layui-word-aux" id="endtimetip">
                                    请先选择活动开始时间
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">内容</label>
                            <div class="layui-input-block">
                                <textarea id="editor" name="descript" lay-verify="descript"></textarea>
                            </div>
                        </div>

                        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                            <legend>附件</legend>
                        </fieldset>

                        <div class="layui-upload">
                            <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
                            <div class="layui-upload-list">
                                <table class="layui-table">
                                    <thead>
                                    <tr><th>文件名</th>
                                        <th>大小</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr></thead>
                                    <tbody id="demoList"></tbody>
                                </table>
                            </div>
                            <button type="button" class="layui-btn" id="testListAction" style="display: none">开始上传</button>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label bug">&nbsp;</label>
                            <div class="layui-input-block" id="submit-btn">
                                <button class="layui-btn layui-btn-primary" lay-submit="" style="width: 100%;" lay-filter="add">发布</button>
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
