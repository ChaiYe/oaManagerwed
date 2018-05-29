<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/13/013
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>

    <script src="../layui/layui.js"></script>
    <script src="../js/vue.js"></script>
    <script src="../js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">

    <style>
        .layui-form{
            background: #fff;
            position:absolute;
            top:50%;
            left:75%;
            margin:-150px 0 0 -150px;
            width:300px;
            padding: 30px 30px;
            box-shadow: 5px 5px 8px #ccc;
        }

        .green{
            color: #05b4a5;
        }

        /*****************/

        body{
            background-image: url(../img/loginBg.png);
            background-size:100%;
            background-repeat:no-repeat;
        }

        #welcome{
            float: left;
        }
        #register{
            float:right;
        }
        #forget{
            float:right;
        }
        .layui-form-label{
            width:20px;
        }
        .error{
            padding: 5px 0px;
            color: red;
        }

    </style>
</head>
<body>
<div class="login shadow login_panel">
    <form id="app" class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/employee/login.action" method="post" <%--@submit="checkForm"--%>>

        <div class="layui-form-item" id="welcome_div">
            <h2 id="welcome">欢迎登录</h2>
            <a href="javascript:void(0);" id="register">
                <div id="register_msg" class="green">
                    立即注册
                    <i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">&#xe602;</i>
                </div>
            </a>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">
                账号
            </label>
            <div class="layui-input-block">
                <input type="text" name="account" autocomplete="off" placeholder="请输入账号" class="layui-input" v-model="name">
                <p class="error">{{nameError}}</p>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" autocomplete="off" placeholder="请输入密码" class="layui-input"  v-model="password">
                <p class="error">{{passwordError}}</p>
            </div>
        </div>

        <div class="layui-form-item">
            <span></span>
            <span><a href="#" id="forget">忘记密码</a></span>
        </div>

        <div>
            <span>
                <input type="submit" value="登录" class="layui-btn layui-btn-fluid"/>
            </span>
        </div>
    </form>
</div>

<script>
   var app = new Vue({
        el:'#app',
        data:{
            errors:[],
            name:null,
            password:null,
            nameError:null,
            passwordError:null
        },
        methods:{
            checkForm:function(e) {
                if(this.name && this.password) {
                    $.ajax({
                        url:"${pageContext.request.contextPath }/employee/login.action",
                        type:"post",
                        data:"account="+this.name+"&password="+this.password,
                        success:function(data){

                                /*TODO  并没有成功*/
                                console.log(data);
                                if(data==="fail")
                                    alert("账号或密码错误");
                                else if(data==="success")
                                    alert("登录成功");
                                else
                                    window.location.href="http://localhost:8080/oaManagerwed/management/management.action";
                        }

                    });
                }

                this.nameError="";
                this.passwordError="";
                if(!this.name) this.nameError="请输入账号";
                if(!this.password) this.passwordError="请输入密码";
                e.preventDefault();
            }
        }
    })
</script>


</body>
</html>

