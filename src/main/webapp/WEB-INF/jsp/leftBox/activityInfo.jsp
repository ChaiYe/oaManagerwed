<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/20/020
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
    <script type="text/javascript">
        $(document).ready(function () {
            $("#jumpBtn").click(function () {
                var pagenum = $("#pageNum").val();
                if(isNaN(pagenum))
                    alert("请输入数字");
                else if(pagenum < 1 || pagenum > ${page.pages})
                    alert("请输入正确页码");
                else{
                    $("#jumpa").attr("href", "${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=" + pagenum);
                    $("#jumpa").append("<span></span>");
                    $("#jumpa span").click();
                }
            })
        })
    </script>
</head>
<body>
<div>
    <div class="info">
        <div class="crumbs-nav">
                <span class="layui-breadcrumb">
                    <a href="" style="color: #00F7DE">信息中心</a>
                    <a><cite style="color: #00F7DE">公告管理</cite></a>
                </span>
        </div>
        <div style="border: #009688 1px solid">
            <div class="panel-header">
                <span class="panel-header-left">公告管理</span>
                <span class="panel-header-right">
                        <a href="${pageContext.request.contextPath}/activity/jumpToAdd.action" class="layui-btn layui-btn-sm panel-header-right-btn">添加</a>
                        <a href="${pageContext.request.contextPath}/activity/findone.action?id=6" class="layui-btn layui-btn-sm panel-header-right-btn">看图</a>
                </span>
            </div>
            <div class="content-page">
                <form action="${pageContext.request.contextPath}/activity/query.action">
                    <div style="margin-top: 24px">
                        <div style="display: inline;">
                            <div style="display: inline;">
                                每页显示条数：<input type="text"/>
                                <a href="javascript:void(0);" onclick="deletes()">批量删除</a>
                            </div>
                        </div>
                        <div style="display: inline;float: right">
                            <div style="display: inline;">
                                筛选：<input type="text" name="queryAcount1"/>
                                            ~
                                <input type="text" name="queryAcount2">

                                <input type="submit" value="提交">
                            </div>
                        </div>
                    </div>
                </form>

                <table class="layui-table">

                    <thead>
                    <tr>
                        <th></th>
                        <th>公告标题</th>
                        <th>公告详情</th>
                        <th>日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%--<c:if test="${!empty activityPageBean.datas}">--%>
                        <%--<c:forEach var="activity" items="${activityPageBean.datas}">--%>
                            <%--<tr class="oneMsg">--%>
                                <%--<td><input type="checkbox" value="${activity.uuid}" name="del"></td>--%>
                                <%--<td><input type="text" value="${activity.name}" readonly="readonly" class="inputs layui-input"/></td>--%>
                                <%--<td><input type="text" value="${activity.descript}" readonly="readonly" class="inputs layui-input"/></td>--%>
                                <%--<td><input type="text" value="${activity.begintime}" readonly="readonly" class="inputs layui-input"/></td>--%>
                                <%--<td><input type="text" value="${activity.endtime}" readonly="readonly" class="inputs layui-input"/></td>--%>
                                <%--<td><input type="text" value="${activity.state}" readonly="readonly" class="inputs layui-input"/></td>--%>
                                <%--<td>--%>
                                    <%--<a href="${pageContext.request.contextPath}/activity/delete.action?uuid=${activity.uuid}" class="layui-btn layui-btn-sm">删除</a>--%>
                                    <%--<a href="${pageContext.request.contextPath}/activity/jumpToEdit.action?uuid=${activity.uuid}" class="layui-btn layui-btn-sm change">修改</a>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                    <%--</c:if>--%>
                    <c:if test="${!empty list}">
                        <c:forEach var="activity" items="${list}">
                            <tr class="oneMsg">
                                <td><input type="checkbox" value="${activity.uuid}" name="del"></td>
                                <td><input type="text" value="${activity.name}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${activity.descript}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${activity.begintime}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${activity.endtime}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${activity.state}" readonly="readonly" class="inputs layui-input"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/activity/delete.action?uuid=${activity.uuid}" class="layui-btn layui-btn-sm">删除</a>
                                    <a href="${pageContext.request.contextPath}/activity/jumpToEdit.action?uuid=${activity.uuid}" class="layui-btn layui-btn-sm change">修改</a>
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
                            <a href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.firstPage}"><li class="firstPage">首页</li></a>
                            <c:if test="${page.hasPreviousPage}">
                                <a href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.prePage}">
                                    <li class="prevPage"> < </li>
                                </a>
                            </c:if>
                            <div class="pageObj clearfix">
                                <c:if test="${page.hasPreviousPage}">
                                    <a href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.prePage}">
                                        <li class="thispage">${page.prePage}</li>
                                    </a>
                                </c:if>
                                <a style="color:blue" href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.pageNum}">
                                    <li class="thispage">${page.pageNum}</li>
                                </a>
                                <c:if test="${page.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.nextPage}">
                                        <li class="thispage">${page.nextPage}</li>
                                    </a>
                                </c:if>
                            </div>
                            <c:if test="${page.hasNextPage}">
                                <a href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.nextPage}">
                                    <li class="nextPage"> > </li></a>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/activity/getActivityByPage.action?currentPage=${page.lastPage}">
                                <li class="lastPage">尾页</li>
                            </a>
                            <li class="last" style="font-size: 14px;">
                                共<span class="totalPage">${page.pages}</span>页，跳转至 <input type="text" class="keuInput" id="pageNum">
                                <button type="button" class="btnSure" id="jumpBtn">确定</button><a href="" id="jumpa"></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function  deletes() {

            var checkedNum = $("input[name='del']:checked").length;
            if(checkedNum == 0) {
                alert("请选择至少一项！");
                return;
            }
            if(confirm("确定要删除所选项目？")){
                var checkedList = new Array();
                $("input[name='del']:checked").each(function() {

                    checkedList.push($(this).val());

                });

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/activity/deletes.action",
                    data: {"dels":checkedList.toString()},
                    success: function(result) {
                        $("[name ='del']:checkbox").attr("checked", false);
                        window.location.reload();
                    }
                });

            }

        }

    </script>



</div>
</body>
</html>
