<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8" />
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <%--批量删除的js--%>
        <%--
        思想: 1.首先给最大的标题栏来一个点击事件
             2. 然后在大标题栏事件的情况下,给下面的小标签设置状态
             2.1 当点击全选按钮时就会给下面小的状态框赋予同样的特性
             2.2 那么小标题需要获取到,通过遍历的形式把小标题一个一个遍历并且赋值上状态--%>
    <script>
        window.onload = function _checkbox() {
            //给全选的/全不选按钮绑定单击事件
            document.getElementById("_thcheckbox").onclick = function () {
                var idArr = document.getElementsByName("ids");
                for (var i = 0;i<idArr.length; i++) {
                    //this指绑定事件的标签,
                    idArr[i].checked = this.checked;
                }
            }
        }
    </script>
    <%-- 批量删除的功能  --%>
    <script>
        function BatchDel() {
            if (window.confirm("确定要删除选中的用户吗")) {
                //提交id数据给服务器,通知要删除的数据
                document.getElementById("_ids").submit();
            }
        }

    </script>
    <%--单独删除键的--%>
    <script>
        function DelUser(_id) {
            if (window.confirm("确认要删除编号" + _id + "用户吗?")) {
                location.href = "${pageContext.request.contextPath}/removeByIdServlet?id=" + _id;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: right; margin: 10px">
        <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
    </div>
    <div style="float: right; margin: 10px">
        <td colspan="8" align="center"><a class="btn btn-primary"
                                          href="javascript:BatchDel()">批量删除</a></td>
    </div>
    <form id="_ids" action="${pageContext.request.contextPath}/batchRemoveServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" name="ids" value="${User.id}" id="_thcheckbox" onclick="_checkbox()"/></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${Userlist}" var="User" varStatus="VS">
                <tr>
                    <td><input type="checkbox" name="ids" id="_tdchecbox" value="${User.id}"/></td>
                    <td>${VS.count}</td>
                    <td>${User.name}</td>
                    <td>${User.gender}</td>
                    <td>${User.age}</td>
                    <td>${User.address}</td>
                    <td>${User.qq}</td>
                    <td>${User.email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findByIdServlet?id=${User.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm"
                           href="javascript:void(0)" onclick="DelUser(${User.id})"
                        >删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>