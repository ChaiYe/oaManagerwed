<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <script src="../../js/axios.min.js" type="text/javascript" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="../../layui/css/layui.css"/>
</head>
<body>
<div style="width: 800px;margin: 80px auto;background: #eee5; border-radius: 5px;box-shadow: 2px 2px 2px rgba(0,0,0,0.4);padding:32px">

    <c:if test="${hadVoted==true}">
        <div>亲，您已经投过票咯</div>
    </c:if>



    <c:if test="${vote!=null}">
        <c:choose>
            <c:when test="${vote.endtime<date}">
                <div>亲，投票已过期</div>
            </c:when>
            <c:otherwise>
                 <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/actvote/insertSelect.action">

                <input type="hidden" name="vote" value="${vote.uuid}">
                <div class="layui-form-item">
                    <div>
                            ${vote.descript}
                    </div>
                    <div>
                        <ul>
                            <li><input type="radio" name="voted" value="${vote.optionA}" title="${vote.optionA}"></li>
                            <li><input type="radio" name="voted" value="${vote.optionB}" title="${vote.optionB}"></li>
                            <li><input type="radio" name="voted" value="${vote.optionC}" title="${vote.optionC}"></li>
                            <li><input type="radio" name="voted" value="${vote.optionD}" title="${vote.optionD}"></li>
                        </ul>

                    </div>
                </div>


                <button type="submit" class="layui-btn">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>


            </form>
            </c:otherwise>
        </c:choose>
    </c:if>

    <script>
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate;
        });

    </script>
</div>

</body>
</html>
