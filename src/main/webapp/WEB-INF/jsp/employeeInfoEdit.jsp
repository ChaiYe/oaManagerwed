<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${sessionScope.employee.employeeInfo.name}个人信息</title>
    <script src="../../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../layui/layui.js " type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="../../layui/css/layui.css" />
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

    <script>
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate;

            //日期
            laydate.render({
                elem: '#date'
            });
            laydate.render({
                elem: '#date1'
            });

            //创建一个编辑器
            var editIndex = layedit.build('LAY_demo_editor');

            //自定义验证规则
            form.verify({
                name : function(value){
                  if(value.length < 1){
                      return '名字不能少于1个字符';
                  }
                  if(value.length > 200){
                      return '名字不能大于200个字符'
                  }
                }
                ,age: function(value){
                    if(isNaN(value) || value > 150 || value < 1){
                        return '请输入正确年龄';
                    }
                }
                ,phone: [/^0?(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[89])[0-9]{8}$/, '请输入正确的手机号（11位）']
                ,email: [/^[a-zA-Z\d]+(\.[a-zA-Z\d]+)*@([\da-zA-Z](-[\da-zA-Z])?)+(\.{1,2}[a-zA-Z]+)+$/, '请输入正确的邮箱，如1a23@123ab.com']
            });

            //监听指定开关
            form.on('switch(switchTest)', function(data){
                layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                    offset: '6px'
                });
                layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
            });
        });
    </script>
</head>
<body>

<div class="layui-row">
    <div class="layui-col-md6 layui-col-md-offset3">

        <div class="description">
            <div class="title">您的个人信息</div>
            <div class="subtitle">管理这些基本信息（您的名称、电子邮件地址和电话号码），以便与其他人联系</div>
        </div>

        <div class="info-content">
            <div class="head-portrait" >
                <img class="img" src="../../img/14ab8759-d897-445d-8156-ea9fe1ca20cd.jpg" />
            </div>


            <div class="edit-panel">
                <div>
                    <form action="${pageContext.request.contextPath}/employee/infoUpdate.action" method="post" class="layui-form">

                        <div class="layui-form-item">
                            <label class="layui-form-label">名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" lay-verify="required|name" autocomplete="off" placeholder="请输入姓名" value="${sessionScope.employee.employeeInfo.name}" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item" pane>
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input type="radio" name="sex" value="男" title="男" <c:if test="${sessionScope.employee.employeeInfo.sex == '男'}">checked="checked"</c:if> />
                                <input type="radio" name="sex" value="女" title="女" <c:if test="${sessionScope.employee.employeeInfo.sex == '女'}">checked="checked"</c:if>>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">年龄</label>
                            <div class="layui-input-block">
                                <input type="number" name="age" lay-verify="required|age" autocomplete="off" class="layui-input"  placeholder="请输入年龄" value="${sessionScope.employee.employeeInfo.age}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">电话</label>
                            <div class="layui-input-block">
                                <input type="tel" name="phone" lay-verify="phone" autocomplete="off" class="layui-input"  placeholder="请输入手机号" value="${sessionScope.employee.employeeInfo.phone}">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-inline">
                                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input"  placeholder="请输入邮箱" value="${sessionScope.employee.employeeInfo.email}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="address" class="layui-input" placeholder="请输入地址" value="${sessionScope.employee.employeeInfo.address}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label bug">&nbsp;</label>
                            <div class="layui-input-block" id="submit-btn">
                                <button class="layui-btn layui-btn-primary" lay-submit="" style="width: 100%;">修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
