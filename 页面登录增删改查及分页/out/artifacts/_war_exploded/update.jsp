<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/updateServlet?id=${showUser.id}" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${showUser.username}"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${showUser.gender=='男'}">
                <input type="radio" name="gender" value="男" checked/>男
                <input type="radio" name="gender" value="女"/>女
            </c:if>
            <c:if test="${showUser.gender=='女'}">
                <input type="radio" name="gender" value="男"/>男
                <input type="radio" name="gender" value="女" checked/>女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" value="${showUser.age}"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select id="address" name="address" class="form-control">
                <%--<c:if test="${showUser.address!=null}">--%>
                <%--<option value="${showUser.address}">${showUser.address}</option>--%>
                <%--</c:if>--%>
                    <c:if test="${showUser.address == '广东'}">
                        <option value="广东" selected>广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南">湖南</option>
                        <option value="山西">山西</option>
                        <option value="上海">上海</option>
                        <option value="西安">西安</option>
                        <option value="北京">北京</option>
                    </c:if>
                    <c:if test="${showUser.address == '广西'}">
                        <option value="广东">广东</option>
                        <option value="广西" selected>广西</option>
                        <option value="湖南">湖南</option>
                        <option value="山西">山西</option>
                        <option value="上海">上海</option>
                        <option value="西安">西安</option>
                        <option value="北京">北京</option>
                    </c:if>
                    <c:if test="${showUser.address == '湖南'}">
                        <option value="广东">广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南" selected>湖南</option>
                        <option value="山西">山西</option>
                        <option value="上海">上海</option>
                        <option value="西安">西安</option>
                        <option value="北京">北京</option>
                    </c:if>
                    <c:if test="${showUser.address == '山西'}">
                        <option value="广东">广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南" >湖南</option>
                        <option value="山西" selected>山西</option>
                        <option value="上海">上海</option>
                        <option value="西安">西安</option>
                        <option value="北京">北京</option>
                    </c:if>
                    <c:if test="${showUser.address == '上海'}">
                        <option value="广东">广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南" >湖南</option>
                        <option value="山西" >山西</option>
                        <option value="上海" selected>上海</option>
                        <option value="西安">西安</option>
                        <option value="北京">北京</option>
                    </c:if>
                    <c:if test="${showUser.address == '西安'}">
                        <option value="广东">广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南" >湖南</option>
                        <option value="山西" >山西</option>
                        <option value="上海">上海</option>
                        <option value="西安" selected>西安</option>
                        <option value="北京">北京</option>
                    </c:if>
                    <c:if test="${showUser.address == '北京'}">
                        <option value="广东">广东</option>
                        <option value="广西">广西</option>
                        <option value="湖南" >湖南</option>
                        <option value="山西" >山西</option>
                        <option value="上海">上海</option>
                        <option value="西安" >西安</option>
                        <option value="北京" selected>北京</option>
                    </c:if>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input id="qq" type="text" class="form-control" name="qq" value="${showUser.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input id="email" type="text" class="form-control" name="email" value="${showUser.email}"/>
        </div>
        <div class="form-group">
            <label for="username">用户名：</label>
            <input id="username" type="text" class="form-control" name="username" value="${showUser.username}"/>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input id="password" type="text" class="form-control" name="password" value="${showUser.password}"/>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>