<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/16/016
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title></title>




    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/vue.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <script src="../layui/layui.js"></script>
    <script type="text/javascript">

        layui.use('laypage', function () {
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                , count: 70 //数据总数，从服务端得到
            });
        });

        /*function change(obj){


            console.log($(obj).parents("tr").children());
            $(obj).parents("tr").find("input").removeAttr("readonly").removeClass("inputs");

        }
        */

        function tab() {

            $(".right").toggle(3000).addClass("show");
        }
    </script>
    <style>
        body {
            /*overflow: hidden;*/
        }

        .layui-nav {
            border-radius: 0px;
        }

        .left {
            margin-left: 200px;

        }

        /* .info {
             padding: 1px 30px;
             height: 800px;
         }*/

        .change {
            /*background: #007DDB;*/

        }




        /***************分页的style*****************/

        /******************按钮*******************/
        .upload{

        }


    </style>

    <link rel="stylesheet" href="../css/homepageGrid.css">
    <script src="../js/homepageGrid.js"></script>
    <script src="../js/popup.js"></script>
    <script src="../js/page.js"></script>
    <link rel="stylesheet" href="../css/leftBox/frame.css">
    <link rel="stylesheet" href="../css/leftBox/tableAndAdd.css">
    <link rel="stylesheet" href="../css/leftBox/add.css">
    <link rel="stylesheet" href="../css/leftBox/table.css">
    <link rel="stylesheet" href="../css/pageMenu.css">

    <script src="../ckeditor/ckeditor.js"></script>
    <script src="../ckeditor/config.js"></script>
    <script src="../js/modify.js"></script>

</head>
<body>
<div class="left">
    <div>
        <ul class="layui-nav">

            <li class="layui-nav-item">

                <a href="" style="display: inline;padding-left: 0px">首页</a>
            </li>

            <li class="layui-nav-item">

                <a href="" style="display: inline;padding-left: 0px">帮助</a>
            </li>

            <li class="layui-nav-item" style="float: right">
                <a href=""><img src="http://t.cn/RCzsdCq" class="layui-nav-img">${sessionScope.employee.account}</a>



                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">个人主页</a></dd>
                    <dd><a href="javascript:;">退出</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" style="float: right">
                <a href="">公司公告<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item" style="float: right">

                <a href="" style="display: inline;padding-left: 0px">活动<span class="layui-badge-dot"></span></a>
            </li>
        </ul>
    </div>

    <iframe name="infoContent" src="#" frameborder="0"></iframe>
</div>


<div class="dh mainbox right" style="display: inline;">
    <div>
        <ul class="layui-nav layui-nav-tree layui-nav-side">
            <li><!--<p style="font-size: 25px;">后台管理系统</p>-->
                <img src="../img/OA管理系统.png" alt="OA管理系统" width="200px" height="45px" style="padding: 7px;"/>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">默认展开</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">选项1</a></dd>
                    <dd><a href="javascript:;">选项2</a></dd>
                    <dd><a href="">跳转</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">信息中心</a>
                <dl class="layui-nav-child">
                    <dd><a href="${pageContext.request.contextPath}/activity/getActivityByPage.action" target="infoContent">活动管理</a></dd>
                    <dd><a href="http://localhost:8080/oaManagerwed/actfile/getActfileByPage.action" target="infoContent">活动文件</a></dd>
                    <dd><a href="http://localhost:8080/oaManagerwed/announce/getAnnounceByPage.action" target="infoContent">公告管理</a></dd>
                    <dd><a href="http://localhost:8080/oaManagerwed/actoption/getActoptionByPage.action" target="infoContent">投票管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">人事管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="http://localhost:8080/oaManagerwed/dept/getDeptByPage.action" target="infoContent">部门管理</a></dd>
                    <dd><a href="http://localhost:8080/oaManagerwed/job/getJobByPage.action" target="infoContent">职位管理</a></dd>
                    <dd><a href="http://localhost:8080/oaManagerwed/employee/list.action" target="infoContent">员工管理</a></dd>
                    <dd><a href="http://localhost:8080/oaManagerwed/plan/getPlanByPage.action" target="infoContent">个人计划</a></dd>
                    <dd><a href="">财务管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">系统管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="">角色授权</a></dd>
                    <dd><a href="">权限管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">更多</a></li>

        </ul>

    </div>

</div>








</body>

<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;

        //…
    });
</script>
</html>
