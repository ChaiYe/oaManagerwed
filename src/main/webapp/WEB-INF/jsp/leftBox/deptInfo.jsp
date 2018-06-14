<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/18/018
  Time: 8:26
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
    <link rel="stylesheet" href="../../../css/pageMenu.css">
    <link rel="stylesheet" href="../../../css/leftBox/frame.css">
    <script src="../../../js/jquery-1.11.1.min.js"></script>
    <script src="../../../js/vue.js"></script>
    <link rel="stylesheet" href="../../../layui/css/layui.css">
    <script src="../../../layui/layui.js"></script>
    <link rel="stylesheet" href="../../../css/homepageGrid.css">
    <script src="../../../js/homepageGrid.js"></script>
    <script src="../../../js/popup.js"></script>
    <script src="../../../js/page.js"></script>
    <link rel="stylesheet" href="../../../css/leftBox/frame.css">
    <link rel="stylesheet" href="../../../css/leftBox/tableAndAdd.css">
    <link rel="stylesheet" href="../../../css/leftBox/add.css">
    <link rel="stylesheet" href="../../../css/leftBox/table.css">
    <link rel="stylesheet" href="../../../css/pageMenu.css">
    <script src="../../../ckeditor/ckeditor.js"></script>
    <script src="../../../ckeditor/config.js"></script>
    <script src="../../../js/modify.js"></script>
</head>
<body>
<div>


    <div class="info">
        <div class="crumbs-nav">
                <span class="layui-breadcrumb">
                    <a href="">人事管理</a>
                    <a><cite>部门管理</cite></a>
                </span>
        </div>
        <div style="border: #009688 1px solid">
            <div class="panel-header">
                <span class="panel-header-left">部门管理</span>
                <span class="panel-header-right">
                        <a href="${pageContext.request.contextPath}/dept/jumpToAdd.action" class="layui-btn layui-btn-sm panel-header-right-btn">添加</a>
                    </span>
            </div>
            <div class="content-page">

                <div style="margin-top: 24px">
                    <div style="display: inline;">
                        <%--<div style="display: inline;">每页显示条数：<input type="text"/></div>--%>
                    </div>
                    <div style="display: inline;float: right">
                       <%-- <div style="display: inline;">筛选：<input type="text"/></div>--%>
                    </div>
                </div>


                <table class="layui-table">
                    <colgroup>
                        <col width="25%">
                        <col width="25%">
                        <col width="25%">
                        <col>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>部门描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${!empty deptPageBean.datas}">
                        <c:forEach var="dept" items="${deptPageBean.datas}">
                        <tr class="oneMsg">
                            <td><input type="text" value="${dept.uuid}" readonly="readonly" class="inputs layui-input"/></td>
                            <td><input type="text" value="${dept.name}" readonly="readonly" class="inputs layui-input"/></td>
                            <td><input type="text" value="${dept.descript}" readonly="readonly" class="inputs layui-input"/></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/dept/delete.action?uuid=${dept.uuid}" class="layui-btn layui-btn-sm">删除</a>
                                <a href="${pageContext.request.contextPath}/dept/edit.action?uuid=${dept.uuid}" class="layui-btn layui-btn-sm change">修改</a>

                            </td>
                        </tr>
                        </c:forEach>
                    </c:if>


                    </tbody>

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
                            <li class="prevPage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage-1}"><</a>  </li>

                                   <%-- <li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage-2}">${deptPageBean.currentPage-2}</a></li>
                                    <li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage-1}">${deptPageBean.currentPage-1}</a></li>--%>
                                    <li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage}">${deptPageBean.currentPage}</a></li>
                                    <li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage+1}">${deptPageBean.currentPage+1}</a></li>
                                    <li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage+2}">${deptPageBean.currentPage+2}</a></li>

                            <li class="nextPage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${deptPageBean.currentPage+1}">> </a></li>
                            <li class="lastPage">尾页</li>
                           <%-- <li class="last" style="font-size: 14px;">
                                共<span class="totalPage">${deptPageBean.totalPage}</span>页，跳转至 <input type="number" class="keuInput" value="1">
                                <button type="button" class="btnSure">确定</button>
                            </li>--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>



</body>
</html>
