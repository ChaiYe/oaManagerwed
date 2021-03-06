<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/20/020
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%--未修改 --%>
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
                        <a href="${pageContext.request.contextPath}/actfile/jumpToAdd.action" class="layui-btn layui-btn-sm panel-header-right-btn">添加</a>

                    </span>
            </div>
            <div class="content-page">

                <form action="${pageContext.request.contextPath}/actfile/query.action">
                    <div style="margin-top: 24px">
                        <div style="display: inline;">
                            <div style="display: inline;">
                               <%-- 每页显示条数：<input type="text"/>--%>
                                <a href="javascript:void(0);" onclick="deletes()">批量删除</a>
                            </div>
                        </div>
                        <div style="display: inline;float: right">
                            <%--<div style="display: inline;">
                                筛选：<input type="text" name="queryAcount1"/>
                                            ~
                                <input type="text" name="queryAcount2">

                                <input type="submit" value="提交">
                            </div>--%>
                        </div>

                    </div>
                </form>

                <table class="layui-table">

                    <thead>
                    <tr>
                        <th></th>
                        <th>存储地址</th>
                        <th>文件名称</th>
                        <th>文件格式</th>
                        <th>文件大小</th>
                        <th>提交时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${!empty actfilePageBean.datas}">
                        <c:forEach var="actfile" items="${actfilePageBean.datas}">
                            <tr class="oneMsg">
                                <td><input type="checkbox" value="${actfile.uuid}" name="del"></td>
                                <input type="hidden" value="${actfile.uuid}" readonly="readonly" class="inputs layui-input"/>
                                <td><input type="text" value="${actfile.path}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${actfile.name}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${actfile.format}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${actfile.size}" readonly="readonly" class="inputs layui-input"/></td>
                                <td><input type="text" value="${actfile.createtime}" readonly="readonly" class="inputs layui-input"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/actfile/delete.action?uuid=${actfile.uuid}" class="layui-btn layui-btn-sm">删除</a>

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
                            <li class="prevPage"> < </li>
                            <div class="pageObj clearfix">
                                <c:if test="${actfilePageBean.currentPage > 2}"><li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${actfilePageBean.currentPage-2}">${actfilePageBean.currentPage-2}</a></li></c:if>
                                <c:if test="${actfilePageBean.currentPage > 1}"><li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${actfilePageBean.currentPage-1}">${actfilePageBean.currentPage-1}</a></li></c:if>
                                <li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${actfilePageBean.currentPage}">${actfilePageBean.currentPage}</a></li>
                                <c:if test="${actfilePageBean.totalPage >= actfilePageBean.currentPage + 1}"><li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${actfilePageBean.currentPage+1}">${actfilePageBean.currentPage+1}</a></li></c:if>
                                <c:if test="${actfilePageBean.totalPage >= actfilePageBean.currentPage + 2}"><li class="thispage"><a href="${pageContext.request.contextPath}/dept/getDeptByPage.action?currentPage=${actfilePageBean.currentPage+2}">${actfilePageBean.currentPage+2}</a></li></c:if>
                            </div>
                            <li class="nextPage"> > </li>
                            <li class="lastPage">尾页</li>
                           <%-- <li class="last" style="font-size: 14px;">
                                共<span class="totalPage"></span>页，跳转至 <input type="number" class="keuInput" value="1">
                                <button type="button" class="btnSure">确定</button>
                            </li>--%>
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
                    url: "${pageContext.request.contextPath}/actfile/deletes.action",
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
