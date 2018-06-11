<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>所有公告</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <script src="../../js/vue.js"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css" />
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

<%--<div class="header">--%>
    <%--<div class="content btn">--%>
        <%--<div style="float: right;">--%>
            <%--<a class="layui-btn layui-btn-normal layui-btn-sm"><i class="layui-icon">&#xe642;</i> 发布公告</a>--%>
        <%--</div>--%>
    <%--</div>--%>

<%--</div>--%>

<div class="content" id="all-announce" >

    <%--<div class="pin info">--%>
        <%--<span style="color: orange;">[置顶]</span>--%>
        <%--XX须知--%>
        <%--<a style="float: right;color: skyblue; font-size:12px ;line-height:24px ;text-decoration: none;">现在去填写</a>--%>
    <%--</div>--%>


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