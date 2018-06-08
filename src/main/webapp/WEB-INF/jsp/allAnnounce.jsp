<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%--
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
    &lt;%&ndash;弹出动画&ndash;%&gt;
    <style>
        /*activity-vote*/
        .activity-vote{
            height:18px;
            overflow: hidden;
            transition: height 0.5s;
        }
        .activity-vote:hover {
            /*height:auto;*/
            height: 200px;
        }
    </style>
    &lt;%&ndash;文件上传框样式&ndash;%&gt;
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
    &lt;%&ndash;右下角按钮样式&ndash;%&gt;
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

</body>--%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="../../js/vue.js"></script>
    <link rel="stylesheet" type="text/css" href="../../layui/css/layui.css"/>
    <style>
        body{
            background: #ebf2fa;
        }
        .header{
            background: white;
            height: 45px;
        }
        .content{
            width: 50%;

            margin: 0 auto;
        }
        .info{
            margin:12px 0;
            padding:12px 15px;
            background: #ffffff;
            box-shadow: 0 0 5px rgba(0,0,0,0.2);
            border-radius: 2px;
        }
        .title{
            color:#3d3d3d
        }
        .info-content{
            padding: 12px;
            color:#838383;
            font-size: 12px;
            line-height: 24px;
        }
        .edit div {
            display: inline;
            font-size:12px ;
        }
        .edit a{
            color: #838383;
            text-decoration: none;
        }
        .author{
            color: #838383;
            float: left;

        }
        .btn{

            padding:8px;
            overflow: hidden;
        }

    </style>
</head>
<body @scroll="none()">

<div class="header">
    <div class="content btn">
        <div style="float: right;">
            <a class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon">&#xe642;</i> 发布公告</a>
        </div>
    </div>

</div>

<div class="content" id="all-announce" >

    <div class="pin info">
        <span style="color: orange;">[置顶]</span>
        XX须知
        <a style="float: right;color: skyblue; font-size:12px ;line-height:24px ;text-decoration: none;">现在去填写</a>
    </div>


    <div class="content-item" v-for="announceDept in announceDepts">
        <div class="info">
            <div class="title">
                {{announceDept.title}}
                <div style="float: right;font-size:smaller">部门：{{announceDept.depart.name}}</div>
            </div>

            <hr />

            <div class="info-content" v-html="announceDept.descript">
                <%--在座各位都是辣鸡<br />
                没错都是辣鸡 不服跳楼？--%>
               <%-- {{announceDept.descript}}--%>

            </div>

            <hr />
            <div class="edit">
                <div>
                   <%-- <a href="#">编辑</a>--%>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <%--<a href="#">删除</a>--%>
                </div>
                <div class="author">
                    <%--叮裆猫 发表于2015-06-25 18:58--%>
                    {{announceDept.createtime|dateFormat}}
                </div>
            </div>
        </div>
    </div>

    <div v-if="announceDepts.length!=0">
        <div style="margin: 0 auto;width: 100%;text-align:center;  ">
            <i class="layui-icon" style="height: 120px ;width: 120px ;font-size: 50px;color: #ccc" @click="loadmore">&#xe61a;</i>
        </div>
    </div>





</div>
<script src="../../js/axios.min.js"></script>
<script>

    var announceDeptVue=new Vue({
        el:"#all-announce",
        data(){
            return {
                announceDepts:[]
            }
        },
        created(){
            /*下拉刷新的关键*/
            /*window.addEventListener('scroll', this.handleScroll, true);*/

            axios.get("${pageContext.request.contextPath}/announce/allAnnounceJson.action").then(
                function (res) {
                    announceDeptVue.announceDepts=res.data;
                    console.log(res);
                }
            ).catch(
                function (err) {
                    console.log(err);
                }
            )
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
        },
        methods:{
            handleScroll(){
                //scrollTop为滚动条在Y轴上的滚动距离。
                //clientHeight为内容可视区域的高度。
                //scrollHeight为内容可视区域的高度加上溢出（滚动）的距离。
                console.log(this.$el.offsetHeight);
                if(this.$el.scrollTop+this.$el.offsetHeight>=this.$el.scrollHeight){

                    this.loadmore();
                    this.scloll=true;
                    this.list +=1;
                }else{

                    this.scloll=false;
                }
            },
            loadmore : function () {
                alert(announceDeptVue.$data.announceDepts.length);
                axios.get("${pageContext.request.contextPath}/announce/allAnnounceJson.action",{
                    params: {
                        nowSize: announceDeptVue.$data.announceDepts.length
                    }
                }).then(
                    function (res) {
                        for(let i =0;i<res.data.length;i++)
                        {
                            announceDeptVue.announceDepts.push(res.data[i]);
                        }

                        console.log(announceDeptVue.announceDepts);
                    }
                ).catch(
                    function (err) {
                        console.log(err);
                    }
                )

            },
            none(){

            }
        }
    });
</script>


</body>
</html>