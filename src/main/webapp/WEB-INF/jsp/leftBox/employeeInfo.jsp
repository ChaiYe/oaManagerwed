<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/16/016
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
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
    <script type="text/javascript">
        $(function (){
            $(".parent").next().hide();
            $(".parent").click(function(){
                $(this).toggleClass("selected");
                $(this).siblings().not(".parent").not(":first-child").hide();
                $(this).next().show();
            });
        })
    </script>
</head>
<body>
<div>


    <div class="info">
        <div class="crumbs-nav">
                <span class="layui-breadcrumb">
                    <a href="">人事管理</a>
                    <span>/</span>
                    <a><cite>员工管理</cite></a>
                </span>
        </div>
        <div style="border: #009688 1px solid">
            <div class="panel-header">
                <span class="panel-header-left">员工管理</span>
                <span class="panel-header-right">
                        <a href="${pageContext.request.contextPath}/management/addEmplyee.action" class="layui-btn layui-btn-sm panel-header-right-btn">添加</a>
                    </span>
            </div>
            <div class="content-page">

                <div style="margin-top: 24px">
                    <div style="display: inline;">
                        <div style="display: inline;">每页显示条数：<input type="text"/></div>
                    </div>
                    <div style="display: inline;float: right">
                        <div style="display: inline;">筛选：<input type="text"/></div>
                    </div>
                </div>


                <table class="layui-table">

                    <tr><th>账号</th><th>姓名</th><th>密码</th><th>状态</th><th>性别</th><th>职位</th><th>部门</th><th>操作</th></tr>
                    <c:forEach items="${employeeAndInfos}" var="item">
                        <tr class="parent" id="row_01">
                            <td>${item.account}</td><td>${item.name}</td><td>${item.password}</td><td>${item.state}</td><td>${item.sex}</td><td>${item.jobName}</td><td>${item.deptName}</td>
                            <td>
                                <a href="${pageContext.request.contextPath }/employee/delete.action?id=${item.id}" class="layui-btn layui-btn-sm">删除</a>
                                <a href="#" class="layui-btn layui-btn-sm change">修改</a>

                            </td>
                        </tr>
                        <tr class="child_row_01"><td colspan="8" align="center">


                            <div style="display: inline">创建时间：${item.createtime}</div>
                                <div style="display: inline">电话：${item.phone}</div>
                                <div style="display: inline">邮件：${item.email}</div>
                                <div style="display: inline">地址：${item.address}</div>
                                <div style="display: inline">年龄：${item.age}</div>


                        </td>
                        </tr>
                    </c:forEach>
                    <%--<c:forEach items="${employeeAndInfos}" var="item">
                        <tr>
                            <td>${item.account}</td>
                            <td>${item.password}</td>

                            <td>${item.state}</td>

                            <td>${item.jobName}</td>
                        </tr>
                    </c:forEach>--%>



                </table>
                <div class="pageBox1">
                    <ul class="pageDiv clearfix">

                    </ul>
                    <div class="notContent hide">
                        无数据
                    </div>
                    <div class="page">



                        <ul class="pageMenu clearfix">
                            <li class="firstPage">首页</li>
                            <li class="prevPage"> < </li>
                            <div class="pageObj clearfix">

                            </div>
                            <li class="nextPage"> > </li>
                            <li class="lastPage">尾页</li>
                            <li class="last" style="font-size: 14px;">
                                共<span class="totalPage"></span>页，跳转至 <input type="number" class="keuInput" value="1">
                                <button type="button" class="btnSure">确定</button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>





</div>
</body>
</html>